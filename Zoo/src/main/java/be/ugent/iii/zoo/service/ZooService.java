package be.ugent.iii.zoo.service;

import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;
import java.util.Arrays;
import java.util.List;
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

    @Transactional
    public List<Zoo> getAllZoos() {
        return (List<Zoo>) zooDAO.findAll();
    }

    @Transactional
    public void addZoo(Zoo zoo) {
        zooDAO.save(zoo);
    }

    @Transactional
    public Zoo getZoo(String zooName) {
        return zooDAO.findByName(zooName);
    }

    @Transactional
    public ZooDepartment getDepartment(String departmentName) {
        return zooDepartmentDAO.findByName(departmentName);
    }

    @Transactional
    public void addZooWithDepartments(Zoo zoo, List<ZooDepartment> departments) {
        for (int i = 0; i < departments.size(); i++) {
            zoo.addZooDepartment(departments.get(i));
        }
        zooDAO.save(zoo);
        zooDepartmentDAO.saveAll(departments);
    }

}
