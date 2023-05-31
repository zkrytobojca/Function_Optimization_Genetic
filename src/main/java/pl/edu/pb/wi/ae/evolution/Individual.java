package pl.edu.pb.wi.ae.evolution;

import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.tool.Formatter;

public class Individual implements Comparable<Individual>{
    private Chromosome chromosome;
    private double fitnessValue;

    public Individual() {
        this.chromosome = new Chromosome();
    }

    public Individual(Chromosome chromosome) {
        this.chromosome = chromosome;
    }

    public Individual(Individual individual) {
        this.chromosome = new Chromosome(individual.chromosome);
        this.fitnessValue = individual.fitnessValue;
    }

    public static IndividualBuilder builder() {
        return new IndividualBuilder();
    }

    public static class IndividualBuilder {
        private ChromosomeConfig chromosomeConfig;

        public IndividualBuilder chromosomeConfig(ChromosomeConfig chromosomeConfig) {
            this.chromosomeConfig = chromosomeConfig;
            return this;
        }

        public Individual build() {
            Chromosome chromosome = new Chromosome(this.chromosomeConfig);
            return new Individual(chromosome);
        }
    }

    public void mutate() {
        this.chromosome.mutate();
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public String getFitnessValueAsString() {
        return Formatter.formatDouble(fitnessValue);
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    @Override
    public int compareTo(Individual individual) {
        return Double.compare(this.fitnessValue, individual.fitnessValue);
    }

    @Override
    public String toString() {
        return "Individual{" +
                "chromosome=" + chromosome +
                ", fitnessValue=" + getFitnessValueAsString() +
                '}';
    }

    public String getGenotype() {
        return chromosome.getGenotype();
    }

    public String getPhenotype() {
        return chromosome.getPhenotype();
    }
}
