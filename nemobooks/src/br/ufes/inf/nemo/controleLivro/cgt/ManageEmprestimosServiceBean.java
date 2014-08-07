package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cgd.BookDAO;
import br.ufes.inf.nemo.controleLivro.cgd.EmprestimoDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@SuppressWarnings("serial")
@Stateless
public class ManageEmprestimosServiceBean extends CrudServiceBean<Emprestimo> implements ManageEmprestimosService{
	@EJB
    private EmprestimoDAO emprestimoDAO;
	
	@EJB
    private BookDAO bookDAO;
	
  
	@Override
	public BaseDAO<Emprestimo> getDAO() {		
		return emprestimoDAO;
	}

	@Override
	protected Emprestimo createNewEntity() {
		return new Emprestimo();
	}
	
	@Override
	public Emprestimo incluirEmprestimo(Emprestimo e) throws Exception {
		emprestimoDAO.save(e);
		return e;
    }

	@Override
    public void alterarEmprestimo(Emprestimo e) throws Exception {	
    	emprestimoDAO.save(e);
    }
	
	@Override
    public boolean apagarEmprestimo(Emprestimo e) {
        emprestimoDAO.delete(e);
        return true;
    }
 
	@Override
	public List<Emprestimo> obterEmprestimos() {
		try {
			return emprestimoDAO.retrieveAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Emprestimo> obterEmprestimos(Member membro) {
		return emprestimoDAO.obterEmprestimos(membro);
	}

	@Override
	public Emprestimo pegarLivroEmprestado(Book book, Member membro) {
		book.setEmprestado(true);
		bookDAO.save(book);
		Emprestimo e = createNewEntity();
		e.setMembro(membro);
		e.setLivro(book);
		emprestimoDAO.save(e);
		return e;
	}

	@Override
	public List<Emprestimo> obterEmprestimos(Member membro, boolean ativo) {
		return emprestimoDAO.obterEmprestimos(membro, ativo);
	}

	@Override
	public Emprestimo devolverLivro(Emprestimo emprestimo) {
		emprestimo.getLivro().setEmprestado(false);
		emprestimo.setDataFim(Calendar.getInstance().getTime());
		emprestimo.setAtivo(false);
		emprestimoDAO.save(emprestimo);
		return emprestimo;
	}	
}
	
	

	

