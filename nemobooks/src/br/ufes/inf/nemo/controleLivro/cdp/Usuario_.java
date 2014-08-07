package br.ufes.inf.nemo.controleLivro.cdp;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-07-15T14:09:30.505-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Usuario, UserType> tipo;
	public static volatile SingularAttribute<Usuario, String> username;
	public static volatile SingularAttribute<Usuario, String> senha;
}
