package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooDepartment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Wouter
 */
public interface ZooDepartmentDAO extends CrudRepository<ZooDepartment, Long> {

    List<ZooDepartment> findByName(@Param("name") String name);
}
