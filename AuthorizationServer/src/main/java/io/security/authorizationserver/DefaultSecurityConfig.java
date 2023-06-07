package io.security.authorizationserver;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(request -> request.anyRequest().authenticated());
        http.formLogin();
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        http.authenticationProvider(daoAuthenticationProvider);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1").password("{noop}1234").authorities("ROLE_USER").build();
        UserDetails user2 = User.withUsername("user2").password("{noop}1234").authorities("ROLE_USER").build();
        UserDetails user3 = User.withUsername("user3").password("{noop}1234").authorities("ROLE_USER").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
