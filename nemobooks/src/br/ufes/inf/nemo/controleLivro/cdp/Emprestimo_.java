package br.ufes.inf.nemo.controleLivro.cdp;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-08-05T13:23:43.897-0300")
@StaticMetamodel(Emprestimo.class)
public class Emprestimo_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Emprestimo, Member> membro;
	public static volatile SingularAttribute<Emprestimo, Book> livro;
	public static volatile SingularAttribute<Emprestimo, Date> dataInicio;
	public static volatile SingularAttribute<Emprestimo, Date> dataFim;
	public static volatile SingularAttribute<Emprestimo, Boolean> ativo;
}
