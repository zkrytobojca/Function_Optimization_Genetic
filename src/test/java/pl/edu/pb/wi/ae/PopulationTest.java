package pl.edu.pb.wi.ae;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.EvolutionConfig;
import pl.edu.pb.wi.ae.config.GeneConfig;
import pl.edu.pb.wi.ae.config.PopulationConfig;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.Population;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.validator.PopulationValidator;

@SpringBootTest
public class PopulationTest {

    private PopulationValidator validator = new PopulationValidator();

    @Test
    void simpleBinaryPopulationTest() {
        PopulationConfig populationConfig = new PopulationConfig();
        populationConfig.setSize(100);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-1000d);
        geneConfig1.setMaxValue(1000d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-1000d);
        geneConfig2.setMaxValue(1000d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setMutationChance(1.0);
        chromosomeConfig.setType(Gene.Type.BINARY);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        Individual.IndividualBuilder individualBuilder = Individual.builder()
                .chromosomeConfig(chromosomeConfig);

        Population population = Population.builder()
                .size(populationConfig.getSize())
                .individualBuilder(individualBuilder)
                .buildPopulation();
        validator.validatePopulation(population, populationConfig, chromosomeConfig);
    }

    @Test
    void simpleFloatingPointPopulationTest() {
        PopulationConfig populationConfig = new PopulationConfig();
        populationConfig.setSize(100);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-1000d);
        geneConfig1.setMaxValue(1000d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-1000d);
        geneConfig2.setMaxValue(1000d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setMutationChance(1.0);
        chromosomeConfig.setType(Gene.Type.FLOATING_POINT);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        Individual.IndividualBuilder individualBuilder = Individual.builder()
                .chromosomeConfig(chromosomeConfig);

        Population population = Population.builder()
                .size(populationConfig.getSize())
                .individualBuilder(individualBuilder)
                .buildPopulation();
        validator.validatePopulation(population, populationConfig, chromosomeConfig);
    }

    @Test
    void uniformCrossoverBinaryIndividualsInPopulation() {
        crossoverIndividualsInPopulationTest(10, Gene.Type.BINARY, Crossover.Type.UNIFORM, 1.0d);
    }

    @Test
    void onePointCrossoverBinaryIndividualsInPopulation() {
        crossoverIndividualsInPopulationTest(10, Gene.Type.BINARY, Crossover.Type.ONE_POINT, 1.0d);
    }

    @Test
    void simpleArithmeticCrossoverFloatingPointIndividualsInPopulation() {
        crossoverIndividualsInPopulationTest(10, Gene.Type.FLOATING_POINT, Crossover.Type.SIMPLE_ARITHMETIC, 1.0d);
    }

    @Test
    void singleArithmeticCrossoverFloatingPointIndividualsInPopulation() {
        crossoverIndividualsInPopulationTest(10, Gene.Type.FLOATING_POINT, Crossover.Type.SINGLE_ARITHMETIC, 1.0d);
    }

    @Test
    void simplePopulationCrossoverWithOdd() {
        crossoverIndividualsInPopulationTest(9, Gene.Type.BINARY, Crossover.Type.UNIFORM, 1.0d);
    }

    @Test
    void simplePopulationCrossoverWithCustomCrossoverChance() {
        crossoverIndividualsInPopulationTest(10, Gene.Type.BINARY, Crossover.Type.UNIFORM, 0.5d);
    }

    private void crossoverIndividualsInPopulationTest(Integer populationSize, Gene.Type geneType, Crossover.Type crossoverType, Double crossoverChance) {
        PopulationConfig populationConfig = new PopulationConfig();
        populationConfig.setSize(populationSize);

        GeneConfig geneConfig1 = new GeneConfig();
        geneConfig1.setMinValue(-1000d);
        geneConfig1.setMaxValue(1000d);
        GeneConfig geneConfig2 = new GeneConfig();
        geneConfig2.setMinValue(-1000d);
        geneConfig2.setMaxValue(1000d);

        ChromosomeConfig chromosomeConfig = new ChromosomeConfig();
        chromosomeConfig.setType(geneType);
        chromosomeConfig.addGeneConfig(geneConfig1);
        chromosomeConfig.addGeneConfig(geneConfig2);

        EvolutionConfig evolutionConfig = new EvolutionConfig();
        evolutionConfig.setCrossoverChance(crossoverChance);
        evolutionConfig.setCrossoverType(crossoverType);

        populationCrossoverTest(evolutionConfig, populationConfig, chromosomeConfig);
    }

    private void populationCrossoverTest(EvolutionConfig evolutionConfig, PopulationConfig populationConfig, ChromosomeConfig chromosomeConfig) {
        Individual.IndividualBuilder individualBuilder = Individual.builder()
                .chromosomeConfig(chromosomeConfig);

        Population population = Population.builder()
                .size(populationConfig.getSize())
                .individualBuilder(individualBuilder)
                .buildPopulation();

        Crossover crossover = Crossover.builder()
                .geneType(chromosomeConfig.getType())
                .crossoverType(evolutionConfig.getCrossoverType())
                .crossoverChance(evolutionConfig.getCrossoverChance())
                .build();

        Population populationOfChildren = crossover.crossPopulation(population);

        population.sort(evolutionConfig.getOptimize());
        populationOfChildren.sort(evolutionConfig.getOptimize());

        System.out.println("Starting population");
        validator.validatePopulation(population, populationConfig, chromosomeConfig);

        System.out.println("Crossed population");
        validator.validatePopulation(populationOfChildren, populationConfig, chromosomeConfig);

        validator.validateBoth(population, populationOfChildren);
    }
}
