package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.exception.InvalidConfigurationException;

import static java.lang.Math.pow;

/***
 * https://en.wikipedia.org/wiki/Himmelblau%27s_function
 */
public class HimmelblauFunction extends Function {
    public HimmelblauFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        int n = individual.getChromosome().getLength();
        if (n > 2) {
            throw new InvalidConfigurationException("Himmelblau's function requires only 2 arguments!. Provided arguments = " + n);
        }
        Gene geneX = individual.getChromosome().getGenes().get(0);
        Gene geneY = individual.getChromosome().getGenes().get(1);
        double x = geneX.getPhenotype();
        double y = geneY.getPhenotype();
        return pow((x*x + y -11), 2) + pow((x + y*y - 7), 2);
    }
}
