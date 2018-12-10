package be.ugent.iii.zoo.service;

import be.ugent.iii.zoo.entity.Zoo;
import java.util.List;

/**
 *
 * @author Wouter
 */
public interface IZooService {
    List<Zoo> getAllZoos();
    Zoo getZooById(long zooId);
    boolean addZoo(Zoo zoo);
    void updateZoo(Zoo zoo);
    void deleteZoo(long zooId);
}
