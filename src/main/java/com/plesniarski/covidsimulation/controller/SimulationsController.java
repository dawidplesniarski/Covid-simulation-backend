package com.plesniarski.covidsimulation.controller;

import com.plesniarski.covidsimulation.domain.dto.SimulationDTO;
import com.plesniarski.covidsimulation.domain.entity.Simulation;
import com.plesniarski.covidsimulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationsController {
    SimulationService simulationService;

    @Autowired
    public SimulationsController(@RequestBody SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @CrossOrigin
    @GetMapping("/findAll")
    public ResponseEntity<List<SimulationDTO>> findAllSimulations() {
        return ResponseEntity.ok(simulationService.findAll());
    }

    @CrossOrigin
    @GetMapping("/findBySimulation/{simulation}")
    public ResponseEntity<SimulationDTO> findBySimulationName(@PathVariable String simulation) {
        return ResponseEntity.ok(simulationService.findBySimulationName(simulation));
    }

    @CrossOrigin
    @PostMapping("/addSimulation")
    public ResponseEntity<Simulation> addSimulation(@RequestBody SimulationDTO simulationDTO) {
        return ResponseEntity.status(201).body(simulationService.addSimulation(simulationDTO));
    }

    @CrossOrigin
    @DeleteMapping("/deleteSimulation/{simulation}")
    public void deleteSimulation(@PathVariable String simulation) {
        simulationService.deleteSimulation(simulation);
    }
}
