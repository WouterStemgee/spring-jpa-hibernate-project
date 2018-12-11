package be.ugent.iii.zoo;

import be.ugent.iii.zoo.service.ZooService;
import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZooApplicationTests {

    @Autowired
    private ZooService service;

    @Test
    public void addZoo() {
        // create Zoo
        String zooName = "ZOO Antwerpen";
        Zoo zoo = new Zoo(zooName, new Address("Koningin Astridplein", 20, 2018, "Antwerpen", "België"), "");

        // add Zoo to database
        service.addZoo(zoo);

        // retreive Zoo from database
        Zoo foundZoo = service.getZoo(zooName);

        assertEquals(foundZoo.getName(), zooName);
    }

    @Test
    public void addZooWithDepartments() {
        // create Zoo
        String zooName = "Planckendael";
        Zoo zoo = new Zoo(zooName, new Address("Leuvensesteenweg", 582, 2812, "Mechelen", "België"), "015 41 49 21");

        // create Departments   
        List<String> departmentNames = Arrays.asList("Europe", "Africa", "America", "Asia", "Oceania");
        List<ZooDepartment> zooDepartments = new ArrayList<>();
        for (int i = 0; i < departmentNames.size(); i++) {
            zooDepartments.add(new ZooDepartment(departmentNames.get(i), zoo));
        }

        // add Zoo to database
        service.addZooWithDepartments(zoo, zooDepartments);

        // retreive Zoo from database
        Zoo foundZoo = service.getZoo(zooName);
        assertEquals(foundZoo.getName(), zooName);
        
        // retreive ZooDepartment's from database, check if bidirectional relation is correctly set
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartment(departmentNames.get(i));
            assertEquals(foundZooDepartment.getName(), departmentNames.get(i));
            assertEquals(foundZooDepartment.getZoo().getName(), zooName);
        }
    }
    
    // TODO: Alle Entities testen door ze aan te maken + wegschrijven naar database + opvragen -> checken indien correct
    //    @Test
    //    public void Test() {}
}
