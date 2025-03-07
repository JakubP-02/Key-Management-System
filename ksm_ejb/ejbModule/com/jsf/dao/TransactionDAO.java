package com.jsf.dao;

import com.jsf.entities.Transaction;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TransactionDAO {

    @PersistenceContext
    private EntityManager em;

    /**
     * Zapisuje nową transakcję w bazie danych.
     *
     * @param transaction obiekt Transaction do zapisania
     */
    public Transaction  save(Transaction transaction) {
        em.persist(transaction);
        return transaction;
    }

    /**
     * Aktualizuje istniejącą transakcję w bazie danych.
     *
     * @param transaction obiekt Transaction do aktualizacji
     */
    public void update(Transaction transaction) {
        em.merge(transaction);
    }

    /**
     * Usuwa transakcję z bazy danych.
     *
     * @param transaction obiekt Transaction do usunięcia
     */
    public void delete(Transaction transaction) {
        em.remove(em.contains(transaction) ? transaction : em.merge(transaction));
    }

    /**
     * Pobiera transakcję na podstawie ID.
     *
     * @param idTransaction ID transakcji
     * @return Obiekt Transaction lub null, jeśli nie znaleziono
     */
    public Transaction findById(Integer idTransaction) {
        return em.find(Transaction.class, idTransaction);
    }

    /**
     * Pobiera wszystkie transakcje z bazy danych.
     *
     * @return Lista transakcji
     */
    public List<Transaction> findAll() {
        return em.createNamedQuery("Transaction.findAll", Transaction.class).getResultList();
    }

    /**
     * Pobiera transakcje dla określonego klucza dostępu.
     *
     * @param accessKeyId ID klucza dostępu
     * @return Lista transakcji dla podanego klucza
     */
    public List<Transaction> findByAccessKey(Integer accessKeyId) {
        return em.createQuery(
                "SELECT t FROM Transaction t WHERE t.accessKey.idAccessKey = :accessKeyId", Transaction.class)
                .setParameter("accessKeyId", accessKeyId)
                .getResultList();
    }

    /**
     * Pobiera transakcje dla określonego członka.
     *
     * @param memberId ID członka
     * @return Lista transakcji dla podanego członka
     */
    public List<Transaction> findByMember(Integer memberId) {
        return em.createQuery(
                "SELECT t FROM Transaction t WHERE t.member1.idMember = :memberId OR t.member2.idMember = :memberId", Transaction.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
