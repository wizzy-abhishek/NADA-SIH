package com.ai.aiml10.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                /*.cors(cors -> cors.disable())*/
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                    .requestMatchers( "/error" , "/auth/**").permitAll()
                    .requestMatchers("/athlete/**").hasAnyRole("ADMIN" ,"USER")
                    .requestMatchers("/biologicalPassport/**").hasRole("ADMIN")
                    .anyRequest().permitAll())
            .sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


            //.formLogin(Customizer.withDefaults());

        return httpSecurity.build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
    }

/*
      @Bean
        UserDetailsService inMemoryUserDetailService(){

            UserDetails normalUser = User
                    .withUsername("Abhishek")
                    .password(passwordEncoder().encode("1234"))
                    .roles("USER")
                    .build();

            UserDetails adminUser = User
                    .withUsername("Gargi")
                    .password(passwordEncoder().encode("Bubu"))
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(normalUser , adminUser);
        }
*/

}