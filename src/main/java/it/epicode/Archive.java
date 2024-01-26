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

        System.out.println("**TEST QUERY");

        System.out.println();
        System.out.println("**Ricerca tramite ISBN");
        catDao.researchByISBN(1).forEach(System.out::println);

        System.out.println();
        System.out.println("**Ricerca tramite anno di pubblicazione");
        catDao.researchByYearOfPublication(2020).forEach(System.out::println);

        System.out.println();
        System.out.println("**Ricerca per titolo o parte di esso");
        catDao.researchByTitle("TITOLO_O_PARTE_DI_ESSO").forEach(System.out::println);

        System.out.println();
        System.out.println("**Ricerca per autore");
        catDao.researchByAuthor("NOME_AUTORE").forEach(System.out::println);

        System.out.println();
        System.out.println("**Ricerca degli elementi attualmente in prestito dato un numero di tessera utente");
        catDao.getCurrentUserLoans(1).forEach(System.out::println);

        System.out.println();
        System.out.println("**Ricerca di tutti i prestiti scaduti e non ancora restituiti");
        //Testato cambiando nel costruttore di Loan la expectedReturnDate con da "startDate.plusDays(30);" a "startDate.minusDays(31);"
        catDao.getAllExpiredLoans().forEach(System.out::println);
    }
}
