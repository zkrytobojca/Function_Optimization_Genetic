package pl.edu.pb.wi.ae;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.ae.validator.IndividualValidator;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.GeneConfig;
import pl.edu.pb.wi.ae.evolution.EvolutionInfo;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.crossover.CrossoverResult;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.crossover.SimpleArithmeticCrossover;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.crossover.SingleArithmeticCrossover;


@SpringBootTest
public class FloatingPointGeneTest {
    private IndividualValidator validator = new IndividualValidator();

    @Test
    void singleIndividualTest() {
        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-9d);
        geneConfig1.setMaxValue(9d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(0d);
        geneConfig2.setMaxValue(1d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.FLOATING_POINT);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        Individual individual = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        validator.validate(individual, chromosomeConfig);
    }

    @Test
    void simpleArithmeticCrossoverTest() {
        Crossover crossover = Crossover.builder()
                .crossoverChance(1.0)
                .crossoverType(Crossover.Type.SIMPLE_ARITHMETIC)
                .geneType(Gene.Type.FLOATING_POINT)
                .build();
        Assertions.assertThat(crossover).isInstanceOf(SimpleArithmeticCrossover.class);


        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(0.3d);
        geneConfig1.setMaxValue(0.3);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(0.1d);
        geneConfig2.setMaxValue(0.1d);
        GeneConfig geneConfig3 = new GeneConfig();
        geneConfig3.setMinValue(0d);
        geneConfig3.setMaxValue(4d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.FLOATING_POINT);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);
        chromosomeConfig.addGeneConfig(geneConfig3);

        Individual individual1 = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        Individual individual2 = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        System.out.println("---Before crossover---");
        validator.validate(individual1, chromosomeConfig);
        validator.validate(individual2, chromosomeConfig);

        CrossoverResult crossoverResult = crossover.cross(individual1, individual2);

        Assertions.assertThat(individual1.getChromosome().getLength()).isEqualTo(crossoverResult.getIndividual1().getChromosome().getLength());
        Assertions.assertThat(individual2.getChromosome().getLength()).isEqualTo(crossoverResult.getIndividual2().getChromosome().getLength());

        System.out.println("---After crossover---");
        validator.validate(crossoverResult.getIndividual1(), chromosomeConfig);
        validator.validate(crossoverResult.getIndividual2(), chromosomeConfig);
    }

    @Test
    void singleArithmeticCrossoverTest() {
        Crossover crossover = Crossover.builder()
                .crossoverChance(1.0)
                .crossoverType(Crossover.Type.SINGLE_ARITHMETIC)
                .geneType(Gene.Type.FLOATING_POINT)
                .build();
        Assertions.assertThat(crossover).isInstanceOf(SingleArithmeticCrossover.class);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(0.3d);
        geneConfig1.setMaxValue(0.3);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(0.1d);
        geneConfig2.setMaxValue(0.1d);
        GeneConfig geneConfig3 = new GeneConfig();
        geneConfig3.setMinValue(0d);
        geneConfig3.setMaxValue(4d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.FLOATING_POINT);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);
        chromosomeConfig.addGeneConfig(geneConfig3);

        Individual individual1 = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        Individual individual2 = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        System.out.println("---Before crossover---");
        validator.validate(individual1, chromosomeConfig);
        validator.validate(individual2, chromosomeConfig);

        CrossoverResult crossoverResult = crossover.cross(individual1, individual2);

        Assertions.assertThat(individual1.getChromosome().getLength()).isEqualTo(crossoverResult.getIndividual1().getChromosome().getLength());
        Assertions.assertThat(individual2.getChromosome().getLength()).isEqualTo(crossoverResult.getIndividual2().getChromosome().getLength());

        System.out.println("---After crossover---");
        validator.validate(crossoverResult.getIndividual1(), chromosomeConfig);
        validator.validate(crossoverResult.getIndividual2(), chromosomeConfig);
    }

    @Test
    void mutationTest() {
        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-9d);
        geneConfig1.setMaxValue(9d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-9d);
        geneConfig2.setMaxValue(9d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.FLOATING_POINT);
        chromosomeConfig.setMutationChance(1.0d);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        Individual individual = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        System.out.println("---Before mutation---");
        validator.validate(individual, chromosomeConfig);

        System.out.println("---After mutation---");
        Integer iterations = 100;
        EvolutionInfo.getInstance().setNumberOfIterations(iterations);
        for (int i = 1; i <= iterations; i++) {
            System.out.println("Iteration=" + i);
            EvolutionInfo.getInstance().setIteration(i);
            individual.mutate();
            validator.validate(individual, chromosomeConfig);
        }
    }
}
