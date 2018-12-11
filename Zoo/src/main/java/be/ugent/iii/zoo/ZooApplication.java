package be.ugent.iii.zoo;

import be.ugent.iii.zoo.entity.Address;
import be.ugent.iii.zoo.entity.Zoo;
import be.ugent.iii.zoo.repository.ZooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZooApplication {

    private static final Logger log = LoggerFactory.getLogger(ZooApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ZooRepository repository) {
        return (args) -> {
            repository.save(new Zoo("Planckendael", new Address("Leuvensesteenweg", 582, 2812, "Mechelen", "België"), "015 41 49 21"));
            log.info("Zoo found with findAll():");
            log.info("-------------------------------");
            for (Zoo zoo : repository.findAll()) {
                log.info(zoo.toString());
            }
        };
    }
}
