package pl.edu.pb.wi.ae.evolution.gene.floatingpoint.crossover;

import pl.edu.pb.wi.ae.evolution.Chromosome;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.crossover.CrossoverResult;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.FloatingPointGene;

import java.util.Random;

public class SimpleArithmeticCrossover implements Crossover {
    private static final double ALPHA = 0.25;
    private final Random rand = new Random();

    private Double crossoverChance;

    public SimpleArithmeticCrossover(Double crossoverChance) {
        this.crossoverChance = crossoverChance;
    }

    @Override
    public CrossoverResult cross(Individual individual1, Individual individual2) {
        CrossoverResult result = new CrossoverResult();

        if (rand.nextFloat() < crossoverChance) {
            Individual newInd1 = new Individual();
            Individual newInd2 = new Individual();

            Chromosome chromosomeOfInd1 = individual1.getChromosome();
            Chromosome chromosomeOfInd2 = individual2.getChromosome();
            int len = chromosomeOfInd1.getLength();

            //Wybierz losowo punkt krzyżowania w zakresie 1,2,...,L-1.
            int crossoverPoint = rand.nextInt(len);
            if (crossoverPoint == 0) {
                crossoverPoint = 1;
            }
            //System.out.println("crossover floatingpoint => " + crossoverPoint);

            for (int i = 0; i < chromosomeOfInd1.getGenes().size(); i++) {
                Gene gene1 = chromosomeOfInd1.getGenes().get(i);
                Gene gene2 = chromosomeOfInd2.getGenes().get(i);
                if (i < crossoverPoint) {
                    newInd1.getChromosome().addGene(gene1);
                    newInd2.getChromosome().addGene(gene2);
                } else {
                    double x1 = gene1.getPhenotype();
                    double x2 = gene2.getPhenotype();
                    double y1 = ALPHA * x1 + (1 - ALPHA) * x2;
                    double y2 = (1 - ALPHA) * x1 + ALPHA * x2;
                    newInd1.getChromosome().addGene(new FloatingPointGene(gene1, y1));
                    newInd2.getChromosome().addGene(new FloatingPointGene(gene2, y2));
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
