package br.org.frameworkdemoiselle.duasbases.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class GenericBookmarkDAO<T, I> extends JPACrud<T, I> {

	private static final long serialVersionUID = 1L;

	//@Inject
	//@Name("bancoUm-ds")
	@PersistenceContext(unitName="bancoUm-ds") 
	private EntityManager em;
	

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
