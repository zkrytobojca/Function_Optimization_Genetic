package pl.edu.pb.wi.ae;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.ae.validator.PopulationValidator;
import pl.edu.pb.wi.ae.config.*;
import pl.edu.pb.wi.ae.evolution.FitnessEvaluator;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.Population;
import pl.edu.pb.wi.ae.evolution.selection.Selection;
import pl.edu.pb.wi.ae.evolution.selection.TournamentSelection;
import pl.edu.pb.wi.ae.function.Function;

@SpringBootTest
public class SelectionTest {

    private PopulationValidator validator = new PopulationValidator();

    @Test
    void simpleBinaryPopulationSelectionTest() {
        PopulationConfig populationConfig = new PopulationConfig();
        populationConfig.setSize(25);

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

        EvolutionConfig evolutionConfig = new EvolutionConfig();
        evolutionConfig.setFunctionType(Function.Type.QUADRATIC);
        evolutionConfig.setOptimize(Function.Optimize.MIN);

        SelectionConfig selectionConfig = new SelectionConfig();
        selectionConfig.setTournamentSize(3);
        ///--------------------------------------------------------------------------------
        populationSelectionTest(evolutionConfig, populationConfig, chromosomeConfig, selectionConfig);
    }

    @Test
    void simpleFloatingPointPopulationSelectionTest() {
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

        EvolutionConfig evolutionConfig = new EvolutionConfig();
        evolutionConfig.setFunctionType(Function.Type.QUADRATIC);
        evolutionConfig.setOptimize(Function.Optimize.MAX);

        SelectionConfig selectionConfig = new SelectionConfig();
        selectionConfig.setTournamentSize(3);
        ///--------------------------------------------------------------------------------
        populationSelectionTest(evolutionConfig, populationConfig, chromosomeConfig, selectionConfig);
    }

    private void populationSelectionTest(EvolutionConfig evolutionConfig, PopulationConfig populationConfig, ChromosomeConfig chromosomeConfig, SelectionConfig selectionConfig) {
        Individual.IndividualBuilder individualBuilder = Individual.builder()
                .chromosomeConfig(chromosomeConfig);

        Population population = Population.builder()
                .size(populationConfig.getSize())
                .individualBuilder(individualBuilder)
                .buildPopulation();

        Function fitnessFunction = Function.builder()
                .functionType(evolutionConfig.getFunctionType())
                .optimize(evolutionConfig.getOptimize())
                .build();
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(fitnessFunction);
        Selection selection = new TournamentSelection(selectionConfig.getTournamentSize(), fitnessEvaluator);

        Population populationOfSelected = selection.select(population);

        population.sort(evolutionConfig.getOptimize());
        populationOfSelected.sort(evolutionConfig.getOptimize());

        System.out.println("Starting population");
        validator.validatePopulation(population, populationConfig, chromosomeConfig);

        System.out.println("Selected population");
        validator.validatePopulation(populationOfSelected, populationConfig, chromosomeConfig);

        validator.validateBoth(population, populationOfSelected);
    }

}
