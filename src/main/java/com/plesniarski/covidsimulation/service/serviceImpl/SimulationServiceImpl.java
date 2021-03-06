package com.plesniarski.covidsimulation.service.serviceImpl;

import com.plesniarski.covidsimulation.domain.dto.SimulationDTO;
import com.plesniarski.covidsimulation.domain.entity.Simulation;
import com.plesniarski.covidsimulation.domain.repository.SimulationsRepository;
import com.plesniarski.covidsimulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SimulationServiceImpl implements SimulationService {

    private final SimulationsRepository simulationsRepository;

    @Autowired
    public SimulationServiceImpl(SimulationsRepository simulationsRepository) {
        this.simulationsRepository = simulationsRepository;
    }

    @Override
    public Simulation addSimulation(SimulationDTO simulationDTO) {
        final Simulation simulation = new Simulation(
                simulationDTO.getSimulationName(),
                simulationDTO.getPopulation(),
                simulationDTO.getInitialNumberOfInfected(),
                simulationDTO.getInfectionRate(),
                simulationDTO.getMortalityRate(),
                simulationDTO.getCureTime(),
                simulationDTO.getMortalTime(),
                simulationDTO.getSimulationTime()
                );
        return simulationsRepository.save(simulation);
    }

    @Override
    public List<SimulationDTO> findAll() {
        return simulationsRepository.
                findAll()
                .stream()
                .map(Simulation::dto)
                .collect(Collectors.toList());
    }

    @Override
    public SimulationDTO findBySimulationName(String simulationName) {
        return simulationsRepository.findBySimulationName(simulationName).dto();
    }

    @Override
    public void deleteSimulation(String simulationName) {
        simulationsRepository.deleteSimulationBySimulationName(simulationName);
    }
}
