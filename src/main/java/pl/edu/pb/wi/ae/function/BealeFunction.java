package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import static java.lang.Math.pow;

/***
 * https://en.wikipedia.org/wiki/Test_functions_for_optimization
 * GLOBAL MINIMUM f(3, 0.5) = 0
 */
public class BealeFunction extends Function {

    public BealeFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n > 2) {
            throw new InvalidConfigurationException("Beale function requires only 2 arguments!. Provided arguments = " + n);
        }
        Gene geneX = individual.getChromosome().getGenes().get(0);
        Gene geneY = individual.getChromosome().getGenes().get(1);
        double x = geneX.getPhenotype();
        double y = geneY.getPhenotype();
        return pow((1.5 - x + x*y), 2) + pow((2.25 - x + x*y*y), 2) + pow((2.625 - x + x*y*y*y), 2);
    }
}