package it.epicode.utility;

import com.github.javafaker.Faker;
import it.epicode.baseClass.Catalogue;
import it.epicode.classes.Book;
import it.epicode.classes.Loan;
import it.epicode.classes.Magazine;
import it.epicode.classes.User;
import it.epicode.dao.CatalogueDAO;
import it.epicode.dao.LoanDAO;
import it.epicode.dao.UserDAO;
import it.epicode.enumerations.Frequency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Utility {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");

    public static Frequency getRandomEnum(Class<Frequency> enumeration) { //Mi genera un valore random dall'enumeratore frequenza
        Random random = new Random();
        Frequency[] values = enumeration.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    public static void createDatabase(int numberOfElement) {
        EntityManager entityManager = emf.createEntityManager();
        CatalogueDAO catDao = new CatalogueDAO(entityManager);
        UserDAO userDao = new UserDAO(entityManager);
        LoanDAO loanDao = new LoanDAO(entityManager);

        Faker faker = new Faker(Locale.ITALY);

        Supplier<Book> bookSupplier = () -> new Book(
                faker.book().title(),
                faker.number().numberBetween(2000, 2024),
                faker.number().numberBetween(50, 1000),
                faker.name().firstName(),
                faker.book().genre());

        Supplier<Magazine> magazineSupplier = () -> new Magazine(
                faker.book().title(),
                faker.number().numberBetween(2000, 2024),
                faker.number().numberBetween(50, 1000),
                getRandomEnum(Frequency.class)
        );

        Supplier<User> userSupplier = () -> new User(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.date().birthday(18, 60).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );

        for (int i = 0; i < numberOfElement; i++) {
            Catalogue magazine = magazineSupplier.get();
            catDao.save(magazine);

            Catalogue book = bookSupplier.get();
            catDao.save(book);

            User user = userSupplier.get();
            userDao.save(user);

            //Creo un prestito per ogni utente
            Loan loan = new Loan(user, i % 2 == 0 ? book : magazine);
            loanDao.save(loan);

        }
    }
}
