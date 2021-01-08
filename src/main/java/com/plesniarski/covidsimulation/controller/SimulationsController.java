package com.plesniarski.covidsimulation.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationsController {
    @CrossOrigin
    @GetMapping("/test")
    public String getTest() {
        return "test";
    }
}
