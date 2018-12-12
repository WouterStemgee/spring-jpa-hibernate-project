package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Janne
 */
@Entity
@Table(name = "zoos")
public class Zoo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zoo_id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Embedded
    @Column(name = "address")
    private Address address;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @OneToMany(mappedBy = "zoo", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<ZooDepartment> departments = new HashSet<>();
    
    @OneToOne(mappedBy = "zoo")
    private ZooOwner owner;
    
    public Zoo() {
    }
    
    public Zoo(String name, Address address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public ZooOwner getOwner() {
        return owner;
    }
    
    public void setOwner(ZooOwner owner) {
        if (owner.getZoo() != this) {
            owner.setZoo(this);
        }
        this.owner = owner;
    }
    
    public Set<ZooDepartment> getDepartments() {
        return departments;
    }
    
    public boolean addZooDepartment(ZooDepartment department) {
        if (department.getZoo() != this) {
            department.setZoo(this);
        }
        return departments.add(department);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.address);
        hash = 79 * hash + Objects.hashCode(this.phoneNumber);
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
        final Zoo other = (Zoo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Zoo{" + "name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + '}';
    }
    
}
