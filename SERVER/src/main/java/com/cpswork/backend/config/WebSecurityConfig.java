	/**
	 * @author: ntwari egide
	 * @description: Web security configuration
	 */

	package com.cpswork.backend.config;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.authentication.AuthenticationManager;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.config.http.SessionCreationPolicy;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;
	import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
	import org.springframework.web.servlet.config.annotation.CorsRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true, jsr250Enabled = true)
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

		@Autowired
		private UserDetailsService jwtUserDetailsService;

		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());

		}

		@Bean
		public PasswordEncoder passwordEncoder() {

			return new BCryptPasswordEncoder();
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}


		//TODO: Only make findUser by id, all details reset public for user entity
		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity
					.cors().and()
					.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers(
							"/v2/api-docs",
							"/v3/api-docs",
							"/v3/api-docs/swagger-config",
							"/configuration/ui",
							"/swagger-ui/**",
							"/webjars/**",
							"/swagger-resources/**"	,
							"/configuration/security",
							"/api/v1/auths/**",
							"/api/v1/users/**"
					).permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.exceptionHandling()
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedMethods("*");
		}
	}
