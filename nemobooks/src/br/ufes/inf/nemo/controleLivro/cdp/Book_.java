package br.ufes.inf.nemo.controleLivro.cdp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;

@StaticMetamodel(Book.class)
public class Book_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Book, String> isbn;
	public static volatile SingularAttribute<Book, String> nome;
	public static volatile SingularAttribute<Book, String> editora;
	public static volatile SingularAttribute<Book, String> autor;
	public static volatile SingularAttribute<Book, Integer> ano;
	public static volatile SingularAttribute<Book, Integer> volume;
	public static volatile SingularAttribute<Book, Integer> edicao;
}
