package pl.edu.pb.wi.ae;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.ae.validator.IndividualValidator;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.GeneConfig;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.crossover.CrossoverResult;
import pl.edu.pb.wi.ae.evolution.gene.binary.crossover.OnePointCrossover;
import pl.edu.pb.wi.ae.evolution.gene.binary.crossover.UniformCrossover;


@SpringBootTest
public class BinaryGeneTest {

    private IndividualValidator validator = new IndividualValidator();

    @Test
    void singleIndividualTest() {
        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-9d);
        geneConfig1.setMaxValue(9d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-17d);
        geneConfig2.setMaxValue(17d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setMutationChance(1.0);
        chromosomeConfig.setType(Gene.Type.BINARY);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        Individual individual = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();

        validator.validate(individual, chromosomeConfig);
    }

    @Test
    void uniformCrossoverTest() {
        Crossover crossover = Crossover.builder()
                .crossoverChance(1.0)
                .crossoverType(Crossover.Type.UNIFORM)
                .geneType(Gene.Type.BINARY)
                .build();
        Assertions.assertThat(crossover).isInstanceOf(UniformCrossover.class);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-1d);
        geneConfig1.setMaxValue(-1d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(2d);
        geneConfig2.setMaxValue(2d);
        GeneConfig geneConfig3 = new GeneConfig();
        geneConfig3.setMinValue(0d);
        geneConfig3.setMaxValue(0d);
        GeneConfig geneConfig4 = new GeneConfig();
        geneConfig4.setMinValue(1d);
        geneConfig4.setMaxValue(1d);
        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.BINARY);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);
        chromosomeConfig.addGeneConfig(geneConfig3);
        chromosomeConfig.addGeneConfig(geneConfig4);

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
    void onePointCrossoverTest() {
        Crossover crossover = Crossover.builder()
                .crossoverChance(1.0)
                .crossoverType(Crossover.Type.ONE_POINT)
                .geneType(Gene.Type.BINARY)
                .build();
        Assertions.assertThat(crossover).isInstanceOf(OnePointCrossover.class);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-9d);
        geneConfig1.setMaxValue(9d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-9d);
        geneConfig2.setMaxValue(9d);
        GeneConfig geneConfig3 = new GeneConfig();
        geneConfig3.setMinValue(10d);
        geneConfig3.setMaxValue(10d);
        GeneConfig geneConfig4 = new GeneConfig();
        geneConfig4.setMinValue(-6d);
        geneConfig4.setMaxValue(15d);
        GeneConfig geneConfig5 = new GeneConfig();
        geneConfig5.setMinValue(-50d);
        geneConfig5.setMaxValue(50d);
        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.BINARY);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);
        chromosomeConfig.addGeneConfig(geneConfig3);
        chromosomeConfig.addGeneConfig(geneConfig4);
        chromosomeConfig.addGeneConfig(geneConfig5);

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
        GeneConfig geneConfig3 = new GeneConfig();
        geneConfig3.setMinValue(-9d);
        geneConfig3.setMaxValue(9d);
        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(Gene.Type.BINARY);
        chromosomeConfig.setMutationChance(1.0d);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);
        chromosomeConfig.addGeneConfig(geneConfig3);

        Individual individual = Individual.builder()
                .chromosomeConfig(chromosomeConfig)
                .build();
        System.out.println(individual);

        System.out.println("---Before mutation---");
        validator.validate(individual, chromosomeConfig);

        individual.mutate();

        System.out.println("---After mutation---");
        validator.validate(individual, chromosomeConfig);
    }
}
