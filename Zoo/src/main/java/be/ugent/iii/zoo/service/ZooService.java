package be.ugent.iii.zoo.service;

import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooAnimal;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.repository.ZooAnimalDAO;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wouter
 */
@Service
public class ZooService {

    @Autowired
    private ZooDAO zooDAO;

    @Autowired
    private ZooDepartmentDAO zooDepartmentDAO;
    
    @Autowired
    private ZooAnimalDAO zooAnimalDAO;

    @Transactional
    public void addZoo(Zoo zoo) {
        zooDAO.save(zoo);
    }

    @Transactional
    public void addZooWithDepartments(Zoo zoo, List<ZooDepartment> departments) {
        zooDAO.save(zoo);
        zooDepartmentDAO.saveAll(departments);
    }
    
    @Transactional
    public void addAnimals(Set<ZooAnimal> animals){
        zooAnimalDAO.saveAll(animals);
    }

    public Zoo getZoo(String zooName) {
        return zooDAO.findByName(zooName);
    }

    public ZooDepartment getDepartment(String departmentName) {
        return zooDepartmentDAO.findByName(departmentName);
    }

    public List<Zoo> getAllZoos() {
        return (List<Zoo>) zooDAO.findAll();
    }

    public List<ZooDepartment> getAllZooDepartments() {
        return (List<ZooDepartment>) zooDepartmentDAO.findAll();
    }

}
