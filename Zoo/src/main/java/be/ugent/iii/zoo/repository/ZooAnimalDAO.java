package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooAnimal;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Wouter
 */
public interface ZooAnimalDAO extends CrudRepository<ZooAnimal, Long> {

}
