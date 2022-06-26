package org.saikumo.vams.config;

import org.saikumo.vams.constant.RoleName;
import org.saikumo.vams.security.filter.JwtAuthenticationTokenFilter;
import org.saikumo.vams.security.handler.RestAuthenticationEntryPoint;
import org.saikumo.vams.security.handler.RestfulAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
		return new RestfulAccessDeniedHandler();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				//配置 权限控制规则
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/user/**").hasAnyAuthority(RoleName.VOLUNTEER.getRoleName()
						, RoleName.ACTIVITY_ORGANIZER.getRoleName(), RoleName.MANAGER.getRoleName())
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler())
				.authenticationEntryPoint(restAuthenticationEntryPoint())
				.and()
				.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.cors()
				.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}

}