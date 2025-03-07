package com.jsf.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Member;

@Stateless
public class MemberDAO {
    private final static String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    // Znajdź użytkownika na podstawie emaila
    public Member findByEmail(String email) {
        try {
            return em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Znajdź użytkownika po ID
    public Member find(int id) {
        return em.find(Member.class, id);
    }

    // Pobierz listę wszystkich użytkowników
    public List<Member> getAllMembers() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    // Pobierz role użytkownika
    public Set<String> getRolesByMember(int memberId) {
        Query query = em.createQuery(
            "SELECT r.name " + 
            "FROM Role r " +
            "JOIN RolePermission rp ON r.idRole = rp.idRole " +
            "JOIN Member m ON m.idRole = r.idRole " +
            "WHERE m.idMember = :memberId"
        );
        query.setParameter("memberId", memberId);

        List<String> rolesList = query.getResultList();
        return new HashSet<>(rolesList);
    }

    // Dodaj nowego użytkownika
    public void create(Member member) {
        em.persist(member);
    }

    // Zaktualizuj istniejącego użytkownika
    public void update(Member member) {
        em.merge(member);
    }

    // Usuń użytkownika po ID
    public void deleteById(int id) {
        Member member = find(id);
        if (member != null) {
            em.remove(member);
        }
    }

    // Usuń użytkownika (przekazując encję)
    public void delete(Member member) {
        em.remove(em.contains(member) ? member : em.merge(member));
    }
}
