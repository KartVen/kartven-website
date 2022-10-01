package pl.kartven.portfoliopage.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Service;
import pl.kartven.portfoliopage.exception.TokenAuthenticationException;
import pl.kartven.portfoliopage.user.CustomUserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenTool {

    private final Long EXPIRATION_TOKEN_TIME;
    private final String SECRET_KEY;

    public JwtTokenTool(
            @Value("${jwt.token.expiration-time-ms}") @DefaultValue("36000000") Long EXPIRATION_TOKEN_TIME,
            @Value("${jwt.token.secret-key}") @DefaultValue("8b977b0aec7ba4e1cc55b6177341699f4ef995e0292cc66c3277261066ca62a866cdff7d4d2d44acd540e0c2b664a5cb00466825e44f45c2e96e39ac8811956b") String SECRET_KEY) {
        this.EXPIRATION_TOKEN_TIME = EXPIRATION_TOKEN_TIME;
        this.SECRET_KEY = SECRET_KEY;
    }

    public String generateToken(CustomUserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        final String subject = userDetails.getUsername();
        final Date createdDate = new Date(System.currentTimeMillis());
        final Date expirationDate = new Date(createdDate.getTime() + EXPIRATION_TOKEN_TIME);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(getClaimsJws(token).getBody());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isValidateToken(String token) {
        try {
            getClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new TokenAuthenticationException("Invalid token signature");
        } catch (MalformedJwtException e) {
            throw new TokenAuthenticationException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new TokenAuthenticationException("Token expired");
        } catch (UnsupportedJwtException e) {
            throw new TokenAuthenticationException("Token unsupported");
        } catch (IllegalArgumentException e) {
            throw new TokenAuthenticationException("An authorization token was not provided");
        }
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
    }

}