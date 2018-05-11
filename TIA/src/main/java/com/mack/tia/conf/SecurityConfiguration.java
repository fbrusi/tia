package com.mack.tia.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mack.tia.dao.UserDAO;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/student/**").hasRole("ALUNO")
		.antMatchers("/teacher/**").hasRole("PROFESSOR")
		.antMatchers("/nonlogged/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/nonlogged/login")
		.defaultSuccessUrl("/nonlogged/redirectProfile")
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/nonlogged/logout"))
		.logoutSuccessUrl("/nonlogged/login")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/views/errors/403.jsp");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDAO).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
}
