package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.DictionaryValue;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class DictionaryValueDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(DictionaryValue dictionaryValue) {
		em.persist(dictionaryValue);
	}

	public DictionaryValue merge(DictionaryValue dictionaryValue) {
		return em.merge(dictionaryValue);
	}

	public void remove(DictionaryValue dictionaryValue) {
		em.remove(em.merge(dictionaryValue));
	}

	public DictionaryValue find(Object id) {
		return em.find(DictionaryValue.class, id);
	}

	
	public List<DictionaryValue> getFullList() {
		List<DictionaryValue> list = null;

		Query query = em.createQuery("select d from DictionaryValue d");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<DictionaryValue> getByType(int dictionaryTypeId) {
	    List<DictionaryValue> list = null;

	    try {
	        Query query = em.createQuery(
	            "SELECT d FROM DictionaryValue d WHERE d.dictionaryType.idDictionaryType = :dictionaryTypeId"
	        );
	        query.setParameter("dictionaryTypeId", dictionaryTypeId);

	        list = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	
	
    public List<DictionaryValue> getValuesForDictionary(String dictionaryName) {
        return em.createQuery(
                "SELECT dv FROM DictionaryValue dv " +
                "JOIN dv.dictionaryType dt " +
                "WHERE dt.name = :dictionaryName", DictionaryValue.class)
            .setParameter("dictionaryName", dictionaryName)
            .getResultList();
    }
	
    
	public List<DictionaryValue> getList(Map<String, Object> searchParams) {
		List<DictionaryValue> list = null;

		// 1. Build query string with parameters
		String select = "select d ";
		String from = "from DictionaryValue d ";
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
