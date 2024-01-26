package it.epicode;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Archive {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");

    public static void main(String[] args) {
        System.out.println("Genero il db");
    }
}
