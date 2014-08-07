package br.ufes.inf.nemo.controleLivro.cgd;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Local
public interface BookDAO extends BaseDAO<Book> {
	public boolean obterLivro(String isbn);
	public boolean obterLivro(String isbn, Long id);
	public List<Book> obterLivros(boolean emprestado);
}
