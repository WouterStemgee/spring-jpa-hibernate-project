package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooAnimal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Wouter
 */
public interface ZooAnimalDAO extends CrudRepository<ZooAnimal, Long> {

    ZooAnimal findByName(@Param("name") String name);
}
