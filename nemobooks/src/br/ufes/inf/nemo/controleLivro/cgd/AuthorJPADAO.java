package br.ufes.inf.nemo.controleLivro.cgd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@SuppressWarnings("serial")
@Stateless
public class AuthorJPADAO extends BaseJPADAO<Author> implements AuthorDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Author> getDomainClass() {
		return Author.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public boolean obterAutor(String nome)
	{
		Query query = entityManager.createQuery("Select a from Author a where a.nome = ?1");
        query.setParameter(1, nome);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
	}
	
	public boolean verificarVinculos(Long id)
	{
		Query query = entityManager.createQuery("Select b from Book b join b.autores a where a.id = ?1");
        query.setParameter(1, id);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
	}
}
