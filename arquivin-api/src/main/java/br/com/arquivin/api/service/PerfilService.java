
package br.com.arquivin.api.service;

import org.springframework.stereotype.Service;

import br.com.arquivin.model.Perfil;
import br.com.arquivin.persistence.repo.PerfilRepository;

@Service
public class PerfilService extends GenericArquivinService<Perfil, PerfilRepository> {
	
	public Perfil getPerfilUsuarioExterno() {
		return getRepository().findByName("USUÁRIO EXTERNO");
	}
	
}
