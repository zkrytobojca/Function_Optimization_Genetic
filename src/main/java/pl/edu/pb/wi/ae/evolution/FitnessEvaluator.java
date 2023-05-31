package pl.edu.pb.wi.ae.evolution;

import pl.edu.pb.wi.ae.function.Function;

public class FitnessEvaluator {
    private Function fitnessFunction;

    public FitnessEvaluator(Function fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public void calculate(Population population) {
        for (Individual individual : population.getIndividuals()) {
            double fitnessValue = fitnessFunction.calculate(individual);
            individual.setFitnessValue(fitnessValue);
        }
    }

    public Individual getTheBestIndividual(Population population) {
        Individual theBest = population.getIndividuals().get(0);
        for (Individual individual : population.getIndividuals()) {
            theBest = getBetter(theBest, individual);
        }
        return theBest;
    }

    public Individual getBetter(Individual individual1, Individual individual2) {
        if (this.fitnessFunction.getOptimize() == Function.Optimize.MAX) {
            if (individual1.compareTo(individual2) < 0) {
                return individual2;
            }
        } else if (this.fitnessFunction.getOptimize() == Function.Optimize.MIN) {
            if (individual1.compareTo(individual2) > 0) {
                return individual2;
            }
        }
        return individual1;
    }
}
