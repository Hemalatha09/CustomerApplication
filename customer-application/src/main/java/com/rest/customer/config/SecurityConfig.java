package com.rest.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Users and Roles for Authentication
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		
		auth.inMemoryAuthentication()
            .withUser("admin").password(encoder().encode("root")).roles("ADMIN")
            .and()
            .withUser("technical").password(encoder().encode("Assessment")).roles("USER", "ADMIN");
	}
	
	// Roles and Access for Authorization
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			//We don't need sessions to be created
			httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			httpSecurity.httpBasic()
		    .and()
		        .authorizeRequests()
		             .antMatchers("/rest/**").hasRole("USER")
		             .antMatchers("/**").hasRole("ADMIN")
		    .and()
		    .csrf().disable().headers().frameOptions().disable();
		}
		
		// Password encoder
		@Bean
		public PasswordEncoder encoder() {

			return new BCryptPasswordEncoder();
		}
}

