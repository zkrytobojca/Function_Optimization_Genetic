package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

/***
 * https://en.wikipedia.org/wiki/Test_functions_for_optimization
 * GLOBAL MINIMUM f(0,0) = 0
 */
public class MatyasFunction extends Function {
    public MatyasFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n > 2) {
            throw new InvalidConfigurationException("Matyas function requires only 2 arguments!. Provided arguments = " + n);
        }
        Gene geneX = individual.getChromosome().getGenes().get(0);
        Gene geneY = individual.getChromosome().getGenes().get(1);
        double x = geneX.getPhenotype();
        double y = geneY.getPhenotype();
        return 0.26*(x*x + y*y) - 0.48*x*y;
    }
}
