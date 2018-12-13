package be.ugent.iii.zoo;

import be.ugent.iii.zoo.service.ZooService;
import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Bird;
import be.ugent.iii.zoo.entity.Mammal;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooAnimal;
import be.ugent.iii.zoo.entity.ZooDepartment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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
        Zoo foundZoo = service.getZooById(zoo.getId());
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
            ZooDepartment department = new ZooDepartment(departmentNames.get(i), zoo);
            zoo.addZooDepartment(department);
            zooDepartments.add(department);
        }

        // check bidirectional relation
        for (ZooDepartment department : zoo.getDepartments()) {
            assertSame(zoo, department.getZoo());
        }

        // add Zoo to database
        service.addZooWithDepartments(zoo, zooDepartments);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());;
        assertEquals(zooName, foundZoo.getName());

        // retreive ZooDepartment's from database, check relation in database
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartmentById(zooDepartments.get(i).getId());
            assertEquals(departmentNames.get(i), foundZooDepartment.getName());
            assertEquals(zooName, foundZooDepartment.getZoo().getName());
        }
    }

    @Test
    public void addAnimals() {
        // create zoo
        String zooName = "Pairi Daiza";
        Zoo zoo = new Zoo(zooName, new Address("Domaine de Cambron", 1, 7940, "Brugelette", "België"), "068 25 08 50");

        // use of other variables
        List<String> types = Arrays.asList("Bird", "Mammal", "Other", "Mammal", "Other");
        List<String> genders = Arrays.asList("female", "male", "male", "female", "female");
        List<String> names = Arrays.asList("Linda", "Martin", "Basil", "Eva", "Janice");
        List<Boolean> flying = Arrays.asList(true, false, false, false, false);
        List<Boolean> hibernate = Arrays.asList(false, true, false, false, false);

        // create Departments
        List<String> departmentNames = Arrays.asList("Cambodja", "Kenia", "Belgie", "Italie", "Amerika");

        List<ZooDepartment> zooDepartments = new ArrayList<>();
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment department = new ZooDepartment(departmentNames.get(i), zoo);
            zoo.addZooDepartment(department);
            zooDepartments.add(department);
        }

        for (int i = 0; i < zooDepartments.size(); i++) {
            if (types.get(i).equals("Bird")) {
                Bird bird = new Bird(genders.get(i), names.get(i), zooDepartments.get(i), flying.get(i));
                zooDepartments.get(i).addZooAnimal(bird);
            } else if (types.get(i).equals("Mammal")) {
                Mammal mam = new Mammal(genders.get(i), names.get(i), zooDepartments.get(i), hibernate.get(i));
                zooDepartments.get(i).addZooAnimal(mam);
            } else {
                ZooAnimal bird = new ZooAnimal(genders.get(i), names.get(i), zooDepartments.get(i));
                zooDepartments.get(i).addZooAnimal(bird);
            }

        }

        // check bidirectional relation
        for (ZooDepartment department : zoo.getDepartments()) {
            assertSame(zoo, department.getZoo());
        }

        service.addZooWithDepartments(zoo, zooDepartments);

        for (ZooDepartment department : zoo.getDepartments()) {
            service.addDepartmentWithAnimals(department, department.getAnimals());
        }

        int mammals = 0;
        int birds = 0;
        int animals = 0;

        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartmentById(zooDepartments.get(i).getId());
            assertEquals(departmentNames.get(i), foundZooDepartment.getName());
            assertEquals(zooName, foundZooDepartment.getZoo().getName());
            animals += foundZooDepartment.getAnimals().size();
            birds += foundZooDepartment.getBirds().size();
            mammals += foundZooDepartment.getMammals().size();

        }
        assertEquals(5, animals);
        assertEquals(1, birds);
        //de 5 dieren worden toegevoegd, maar de mammals blijft leeg.
        assertEquals(0, mammals);
    }

}
