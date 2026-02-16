package com.akash.webApp.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.akash.webApp.Model.RoleEnum;
import com.akash.webApp.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     @Autowired
        MyUserDetailsService myUserService;
     @Autowired
        JWTFilter jwtFilter;
 
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity){

      return  httpSecurity
        .csrf((cutomizer) -> cutomizer.disable())
       
        .httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .addFilterBefore(jwtFilter,  UsernamePasswordAuthenticationFilter.class )
        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/respondent/**").hasRole(RoleEnum.RESPONDENT.toString())
                        .requestMatchers("/resident/**").hasRole(RoleEnum.RESIDENT.toString())
                        .requestMatchers("/admin/**").hasRole( RoleEnum.ADMIN.toString())
                        
                        .anyRequest().authenticated()
                        
          )



         .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
       
         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(myUserService);
         authProvider.setPasswordEncoder(  new BCryptPasswordEncoder(12) );
        return authProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
    {
        return authConfig.getAuthenticationManager();
    }
    
}
