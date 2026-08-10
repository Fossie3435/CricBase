/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.org.cricbase.Mappers.GroundMapper;
import uk.org.cricbase.Models.Ground;

/**
 *
 * @author Benjamin
 */
@Service
public class GroundService {
    private final GroundMapper groundMapper;
    
    public GroundService(GroundMapper groundMapper) {
        this.groundMapper = groundMapper;
    }
    
    public Optional<Ground> getGroundById(long id) {
        Ground ground = groundMapper.findGroundById(id);
        if(ground != null) {
            return Optional.of(ground);
        }
        return Optional.empty();
    }
    
    public Optional<Ground> getGroundByName(String name) {
        Ground ground = groundMapper.findGroundByName(name);
        if(ground != null) {
            return Optional.of(ground);
        }
        return Optional.empty();
    }
    
    public Ground addNewGround(Ground ground) {
        groundMapper.insertGround(ground);
        return ground;
    }
    
    public Ground addNewGround(String venue, String city) {
        Ground g = new Ground(venue, city);
        return this.addNewGround(g);
    }
    
    
}
