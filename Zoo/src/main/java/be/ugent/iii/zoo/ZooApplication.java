package be.ugent.iii.zoo;

import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.entity.ZooDepartment;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import be.ugent.iii.zoo.repository.ZooDAO;
import be.ugent.iii.zoo.repository.ZooDepartmentDAO;

@SpringBootApplication
public class ZooApplication {

    private static final Logger log = LoggerFactory.getLogger(ZooApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ZooDAO zDAO, ZooDepartmentDAO zpDAO) {
        return (args) -> {
//            zDAO.save(new Zoo("Planckendael", new Address("Leuvensesteenweg", 582, 2812, "Mechelen", "België"), "015 41 49 21"));
//            Zoo zoo = zDAO.findByName("Planckendael");
//            ZooDepartment zp = new ZooDepartment("Waterdieren", zoo);
//            zoo.addZooDepartment(zp);
//            zpDAO.save(zp);
        };
    }
}
