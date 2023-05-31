package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.List;

/***
 * https://en.wikipedia.org/wiki/Test_functions_for_optimization
 * GLOBAL MINIMUM f(x1,...,xn)=f(0,...,0) = 0
 */
public class SphereFunction extends Function {
    public SphereFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        double sum = 0;
        List<Gene> genes = individual.getChromosome().getGenes();
        for(Gene gene : genes) {
            double Xi = gene.getPhenotype();
            sum += Xi*Xi;
        }
        return sum;
    }
}
