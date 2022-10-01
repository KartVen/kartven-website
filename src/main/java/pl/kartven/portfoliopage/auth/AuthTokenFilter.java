package pl.kartven.portfoliopage.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kartven.portfoliopage.user.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    private final JwtTokenTool jwtTokenTool;

    private final CustomUserDetailsService customUserDetailsService;

    public AuthTokenFilter(JwtTokenTool jwtTokenTool, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenTool = jwtTokenTool;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = extractToken(request);
            if (jwt != null && jwtTokenTool.isValidateToken(jwt)) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtTokenTool.extractUsername(jwt));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.split(" ")[1];
        }
        return null;
    }
}
