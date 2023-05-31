package pl.edu.pb.wi.ae.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.edu.pb.wi.ae.evolution.crossover.Crossover;
import pl.edu.pb.wi.ae.function.Function;

@Configuration
@ConfigurationProperties(prefix = "evolution")
public class EvolutionConfig {
    private Integer iterations;
    private Function.Optimize optimize;
    private Double crossoverChance;
    private Crossover.Type crossoverType;
    private Function.Type functionType;

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Function.Optimize getOptimize() {
        return optimize;
    }

    public void setOptimize(Function.Optimize optimize) {
        this.optimize = optimize;
    }

    public Double getCrossoverChance() {
        return crossoverChance;
    }

    public void setCrossoverChance(Double crossoverChance) {
        this.crossoverChance = crossoverChance;
    }

    public Crossover.Type getCrossoverType() {
        return crossoverType;
    }

    public void setCrossoverType(Crossover.Type crossoverType) {
        this.crossoverType = crossoverType;
    }

    public Function.Type getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Function.Type functionType) {
        this.functionType = functionType;
    }
}
