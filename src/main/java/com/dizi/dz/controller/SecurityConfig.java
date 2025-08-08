package com.dizi.dz.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // old version -> EnableGlobalMethodSecurity
public class SecurityConfig {

    /* Old version of Spring Security

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // برای راحتی تست (در پروژه واقعی مراقب باش)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/ingredients").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ingredients").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/login", "/public/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET, "/api/ingredients").permitAll()
                        .requestMatchers("/", "/login", "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean // Need to implement UserDetailsService as mentioned this is authorization specification
    public UserDetailsService userDetailsService() {
        UserDetails admin = User
                .withUsername("admin")
                .password("{noop}admin123")
//                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                //.roles("ROLE_ADMIN") // shows error because automatically adds "ROLE_" prefix
                .build();

        UserDetails user = User
                .withUsername("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
