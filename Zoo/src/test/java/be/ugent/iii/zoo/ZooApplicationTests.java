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
import java.util.NoSuchElementException;
import org.hibernate.LazyInitializationException;
import static org.junit.Assert.assertEquals;
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
     *  Add entity to database
     */
    @Test
    public void addZoo() {
        // create Zoo
        String zooName = "ZOO Antwerpen";
        Zoo zoo = new Zoo(zooName, new Address("Koningin Astridplein", 20, 2018, "Antwerpen", "België"), "032 24 89 10");

        // add Zoo to database
        service.addZoo(zoo);

        // retreive Zoo from database
        Zoo foundZoo = service.getZooById(zoo.getId());
        assertEquals(zooName, foundZoo.getName());
    }

    /* TEST:
     *  OneToOne relation between Zoo and ZooOwner
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
    }

    /* TEST:
     *  OneToMany relation between Zoo and ZooDepartment 
     *  Adding multiple entities to database
     *  FetchType = EAGER
    .*  Cascade (REMOVE): if we remove the Zoo, the ZooDepartment's should also be removed from the database
     */
    @Test(expected = NoSuchElementException.class)
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

        // remove Zoo from database
        service.deleteZoo(zoo.getId());
        for (ZooDepartment department : zoo.getDepartments()) {
            // will throw NoSuchElementException since the departments should be removed aswell
            service.getDepartmentById(department.getId());
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
            try {
                // try to get the ZooAnimal collection, but will throw LazyInitializationException since they are not initialized because of lazy fetching
                int animalCount = foundZooDepartment.getAnimals().size();
            } catch (LazyInitializationException e) {
                // explicitly fetch the Animal's with another Query since we are using lazy fetching here
                List<ZooAnimal> animalList = service.getAnimalsByDepartmentId(foundZooDepartment.getId());
                foundZooDepartment.setAnimals(new HashSet<>(animalList));

                // count each animal (test inheritance)
                animals += foundZooDepartment.getAnimals().size();
                birds += foundZooDepartment.getBirds().size();
                mammals += foundZooDepartment.getMammals().size();
            }
        }

        // check if correct amount
        assertEquals(5, animals);
        assertEquals(1, birds);
        assertEquals(0, mammals);
    }

    /* TEST:
     *  ManyToMany relation between ZooDepartment and ZooKeeper
     *  No cascade (default): if we remove a ZooDepartment, the ZooKeeper's should remain in the database
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
            zooDepartments.add(department);
        }

        for (int i = 0; i < keeperNames.size(); i++) {
            ZooKeeper keeper = new ZooKeeper(keeperNames.get(i), new Address("Meenseweg", 497, 8900, "Ieper", "België"));
            for (int j = 0; j < departmentNames.size(); j++) {
                keeper.addDepartment(zooDepartments.get(j));
                zooDepartments.get(j).addZooKeeper(keeper);
            }
            zooKeepers.add(keeper);
        }

        for (int i = 0; i < departmentNames.size(); i++) {
            ZooDepartment department = zooDepartments.get(i);
            zoo.addZooDepartment(department);
        }

        // create ZooOwner
        ZooOwner owner = new ZooOwner("Albert Florizoone", new Address("Meenseweg", 497, 8900, "Ieper", "België"), zoo);
        zoo.setOwner(owner);

        // add Zoo to database  
        service.addZooWithOwner(zoo, owner);
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

        // check Cascade: if we delete the first department, the ZooKeepers from that department should remain in the database
        long departmentId = zooDepartments.get(0).getId();
        int countBefore = service.getAllWorkers().size();
        service.deleteDepartment(departmentId);
        int countAfter = service.getAllWorkers().size();
        assertEquals(countBefore, countAfter);
    }

    /* TEST:
     *  Edit entity properties and save the changes to the database
     */
    @Test
    public void ChangeZooAddress() {
        // initialise Zoo
        String zooName = "Olmense zoo";
        Address address = new Address("Bosstraat", 33, 2491, "Balen", "België");
        Zoo zoo = new Zoo(zooName, address, "014 30 98 82");
        service.addZoo(zoo);

        // get Zoo based on name
        List<Zoo> zoos = service.getZooByName(zooName);
        assertEquals(1, zoos.size());

        // change the Address of the found zoo
        Address newAddress = new Address("Bukenberg", 45, 2491, "Balen", "België");
        zoos.get(0).setAddress(newAddress);

        service.updateZoo(zoos.get(0));

        // get Zoo based on name, should get the Zoo with the new Address
        List<Zoo> newZoos = service.getZooByName(zooName);

        Address zooAddr = newZoos.get(0).getAddress();

        // street and number are the only things changed in the Address
        assertEquals(newAddress.getStreet(), zooAddr.getStreet());
        assertEquals(newAddress.getNumber(), zooAddr.getNumber());
    }

}
