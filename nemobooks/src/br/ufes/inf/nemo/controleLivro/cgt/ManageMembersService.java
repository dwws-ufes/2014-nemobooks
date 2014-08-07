package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageMembersService extends CrudService<Member> {
	public Member incluirMembro(Member m) throws Exception;
	public void alterarMembro(Member m) throws Exception;
	public boolean apagarMembro(Member membro);
	public List<Member> obterMembros();
	public boolean obterMembro(String matricula_Siape);
	public boolean obterMembro(String matricula_Siape, Long id);
	public Member obterMembro(Usuario u);
}
