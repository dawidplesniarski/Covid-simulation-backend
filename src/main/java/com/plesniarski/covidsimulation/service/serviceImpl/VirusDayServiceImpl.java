package com.plesniarski.covidsimulation.service.serviceImpl;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.Simulation;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;
import com.plesniarski.covidsimulation.domain.repository.SimulationsRepository;
import com.plesniarski.covidsimulation.domain.repository.VirusDaysRepository;
import com.plesniarski.covidsimulation.service.VirusDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*
    * 1. Wyszukaj symulację z informacjami po nazwie
    * 2. Pętla for z ilością dni symulacji
    * 3. Pierwszy dzien symulacji ma parametry symulacji
    * 4. Osoby zakażone przemnóż przez wskaźnik zakazen
    * */
    @Override
    public List<VirusDayDTO> findVirusDays(String simulationName) {

        virusDaysRepository.deleteAll(); // cleaning simulation
        final Simulation simulation = simulationsRepository.findBySimulationName(simulationName);

        int pI = simulation.getInitialNumberOfInfected(); //osoby zakażone
        int pV = (simulation.getPopulation() - simulation.getInitialNumberOfInfected()); // osoby zdrowe
        int pM = 0; // osoby zmarłe
        int pR = 0; // osoby ozdrowiałe

        for(int i = 0; i < simulation.getSimulationTime(); i++) {
            pI -= pM + pR;
            VirusDay virusDay = new VirusDay(
                    simulationName, pI, pV, pM, pR);
            virusDaysRepository.save(virusDay);

            if (i > simulation.getMortalTime() - 3) {
                pM += simulation.getMortalityRate();
            }
            if (i > simulation.getCureTime() - 3) {
                pR += i * 2.5;
            }
            pI += (int) (pI * simulation.getInfectionRate());
            pV = simulation.getPopulation() - pI;
        }
        return virusDaysRepository.findBySimulationName(simulationName).stream().map(VirusDay::dto).collect(Collectors.toList());
    }
}
