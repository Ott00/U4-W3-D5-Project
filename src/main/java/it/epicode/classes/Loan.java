package it.epicode.classes;

import it.epicode.baseClass.Catalogue;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "loan_element_id")
    private Catalogue loanElement;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;
    @Column(name = "return_date")
    private LocalDate returnDate;

    public Loan() {
    }

    public Loan(User user, Catalogue loanElement) {
        this.user = user;
        this.loanElement = loanElement;
        this.startDate = LocalDate.now();
        this.expectedReturnDate = startDate.plusDays(30);
    }

    public Long getId() {
        return id;
    }

    public Catalogue getLoanElement() {
        return loanElement;
    }

    public void setLoanElement(Catalogue loanElement) {
        this.loanElement = loanElement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
