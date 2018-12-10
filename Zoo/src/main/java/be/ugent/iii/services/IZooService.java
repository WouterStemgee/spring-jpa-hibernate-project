/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.services;

import be.ugent.iii.entities.Zoo;
import java.util.List;

/**
 *
 * @author Wouter
 */
public interface IZooService {
    List<Zoo> getAllZoos();
}
