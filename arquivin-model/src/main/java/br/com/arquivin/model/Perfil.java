package br.com.arquivin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4122007492078954867L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
	@SequenceGenerator(sequenceName = "seq_perfil", allocationSize = 1, name = "seq_perfil")
	@Column(name = "id_perfil")
	private Long idPerfil;
	
	@Column(length = 100, nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	
}
