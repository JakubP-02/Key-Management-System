package com.jsfcourse.access_key;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dto.AccessKeyWithTransactionCreateDTO;
import com.jsf.dto.AccessKeyWithTransactionDTO;
import com.jsf.dto.DictionaryValueDTO;
import com.jsf.dto.MemberDTO;
import com.jsf.entities.AccessKey;
import com.jsf.service.AccessKeyService;
import com.jsf.service.DictionaryService;
import com.jsf.service.MemberService;

@Named
@ViewScoped
public class AccessKeyListBB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3529719261077699645L;

	// Wstrzyknięcia
    @Inject
    private AccessKeyService accessKeyService;

    @Inject
    private DictionaryService dictionaryService; 
    
    @Inject
    private MemberService memberService;


    @Inject
    ExternalContext extcontext;

    @Inject
    private Flash flash;

	private static final String PAGE_ACCESS_KEY_EDIT = "accessKeyEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;
    
    private Integer selectedUnitId;
    
    private List<AccessKeyWithTransactionDTO> keysWithTransactions;
    
    private AccessKeyWithTransactionCreateDTO newKey = new AccessKeyWithTransactionCreateDTO();

    private List<DictionaryValueDTO> keyTypes; // Wartości słownika KEY_TYPE dla comboboxa 
	
    private List<MemberDTO> sourceMembers; // Lista użytkowników źródłowych (source)
    

    // Przykładowy filtr (analogicznie do 'address' w UnitListBB)
    private String statusDescription;

    
    @PostConstruct
    public void init() {
        selectedUnitId = (Integer) flash.get("selectedUnitId");
        if (selectedUnitId != null) {
            loadKeys();
        }
        
        keyTypes = dictionaryService.getValuesForDictionary("key_type"); // Pobranie danych słownika "key_type"
        sourceMembers = memberService.getAllMembers(); // Pobranie użytkowników źródłowych
    }

    // GETTER / SETTER dla statusDescription
    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    // 1. Pobieranie wszystkich kluczy – analogicznie do getFullList() w UnitListBB
    public List<AccessKey> getFullList() {
        return accessKeyService.getAllKeys();
    }

    // 2. Pobieranie listy kluczy z filtrem – analogicznie do getList() w UnitListBB
    public List<AccessKey> getList() {
        return accessKeyService.getAccessKeysByDescription(statusDescription);
    }

    // Dodawanie nowego klucza – analogicznie do newUnit()
    public String newAccessKey() {
        // Tworzenie nowego obiektu (jeśli w serwisie masz metodę createNewAccessKey())
        AccessKey accessKey = accessKeyService.createNewAccessKey();

        // Przekazanie obiektu przez Flash
        flash.put("accessKey", accessKey);

        return PAGE_ACCESS_KEY_EDIT;
    }

    // Edycja klucza – analogicznie do editUnit(Unit unit)
    public String editAccessKey(AccessKey accessKey) {
        flash.put("accessKey", accessKey);
        return PAGE_ACCESS_KEY_EDIT;
    }

    // Usuwanie klucza – analogicznie do deleteUnit(Unit unit)
    public String deleteAccessKey(AccessKey accessKey) {
        accessKeyService.deleteAccessKey(accessKey);
        return PAGE_STAY_AT_THE_SAME;
    }
    
    

    public void loadKeys() {
        if (selectedUnitId != null) {
            keysWithTransactions = accessKeyService.getAccessKeysWithLastTransaction(selectedUnitId);
        }
        
    }
    
    
    // Getter dla keysWithTransactions
    public List<AccessKeyWithTransactionDTO> getKeysWithTransactions() {
        return keysWithTransactions;
    }
    
    
    // Setter dla keysWithTransactions (jeśli potrzebny)
    public void setKeysWithTransactions(List<AccessKeyWithTransactionDTO> keysWithTransactions) {
        this.keysWithTransactions = keysWithTransactions;
    }

    
    public Integer getSelectedUnitId() {
        return selectedUnitId;
    }

    public void setSelectedUnitId(Integer selectedUnitId) {
        this.selectedUnitId = selectedUnitId;
    }

    // Metoda ustawiająca selectedUnitId
    public String showKeysForUnit(Integer unitId) {
//        this.selectedUnitId = unitId;
        
        flash.put("selectedUnitId", unitId); // Przekazanie zmiennej przez Flash
        
        // Możesz dodać logikę przygotowania danych, jeśli potrzebne

        // Zwraca nazwę widoku, do którego JSF ma przejść
        return "/view/secured/accessKey/acceessKeyWithLastTransaction.xhtml?faces-redirect=true";
    }


    // Metoda do obsługi dodawania kluczy
    public void addKeys() {
        newKey.setIdUnit(selectedUnitId); // Przypisanie kontekstu unitu
        accessKeyService.createtAccessKeysWithTransaction(newKey); // Przekazanie do serwisu EJB
        
        
        setNewKey(new AccessKeyWithTransactionCreateDTO());   
        // Opcjonalnie: odśwież listę kluczy
        loadKeys();
    }

    // Gettery i Settery
    public AccessKeyWithTransactionCreateDTO getNewKey() {
        return newKey;
    }

    public void setNewKey(AccessKeyWithTransactionCreateDTO newKey) {
        this.newKey = newKey;
    }
    
    
    public void prepareAddKeyDialog() {
        if (newKey == null) {
            newKey = new AccessKeyWithTransactionCreateDTO(); // Upewnij się, że DTO nie jest null
        }
        
        newKey.setIdUnit(selectedUnitId);
        
        newKey.setQuantity(1); // Ustaw domyślną wartość liczby kluczy

        // na razie ustawiam na sztywno, ale nalezałoby to odczytać z bazy
        newKey.setIdRecipient(2);    // Rola - uczestnik procesu, member type - Biuro
        
        // ustawiamy zalogowanego usera na razie - 3 
        //TODO nalezy to oprogramować
        newKey.setIdMember(3);
        

    }

    
	public List<DictionaryValueDTO> getKeyTypes() {
		return keyTypes;
	}

	public List<MemberDTO> getSourceMembers() {
		return sourceMembers;
	}

	public void setSourceMembers(List<MemberDTO> sourceMembers) {
		this.sourceMembers = sourceMembers;
	}

    

	
    
}
