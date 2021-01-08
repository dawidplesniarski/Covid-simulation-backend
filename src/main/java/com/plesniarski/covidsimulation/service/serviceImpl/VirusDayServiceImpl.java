package com.plesniarski.covidsimulation.service.serviceImpl;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;
import com.plesniarski.covidsimulation.domain.entity.VirusDay;
import com.plesniarski.covidsimulation.domain.repository.VirusDaysRepository;
import com.plesniarski.covidsimulation.service.VirusDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusDayServiceImpl implements VirusDayService {

    VirusDaysRepository virusDaysRepository;

    @Autowired
    public VirusDayServiceImpl(VirusDaysRepository virusDaysRepository) {
        this.virusDaysRepository = virusDaysRepository;
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
}
