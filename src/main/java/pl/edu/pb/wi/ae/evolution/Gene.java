package pl.edu.pb.wi.ae.evolution;

import java.util.Random;

public abstract class Gene {
    public enum Type {
        BINARY, FLOATING_POINT
    }

    protected final Random rand = new Random();
    protected Double minValue;
    protected Double maxValue;
    protected Double mutationChance;

    public Gene() {
    }

    public Gene(Double minValue, Double maxValue, Double mutationChance) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.mutationChance = mutationChance;
    }

    protected Double buildRandom() {
        double r = rand.nextDouble();
        return minValue + r * (maxValue - minValue);
    }

    public abstract String getGenotype();
    public abstract double getPhenotype();
    public abstract void mutate();
    public abstract Gene clone();

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }
}
