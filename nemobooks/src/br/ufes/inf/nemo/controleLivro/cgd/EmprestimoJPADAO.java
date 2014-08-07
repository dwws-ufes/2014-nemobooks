package br.ufes.inf.nemo.controleLivro.cgd;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@SuppressWarnings("serial")
@Stateless
public class EmprestimoJPADAO extends BaseJPADAO<Emprestimo> implements EmprestimoDAO {
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public Class<Emprestimo> getDomainClass() {
		return Emprestimo.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Emprestimo> obterEmprestimos(Member membro) {
		Query query = entityManager.createQuery("Select e from Emprestimo e where e.membro = ?1");
        query.setParameter(1, membro);
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        for(int i=0; i<query.getResultList().size();i++)
        {
        	emprestimos.add((Emprestimo) query.getResultList().get(i));
        }
        return emprestimos;
	}

	@Override
	public List<Emprestimo> obterEmprestimos(Member membro, boolean ativo) {
		Query query = entityManager.createQuery("Select e from Emprestimo e where e.membro = ?1 and e.ativo = ?2");
        query.setParameter(1, membro);
        query.setParameter(2, ativo);
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        for(int i=0; i<query.getResultList().size();i++)
        {
        	emprestimos.add((Emprestimo) query.getResultList().get(i));
        }
        return emprestimos;
	}
	
	
	@Override
	public void apagarEmprestimos(Book livro) {
		Query query = entityManager.createQuery("Delete Emprestimo where livro = ?1");
        query.setParameter(1, livro);
        query.executeUpdate();
	}

	@Override
	public void apagarEmprestimos(Member membro) {
		Query query = entityManager.createQuery("Delete Emprestimo where membro = ?1");
        query.setParameter(1, membro);
        query.executeUpdate();
		
	}
}
