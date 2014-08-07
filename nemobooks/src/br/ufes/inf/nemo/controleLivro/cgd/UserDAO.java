package br.ufes.inf.nemo.controleLivro.cgd;


import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Usuario;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;

@Local
public interface UserDAO extends BaseDAO<Usuario>{
    public Usuario obterUsuario(String login, String senha);
}
