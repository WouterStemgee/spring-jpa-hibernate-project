package be.ugent.iii.zoo;

import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;
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
    private ZooDAO zDAO;

    @Autowired
    private ZooDepartmentDAO zdDAO;

    @Test
    public void addZooWithDepartment() {
        Zoo zoo = new Zoo("Planckendael", new Address("Leuvensesteenweg", 582, 2812, "Mechelen", "België"), "015 41 49 21");
        ZooDepartment department = new ZooDepartment("Waterdieren", zoo);
        zoo.addZooDepartment(department);
        
        // TODO: controller maken die alle DAO's beheert en wijzigingen doorvoert
        zDAO.save(zoo);
        zdDAO.save(department);

        Zoo foundZoo = zDAO.findByName("Planckendael");
        ZooDepartment foundDepartment = zdDAO.findByName("Waterdieren");

        assertEquals(foundZoo.getName(), "Planckendael");
        assertEquals(foundDepartment.getName(), "Waterdieren");
        assertEquals(foundDepartment.getZoo().getName(), "Planckendael");
    }
}
