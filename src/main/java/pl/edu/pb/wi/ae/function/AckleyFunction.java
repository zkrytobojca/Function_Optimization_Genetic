package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import static java.lang.Math.*;

/***
 * https://en.wikipedia.org/wiki/Ackley_function
 * GLOBAL MINIMUM f(0, 0) = 0
 */
public class AckleyFunction extends Function {

    public AckleyFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n > 2) {
            throw new InvalidConfigurationException("Ackley function requires only 2 arguments!. Provided arguments = " + n);
        }
        Gene geneX = individual.getChromosome().getGenes().get(0);
        Gene geneY = individual.getChromosome().getGenes().get(1);
        double x = geneX.getPhenotype();
        double y = geneY.getPhenotype();
        return -20 * exp(-0.2 * sqrt(0.5*(x*x + y*y))) - exp(0.5*(cos(2*PI*x) + cos(2*PI*y))) + E + 20;
    }
}
