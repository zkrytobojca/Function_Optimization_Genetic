package pl.edu.pb.wi.ae.evolution.gene.binary.crossover;


import pl.edu.pb.wi.ae.evolution.Chromosome;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.crossover.CrossoverResult;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.gene.binary.BinaryGene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.Random;

public class UniformCrossover implements Crossover {
    private static final double PROBABILITY = 0.5;
    private Double crossoverChance;
    private final Random rand = new Random();

    public UniformCrossover(Double crossoverChance) {
        this.crossoverChance = crossoverChance;
    }

    @Override
    public CrossoverResult cross(Individual individual1, Individual individual2) {
        CrossoverResult result = new CrossoverResult();

        if (rand.nextDouble() < crossoverChance) {
            Individual newInd1 = new Individual();
            Individual newInd2 = new Individual();

            Chromosome chromosomeOfInd1 = individual1.getChromosome();
            Chromosome chromosomeOfInd2 = individual2.getChromosome();
            for (int i = 0; i < chromosomeOfInd1.getLength(); i++) {
                Gene gene1 = chromosomeOfInd1.getGenes().get(i);
                Gene gene2 = chromosomeOfInd2.getGenes().get(i);
                StringBuilder newGenotype1 = new StringBuilder();
                StringBuilder newGenotype2 = new StringBuilder();
                for (int j = 0; j < gene1.getGenotype().length(); j++) {
                    if(rand.nextDouble() < PROBABILITY) {
                        newGenotype1.append(gene1.getGenotype().charAt(j));
                        newGenotype2.append(gene2.getGenotype().charAt(j));
                    }
                    else {
                        newGenotype1.append(gene2.getGenotype().charAt(j));
                        newGenotype2.append(gene1.getGenotype().charAt(j));
                    }
                }
                BinaryGene newGene1 = new BinaryGene(gene1, newGenotype1.toString());
                BinaryGene newGene2 = new BinaryGene(gene2, newGenotype2.toString());
                newGene1.checkAndAdjustToLimits();
                newGene2.checkAndAdjustToLimits();
                newInd1.getChromosome().addGene(newGene1);
                newInd2.getChromosome().addGene(newGene2);
            }
            result.setIndividual1(newInd1);
            result.setIndividual2(newInd2);
        } else {
            result.setIndividual1(new Individual(individual1)); //TODO inaczej
            result.setIndividual2(new Individual(individual2));
        }

        return result;
    }
}
