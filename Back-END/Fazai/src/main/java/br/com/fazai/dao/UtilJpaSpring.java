package br.com.fazai.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;

public class UtilJpaSpring {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	@Autowired
	private EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	
}
