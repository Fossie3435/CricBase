/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.org.cricbase.Models.Ground;
import uk.org.cricbase.Services.GroundService;

/**
 *
 * @author Benjamin
 */
@RestController
@RequestMapping("/grounds")
public class GroundController {
    @Autowired
    private GroundService groundService;
    
}
