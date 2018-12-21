package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 *
 * @author Wouter
 */
@Entity
@DiscriminatorValue("Keeper")
public class ZooKeeper extends ZooWorker implements Serializable {

    @ManyToMany(mappedBy = "zooKeepers", fetch = FetchType.EAGER)
    private Set<ZooDepartment> departments = new HashSet<>();

    public ZooKeeper() {
    }

    public ZooKeeper(String name, Address address) {
        super(name, address);
    }

    public Set<ZooDepartment> getDepartments() {
        return departments;
    }
    
    public void setDepartments(List<ZooDepartment> departments) {
        this.departments = new HashSet<>(departments);
    }

    public boolean addDepartment(ZooDepartment department) {
        return departments.add(department);
    }

}
