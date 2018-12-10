/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.zoo.service;

import be.ugent.iii.zoo.entity.Zoo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ugent.iii.zoo.repository.ZooRepository;

/**
 *
 * @author Janne
 */
@Service
public class ZooService implements IZooService {

    @Autowired
    private ZooRepository zooRepository;

    @Override
    public List<Zoo> getAllZoos() {
        List<Zoo> list = new ArrayList<>();
        zooRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Zoo getZooById(long zooId) {
        Zoo zoo = zooRepository.findById(zooId).get();
        return zoo;
    }

    @Override
    public synchronized boolean addZoo(Zoo zoo) {
        List<Zoo> list = zooRepository.findByName(zoo.getName());
        if (list.size() > 0) {
            return false;
        } else {
            zooRepository.save(zoo);
            return true;
        }
    }

    @Override
    public void updateZoo(Zoo zoo) {
        zooRepository.save(zoo);
    }

    @Override
    public void deleteZoo(long zooId) {
        zooRepository.delete(getZooById(zooId));
    }

}
