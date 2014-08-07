package br.ufes.inf.nemo.controleLivro.cdp;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-22T14:43:36.485-0300")
@StaticMetamodel(Author.class)
public class Author_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Author, String> nome;
	public static volatile SingularAttribute<Author, String> nomeReferencia;
	public static volatile SingularAttribute<Author, String> resumo;
}
