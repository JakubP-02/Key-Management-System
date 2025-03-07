package com.jsf.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.jsf.dao.AccessKeyDAO;
import com.jsf.dao.DictionaryValueDAO;
import com.jsf.dao.MemberDAO;
import com.jsf.dao.TransactionDAO;
import com.jsf.dao.UnitDAO;
import com.jsf.dto.AccessKeyWithTransactionCreateDTO;
import com.jsf.dto.AccessKeyWithTransactionDTO;
import com.jsf.entities.AccessKey;
import com.jsf.entities.DictionaryValue;
import com.jsf.entities.Transaction;
import com.jsf.entities.Unit;

@Stateless
public class AccessKeyService {

    @Inject
    private AccessKeyDAO accessKeyDAO;
    
    @Inject
    private TransactionDAO transactionDAO;

    @Inject
    private UnitDAO unitDAO;

    @Inject
    private MemberDAO memberDAO;
    
    @Inject
    private DictionaryValueDAO dictionaryValueDAO;

    // Pobieranie wszystkich kluczy
    public List<AccessKey> getAllKeys() {
        return accessKeyDAO.getFullList();
    }

    // Przykład filtrowania kluczy (np. po statusDescription)
    public List<AccessKey> getAccessKeysByDescription(String statusDesc) {
        if (statusDesc != null && !statusDesc.isEmpty()) {
            Map<String, Object> searchParams = new HashMap<>();
            searchParams.put("statusDescription", statusDesc);
            return accessKeyDAO.getList(searchParams);
        }
        return getAllKeys(); 
    }

    // Metoda do pobierania listy DictionaryValue wg ID dictionary_type
    public List<DictionaryValue> getDictionaryValuesByType(int typeId) {
        return dictionaryValueDAO.getByType(typeId);
    }

    public AccessKey createNewAccessKey() {
        return new AccessKey();
    }

    // Usuwanie klucza
    public void deleteAccessKey(AccessKey accessKey) {
        accessKeyDAO.remove(accessKey);
    }

    /**
     * Metoda 1: Delegujemy ustawianie klucza do serwisu (dla keyType, keyStatus, unit).
     * @param accessKey - obiekt klucza (np. z polami: statusDescription).
     * @param selectedTypeId - wybrany ID w dictionary_value (typ klucza).
     * @param selectedStatusId - wybrany ID w dictionary_value (status klucza).
     * @param selectedUnitId - wybrany ID w tabeli unit (lokal).
     */
    public void saveAccessKey(AccessKey accessKey,
                              Integer selectedTypeId,
                              Integer selectedStatusId,
                              Integer selectedUnitId) throws Exception {

        // 1. Pobieramy typ klucza z dictionary_value
        DictionaryValue typeDV = (selectedTypeId != null)
                ? dictionaryValueDAO.find(selectedTypeId)
                : null;

        // 2. Pobieramy status klucza z dictionary_value
        DictionaryValue statusDV = (selectedStatusId != null)
                ? dictionaryValueDAO.find(selectedStatusId)
                : null;

        // 3. Pobieramy lokal z tabeli unit
        Unit unit = (selectedUnitId != null)
                ? unitDAO.find(selectedUnitId)
                : null;

        // 4. Ustawiamy w obiekcie AccessKey
        accessKey.setKeyType(typeDV);
        accessKey.setKeyStatus(statusDV);
        accessKey.setUnit(unit);

        // 5. Decydujemy: nowy czy aktualizacja
        if (accessKey.getIdAccessKey() == null) {
            // Nowy rekord
            accessKeyDAO.create(accessKey);
        } else {
            // Aktualizacja
            accessKeyDAO.merge(accessKey);
        }
    }
    
    
    public List<AccessKeyWithTransactionDTO> getAccessKeysWithLastTransaction(Integer id_unit) {
    	
        List<Object[]> results = accessKeyDAO.findAccessKeysWithLastTransactionByUnit(id_unit);
        List<AccessKeyWithTransactionDTO> keysWithTransactions = new ArrayList<>();

        
        for (Object[] result : results) {

            // Obsługa daty z uwzględnieniem możliwego null
            Date transactionDate = null;
            if (result[7] != null) {
                if (result[7] instanceof java.sql.Timestamp) {
                    transactionDate = new Date( ((java.sql.Timestamp) result[7]).getTime());
                } else if (result[7] instanceof java.sql.Date) {
                    transactionDate = new Date(((java.sql.Date) result[7]).getTime());
                } else {
                    transactionDate = (Date) result[7];
                }
            }


            keysWithTransactions.add(new AccessKeyWithTransactionDTO(
                (Integer) result[0],
                (Integer) result[1],
                (String)  result[2],
                (Integer) result[3],
                (String)  result[4],
                (String)  result[5],
                (Integer) result[6],
            	transactionDate,
            	(String)  result[8],
            	(Integer) result[9],
            	(String)  result[10],
            	(String)  result[11],
            	(String)  result[12]
            ));
        }
        
        return keysWithTransactions;
    }

    
    
    
    public List<AccessKeyWithTransactionDTO> createtAccessKeysWithTransaction(AccessKeyWithTransactionCreateDTO accessKeyWithTransactionCreateDTO) {
    	
    	for (int i=0; i < accessKeyWithTransactionCreateDTO.getQuantity();i++) {
    		AccessKey accessKey =  new AccessKey();
    		accessKey.setDescription(accessKeyWithTransactionCreateDTO.getDescription());
    		
    		accessKey.setKeyStatus(dictionaryValueDAO.find(9));
    		
    		accessKey.setKeyType(dictionaryValueDAO.find(accessKeyWithTransactionCreateDTO.getKeyType()));
  		
    		accessKey.setUnit( unitDAO.find(accessKeyWithTransactionCreateDTO.getIdUnit()) );
   		
    		accessKey =  accessKeyDAO.create(accessKey); // mymy uzupełnione ID
    		
    		Transaction transaction = new Transaction();
    		transaction.setAccessKey(accessKey);
    		transaction.setDetails(accessKeyWithTransactionCreateDTO.getDetails());
    		transaction.setTransactionDate(new Date());
    		
    		transaction.setMember(	memberDAO.find(accessKeyWithTransactionCreateDTO.getIdMember()));
    		transaction.setMemberRecipient(memberDAO.find(accessKeyWithTransactionCreateDTO.getIdRecipient()) );
    		transaction.setMemberSource(memberDAO.find(accessKeyWithTransactionCreateDTO.getIdSource()));

    		transaction.setTransactionType(dictionaryValueDAO.find(13));
    		
    		transaction =  transactionDAO.save(transaction);
    		
    		
    	}

    	
    	
    	
 /*   	
        private Integer keyType;        // Typ klucza
        private String description;     // Opis klucza
    	
        private Integer quantity;       // Liczba kluczy - ile instancji klucza zostanie dodanych
        
    	// Część dotycząca Transaction
    	private Integer idUnit;         // Do którego unitu należą tworzone klucze 
        private Integer idMember;       // ID osoby rejestrującej operację
        private Integer idSource;       // ID osoby/lokalizacji przekazującej klucze
        private Integer idRecipient;    // ID osoby/lokalizacji będącej odbiorcą kluczy
        private String details;         // Opis operacji
*/
    	
    	
    	
        return getAccessKeysWithLastTransaction(accessKeyWithTransactionCreateDTO.getIdUnit());
    }
    
    
}
