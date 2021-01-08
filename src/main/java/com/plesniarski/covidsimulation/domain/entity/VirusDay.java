package com.plesniarski.covidsimulation.domain.entity;

import com.plesniarski.covidsimulation.domain.dto.VirusDayDTO;

import javax.persistence.*;

@Entity
@Table(name = "virusdays")
public class VirusDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String simulationName;
    private int infectedPeoples;
    private int healthyPeoples;
    private int deadPeoples;
    private int curedPeoples;

    public VirusDay() {}

    public VirusDay(String simulationName, int infectedPeoples, int healthyPeoples, int deadPeoples, int curedPeoples) {
        this.simulationName = simulationName;
        this.infectedPeoples = infectedPeoples;
        this.healthyPeoples = healthyPeoples;
        this.deadPeoples = deadPeoples;
        this.curedPeoples = curedPeoples;
    }

    public Long getId() {
        return id;
    }

    public String getSimulationName() {
        return simulationName;
    }

    public int getInfectedPeoples() {
        return infectedPeoples;
    }

    public int getHealthyPeoples() {
        return healthyPeoples;
    }

    public int getDeadPeoples() {
        return deadPeoples;
    }

    public int getCuredPeoples() {
        return curedPeoples;
    }

    public VirusDayDTO dto() {
        return new VirusDayDTO(
                getId(),
                getSimulationName(),
                getInfectedPeoples(),
                getHealthyPeoples(),
                getDeadPeoples(),
                getCuredPeoples()
        );
    }
}
