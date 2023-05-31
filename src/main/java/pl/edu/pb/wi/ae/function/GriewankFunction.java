package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

/***
 * https://en.wikipedia.org/wiki/Griewank_function
 */
public class GriewankFunction extends Function {

    public GriewankFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        double sum = 0;
        double product = 1;
        List<Gene> genes = individual.getChromosome().getGenes();
        int i = 1;
        for(Gene gene : genes) {
            double Xi = gene.getPhenotype();
            sum += Xi*Xi;
            product *= cos(Xi/sqrt(i));
            i++;
        }
        return 1 + (1f/4000) * sum - product;
    }
}
