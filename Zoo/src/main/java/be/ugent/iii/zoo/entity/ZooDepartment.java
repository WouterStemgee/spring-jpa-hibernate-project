package be.ugent.iii.zoo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Wouter
 */
@Entity
@Table(name = "departments")
public class ZooDepartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Set<ZooAnimal> animals = new HashSet<>();

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "zookeepers_per_departments", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "worker_id"))
    private List<ZooKeeper> zooKeepers = new ArrayList<>();

    public ZooDepartment() {
    }

    public ZooDepartment(String name, Zoo zoo) {
        this.name = name;
        this.zoo = zoo;
    }

    public List<ZooKeeper> getZooKeepers() {
        return zooKeepers;
    }

    public boolean addZooKeeper(ZooKeeper zooKeeper) {
        if (!zooKeeper.getDepartments().contains(this)) {
            zooKeeper.addDepartment(this);
        }
        return zooKeepers.add(zooKeeper);
    }

    public Set<ZooAnimal> getAnimals() {
        return animals;
    }

    public boolean addZooAnimal(ZooAnimal animal) {
        if (animal.getDepartment() != this) {
            animal.setDepartment(this);
        }
        return animals.add(animal);
    }

    public Set<ZooAnimal> getBirds() {
        Set<ZooAnimal> birds = getAnimals();
        for (ZooAnimal animal : birds) {
            if (!(animal instanceof Bird)) {
                birds.remove(animal);
            }
        }
        return birds;
    }

    public Set<ZooAnimal> getMammals() {
        Set<ZooAnimal> mammals = getAnimals();
        for (ZooAnimal animal : mammals) {
            if (!(animal instanceof Mammal)) {
                mammals.remove(animal);
            }
        }
        return mammals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZooDepartment{id=" + id + ", name=" + name + '}';
    }

}
