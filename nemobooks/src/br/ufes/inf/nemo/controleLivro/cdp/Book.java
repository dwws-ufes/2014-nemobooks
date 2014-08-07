package br.ufes.inf.nemo.controleLivro.cdp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@SuppressWarnings("serial")
@Entity
public class Book extends PersistentObjectSupport implements PersistentObject{
	private String isbn;
	private String titulo;
	private String editora;
	private String localPublicacao;
	@ManyToMany
	@JoinTable(name="book_author")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Author> autores;
	private Integer ano;
	private String complemento;
	private Boolean emprestado;
	
	
	public Book() {
		autores = new ArrayList<>();
		emprestado = false;
	}
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getLocalPublicacao() {
		return localPublicacao;
	}
	public void setLocalPublicacao(String localPublicacao) {
		this.localPublicacao = localPublicacao;
	}
	
	public List<Author> getAutores() {
		return autores;
	}
	public void setAutores(List<Author> autores) {
		this.autores = autores;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Boolean getEmprestado() {
		return emprestado;
	}

	public void setEmprestado(Boolean emprestado) {
		this.emprestado = emprestado;
	}


	public String listarAutores()
    {
    	String lista = "";
    	for(int i=0; i<this.getAutores().size()-1;i++)
    	{
			lista += this.getAutores().get(i).getNome() + ";\n";
		}
    	lista += this.getAutores().get(this.getAutores().size()-1).getNome();
    	return lista;
    }

	@Override
	public String toString() {
		return this.getTitulo();
	}
	
	
	
}
