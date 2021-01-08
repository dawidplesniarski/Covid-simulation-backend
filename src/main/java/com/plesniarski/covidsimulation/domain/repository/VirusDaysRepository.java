package com.plesniarski.covidsimulation.domain.repository;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirusDaysRepository extends JpaRepository<VirusDay, Long> {
    List<VirusDay> findBySimulationName(String simulationName);
}
