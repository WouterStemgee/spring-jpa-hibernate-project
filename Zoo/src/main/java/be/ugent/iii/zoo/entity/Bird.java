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
import javax.persistence.Table;

/**
 *
 * @author Janne
 */
@Entity
@DiscriminatorValue("Bird")
public class Bird extends ZooAnimal implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "animal_id")
    private Long id;
    
    //can the specified bird fly or not
    @Column (name = "flying")
    private boolean flying;

    public Bird() {
    }
    
    public Bird(String gender, String name, ZooDepartment department, boolean flying){
        super(gender, name, department);
        this.flying = flying;
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
        if (!(object instanceof Bird)) {
            return false;
        }
        Bird other = (Bird) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bird[ id=" + id + " name=" + super.getName() + " gender=" + super.getGender()+ " flying = " + flying + " ]" ;
    }
    
    public boolean isFlying(){
        return flying;
    }
    
    public void setFlying(boolean flying){
        this.flying = flying;
    }
    
    
    
}
