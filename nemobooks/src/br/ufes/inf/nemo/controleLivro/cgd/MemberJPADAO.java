package br.ufes.inf.nemo.controleLivro.cgd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@SuppressWarnings("serial")
@Stateless
public class MemberJPADAO extends BaseJPADAO<Member> implements MemberDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean obterMembro(String matricula_Siape)
    {
    	Query query = entityManager.createQuery("Select m from Member m where m.matricula_Siape = ?1");
        query.setParameter(1, matricula_Siape);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
        
    }
	
	@Override
	public boolean obterMembro(String matricula_Siape, Long id)
	{
		Query query = entityManager.createQuery("Select m from Member m where m.matricula_Siape = ?1 and m.id <> ?2");
        query.setParameter(1, matricula_Siape);
        query.setParameter(2, id);
        if(query.getResultList().isEmpty())
        {
        	return false;
        }
        return true;
	}
	
	@Override
	public Member obterMembro(Usuario u) {
		Query query = entityManager.createQuery("Select m from Member m where m.usuario = ?1");
        query.setParameter(1, u);
        return (Member) query.getResultList().get(0);
	}

	@Override
	public Class<Member> getDomainClass() {
		return Member.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
