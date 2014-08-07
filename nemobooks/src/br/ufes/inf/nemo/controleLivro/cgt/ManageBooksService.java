package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageBooksService extends CrudService<Book> {
	public Book incluirLivro(Book l, List<Long> autores) throws Exception;
	public void alterarLivro(Book l) throws Exception;
	public void apagarLivro(Book livro);
	public List<Book> obterLivros();
	public boolean obterLivro(String isbn);
	public boolean obterLivro(String isbn, Long id);
	public List<Book> obterLivros(boolean emprestado);
}
