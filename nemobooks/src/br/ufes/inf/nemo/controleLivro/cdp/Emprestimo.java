package br.ufes.inf.nemo.controleLivro.cdp;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@SuppressWarnings("serial")
@Entity
public class Emprestimo extends PersistentObjectSupport implements PersistentObject{
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Member membro;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Book livro;
	@Temporal(TemporalType.DATE) 
	private Date dataInicio;
	@Temporal(TemporalType.DATE) 
	private Date dataFim;
	private Boolean ativo;
	
	public Emprestimo() {
		this.setDataInicio(Calendar.getInstance().getTime());
		this.setDataFim(null);
		this.setAtivo(true);	
	}
	
	public Book getLivro() {
		return livro;
	}
	public void setLivro(Book livro) {
		this.livro = livro;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Member getMembro() {
		return membro;
	}
	public void setMembro(Member membro) {
		this.membro = membro;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String dataInicioFormatada()
	{
		@SuppressWarnings("deprecation")
		String data = dataInicio.getDate()+"/" +dataInicio.getMonth() +"/"+(1900+dataFim.getYear());
		return data;
	}
	
	public String dataFimFormatada()
	{
		@SuppressWarnings("deprecation")
		String data = dataFim.getDate()+"/" +dataFim.getMonth() +"/"+(1900+dataFim.getYear());
		return data;
	}
}
