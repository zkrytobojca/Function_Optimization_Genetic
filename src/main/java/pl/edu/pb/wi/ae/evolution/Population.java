package pl.edu.pb.wi.ae.evolution;


import pl.edu.pb.wi.ae.function.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
    private List<Individual> individuals;

    public Population(Integer size) {
        this.individuals = new ArrayList<>(size);
    }

    public static PopulationBuilder builder() {
        return new PopulationBuilder();
    }

    public static class PopulationBuilder {
        private Integer size;
        private Individual.IndividualBuilder individualBuilder;

        public PopulationBuilder size(Integer size) {
            this.size = size;
            return this;
        }

        public PopulationBuilder individualBuilder(Individual.IndividualBuilder individualBuilder) {
            this.individualBuilder = individualBuilder;
            return this;
        }

        public Population buildPopulation() {
            Population population = new Population(this.size);
            for (int i = 0; i < this.size; i++) {
                Individual individual = this.individualBuilder.build();
                population.addIndividual(individual);
            }
            return population;
        }
    }

    public void addIndividual(Individual individual) {
        this.individuals.add(individual);
    }

    public Individual getIndividual(int index) {
        return this.individuals.get(index);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Population{\n");
        for (Individual individual : individuals) {
            sb.append("\tgenotype=").append(individual.getGenotype());
            sb.append(", phenotype=").append(individual.getPhenotype());
            sb.append(", fitness=").append(individual.getFitnessValueAsString()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public void sort(Function.Optimize order) {
        Collections.sort(individuals);
        if (order == Function.Optimize.MIN) {
            Collections.reverse(individuals);
        }
    }
}
