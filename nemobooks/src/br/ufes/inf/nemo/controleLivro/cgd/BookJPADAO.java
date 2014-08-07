package br.ufes.inf.nemo.controleLivro.cgd;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@SuppressWarnings("serial")
@Stateless
public class BookJPADAO extends BaseJPADAO<Book> implements BookDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean obterLivro(String isbn)
    {
    	Query query = entityManager.createQuery("Select l from Book l where l.isbn = ?1");
        query.setParameter(1, isbn);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
        
    }
	
	@Override
	public boolean obterLivro(String isbn, Long id)
	{
		Query query = entityManager.createQuery("Select l from Book l where l.isbn = ?1 and l.id <> ?2");
        query.setParameter(1, isbn);
        query.setParameter(2, id);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
	}
	
	@Override
	public List<Book> obterLivros(boolean emprestado) {
		Query query = entityManager.createQuery("Select l from Book l where l.emprestado = ?1");
        query.setParameter(1, emprestado);
        List<Book> livros = new ArrayList<Book>();
        for(int i=0; i<query.getResultList().size();i++)
        {
        	livros.add((Book) query.getResultList().get(i));
        }
        return livros;
	}

	@Override
	public Class<Book> getDomainClass() {
		return Book.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
