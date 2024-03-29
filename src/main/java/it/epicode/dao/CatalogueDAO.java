package it.epicode.dao;

import it.epicode.baseClass.Catalogue;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogueDAO {
    private final EntityManager entityManager;

    public CatalogueDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Catalogue getById(long ISBN) {
        return entityManager.find(Catalogue.class, ISBN);
    }

    public void save(Catalogue catalogueElement) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(catalogueElement);
        transaction.commit();

        System.out.println("'" + catalogueElement.getTitle() + "' aggiunto al catalogo correttamente!");
    }

    public void deleteById(long ISBN) {
        Catalogue catalogueElement = this.getById(ISBN);

        if (catalogueElement != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(catalogueElement);
            transaction.commit();

            System.out.println("'" + catalogueElement.getTitle() + "' eliminato correttamente dal catalogo!");

        } else {
            System.out.println("Non è stato travato nessun elemento nel catalogo con questo codice ISBN: " + ISBN);
        }
    }

    public List<Catalogue> researchByISBN(long ISBN) {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("researchByISBN", Catalogue.class);
        query.setParameter("ISBN", ISBN);
        if (query.getResultList().isEmpty()) {
            System.out.println("Non sono presenti elementi nel catalogo con questo id: " + ISBN);
            return null;
        } else return query.getResultList();
    }

    public List<Catalogue> researchByYearOfPublication(int yearOfPublication) {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("researchByYearOfPublication", Catalogue.class);
        query.setParameter("yearOfPublication", yearOfPublication);
        if (query.getResultList().isEmpty()) {
            System.out.println("Non sono presenti elementi nel catalogo pubblicati nel " + yearOfPublication);
        }
        return query.getResultList();
    }

    public List<Catalogue> researchByTitle(String partialName) {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("researchByTitle", Catalogue.class);
        query.setParameter("partialName", partialName);
        if (query.getResultList().isEmpty()) {
            System.out.println("Nessun elemento del catalogo trovato");
        }
        return query.getResultList();
    }

    public List<Catalogue> researchByAuthor(String authorName) {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("researchByAuthor", Catalogue.class);
        query.setParameter("author", authorName);
        if (query.getResultList().isEmpty()) {
            System.out.println("Nessun elemento del catalogo trovato");
        }
        return query.getResultList();
    }

    public List<Catalogue> getCurrentUserLoans(long cardUserId) {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("getCurrentUserLoans", Catalogue.class);
        query.setParameter("card_user", cardUserId);
        if (query.getResultList().isEmpty()) {
            System.out.println("Nessun elemento in prestito attualmente");
        }
        return query.getResultList();
    }

    public List<Catalogue> getAllExpiredLoans() {
        TypedQuery<Catalogue> query = entityManager.createNamedQuery("getAllExpiredLoans", Catalogue.class);
        if (query.getResultList().isEmpty()) {
            System.out.println("Nessun elemento con prestito scaduto trovato");
        }
        return query.getResultList();
    }
}
