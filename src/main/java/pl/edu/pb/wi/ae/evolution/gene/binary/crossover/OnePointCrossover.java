package pl.edu.pb.wi.ae.evolution.gene.binary.crossover;

import pl.edu.pb.wi.ae.evolution.Chromosome;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.crossover.CrossoverResult;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.gene.binary.BinaryGene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.Random;

public class OnePointCrossover implements Crossover {
    private Double crossoverChance;
    private final Random rand = new Random();

    public OnePointCrossover(Double crossoverChance) {
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

            //Wybierz losowo punkt krzyżowania w zakresie 1,2,...,L-1.
            int crossoverPoint = rand.nextInt(chromosomeOfInd1.getLength());
            if (crossoverPoint == 0) {
                crossoverPoint = 1;
            }
            //System.out.println("crossover floatingpoint => " + crossoverPoint);

            for (int i = 0; i < chromosomeOfInd1.getLength(); i++) {
                Gene gene1 = chromosomeOfInd1.getGenes().get(i);
                Gene gene2 = chromosomeOfInd2.getGenes().get(i);

                BinaryGene newGene1 = new BinaryGene(gene1, gene1.getGenotype());
                BinaryGene newGene2 = new BinaryGene(gene2, gene2.getGenotype());
                newGene1.checkAndAdjustToLimits();
                newGene2.checkAndAdjustToLimits();
                if (i < crossoverPoint) {
                    newInd1.getChromosome().addGene(newGene1);
                    newInd2.getChromosome().addGene(newGene2);
                } else {
                    newInd1.getChromosome().addGene(newGene2);
                    newInd2.getChromosome().addGene(newGene1);
                }
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
