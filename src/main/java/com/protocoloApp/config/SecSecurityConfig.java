package com.protocoloApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginErrorHandler loginErrorHandler;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private LoginSuccessfulHandler loginSuccessfulHandler;
	@Autowired
	private CustomBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.apply(new FormLoginFilterConfigurer<>(http, "/", "/login", "username", "password", loginErrorHandler,
				loginSuccessfulHandler));

		http.csrf().disable().cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/source").permitAll().and()
				.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
