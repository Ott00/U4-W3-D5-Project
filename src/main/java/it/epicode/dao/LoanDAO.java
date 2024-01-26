package it.epicode.dao;


import it.epicode.classes.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LoanDAO {
    private final EntityManager entityManager;

    public LoanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Loan getById(long loanId) {
        return entityManager.find(Loan.class, loanId);
    }

    public void save(Loan loan) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();

        System.out.println("Prestito '" + loan.getId() + "' aggiunto correttamente!");
    }

    public void deleteById(long loanId) {
        Loan loan = this.getById(loanId);

        if (loan != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(loan);
            transaction.commit();

            System.out.println("Prestito '" + loan.getId() + "' eliminato correttamente!");

        } else {
            System.out.println("Non è stato travato nessun prestito con questo ID: " + loanId);
        }
    }
}
