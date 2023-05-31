package pl.edu.pb.wi.ae.evolution.selection;

import pl.edu.pb.wi.ae.evolution.FitnessEvaluator;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentSelection implements Selection {
    private Integer tournamentSize;
    private FitnessEvaluator fitnessEvaluator;

    public TournamentSelection(Integer tournamentSize, FitnessEvaluator fitnessEvaluator) {
        this.tournamentSize = tournamentSize;
        this.fitnessEvaluator = fitnessEvaluator;
    }

    @Override
    public Population select(Population population) {
        int populationSize = population.getIndividuals().size();
        Population newPopulation = new Population(populationSize);
        List<Integer> range = IntStream.range(0, populationSize).boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        this.fitnessEvaluator.calculate(population);

        for (int i = 0; i < populationSize; i++) {
            Collections.shuffle(range);
            Individual selected = matchUpForSelection(population, range);
            newPopulation.addIndividual(selected);
        }
        return newPopulation;
    }

    private Individual matchUpForSelection(Population population, List<Integer> range) {
        Individual winner = null;
        int counter = 0;
        for (Integer randomIndex : range) {
            if (counter >= this.tournamentSize) {
                break;
            }
            Individual individual = population.getIndividual(randomIndex);
            if (winner == null) {
                winner = individual;
            } else {
                winner = this.fitnessEvaluator.getBetter(winner, individual);
            }
            counter++;
        }
        if (winner == null) {
            return null;
        }
        return new Individual(winner);
    }

    public static void main(String[] args) {
        int populationsize = 5;
        int tournamentSize = 10;
        List<Integer> range = IntStream.range(0, populationsize).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        range.subList(0, tournamentSize).forEach(System.out::println);
    }
}
