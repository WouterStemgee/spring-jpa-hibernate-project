package be.ugent.iii.zoo.service;

import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooAnimal;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.entity.ZooOwner;
import be.ugent.iii.zoo.repository.ZooAnimalDAO;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;
import be.ugent.iii.zoo.repository.ZooWorkerDAO;
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
    private ZooWorkerDAO zooWorkerDAO;

    @Autowired
    private ZooDepartmentDAO zooDepartmentDAO;

    @Autowired
    private ZooAnimalDAO zooAnimalDAO;

    @Transactional
    public synchronized boolean addZoo(Zoo zoo) {
        List<Zoo> list = zooDAO.findByName(zoo.getName());
        if (list.size() > 0) {
            return false;
        } else {
            zooDAO.save(zoo);
            return true;
        }
    }
 
    @Transactional
    public synchronized void addZooWithOwner(Zoo zoo, ZooOwner owner) {
        if (addZoo(zoo)) {
            zooWorkerDAO.save(owner);
        }
    }
    
    @Transactional
    public synchronized void addZooWithDepartments(Zoo zoo, List<ZooDepartment> departments) {
        if (addZoo(zoo)) {
            zooDepartmentDAO.saveAll(departments);
        }
    }

    @Transactional
    public synchronized void addDepartmentWithAnimals(ZooDepartment department, Set<ZooAnimal> animals) {
        zooDepartmentDAO.save(department);
        zooAnimalDAO.saveAll(animals);
    }

    public Zoo getZooById(long ZooId) {
        return zooDAO.findById(ZooId).get();
    }

    public List<Zoo> getAllZoos() {
        return (List<Zoo>) zooDAO.findAll();
    }

    public ZooDepartment getDepartmentById(long departmentId) {
        return zooDepartmentDAO.findById(departmentId).get();
    }

    public List<ZooDepartment> getAllDepartments() {
        return (List<ZooDepartment>) zooDepartmentDAO.findAll();
    }
    
    public ZooAnimal getAnimalById(long animalId) {
        return zooAnimalDAO.findById(animalId).get();
    }

    public List<ZooAnimal> getAllAnimals() {
        return (List<ZooAnimal>) zooAnimalDAO.findAll();
    }

    @Transactional
    public void updateZoo(Zoo zoo) {
        zooDAO.save(zoo);
    }
    
    @Transactional
    public void updateDepartment(ZooDepartment department) {
        zooDepartmentDAO.save(department);
    }
    
    @Transactional
    public void updateAnimal(ZooAnimal animal) {
        zooAnimalDAO.save(animal);
    }

    @Transactional
    public void deleteZoo(long zooId) {
        zooDAO.delete(getZooById(zooId));
    }
    
    @Transactional
    public void deleteDepartment(long departmentId) {
        zooDAO.delete(getZooById(departmentId));
    }
    
    @Transactional
    public void deleteAnimal(long animalId) {
        zooDAO.delete(getZooById(animalId));
    }

}
