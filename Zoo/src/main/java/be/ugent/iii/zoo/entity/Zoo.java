package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "zoo")
public class Zoo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zoo_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    @Column(name = "address")
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    public Zoo() {}
    
    public Zoo(String name, Address address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
        int hash = 0;
        hash += (id != 0 ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Zoo)) {
            return false;
        }
        Zoo other = (Zoo) object;
        if ((this.id == 0 && other.id != 0) || (this.id != 0 && !this.id.equals(other.id))) {
            return false;
        } else if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        } else if ((this.address == null && other.address != null) || (this.address != null && !this.address.equals(other.address))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zoo[name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber +"]";
    }

}
