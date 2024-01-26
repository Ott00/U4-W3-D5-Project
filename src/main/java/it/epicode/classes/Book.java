package it.epicode.classes;

import it.epicode.baseClass.Catalogue;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
public class Book extends Catalogue {
    private String author;
    private String type;

    public Book() {
    }

    public Book(String title, int yearOfPublication, int numberOfPages, String author, String type) {
        super(title, yearOfPublication, numberOfPages);
        this.author = author;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
