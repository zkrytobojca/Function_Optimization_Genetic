package pl.edu.pb.wi.ae.evolution.gene.floatingpoint;

import pl.edu.pb.wi.ae.evolution.EvolutionInfo;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.tool.Formatter;

import java.util.Random;


public class FloatingPointGene extends Gene {
    private static final double C = 2;
    private Double value;

    public FloatingPointGene(Double minValue, Double maxValue, Double mutationChance) {
        super(minValue, maxValue, mutationChance);
        this.value = buildRandom();
    }

    public FloatingPointGene(Gene gene, Double value) {
        this((FloatingPointGene) gene);
        this.value = value;
    }

    private FloatingPointGene(FloatingPointGene floatingPointGene) {
        this.value = floatingPointGene.value;
        this.minValue = floatingPointGene.minValue;
        this.maxValue = floatingPointGene.maxValue;
        this.mutationChance = floatingPointGene.mutationChance;
    }

    @Override
    public String getGenotype() {
        //Long.toBinaryString((long) getPhenotype());
        return "";
    }

    @Override
    public double getPhenotype() {
        return this.value;
    }

    @Override
    public void mutate() {
        if (rand.nextDouble() < this.mutationChance) {
            double iteration = EvolutionInfo.getInstance().getIteration().doubleValue();
            double numberOfIterations = EvolutionInfo.getInstance().getNumberOfIterations().doubleValue();
            double r = rand.nextDouble();
            double delta = 1 - Math.pow(r, (1 - (iteration/numberOfIterations))*C);
            if (rand.nextDouble() < 0.5f) {
                Double y = this.maxValue - this.value;
                this.value = this.value + y * delta;
            } else {
                Double y = this.value - this.minValue;
                this.value = this.value - y * delta;
            }
        }
    }

    @Override
    public Gene clone() {
        return new FloatingPointGene(this);
    }

    @Override
    public String toString() {
        return "FloatingPointGene{" +
                "genotype='" + getGenotype() + '\'' +
                ", phenotype=" + Formatter.formatDouble(getPhenotype()) +
                '}';
    }

    public static void main(String[] args) {
        double r = new Random().nextDouble();
        double iteration = 1.0;
        double maxIter = 10.0;
        double x = Math.pow(r, C * (1 - iteration/maxIter));
        System.out.println(x);
        double delta = 1 - x;
        System.out.println(delta);
    }
}
