package com.plesniarski.covidsimulation.service.serviceImpl;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.Simulation;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;
import com.plesniarski.covidsimulation.domain.repository.SimulationsRepository;
import com.plesniarski.covidsimulation.domain.repository.VirusDaysRepository;
import com.plesniarski.covidsimulation.service.VirusDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusDayServiceImpl implements VirusDayService {

    VirusDaysRepository virusDaysRepository;
    SimulationsRepository simulationsRepository;

    @Autowired
    public VirusDayServiceImpl(VirusDaysRepository virusDaysRepository,
                               SimulationsRepository simulationsRepository) {
        this.virusDaysRepository = virusDaysRepository;
        this.simulationsRepository = simulationsRepository;
    }

    @Override
    public VirusDay addVirusDay(VirusDayDTO virusDayDTO) {
        final VirusDay virusDay = new VirusDay(
                virusDayDTO.getSimulationName(),
                virusDayDTO.getInfectedPeoples(),
                virusDayDTO.getHealthyPeoples(),
                virusDayDTO.getDeadPeoples(),
                virusDayDTO.getCuredPeoples()
        );
        return virusDaysRepository.save(virusDay);
    }

    @Override
    public List<VirusDayDTO> findAll() {
        return virusDaysRepository
                .findAll()
                .stream()
                .map(VirusDay::dto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VirusDayDTO> findBySimulation(String simulationName) {
        return virusDaysRepository
                .findBySimulationName(simulationName)
                .stream()
                .map(VirusDay::dto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VirusDayDTO> findVirusDays(String simulationName) {

        virusDaysRepository.deleteAll(); // cleaning simulation
        final Simulation simulation = simulationsRepository.findBySimulationName(simulationName);

        int pI = simulation.getInitialNumberOfInfected(); //infected
        int pV = (simulation.getPopulation() - simulation.getInitialNumberOfInfected()); // healthy
        int pM = 0; // deaths
        int pR = 0; // recovered

        List<VirusDay> virusDays = new ArrayList<>();

        for (int i = 0; i < simulation.getSimulationTime(); i++) {
            pI -= pM + pR;
            VirusDay virusDay = new VirusDay(
                    simulationName, pI, pV, pM, pR);
            if(pV > 0) {
                virusDays.add(virusDay);

                if (i > simulation.getMortalTime() - 3) {
                    pM += simulation.getMortalityRate();
                }
                if (i > simulation.getCureTime() - 2) {
                    if((i - simulation.getCureTime() + 1) == 0) {
                        pR += simulation.getInitialNumberOfInfected();
                    } else {
                        pR+= virusDays.get(i - simulation.getCureTime() + 2).getInfectedPeoples() - virusDays.get(i - simulation.getCureTime() + 1).getInfectedPeoples();
                    }
                }
                pI += (int) (pI * simulation.getInfectionRate());
                pV = simulation.getPopulation() - pI;
            } else if(pV < 0) {
                virusDays.add(virusDays.get(virusDays.size() - 1));
            }
        }

        virusDaysRepository.saveAll(virusDays);

        return virusDaysRepository.findBySimulationName(simulationName).stream().map(VirusDay::dto).collect(Collectors.toList());
    }
}
