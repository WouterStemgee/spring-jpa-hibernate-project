/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.repositories;

import be.ugent.iii.entities.Zoo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Janne
 */
public interface ZooRepository extends CrudRepository<Zoo, Long> {
    
    @Query("SELECT * from Zoo z WHERE z.name=:name")
    List<Zoo> findByName(@Param("name") String name);
   
    
    
}
