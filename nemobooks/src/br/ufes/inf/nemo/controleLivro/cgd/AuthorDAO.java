package br.ufes.inf.nemo.controleLivro.cgd;

import javax.ejb.Local;

import br.ufes.inf.nemo.controleLivro.cdp.Author;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;


@Local
public interface AuthorDAO extends BaseDAO<Author> {
	public boolean obterAutor(String nome);
	public boolean verificarVinculos(Long id);
}
