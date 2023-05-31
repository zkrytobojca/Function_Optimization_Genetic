package pl.edu.pb.wi.ae.evolution;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.EvolutionConfig;
import pl.edu.pb.wi.ae.config.PopulationConfig;
import pl.edu.pb.wi.ae.config.SelectionConfig;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.evolution.selection.Selection;
import pl.edu.pb.wi.ae.evolution.selection.TournamentSelection;
import pl.edu.pb.wi.ae.function.Function;
import pl.edu.pb.wi.ae.tool.Formatter;

@Service
public class EvolutionManager {

    private EvolutionConfig evolutionConfig;
    private PopulationConfig populationConfig;
    private ChromosomeConfig chromosomeConfig;
    private SelectionConfig selectionConfig;

    @Autowired
    public EvolutionManager(EvolutionConfig evolutionConfig, PopulationConfig populationConfig, ChromosomeConfig chromosomeConfig, SelectionConfig selectionConfig) {
        this.evolutionConfig = evolutionConfig;
        this.populationConfig = populationConfig;
        this.chromosomeConfig = chromosomeConfig;
        this.selectionConfig = selectionConfig;
    }

    private Population population;
    private Selection selection;
    private Crossover crossover;
    private FitnessEvaluator fitnessEvaluator;

    public void initialize() {
        Individual.IndividualBuilder individualBuilder = Individual.builder()
                .chromosomeConfig(chromosomeConfig);

        this.population = Population.builder()
                .size(populationConfig.getSize())
                .individualBuilder(individualBuilder)
                .buildPopulation();
        this.crossover = Crossover.builder()
                .geneType(chromosomeConfig.getType())
                .crossoverType(evolutionConfig.getCrossoverType())
                .crossoverChance(evolutionConfig.getCrossoverChance())
                .build();
        Function fitnessFunction = Function.builder()
                .functionType(evolutionConfig.getFunctionType())
                .optimize(evolutionConfig.getOptimize())
                .build();
        this.fitnessEvaluator = new FitnessEvaluator(fitnessFunction);
        this.selection = new TournamentSelection(
                selectionConfig.getTournamentSize(),
                fitnessEvaluator);
    }

    public void start() {
        Integer iterations = evolutionConfig.getIterations();
        EvolutionInfo.getInstance().setNumberOfIterations(iterations);
        for (int i = 1; i <= iterations; i++) {
            EvolutionInfo.getInstance().setIteration(i);

            Population populationOfSelected = selection.select(population);

            Population populationOfChildren = crossover.crossPopulation(populationOfSelected);

            for (Individual individual : populationOfChildren.getIndividuals()) {
                individual.mutate();
            }
            population = populationOfChildren;
        }
        fitnessEvaluator.calculate(population);
        //population.sort(evolutionConfig.getOptimize());
        //System.out.println(population);

        Individual theBest = fitnessEvaluator.getTheBestIndividual(population);
        System.out.println("***The best individual***\n" + theBest);
        double bestFitness = theBest.getFitnessValue();
        //System.out.println(bestFitness+"\n");
        System.out.println("***The best fitness = " + Formatter.formatDouble(bestFitness));
    }
}