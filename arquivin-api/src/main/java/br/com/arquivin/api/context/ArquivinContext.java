package br.com.arquivin.api.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.arquivin.framework.utils.jwt.model.ArquivinUsernamePasswordAuthenticationToken;

import br.com.arquivin.api.service.CompanyService;
import br.com.arquivin.api.service.UserCompanyService;
import br.com.arquivin.api.service.UserService;
import br.com.arquivin.model.Company;
import br.com.arquivin.model.User;
import br.com.arquivin.model.UserCompany;

@Component
public class ArquivinContext {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private CompanyService companyService;
	
	@Autowired 
	private UserCompanyService userCompanyService;
	
	/**
	 * Recupera o usuario logado.
	 * 
	 * @return
	 */
	public User getLoggedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findByEmail((String) auth.getPrincipal());
		Company company = getLoggedCompany();
		UserCompany userCompany = userCompanyService.find(user, company);
		user.setPerfil(userCompany.getPerfil());
		return user;
	}
	
	/**
	 * Recupera a empresa logada
	 * @return
	 */
	public Company getLoggedCompany() {
		ArquivinUsernamePasswordAuthenticationToken auth = (ArquivinUsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		return companyService.loadCompanyBySubDomain(auth.getSubDomain());
	}
	
	/**
	 * Retorna o subdominio da url
	 * @return
	 */
	public String getSubDomain() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request.getHeader("referer").split("\\.")[0].replaceAll("http://", "").replaceAll("https://", "").replaceAll("www.", "");
	}
	
	/**
	 * Retorna a empresa com base no subdomio da url.
	 * @return
	 */
	public Company getCompanyFromSubDomainUrl() {
		return companyService.loadCompanyBySubDomain(getSubDomain());
	}

}
