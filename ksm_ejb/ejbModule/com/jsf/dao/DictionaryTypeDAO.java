package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.DictionaryType;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class DictionaryTypeDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

//	public void create(DictionaryType dictionaryType) {
//		em.persist(dictionaryType);
//	}
//
//	public DictionaryType merge(DictionaryType dictionaryType) {
//		return em.merge(dictionaryType);
//	}
//
//	public void remove(DictionaryType dictionaryType) {
//		em.remove(em.merge(dictionaryType));
//	}

	public DictionaryType find(Object id) {
		return em.find(DictionaryType.class, id);
	}

	public List<DictionaryType> getFullList() {
		List<DictionaryType> list = null;

		Query query = em.createQuery("select d from DictionaryType d");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<DictionaryType> getList(Map<String, Object> searchParams) {
		List<DictionaryType> list = null;

		// 1. Build query string with parameters
		String select = "select d ";
		String from = "from DictionaryType d ";
		String where = "";
		String orderby = "order by d.name";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "d.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
