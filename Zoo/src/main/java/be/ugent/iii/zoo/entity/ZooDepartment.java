package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Wouter
 */
@Entity
@Table(name = "departments")
public class ZooDepartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long department_id;
      
    @ManyToOne
    @JoinColumn(name="zoo_id")
    private Zoo zoo;

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
 
    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ZooDepartment other = (ZooDepartment) obj;
        if (!Objects.equals(this.department_id, other.department_id)) {
            return false;
        }
        if (!Objects.equals(this.zoo, other.zoo)) {
            return false;
        }
        return true;
    }
    
    
    

}
