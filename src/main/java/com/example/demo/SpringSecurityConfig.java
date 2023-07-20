package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(
				request->{
					request.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/user").hasAnyRole("USER","ADMIN")
						.requestMatchers("/","/createUsers").permitAll()
						.anyRequest()
						.authenticated();
					
				}
				).formLogin();
		
		return http.build();
		
		
	}
	
	
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
	
		
		return new BCryptPasswordEncoder();
	}
	

}
