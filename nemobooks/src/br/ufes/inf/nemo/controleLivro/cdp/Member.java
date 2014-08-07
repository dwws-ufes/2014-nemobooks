package br.ufes.inf.nemo.controleLivro.cdp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@SuppressWarnings("serial")
@Entity
public class Member extends PersistentObjectSupport implements PersistentObject{
	private String nomeCompleto;
	private String matricula_Siape;
	private AcademicLevel nivel;
	private Boolean professorPPGI;
	private String curriculoLattes;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Usuario usuario;
	
	
	public Member() {
		curriculoLattes = "http://lattes.cnpq.br/";
	}
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getMatricula_Siape() {
		return matricula_Siape;
	}
	public void setMatricula_Siape(String matricula_Siape) {
		this.matricula_Siape = matricula_Siape;
	}
	@Enumerated(EnumType.STRING)
	public AcademicLevel getNivel() {
		return nivel;
	}
	public void setNivel(AcademicLevel nivel) {
		this.nivel = nivel;
	}
	public Boolean getProfessorPPGI() {
		return professorPPGI;
	}
	public void setProfessorPPGI(Boolean professorPPGI) {
		this.professorPPGI = professorPPGI;
	}
	public String getCurriculoLattes() {
		return curriculoLattes;
	}
	public void setCurriculoLattes(String curriculoLattes) {
		this.curriculoLattes = curriculoLattes;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String ehProfessor()
	{
		if(getProfessorPPGI()) return "SIM";
		return "NÃO";
	}
	
	@Override
	public String toString() {
		return this.getNomeCompleto();
	}
	
	
	
}
