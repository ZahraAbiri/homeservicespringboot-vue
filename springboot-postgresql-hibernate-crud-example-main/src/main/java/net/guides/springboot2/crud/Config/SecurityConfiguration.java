package net.guides.springboot2.crud.Config;

import net.guides.springboot2.crud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.authorizeRequests()
//	            .antMatchers("/register").permitAll()
//	            .antMatchers("/confirm").permitAll();
//	    }
	private String frontUrl = "http://localhost:8081/";

//	@Autowired
//	private CustomerService customerService;

	@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
/* http.authorizeRequests()
	            .antMatchers("/register").permitAll()
	            .antMatchers("/confirm").permitAll();*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/us/register", frontUrl).permitAll()
				.anyRequest().authenticated()
//                .anyRequest().permitAll()
				.and()
				.httpBasic();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(customerService)
//				.passwordEncoder(passwordEncoder());
//	}



}
