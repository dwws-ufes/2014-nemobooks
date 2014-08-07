package br.ufes.inf.nemo.controleLivro.cgd;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Book;
import br.ufes.inf.nemo.controleLivro.cdp.Emprestimo;
import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface EmprestimoDAO extends BaseDAO<Emprestimo> {
	List<Emprestimo> obterEmprestimos(Member membro);
	List<Emprestimo> obterEmprestimos(Member membro, boolean ativo);
	void apagarEmprestimos(Book livro);
	void apagarEmprestimos(Member membro);
}
