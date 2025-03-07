package com.jsfcourse.member;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dto.DictionaryValueDTO;
import com.jsf.dto.MemberCreateDTO;
import com.jsf.dto.MemberDTO;
import com.jsf.dto.MemberEditDTO;
import com.jsf.dto.RoleDTO;
import com.jsf.entities.Member;
import com.jsf.service.DictionaryService;
import com.jsf.service.MemberService;

@Named
@ViewScoped
public class MemberListBB implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2242731560830489525L;
	
	
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String email;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @Inject
    private MemberService memberService;

    @Inject
    private DictionaryService dictionaryService;
    
    private List<MemberDTO> members;
    private MemberCreateDTO newMember = new MemberCreateDTO();
    private MemberEditDTO selectedMember = new MemberEditDTO();

    

    private List<RoleDTO> roles; // Lista ról 
    private List<DictionaryValueDTO> memberTypes; // Lista typów uzytkownika 
    private List<DictionaryValueDTO> memberStatuses; // Lista statusów uzytkownika 

    
    
    @PostConstruct
    public void init()    {
    	roles= memberService.getAllRoles();
    	memberTypes =  dictionaryService.getValuesForDictionary("member_type");
    	memberStatuses =  dictionaryService.getValuesForDictionary("member_status");
    }
    
    public void loadSelectedMember(Integer memberId) {
        if (memberId != null) {
            this.selectedMember = memberService.findMemberById(memberId);
            
            if (this.selectedMember == null) {
                this.selectedMember = new MemberEditDTO(); // Zabezpieczenie przed nullem
            }
        }
    }


    public void loadMembers() {
    	setMembers(memberService.getAllMembers());
    }
    
    public List<MemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<MemberDTO> members) {
		this.members = members;
	}

		
	public List<RoleDTO> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	

	public List<DictionaryValueDTO> getMemberTypes() {
		return memberTypes;
	}


	public void setMemberTypes(List<DictionaryValueDTO> memberTypes) {
		this.memberTypes = memberTypes;
	}
	public List<DictionaryValueDTO> getMemberStatuses() {
		return memberStatuses;
	}


	public void setMemberStatuses(List<DictionaryValueDTO> memberStatuses) {
		this.memberStatuses = memberStatuses;
	}


	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberCreateDTO getNewMember() {
		return newMember;
	}

	public void setNewMember(MemberCreateDTO newMember) {
		this.newMember = newMember;
	}

	
	public void addMember() {

		newMember.setIdMemberStatus(5);  // ustawiam status Aktywny dla nowe użytkownika
		memberService.createMember(newMember);
		
	}
    public String deleteMember(Member member) {
        memberService.deleteMember(member);
        return PAGE_STAY_AT_THE_SAME;
    }
    
    public MemberEditDTO getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberEditDTO selectedMember) {
        this.selectedMember = selectedMember;
    }

    public void updateMember() {
        if (selectedMember != null && selectedMember.getId() != null) {
            memberService.updateMember(selectedMember);
            loadMembers(); // Odśwież listę użytkowników
        }
    }
    
}
