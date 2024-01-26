package it.epicode.baseClass;

import it.epicode.classes.Loan;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "catalogues")
@DiscriminatorColumn(name = "category")
@NamedQuery(name = "researchByISBN", query = "SELECT c FROM Catalogue c WHERE c.ISBN = :ISBN")
@NamedQuery(name = "researchByYearOfPublication", query = "SELECT c FROM Catalogue c WHERE c.yearOfPublication = :yearOfPublication")
@NamedQuery(name = "researchByTitle", query = "SELECT c FROM Catalogue c WHERE LOWER(c.title) LIKE LOWER(CONCAT(:partialName, '%'))")
public abstract class Catalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long ISBN;
    @Column(nullable = false)
    protected String title;
    @Column(name = "year_of_publication", nullable = false)
    protected int yearOfPublication;
    @Column(name = "number_of_pages", nullable = false)
    protected int numberOfPages;
    @OneToMany(mappedBy = "loanElement", cascade = CascadeType.REMOVE)
    protected List<Loan> loanList;

    public Catalogue() {
    }

    public Catalogue(String title, int yearOfPublication, int numberOfPages) {
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
    }

    public Long getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }
}
