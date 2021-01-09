package com.plesniarski.covidsimulation.domain.repository;

import com.plesniarski.covidsimulation.domain.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SimulationsRepository extends JpaRepository<Simulation, Long> {
    Simulation findBySimulationName(String simulationName);
    void deleteSimulationBySimulationName(String simulationName);
}
