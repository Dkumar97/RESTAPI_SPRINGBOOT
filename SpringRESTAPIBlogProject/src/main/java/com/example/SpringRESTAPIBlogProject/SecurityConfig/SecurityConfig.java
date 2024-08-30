package com.example.SpringRESTAPIBlogProject.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//When you create a class annotated with @Configuration, it tells Spring that this class contains configuration settings.
// When you add @EnableWebSecurity alongside @Configuration, it indicates that this class will define security-related configurations.
//Within a class annotated with @Configuration and @EnableWebSecurity, you can override methods to customize security settings,
// such as defining how users are authenticated and authorized.
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    public  UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
return configuration.getAuthenticationManager();
    }

    @Bean
    public static  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) ->
               // authorize.anyRequest().authenticated())
authorize.requestMatchers(HttpMethod.GET,"/posts/**").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
     return http.build();
    }

  /*  @Bean
    public UserDetailsService userDetailsService() {
       UserDetails dush = User.builder().username("dush").password(passwordEncoder().encode("dush")).roles("USER").build();
       UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("USER", "ADMIN").build();

       return new InMemoryUserDetailsManager(dush, admin);
    }*/
}
