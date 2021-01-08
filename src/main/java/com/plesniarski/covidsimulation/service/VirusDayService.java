package com.plesniarski.covidsimulation.service;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;

import java.util.List;

public interface VirusDayService {
    VirusDay addVirusDay(VirusDayDTO virusDayDTO);
    List<VirusDayDTO> findAll();
    List<VirusDayDTO> findBySimulation(String simulationName);
}
