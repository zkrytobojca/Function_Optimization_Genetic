package pl.edu.pb.wi.ae.evolution.crossover;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.Population;
import pl.edu.pb.wi.ae.evolution.gene.binary.crossover.OnePointCrossover;
import pl.edu.pb.wi.ae.evolution.gene.binary.crossover.UniformCrossover;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.crossover.SimpleArithmeticCrossover;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.crossover.SingleArithmeticCrossover;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import java.util.Iterator;

public interface Crossover {

    enum Type {
        ONE_POINT, UNIFORM, SIMPLE_ARITHMETIC, SINGLE_ARITHMETIC
    }

    static CrossoverBuilder builder() {
        return new CrossoverBuilder();
    }

    class CrossoverBuilder {
        private Gene.Type geneType;
        private Crossover.Type crossoverType;
        private Double crossoverChance;

        public CrossoverBuilder geneType(Gene.Type geneType) {
            this.geneType = geneType;
            return this;
        }

        public CrossoverBuilder crossoverType(Crossover.Type crossoverType) {
            this.crossoverType = crossoverType;
            return this;
        }

        public CrossoverBuilder crossoverChance(Double crossoverChance) {
            this.crossoverChance = crossoverChance;
            return this;
        }

        public Crossover build() {
            switch (this.geneType) {
                case BINARY:
                    switch (this.crossoverType) {
                        case ONE_POINT:
                            return new OnePointCrossover(crossoverChance);
                        case UNIFORM:
                            return new UniformCrossover(crossoverChance);
                        default:
                            throw new InvalidConfigurationException("Invalid crossover type");
                    }
                case FLOATING_POINT:
                    switch (this.crossoverType) {
                        case SIMPLE_ARITHMETIC:
                            return new SimpleArithmeticCrossover(crossoverChance);
                        case SINGLE_ARITHMETIC:
                            return new SingleArithmeticCrossover(crossoverChance);
                        default:
                            throw new InvalidConfigurationException("Invalid crossover type");
                    }
                default:
                    throw new InvalidConfigurationException("Invalid geneType");
            }
        }
    }

    CrossoverResult cross(Individual individual1, Individual individual2);

    default Population crossPopulation(Population population) {
        Population populationOfChildren = new Population(population.getIndividuals().size());

        Iterator<Individual> it = population.getIndividuals().iterator();
        while (it.hasNext()) {
            Individual ind1 = it.next();
            if (it.hasNext()) {
                Individual ind2 = it.next();
                CrossoverResult crossoverResult = cross(ind1, ind2);
                populationOfChildren.addIndividual(crossoverResult.getIndividual1());
                populationOfChildren.addIndividual(crossoverResult.getIndividual2());
            } else {
                Individual copyOfInd1 = new Individual(ind1);
                populationOfChildren.addIndividual(copyOfInd1); // odd size of population => this element is not crossed
            }
        }
        return populationOfChildren;
    }
}
