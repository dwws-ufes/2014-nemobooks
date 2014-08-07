package br.ufes.inf.nemo.controleLivro.cdp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Member.class)
public class Member_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Member, String> nomeCompleto;
	public static volatile SingularAttribute<Member, String> matricula_Siape;
	public static volatile SingularAttribute<Member, AcademicLevel> nivel;
	public static volatile SingularAttribute<Member, Boolean> professorPPGI;
	public static volatile SingularAttribute<Member, String> curriculoLattes;	
}
