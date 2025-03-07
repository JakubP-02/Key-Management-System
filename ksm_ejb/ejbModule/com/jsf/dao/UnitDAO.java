package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Unit;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UnitDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Unit unit) {
		em.persist(unit);
	}

	public Unit merge(Unit unit) {
		return em.merge(unit);
	}

	public void remove(Unit unit) {
		em.remove(em.merge(unit));
	}

	public Unit find(Object id) {
		return em.find(Unit.class, id);
	}

	public List<Unit> getFullList() {
		List<Unit> list = null;

		Query query = em.createQuery("select u from Unit u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Unit> getList(Map<String, Object> searchParams) {
		List<Unit> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from Unit u ";
		String where = "";
		String orderby ="";// "order by u.id_unit";

		// search for surname
		String address = (String) searchParams.get("address");
		if (address != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.address like :address ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (address != null) {
			query.setParameter("address", address+"%");
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
