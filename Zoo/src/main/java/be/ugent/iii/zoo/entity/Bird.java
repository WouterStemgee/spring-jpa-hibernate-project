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
@DiscriminatorValue("Bird")
public class Bird extends ZooAnimal implements Serializable {

    @Column(name = "flying")
    private boolean flying;

    public Bird() {
    }

    public Bird(String gender, String name, ZooDepartment department, boolean flying) {
        super(gender, name, department);
        this.flying = flying;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.flying ? 1 : 0);
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
        final Bird other = (Bird) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Bird[ id=" + super.getId() + ", name=" + super.getName() + ", gender=" + super.getGender() + ", flying= " + flying + " ]";
    }

}
