package br.ufes.inf.nemo.controleLivro.cdp;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@SuppressWarnings("serial")
@Entity
public class Author extends PersistentObjectSupport implements PersistentObject{
	private String nome;
	private String nomeReferencia;
	@Type(type="org.hibernate.type.StringClobType")
	private String resumo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeReferencia() {
		return nomeReferencia;
	}

	public void setNomeReferencia(String nomeReferencia) {
		this.nomeReferencia = nomeReferencia;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
}
