package com.jsfcourse.member;

import java.io.Serializable;
import java.security.Principal;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;


@Named( "userBean")
@ViewScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 528467056729047256L;

    public String getLoggedUserName() {
        FacesContext context = FacesContext.getCurrentInstance();
        Principal principal = context.getExternalContext().getUserPrincipal();
        
        return principal != null ? principal.getName() : ""; // Jeśli brak zalogowanego użytkownika, zwraca pusty napis
    }	
	

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/view/secured/unit/unitList.xhtml?faces-redirect=true"; // Przekierowanie + wymuszenie logowania
    }
    
}
