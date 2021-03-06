package com.hcl.MusicMelody.config;

import com.hcl.MusicMelody.services.MyUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private MyUserDetailService userDetailService;

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		String loginPage = "/login";
		String logoutPage = "/logout";
		String index = "/index";
		
		http.
			authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers(loginPage).permitAll()
			.antMatchers(index).permitAll()
			.antMatchers("/registration").permitAll()
			.antMatchers(HttpMethod.POST, "/admin/home/addTask").hasAuthority("ADMIN")
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
			.anyRequest()
			.authenticated()
			.and().csrf().disable()
			.formLogin() 
			.loginPage(loginPage)
			.loginPage("/")
			.failureUrl("/login?error=true")
			.successHandler(authenticationSuccessHandler)
			.usernameParameter("user_name")
			.passwordParameter("password")
			.and().logout()
			.logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
			.logoutSuccessUrl(loginPage).and().exceptionHandling()
			.and().exceptionHandling().accessDeniedPage("/login");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web
		.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
			}
}
