package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Wouter
 */
@Entity
@DiscriminatorValue("K")
public class ZooKeeper extends ZooWorker implements Serializable {

    @ManyToMany(mappedBy = "zooKeepers")
    private Set<ZooDepartment> departments = new HashSet<>();

    public ZooKeeper() {
    }

    public ZooKeeper(String name, Address address, Set<ZooDepartment> departments) {
        super(name, address);
        for (ZooDepartment department : departments) {
            addDepartment(department);
        }
    }

    public Set<ZooDepartment> getDepartments() {
        return departments;
    }

    public boolean addDepartment(ZooDepartment department) {
        if (!department.getZooKeepers().contains(this)) {
            department.addZooKeeper(this);
        }
        return departments.add(department);
    }

}
