package br.ufes.inf.nemo.controleLivro.cgt;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cgd.AuthorDAO;
import br.ufes.inf.nemo.controleLivro.cgd.BookDAO;
import br.ufes.inf.nemo.controleLivro.cgd.EmprestimoDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@SuppressWarnings("serial")
@Stateless
public class ManageBooksServiceBean extends CrudServiceBean<Book> implements ManageBooksService{
	
	@EJB
    private BookDAO livroDAO;
	
	@EJB
    private AuthorDAO autorDAO;
	
	@EJB
    private EmprestimoDAO emprestimoDAO;
  
	@Override
	public BaseDAO<Book> getDAO() {		
		return livroDAO;
	}

	@Override
	protected Book createNewEntity() {
		return new Book();
	}
	
	@Override
	public Book incluirLivro(Book l, List<Long> autores) throws Exception {
		livroDAO.save(l);
		for (Long a : autores) {
			l.getAutores().add(autorDAO.retrieveById(a));
		}

        return l;
    }

	@Override
    public void alterarLivro(Book l) throws Exception {	
    	livroDAO.save(l);
    }
	
 
	@Override
    public void apagarLivro(Book livro) {
		emprestimoDAO.apagarEmprestimos(livro);
		livroDAO.delete(livro);
    }
 
	@Override
	public List<Book> obterLivros() {
		try {
			return livroDAO.retrieveAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean obterLivro(String isbn) {
		return livroDAO.obterLivro(isbn);
	}
	
	@Override
	public boolean obterLivro(String isbn, Long id)
	{
		return livroDAO.obterLivro(isbn, id);
	}

	@Override
	public List<Book> obterLivros(boolean emprestado) {
		return livroDAO.obterLivros(emprestado);
	}
	
	
	

	


	

	

	
    
}