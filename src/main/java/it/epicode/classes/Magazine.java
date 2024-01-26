package it.epicode.classes;

import it.epicode.baseClass.Catalogue;
import it.epicode.enumerations.Frequency;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Catalogue {
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public Magazine() {
    }

    public Magazine(String title, int yearOfPublication, int numberOfPages, Frequency frequency) {
        super(title, yearOfPublication, numberOfPages);
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "frequency=" + frequency +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
