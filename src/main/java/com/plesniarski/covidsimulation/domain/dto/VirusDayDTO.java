package com.plesniarski.covidsimulation.domain.dto;

public class VirusDayDTO {
    private Long id;
    private String simulationName;
    private int infectedPeoples;
    private int healthyPeoples;
    private int deadPeoples;
    private int curedPeoples;

    public VirusDayDTO() {}

    public VirusDayDTO(Long id, String simulationName, int infectedPeoples, int healthyPeoples, int deadPeoples, int curedPeoples) {
        this.id = id;
        this.simulationName = simulationName;
        this.infectedPeoples = infectedPeoples;
        this.healthyPeoples = healthyPeoples;
        this.deadPeoples = deadPeoples;
        this.curedPeoples = curedPeoples;
    }

    public VirusDayDTO(String simulationName, int infectedPeoples, int healthyPeoples, int deadPeoples, int curedPeoples) {
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
}
