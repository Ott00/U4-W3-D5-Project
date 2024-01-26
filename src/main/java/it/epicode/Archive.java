package it.epicode;


import it.epicode.dao.CatalogueDAO;
import it.epicode.dao.LoanDAO;
import it.epicode.dao.UserDAO;
import it.epicode.utility.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Archive {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");

    public static void main(String[] args) {
        EntityManager entityManager = emf.createEntityManager();
        CatalogueDAO catDao = new CatalogueDAO(entityManager);
        UserDAO userDao = new UserDAO(entityManager);
        LoanDAO loanDao = new LoanDAO(entityManager);

        //Funzione che accetta un intero e ci crea N magazine, N libri, N utenti e N prestiti
        Utility.createDatabase(10);

    }
}
