package br.ufes.inf.nemo.controleLivro.cgd;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;

@SuppressWarnings("serial")
@Stateless
public class UserJPADAO extends BaseJPADAO<Usuario> implements UserDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Class<Usuario> getDomainClass() {
		return Usuario.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Usuario obterUsuario(String login, String senha) {
		Query query = entityManager.createQuery("Select u from Usuario u where u.username = ?1 and u.senha = ?2");
        query.setParameter(1, login);
        query.setParameter(2, senha);
        if(!query.getResultList().isEmpty())
        {
        	return (Usuario) query.getResultList().get(0);
        }
        return null;
        
        
	}

	

	
    
}
