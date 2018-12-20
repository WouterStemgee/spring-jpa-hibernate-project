package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public ZooKeeper(String name, Address address, List<ZooDepartment> departments) {
        super(name, address);
        for (ZooDepartment department : departments) {
            addDepartment(department);
        }
    }

    public Set<ZooDepartment> getDepartments() {
        return departments;
    }

    public boolean addDepartment(ZooDepartment department) {
        return departments.add(department);
    }

}
