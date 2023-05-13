package br.com.arquivin.api.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.arquivin.framework.utils.jwt.filter.CORSFilter;
import com.arquivin.framework.utils.jwt.filter.JWTAuthenticationFilter;
import com.arquivin.framework.utils.jwt.filter.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class CustomSecurityCfg extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/test").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/login/lostPassword/{email:.+}").permitAll()
		.antMatchers("/login/changePassword/{token}").permitAll()
		.antMatchers("/login/newUserByToken/{token}").permitAll()
		.antMatchers("/company/config/{subDomain}").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterBefore(new CORSFilter(), UsernamePasswordAuthenticationFilter.class)
		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
		
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

}
