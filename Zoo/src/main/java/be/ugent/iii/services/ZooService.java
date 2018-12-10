/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.services;

import be.ugent.iii.entities.Zoo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import be.ugent.iii.repositories.ZooRepository;

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
    
    
    
}
