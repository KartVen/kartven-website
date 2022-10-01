package pl.kartven.portfoliopage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenAuthenticationException extends RuntimeException {
    public TokenAuthenticationException(String message) {
        super(message);
    }
}
