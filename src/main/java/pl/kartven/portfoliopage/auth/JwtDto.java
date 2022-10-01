package pl.kartven.portfoliopage.auth;

import java.util.List;

public record JwtDto(String token, Long id, String username, List<String> roles) {
}
