package com.dizi.dz.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity  // old version -> EnableGlobalMethodSecurity
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:8081") // your client
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type") // allow JWT
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Permit POST and DELETE with scopes
                        .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        .requestMatchers(HttpMethod.DELETE, "/api/ingredients/**").hasAuthority("SCOPE_deleteIngredients")

                        // Public access for GET ingredients and static/open paths
                        .requestMatchers(HttpMethod.GET, "/api/ingredients").permitAll()
                        .requestMatchers("/", "/login", "/public/**").permitAll()

                        // Explicitly allow exact path and wildcard for OAuth2 code callback
                        .requestMatchers("/login/oauth2/code/dizi-cloud", "/login/oauth2/code/dizi-cloud/**").permitAll()

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

//    @Bean // Need to implement UserDetailsService as mentioned this is authorization specification
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User
//                .withUsername("admin")
//                .password("{noop}admin123")
////                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                //.roles("ROLE_ADMIN") // shows error because automatically adds "ROLE_" prefix
//                .build();
//
//        UserDetails user = User
//                .withUsername("user")
//                .password("{noop}user123")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
