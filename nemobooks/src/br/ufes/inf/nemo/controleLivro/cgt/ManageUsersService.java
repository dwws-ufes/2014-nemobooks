package br.ufes.inf.nemo.controleLivro.cgt;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageUsersService extends CrudService<Usuario>{
	public Usuario obterUsuario(String username, String senha);
	public void incluirUsuario(Usuario u);
	public void alterarUsuario(Usuario u);
}
