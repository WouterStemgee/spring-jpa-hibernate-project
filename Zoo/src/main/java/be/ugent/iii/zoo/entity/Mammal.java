package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Janne
 */
@Entity
@DiscriminatorValue("Mammal")
public class Mammal extends ZooAnimal implements Serializable {

    @Column(name = "hibernation")
    private boolean hibernation;

    public Mammal() {
    }

    public Mammal(String gender, String name, ZooDepartment department, boolean hibernation) {
        super(gender, name, department);
        this.hibernation = hibernation;
    }

    public boolean isHibernation() {
        return hibernation;
    }

    public void setHibernation(boolean hibernation) {
        this.hibernation = hibernation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.hibernation ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mammal other = (Mammal) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Mammal[ id=" + super.getId() + " name=" + super.getName() + " gender=" + super.getGender() + " hibernation = " + hibernation + " ]";
    }

}
