package it.epicode.dao;


import it.epicode.classes.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User getById(long cardId) {
        return entityManager.find(User.class, cardId);
    }

    public void save(User user) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(user);
        transaction.commit();

        System.out.println("'" + user.getName() + " " + user.getSurname() + "' aggiunto correttamente!");
    }

    public void deleteById(long cardId) {
        User user = this.getById(cardId);

        if (user != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(user);
            transaction.commit();

            System.out.println("'" + user.getName() + " " + user.getSurname() + "' eliminato correttamente dal catalogo!");

        } else {
            System.out.println("Non è stato travato nessun utente con questa tessera: " + cardId);
        }
    }
}
