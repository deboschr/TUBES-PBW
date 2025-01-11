package com.runtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @SuppressWarnings("removal")
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/user/signup", "/user/signin").permitAll() // Halaman publik
                  .anyRequest().authenticated() // Halaman lain memerlukan login
            )
            .formLogin(form -> form
                  .loginPage("/user/signin") // Halaman login custom
                  .defaultSuccessUrl("/user/profile", true) // Redirect setelah login berhasil
                  .permitAll())
            .logout(logout -> logout
                  .logoutUrl("/user/signout") // URL logout
                  .logoutSuccessUrl("/user/signin") // Redirect setelah logout
                  .permitAll());

      return http.build();
   }
}
