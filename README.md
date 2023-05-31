# Function_Optimization_Genetic
This is an import from my old GitLab repository. 

## About
This application was created in Java version 8 and based on the Spring Boot framework. It supports two types of genetic algorithms:
1) standard genetic algorithm (Goldberg),
2) algorithm using chromosomes with floating point representation (Michalewicz).

The general flow of the genetic algorithm is shown in the figure below. For this application, the condition for stopping the algorithm is to complete a defined number of operations.

![Diagram](https://github.com/zkrytobojca/Function_Optimization_Genetic/assets/49489021/a982d664-3b48-4237-a720-d2bd951ddcfb)

The application allows you to set various parameters of the evolutionary algorithm such as:
- chromosome type - binary or floating-point,
- mutation chance in the range of 0.0 to 1.0,
- the number of genes that represent successive parameters of a multivariable function along with a lower and upper bound,
- population size,
- tournament size 𝑞 for tournament type selection,
- method of optimizing functions of many variables - determines whether the algorithm should search for the global minimum or maximum of this function,
- number of iterations,
- the chance of crossing two individuals in range from 0.0 to 1.0,
- the type of crossing depending on the type of chromosome:
  - one-point (binary)
  - uniform (binary)
  - simple arithmetic (floating-point)
  - single arithmetic (floating-point)
- 10 different multi-variable functions for testing

All these parameters can be set in the configuration file - application.properties.

## Technologies used
- Java 8
- Spring Boot framework
