package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooAnimal;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Wouter
 */
public interface ZooAnimalDAO extends CrudRepository<ZooAnimal, Long> {
    List<ZooAnimal> findByName(@Param("name") String name);
    List<ZooAnimal> findByDepartmentId(@Param("department_id") long departmentId);
}
