package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooWorker;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Wouter
 */
public interface ZooWorkerDAO extends CrudRepository<ZooWorker, Long> {

    List<ZooWorker> findByName(@Param("name") String name);
}
