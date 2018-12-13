/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Janne
 */
@Entity
@DiscriminatorValue("Mammal")
public class Mammal extends ZooAnimal implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "animal_id")
    private Long id;
    
    @Column (name="hibernation")
    private boolean hibernation;

    public Mammal() {
    }
    
    public Mammal(String gender, String name, ZooDepartment department, boolean hibernation){
        super(gender, name, department);
        this.hibernation = hibernation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mammal)) {
            return false;
        }
        Mammal other = (Mammal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mammal[ id=" + id + " name=" + super.getName() + " gender=" + super.getGender()+ " hibernation = " + hibernation + " ]" ;
    }

    public boolean isHibernation() {
        return hibernation;
    }

    public void setHibernation(boolean hibernation) {
        this.hibernation = hibernation;
    }
    
}
