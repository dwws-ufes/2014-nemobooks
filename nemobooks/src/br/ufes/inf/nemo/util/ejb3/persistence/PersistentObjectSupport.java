package br.ufes.inf.nemo.util.ejb3.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.ufes.inf.nemo.util.ejb3.domain.DomainObjectSupport;

/**
 * Standard implementation for persistent objects, implementing EJB 3's standard annotations for persistence. This
 * implementation extends on the DomainObjectSupport implementation to provide equals() and hashCode() based on UUIDs.
 * 
 * <i>This class is part of the Engenho de Software CRUD framework for EJB3 (Java EE 6).</i>
 * 
 * @see br.ufes.inf.nemo.util.ejb3.domain.DomainObjectSupport
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.1
 */
@MappedSuperclass
public abstract class PersistentObjectSupport extends DomainObjectSupport implements PersistentObject {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** Identifier attribute (primary-key). */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	/** @see br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject#getId() */
	@Override
	public Long getId() {
		return id;
	}

	/** Setter for id. */
	public void setId(Long id) {
		this.id = id;
	}



	/** @see br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject#isPersistent() */
	@Override
	public boolean isPersistent() {
		return (id != null);
	}

	/** @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "Instance of " + getClass().getName() + " (id: " + id + "; uuid: " + uuid + ")";
	}
}
