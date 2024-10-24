package com.ai.aiml10.configuration;

import com.ai.aiml10.filter.JWTFilter;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ai.aiml10.enums.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JWTFilter jwtFilter ;

    private static final String[] publicRoutes = {
            "/error" , "/auth/login" , "/auth/refresh" ,"/actuator/**" , "/mail"
    };

    private static final String[] addNewAccounts = {
            "/auth/signUp"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorization -> authorization

                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(addNewAccounts)
                        .hasRole(ADMIN.name())

                        .requestMatchers(HttpMethod.PATCH , "/athlete/**")
                        .hasRole(ADMIN.name())

                        .requestMatchers(HttpMethod.GET , "/athlete/view/**" , "athlete/status/**")
                        .hasAnyRole(ADMIN.name() , ANALYST.name() ,INVESTIGATOR.name())

                        .requestMatchers(HttpMethod.POST , "/athlete/add/**")
                        .hasRole(ADMIN.name())

                        .requestMatchers(HttpMethod.GET , "/biologicalPassport/**" )
                        .hasAnyRole(ADMIN.name() , INVESTIGATOR.name() , ANALYST.name())

                        .requestMatchers(HttpMethod.GET , "/bloodTest/**" , "/urineTest/**")
                        .hasAnyRole(ADMIN.name() , ANALYST.name() , INVESTIGATOR.name())

                        .requestMatchers(HttpMethod.POST , "/bloodTest/**" , "/urineTest/**" )
                        .hasAnyRole(ADMIN.name() , OPERATOR.name())

                    .anyRequest().authenticated())
            .sessionManagement(sessionManagement -> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);



        return httpSecurity.build();

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
                    .password(passwordEncoder().encode("Bear"))
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(normalUser , adminUser);
        }
*/

}