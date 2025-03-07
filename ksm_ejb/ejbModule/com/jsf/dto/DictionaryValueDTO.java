package com.jsf.dto;

public class DictionaryValueDTO {
	
	private Integer id;   // ID wartości
    private String name;  // Nazwa wartości

    public DictionaryValueDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
    
	

}
