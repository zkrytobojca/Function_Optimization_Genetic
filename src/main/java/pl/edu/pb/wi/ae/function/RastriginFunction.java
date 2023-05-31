package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;

import java.util.List;

/***
 * https://en.wikipedia.org/wiki/Rastrigin_function
 * GLOBAL MINIMUM f(0, ..., 0) = 0
 */
public class RastriginFunction extends Function {
    public RastriginFunction(Optimize optimize) {
        super(optimize);
    }

    @Override
    public double calculate(Individual individual) {
        final double A = 10;
        int n = individual.getChromosome().getLength();
        double sum = 0;
        List<Gene> genes = individual.getChromosome().getGenes();
        for(Gene gene : genes) {
            double Xi = gene.getPhenotype();
            sum += Xi*Xi - A*Math.cos(2*Math.PI*Xi);
        }
        return A*n + sum;
    }
}
