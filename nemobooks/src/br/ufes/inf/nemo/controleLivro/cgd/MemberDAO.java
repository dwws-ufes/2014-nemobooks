package br.ufes.inf.nemo.controleLivro.cgd;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface MemberDAO extends BaseDAO<Member> {
	public boolean obterMembro(String matricula_Siape);
	public boolean obterMembro(String matricula_Siape, Long id);
	public Member obterMembro(Usuario u);
}
