package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageEmprestimosService extends CrudService<Emprestimo> {
	public Emprestimo incluirEmprestimo(Emprestimo e) throws Exception;
	public void alterarEmprestimo(Emprestimo e) throws Exception;
	public boolean apagarEmprestimo(Emprestimo e);
	public List<Emprestimo> obterEmprestimos();
	public List<Emprestimo> obterEmprestimos(Member membro);
	public Emprestimo pegarLivroEmprestado(Book book, Member membro);
	public List<Emprestimo> obterEmprestimos(Member membro, boolean ativo);
	public Emprestimo devolverLivro(Emprestimo emprestimo);
}
