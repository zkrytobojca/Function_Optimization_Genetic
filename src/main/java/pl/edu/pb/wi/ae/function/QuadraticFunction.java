package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.List;

/***
 * Only for test purposes
 */
@Deprecated
public class QuadraticFunction extends Function {

    public QuadraticFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        double result = 0;
        List<Gene> genes = individual.getChromosome().getGenes();
        for(Gene gene : genes) {
            double value = gene.getPhenotype();
            result += value * value;
        }
        return result;
    }
}
