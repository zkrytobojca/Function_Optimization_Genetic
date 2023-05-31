package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import static java.lang.Math.pow;

/***
 * https://en.wikipedia.org/wiki/Test_functions_for_optimization
 * GLOBAL MINIMUM f(0,0) = 0
 */
public class ThreeHumpCamelFunction extends Function {
    public ThreeHumpCamelFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n > 2) {
            throw new InvalidConfigurationException("Three-hump camel function requires only 2 arguments!. Provided arguments = " + n);
        }
        Gene geneX = individual.getChromosome().getGenes().get(0);
        Gene geneY = individual.getChromosome().getGenes().get(1);
        double x = geneX.getPhenotype();
        double y = geneY.getPhenotype();
        return 2*pow(x, 2) - 1.05*pow(x, 4) + pow(x, 6)/6 + x*y + pow(y, 2);
    }
}
