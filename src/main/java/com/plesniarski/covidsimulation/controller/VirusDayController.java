package com.plesniarski.covidsimulation.controller;


import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;
import com.plesniarski.covidsimulation.service.VirusDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/days")
public class VirusDayController {
    VirusDayService virusDayService;

    @Autowired
    public VirusDayController(@RequestBody VirusDayService virusDayService) {
        this.virusDayService = virusDayService;
    }

    @CrossOrigin
    @GetMapping("/findAll")
    public ResponseEntity<List<VirusDayDTO>> findAllDays() {
        return ResponseEntity.ok(virusDayService.findAll());
    }

    @CrossOrigin
    @PostMapping("/addVirusDay")
    public ResponseEntity<VirusDay> addVirusDay(@RequestBody VirusDayDTO virusDayDTO) {
        return ResponseEntity.status(201).body(virusDayService.addVirusDay(virusDayDTO));
    }

    @CrossOrigin
    @GetMapping("/findBySimulation/{simulation}")
    public ResponseEntity<List<VirusDayDTO>> getVirusDaysBySimulation(@PathVariable String simulation) {
        return ResponseEntity.ok(virusDayService.findBySimulation(simulation));
    }

    @CrossOrigin
    @GetMapping("/generateSimulation/{simulation}")
    public ResponseEntity<List<VirusDayDTO>> generateSimulation(@PathVariable String simulation) {
        return ResponseEntity.ok(virusDayService.findVirusDays(simulation));
    }
}
