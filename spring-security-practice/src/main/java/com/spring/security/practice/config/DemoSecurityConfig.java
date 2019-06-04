package com.spring.security.practice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.security.practice.service.CustomUserDetails;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired(required = true)
	private CustomUserDetails customUserDetails; 
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource);
		auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder());
		
		/** below authentication is for default tables of Spring security*/
//		auth.jdbcAuthentication().dataSource(dataSource);

		/** below lines are for hardcoded users */
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication().withUser(users.username("kamal").password("arora").roles("EMPLOYEE"))
//				.withUser(users.username("vicky").password("arora123").roles("EMPLOYEE", "MANAGER"))
//				.withUser(users.username("testuser").password("arora1234").roles("ADMIN", "EMPLOYEE"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole("USER").antMatchers("/manager").hasRole("ACTUATOR")
				.antMatchers("/admin").hasRole("ADMIN").and().formLogin().loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticate").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/custom-access-denied");
	}

}
