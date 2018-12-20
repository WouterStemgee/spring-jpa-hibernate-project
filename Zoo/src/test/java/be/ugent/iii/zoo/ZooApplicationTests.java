package be.ugent.iii.zoo;

import be.ugent.iii.zoo.service.ZooService;
import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Bird;
import be.ugent.iii.zoo.entity.Mammal;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooAnimal;
import be.ugent.iii.zoo.entity.ZooDepartment;
import be.ugent.iii.zoo.entity.ZooKeeper;
import be.ugent.iii.zoo.entity.ZooOwner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
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

    /* TEST:
     *  OneToOne relation between Zoo and ZooOwner
     *  Edit 1 entity property and save the changes to the database
     */
    @Test
    public void addAndUpdateZoo() {
        // create Zoo
        String zooName = "ZOO Antwerpen";
        Zoo zoo = new Zoo(zooName, new Address("Koningin Astridplein", 20, 2018, "Antwerpen", "België"), "");

        // add Zoo to database
        service.addZoo(zoo);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());
        assertEquals(zooName, foundZoo.getName());
        // before edit
        assertEquals("", foundZoo.getPhoneNumber());

        // edit Zoo
        String phoneNumber = "032 24 89 10";
        foundZoo.setPhoneNumber(phoneNumber);
        service.updateZoo(foundZoo);
        foundZoo = service.getZooById(foundZoo.getId());
        // after edit
        assertEquals(phoneNumber, foundZoo.getPhoneNumber());
    }

    /* TEST:
     *  OneToOne relation between Zoo and ZooOwner
     *  No cascade (default): if we remove the owner the zoo should remain in the database
     */
    @Test
    public void addZooWithOwner() {
        // create Zoo
        String zooName = "De grotten van Han";
        Zoo zoo = new Zoo(zooName, new Address("Rue Joseph Lamotte", 2, 5580, "Han-sur-Lesse", "België"), "084 37 72 13");

        // create ZooOwner
        ZooOwner owner = new ZooOwner("Han Verschuure", new Address("Rue Joseph Lamotte", 2, 5580, "Han-sur-Lesse", "België"), zoo);
        zoo.setOwner(owner);

        // check bidirectional object-relation
        assertSame(zoo, owner.getZoo());

        // add Zoo with ZooOwner to database
        service.addZooWithOwner(zoo, owner);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());
        assertEquals(zoo.getName(), foundZoo.getName());
        assertEquals(zoo.getOwner().getName(), foundZoo.getOwner().getName());
        
        // delete the ZooOwner
        service.deleteWorker(owner.getId());
        
        // check if Zoo is still present in database
        foundZoo = service.getZooById(zoo.getId());
        assertEquals(zoo.getId(), foundZoo.getId());
        
        // re-add ZooOwner to database
        service.addZooWithOwner(zoo, owner);   
    }

    /* TEST:
     *  OneToMany relation between Zoo and ZooDepartment 
     *  FetchType = EAGER
     */
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

        // check bidirectional object-relation
        for (ZooDepartment department : zoo.getDepartments()) {
            assertSame(zoo, department.getZoo());
        }

        // add Zoo to database
        service.addZooWithDepartments(zoo, zooDepartments);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());
        assertEquals(zooName, foundZoo.getName());

        // retreive ZooDepartment's from database, check relation in database
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartmentById(zooDepartments.get(i).getId());
            assertEquals(departmentNames.get(i), foundZooDepartment.getName());
            assertEquals(zooName, foundZooDepartment.getZoo().getName());
        }
    }

    /* TEST:
     *  OneToMany relation between ZooDepartment and ZooAnimal
     *  FetchType = LAZY
     *  Inheritance
     */
    @Test
    public void addDepartmentWithAnimals() {
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

        // add Zoo to database
        service.addZooWithDepartments(zoo, zooDepartments);
        for (ZooDepartment department : zoo.getDepartments()) {
            service.addDepartmentWithAnimals(department, department.getAnimals());
        }

        // retreive data from database
        int mammals = 0;
        int birds = 0;
        int animals = 0;
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartmentById(zooDepartments.get(i).getId());
            assertEquals(departmentNames.get(i), foundZooDepartment.getName());
            assertEquals(zooName, foundZooDepartment.getZoo().getName());

            // explicitly fetch the Animal's with another Query since we are using lazy fetching here
            List<ZooAnimal> animalList = service.getAnimalsByDepartmentId(foundZooDepartment.getId());
            foundZooDepartment.setAnimals(new HashSet<>(animalList));

            // count each animal (test inheritance)
            animals += foundZooDepartment.getAnimals().size();
            birds += foundZooDepartment.getBirds().size();
            mammals += foundZooDepartment.getMammals().size();
        }

        // check if correct amount
        assertEquals(5, animals);
        assertEquals(1, birds);
        assertEquals(0, mammals);
    }

    /* TEST:
     *  ManyToMany relation between ZooDepartment and ZooKeeper
     *  Cascade will be checked
     */
    @Test
    public void addDepartmentsWithKeepers() {
        // create Zoo
        String zooName = "Bellewaerde";
        Zoo zoo = new Zoo(zooName, new Address("Meenseweg", 497, 8900, "Ieper", "België"), "057 46 86 86");

        // create Department's and ZooKeeper's
        List<String> departmentNames = Arrays.asList("Canada", "India", "Savanne", "Jungle", "Mexico", "Far West", "Kidspark");
        List<String> keeperNames = Arrays.asList("Emma", "Lucas", "Elise", "Noah", "Arthur", "Sofie", "Bob");
        List<ZooDepartment> zooDepartments = new ArrayList<>();
        List<ZooKeeper> zooKeepers = new ArrayList<>();
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment department = new ZooDepartment(departmentNames.get(i), zoo);
            for (int j = 0; j < keeperNames.size(); j++) {
                ZooKeeper keeper = new ZooKeeper(keeperNames.get(i), new Address("Meenseweg", 497, 8900, "Ieper", "België"), zooDepartments);
                department.addZooKeeper(keeper);
                zooKeepers.add(keeper);
            }
            zoo.addZooDepartment(department);
            zooDepartments.add(department);
        }

        // create ZooOwner
        ZooOwner owner = new ZooOwner("Albert Florizoone", new Address("Meenseweg", 497, 8900, "Ieper", "België"), zoo);
        zoo.setOwner(owner);

        // add Zoo to database  
        service.addZooWithOwner(zoo, owner);
        service.addZooWithDepartments(zoo, zooDepartments);
        service.addDepartmentsWithKeepers(zooDepartments, zooKeepers);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());
        assertEquals(zooName, foundZoo.getName());

        // retreive ZooDepartment's from database, check relation in database
        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment foundZooDepartment = service.getDepartmentById(zooDepartments.get(i).getId());
            assertEquals(departmentNames.get(i), foundZooDepartment.getName());
            for (ZooKeeper keeper : foundZooDepartment.getZooKeepers()) {
                assertTrue(keeper.getDepartments().contains(foundZooDepartment));
            }
        }

        // check Cascade: if we delete the first department, the ZooKeepers from that department should also be removed from the database
        long departmentId = zooDepartments.get(0).getId();
        int countBefore = service.getAllWorkers().size();
        service.deleteDepartment(departmentId);
        int countAfter = service.getAllWorkers().size();
        assertNotEquals(countBefore, countAfter);
    }

}
