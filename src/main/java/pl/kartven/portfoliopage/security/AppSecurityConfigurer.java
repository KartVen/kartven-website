package pl.kartven.portfoliopage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.kartven.portfoliopage.auth.AuthTokenFilter;
import pl.kartven.portfoliopage.auth.CustomAuthEntryPoint;
import pl.kartven.portfoliopage.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurer {

    private final CustomAuthEntryPoint unauthorizedEntryPointHandler;
    private final AuthenticationConfiguration authConfiguration;
    private final AuthTokenFilter authTokenFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public AppSecurityConfigurer(CustomAuthEntryPoint unauthorizedEntryPointHandler, AuthenticationConfiguration authConfiguration, AuthTokenFilter authTokenFilter, CustomUserDetailsService customUserDetailsService) {
        this.unauthorizedEntryPointHandler = unauthorizedEntryPointHandler;
        this.authConfiguration = authConfiguration;
        this.authTokenFilter = authTokenFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    public String[] urlGetAntPatterns() {
        return new String[]{
                "/api/category/**",
                "/api/link/**"
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPointHandler);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/signin").permitAll()
                .antMatchers(HttpMethod.GET, urlGetAntPatterns()).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
