package br.com.arquivin.api.cfg;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arquivin.framework.utils.jwt.model.AccountCredentials;

import br.com.arquivin.api.service.CompanyService;
import br.com.arquivin.api.service.UserCompanyService;
import br.com.arquivin.api.service.UserService;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.Perfil;
import br.com.arquivin.model.User;
import br.com.arquivin.model.UserCompany;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserCompanyService userCompanyService;
	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		AccountCredentials credentials = (AccountCredentials) auth.getPrincipal();
		String login = credentials.getLogin();
		String password = credentials.getPassword();
		
		String subDomain = getSubDomain();
		
		User user = userService.findByEmail(login);
		Company company = companyService.loadCompanyBySubDomain(subDomain);
		UserCompany userCompany = userCompanyService.find(user, company);
		
		if (user == null || (user.getPwd() != null && !user.getPwd().equals(password))) {
			throw new BadCredentialsException("E-mail ou senha inválidos!");
		}
		
		if(userCompany == null) {
			throw new BadCredentialsException("Você não possui acesso a empresa");
		}
		
		if(!subDomain.equals(credentials.getSubDomain())) {
			throw new BadCredentialsException("Favor efetuar o login novamente");
		}
		
		userService.updateLastLoginAndStatus(user.getIdUser());		
		return new UsernamePasswordAuthenticationToken(credentials, password, getAuthorities(Arrays.asList(userCompany.getPerfil())));
	}
	
	/**
	 * Recupera o subdominio
	 * @return
	 */
	private String getSubDomain() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request.getHeader("referer").split("\\.")[0].replaceAll("http://", "").replaceAll("https://", "").replaceAll("www.", "");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		User user = userService.findByEmail(username);
		if (user == null || (user.getPwd() != null && !user.getPwd().equals(""))) {
			throw new BadCredentialsException("E-mail ou senha inválidos!");
		}
		return null;
	}
	
	
	private static Collection<GrantedAuthority> getAuthorities(List<Perfil> roles) {
		Collection<GrantedAuthority> authorities = null;
		if(roles != null && !roles.isEmpty()){
			authorities = new HashSet<>(roles.size());
			for(Perfil role : roles) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
			}
		}else{
			authorities = new HashSet<>(0);
		}
		return authorities;
	}

}
