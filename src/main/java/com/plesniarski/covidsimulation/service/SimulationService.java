package com.plesniarski.covidsimulation.service;

import com.plesniarski.covidsimulation.domain.dto.SimulationDTO;
import com.plesniarski.covidsimulation.domain.entity.Simulation;

import java.util.List;

public interface SimulationService {
    Simulation addSimulation(SimulationDTO simulationDTO);
    List<SimulationDTO> findAll();
}
