package com.plesniarski.covidsimulation.domain.repository;

import com.plesniarski.covidsimulation.domain.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationsRepository extends JpaRepository<Simulation, Long> {
    Simulation findBySimulationName(String simulationName);
}
