package be.ugent.iii.zoo.entity;

import java.io.Serializable;
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

    private static final long serialVersionUID = 1L;

    @ManyToMany(mappedBy = "zooKeepers")
    private Set<ZooDepartment> departments;

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
