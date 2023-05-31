package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import java.util.List;

import static java.lang.Math.pow;

/***
 * https://en.wikipedia.org/wiki/Rosenbrock_function
 * GLOBAL MINIMUM f(1, 1) = 0
 */
public class RosenbrockFunction extends Function {
    public RosenbrockFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n < 2) {
            throw new InvalidConfigurationException("Rosenbrock function requires at least 2 arguments!. Provided arguments = " + n);
        }
        double sum = 0;
        List<Gene> genes = individual.getChromosome().getGenes();
        for(int i = 0; i < n-1; i++) {
            Gene geneXi = genes.get(i);
            Gene geneXiplusOne = genes.get(i+1);
            double Xi = geneXi.getPhenotype();
            double XiplusOne = geneXiplusOne.getPhenotype();
            sum += 100*pow((XiplusOne - Xi*Xi), 2) + pow((1 - Xi), 2);
        }
        return sum;
    }
}
