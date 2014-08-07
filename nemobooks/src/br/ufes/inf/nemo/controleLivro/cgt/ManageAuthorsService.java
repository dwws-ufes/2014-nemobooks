package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageAuthorsService extends CrudService<Author> {
	public Author incluirAutor(Author a) throws Exception;
	public void alterarAutor(Author a) throws Exception;
	public boolean apagarAutor(Author autor);
	public List<Author> obterAutores();
	public Author buscaSparqlAutor(String nome);
	public boolean obterAutor(String nome);
	public boolean verificarVinculos(Long id);
}
