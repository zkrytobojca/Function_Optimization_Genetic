package pl.edu.pb.wi.ae.evolution;

public class EvolutionInfo {
    private static EvolutionInfo ourInstance = new EvolutionInfo();
    private Integer iteration;
    private Integer numberOfIterations;

    public static EvolutionInfo getInstance() {
        return ourInstance;
    }

    private EvolutionInfo() { }

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    public Integer getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(Integer numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }
}
