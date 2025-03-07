package com.jsf.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;

import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.jsf.dao.MemberDAO;
import com.jsf.dao.RoleDAO;
import com.jsf.dto.MemberCreateDTO;
import com.jsf.dto.MemberDTO;
import com.jsf.dto.MemberEditDTO;
import com.jsf.dto.RoleDTO;
import com.jsf.entities.DictionaryValue;
import com.jsf.entities.Member;
import com.jsf.entities.Role;
import com.jsf.entities.Transaction;


@Stateless
public class MemberService {
    @Inject
    private MemberDAO memberDAO;

    @Inject
    private RoleDAO roleDAO;

    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        
        // Konwersja encji na DTO
        return members.stream()
                .map(member -> new MemberDTO(member.getIdMember(), member.getFirstName(), member.getLastName(), 
                		member.getEmail(), member.getMemberStatus().getIdDictionaryValue(), member.getMemberStatus().getValue(),
                		member.getMemberType().getIdDictionaryValue(), member.getMemberType().getValue(), 
                		member.getRole().getIdRole(), member.getRole().getName()))
                .collect(Collectors.toList());
    }
    

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleDAO.getAllRoles();
        
        // Konwersja encji na DTO
        return roles.stream()
                .map(role -> new RoleDTO(role.getIdRole(), role.getDescription(), role.getName()))
                .collect(Collectors.toList());
    }
    
    
    
	// Pobieranie wszystkich userów
	public List<Member> getFullList() {
		return memberDAO.getAllMembers();
	}


    public Member findMemberById(int id) {
        return memberDAO.find(id);
    }

    public Set<String> getRolesByMember(int memberId) {
        return memberDAO.getRolesByMember(memberId);
    }

    public void createMember(MemberCreateDTO memberDTO) {

    	Member member = new Member();
    	member.setEmail(memberDTO.getEmail());
    	member.setFirstName(memberDTO.getFirstName());
    	member.setLastName(memberDTO.getLastName());
    	member.setPassword(memberDTO.getPassword());
    	
    	member.setMemberStatus( new DictionaryValue());
    	member.getMemberStatus().setIdDictionaryValue(memberDTO.getIdMemberStatus());

    	member.setMemberType (new DictionaryValue());
    	member.getMemberType().setIdDictionaryValue(memberDTO.getIdMemberType());
    	
    	member.setRole(new Role());
    	member.getRole().setIdRole(memberDTO.getIdRole());
    	
        memberDAO.create(member);
    }

    public void deleteMember(Member member) {
        memberDAO.delete(member);
    }

	public Member findMemberByEmail(String email) {
		// TODO Auto-generated method stub
		return memberDAO.findByEmail(email);
	}
	
	public void updateMember(MemberEditDTO memberDTO) {
	    if (memberDTO.getId() == null) {
	        return; // Bez ID nie można zapisać zmian
	    }

	    Member member = memberDAO.find(memberDTO.getId()); // Pobranie encji po ID

	    if (member != null) {
	        member.setFirstName(memberDTO.getFirstName());
	        member.setLastName(memberDTO.getLastName());
	        member.setEmail(memberDTO.getEmail());
	        member.setPassword(memberDTO.getPassword());

	        // Aktualizacja statusu użytkownika
	        if (memberDTO.getIdMemberStatus() != null) {
	            member.setMemberStatus(new DictionaryValue());
	            member.getMemberStatus().setIdDictionaryValue(memberDTO.getIdMemberStatus());
	        }

	        // Aktualizacja typu użytkownika
	        if (memberDTO.getIdMemberType() != null) {
	            member.setMemberType(new DictionaryValue());
	            member.getMemberType().setIdDictionaryValue(memberDTO.getIdMemberType());
	        }

	        // Aktualizacja roli użytkownika
	        if (memberDTO.getIdRole() != null) {
	            member.setRole(new Role());
	            member.getRole().setIdRole(memberDTO.getIdRole());
	        }

	        memberDAO.update(member);
	    }
	}
	

	public MemberEditDTO findMemberById(Integer id) {
	    Member member = memberDAO.find(id);
	    if (member == null) {
	        return null;
	    }

	    MemberEditDTO dto = new MemberEditDTO();
	    dto.setId(member.getIdMember());
	    dto.setFirstName(member.getFirstName());
	    dto.setLastName(member.getLastName());
	    dto.setEmail(member.getEmail());
	    dto.setPassword(member.getPassword());
	    dto.setIdMemberStatus(member.getMemberStatus() != null ? member.getMemberStatus().getIdDictionaryValue() : null);
	    dto.setIdMemberType(member.getMemberType() != null ? member.getMemberType().getIdDictionaryValue() : null);
	    dto.setIdRole(member.getRole() != null ? member.getRole().getIdRole() : null);

	    return dto;
	}

	
}

