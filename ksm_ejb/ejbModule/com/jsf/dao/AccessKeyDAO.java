package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.AccessKey;

@Stateless
public class AccessKeyDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    // Tworzenie nowego obiektu AccessKey
    public AccessKey create(AccessKey accessKey) {
        em.persist(accessKey);
        return accessKey;
    }

    // Aktualizacja istniejącego obiektu AccessKey
    public AccessKey merge(AccessKey accessKey) {
        return em.merge(accessKey);
    }

    // Usuwanie obiektu AccessKey
    public void remove(AccessKey accessKey) {
        em.remove(em.merge(accessKey));
    }

    // Znajdowanie obiektu AccessKey po jego kluczu głównym
    public AccessKey find(Object id) {
        return em.find(AccessKey.class, id);
    }

    // Pobranie wszystkich AccessKey
    public List<AccessKey> getFullList() {
        List<AccessKey> list = null;

        Query query = em.createQuery("SELECT a FROM AccessKey a");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Przykładowy filtr, podobnie jak w UnitDAO możesz przyjmować mapę "searchParams"
     * i budować zapytanie dynamicznie. Dla AccessKey może to być np. filtr
     * po statusie, typie, ID lokalu itp.
     */
    public List<AccessKey> getList(Map<String, Object> searchParams) {
        List<AccessKey> list = null;

        // 1. Budowanie zapytania bazowego
        String select = "SELECT a ";
        String from = "FROM AccessKey a ";
        String where = "";
        String orderby = "";

        // Przykład filtra np. po polu "statusDescription"
        String statusDesc = (String) searchParams.get("statusDescription");
        if (statusDesc != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "a.statusDescription LIKE :statusDesc ";
        }
        
        // ... inne parametry, np. idKeyType, idKeyStatus, idUnit ...
        // (o ile chcesz je tu obsłużyć w postaci int lub String)

        // 2. Tworzenie obiektu zapytania
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Ustawianie parametrów
        if (statusDesc != null) {
            query.setParameter("statusDesc", statusDesc + "%");
        }

        // 4. Wykonanie zapytania
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public List<Object[]> findAccessKeysWithLastTransactionByUnit(int id_unit) {
		String query = 
				"select ak.id_access_key, ak.id_key_type, kt.value, ak.id_key_status, ks.value, ak.description, t.id_transaction,\n" + 
						"t.transaction_date, t.details, t.id_recipient, m.first_name, m.last_name, m.email\n" + 
				"from access_key ak\n" + 
				"left join dictionary_value kt on id_key_type = kt.id_dictionary_value\n" + 
				"left join dictionary_value ks on id_key_status = ks.id_dictionary_value\n" + 
				"left join (\n" + 
				"select id_access_key, MAX(transaction_date) latest_transaction_date\n" + 
				"from transaction\n" + 
				"group by id_access_key)\n" + 
				"latest_transactions on ak.id_access_key = latest_transactions.id_access_key\n" + 
				"left join transaction t on ak.id_access_key = t.id_access_key\n" + 
				"and t.transaction_date = latest_transactions.latest_transaction_date\n" + 
				"left join member m on t.id_recipient = m.id_member\n" + 
				"where ak.id_unit = :id_unit\n" + 
				"order by ak.id_key_type;\n";				
		
        return em.createNativeQuery(query)
                 .setParameter("id_unit", id_unit)
                 .getResultList();
    }
    
}
