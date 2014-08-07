package br.ufes.inf.nemo.controleLivro.cgt;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.controleLivro.cdp.Member;
import br.ufes.inf.nemo.controleLivro.cdp.UserType;
import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgd.EmprestimoDAO;
import br.ufes.inf.nemo.controleLivro.cgd.MemberDAO;
import br.ufes.inf.nemo.controleLivro.cgd.UserDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@SuppressWarnings("serial")
@Stateless
public class ManageMembersServiceBean extends CrudServiceBean<Member> implements ManageMembersService{
	
	@EJB
    private MemberDAO memberDAO;
	
	@EJB
    private EmprestimoDAO emprestimoDAO;
	
	@EJB
    private UserDAO userDAO;
  
	@Override
	public BaseDAO<Member> getDAO() {		
		return memberDAO;
	}

	@Override
	protected Member createNewEntity() {
		return new Member();
	}
	
	@Override
	public Member incluirMembro(Member m) throws Exception {
		Usuario u = new Usuario();
		u.setTipo(UserType.Membro_Nemo);
		u.setSenhaCriptografar("abc123");
		u.setUsername(m.getMatricula_Siape());
		userDAO.save(u);
		m.setUsuario(u);
		memberDAO.save(m);
        return m;
    }

	@Override
    public void alterarMembro(Member m) throws Exception {	
    	m.getUsuario().setUsername(m.getMatricula_Siape());
		memberDAO.save(m);
    }
	
	@Override
    public boolean apagarMembro(Member membro) {
		if(emprestimoDAO.obterEmprestimos(membro,true).isEmpty())
		{
			emprestimoDAO.apagarEmprestimos(membro);
        	memberDAO.delete(membro);
			return true;
		}
		else
		{
			return false;
		}
    }
 
	@Override
	public List<Member> obterMembros() {
		try {
			return memberDAO.retrieveAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean obterMembro(String matricula_Siape)
	{
		return memberDAO.obterMembro(matricula_Siape);
	}

	@Override
	public boolean obterMembro(String matricula_Siape, Long id) {
		return memberDAO.obterMembro(matricula_Siape, id);
	}

	@Override
	public Member obterMembro(Usuario u) {
		return memberDAO.obterMembro(u);
	}

	
}
