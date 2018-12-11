package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooDepartment;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Wouter
 */
public interface ZooDepartmentDAO extends CrudRepository<ZooDepartment, Long> {
    
}
