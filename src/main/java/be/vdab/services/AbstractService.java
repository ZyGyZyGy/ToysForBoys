package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;

public class AbstractService {

    private EntityManager getEntityManager() {
	return JPAFilter.getEntityManager();
    }

}
