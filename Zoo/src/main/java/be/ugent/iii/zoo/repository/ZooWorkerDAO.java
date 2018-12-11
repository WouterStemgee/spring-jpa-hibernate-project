package be.ugent.iii.zoo.repository;

import be.ugent.iii.zoo.entity.ZooWorker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Wouter
 */
public interface ZooWorkerDAO extends CrudRepository<ZooWorker, Long> {

    ZooWorker findByName(@Param("name") String name);
}
