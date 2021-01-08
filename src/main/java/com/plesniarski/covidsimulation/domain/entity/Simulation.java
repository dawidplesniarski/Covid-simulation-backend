package com.plesniarski.covidsimulation.domain.entity;

import com.plesniarski.covidsimulation.domain.dto.SimulationDTO;

import javax.persistence.*;

@Entity
@Table(name="simulations1")
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String simulationName; // nazwa symulacji
    private int population; // liczba populacji
    private int initialNumberOfInfected; // początkowa liczba zarażonych
    private double infectionRate; // wskaźnik R zarażających
    private int mortalityRate; //wskaźnik śmiertelności
    private int cureTime; // czas wyzdrowienia chorego
    private int mortalTime; // czas śmierci od zarażenia
    private int simulationTime; // czas trwania symulacji

    public Simulation(String simulationName, int population, int initialNumberOfInfected, double infectionRate, int mortalityRate, int cureTime, int mortalTime, int simulationTime) {
        this.simulationName = simulationName;
        this.population = population;
        this.initialNumberOfInfected = initialNumberOfInfected;
        this.infectionRate = infectionRate;
        this.mortalityRate = mortalityRate;
        this.cureTime = cureTime;
        this.mortalTime = mortalTime;
        this.simulationTime = simulationTime;
    }

    public Simulation() {}

    public Long getId() {
        return id;
    }

    public String getSimulationName() {
        return simulationName;
    }

    public int getInitialNumberOfInfected() {
        return initialNumberOfInfected;
    }

    public double getInfectionRate() {
        return infectionRate;
    }

    public int getMortalityRate() {
        return mortalityRate;
    }

    public int getCureTime() {
        return cureTime;
    }

    public int getMortalTime() {
        return mortalTime;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getPopulation() {
        return population;
    }

    public SimulationDTO dto() {
        return new SimulationDTO(
                getId(),
                getSimulationName(),
                getPopulation(),
                getInitialNumberOfInfected(),
                getInfectionRate(),
                getMortalityRate(),
                getCureTime(),
                getMortalTime(),
                getSimulationTime());
    }
}
