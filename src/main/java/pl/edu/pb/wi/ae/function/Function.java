package pl.edu.pb.wi.ae.function;

import pl.edu.pb.wi.ae.evolution.Individual;

public abstract class Function {
    private Function.Optimize optimize;

    public enum Type { QUADRATIC, RASTRIGIN, ACKLEY, GRIEWANK, ROSENBROCK, BEALE, BOOTH, MATYAS, SPHERE, THREE_HUMP_CAMEL, HIMMELBLAU }
    public enum Optimize { MIN, MAX }

    public Function(Optimize optimize) {
        this.optimize = optimize;
    }

    public static FunctionBuilder builder() {
        return new FunctionBuilder();
    }

    public static class FunctionBuilder {
        private Function.Type type;
        private Function.Optimize optimize;

        public FunctionBuilder functionType(Function.Type type) {
            this.type = type;
            return this;
        }

        public FunctionBuilder optimize(Function.Optimize optimize) {
            this.optimize = optimize;
            return this;
        }

        public Function build() {
            switch (this.type) {
                case QUADRATIC:
                    return new QuadraticFunction(this.optimize);
                case RASTRIGIN:
                    return new RastriginFunction(this.optimize);
                case ACKLEY:
                    return new AckleyFunction(this.optimize);
                case GRIEWANK:
                    return new GriewankFunction(this.optimize);
                case ROSENBROCK:
                    return new RosenbrockFunction(this.optimize);
                case BEALE:
                    return new BealeFunction(this.optimize);
                case BOOTH:
                    return new BoothFunction(this.optimize);
                case MATYAS:
                    return new MatyasFunction(this.optimize);
                case SPHERE:
                    return new SphereFunction(this.optimize);
                case THREE_HUMP_CAMEL:
                    return new ThreeHumpCamelFunction(this.optimize);
                case HIMMELBLAU:
                    return new HimmelblauFunction(this.optimize);
                //TODO inne funkcje
            }
            return null;
        }
    }

    public abstract double calculate(Individual individual);

    public Optimize getOptimize() {
        return optimize;
    }
}
