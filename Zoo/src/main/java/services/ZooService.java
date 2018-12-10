/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ZooRepository;

/**
 *
 * @author Janne
 */
@Service
public class ZooService {
    
    @Autowired
    private ZooRepository zooRepository;
    
}
