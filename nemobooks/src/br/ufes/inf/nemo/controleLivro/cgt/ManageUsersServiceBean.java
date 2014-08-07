package br.ufes.inf.nemo.controleLivro.cgt;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.controleLivro.cgd.UserDAO;
import br.ufes.inf.nemo.util.ejb3.application.CrudServiceBean;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@SuppressWarnings("serial")
@Stateless
public class ManageUsersServiceBean extends CrudServiceBean<Usuario> implements ManageUsersService{

	@EJB
    private UserDAO userDAO;
	
	@Override
	public BaseDAO<Usuario> getDAO() {
		return userDAO;
	}

	@Override
	protected Usuario createNewEntity() {
		return new Usuario();
	}
	
	@Override
	public Usuario obterUsuario(String username, String senha) {
		return userDAO.obterUsuario(username, senha);
	}
	
	@Override
	public void incluirUsuario(Usuario u)
	{
		userDAO.save(u);
	}
	
	@Override
	public void alterarUsuario(Usuario u)
	{
		userDAO.save(u);
	}

	
}
