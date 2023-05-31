package pl.edu.pb.wi.ae.validator;

import org.assertj.core.api.Assertions;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.PopulationConfig;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.Population;

public class PopulationValidator {
    private IndividualValidator validator = new IndividualValidator();

    public void validatePopulation(Population population, PopulationConfig populationConfig, ChromosomeConfig chromosomeConfig) {
        Assertions.assertThat(population).isNotNull();

        Assertions.assertThat(population.getIndividuals()).isNotNull();
        Assertions.assertThat(population.getIndividuals()).isNotEmpty();
        Assertions.assertThat(population.getIndividuals().size()).isEqualTo(populationConfig.getSize());

        for (Individual individual : population.getIndividuals()) {
            validator.validate(individual, chromosomeConfig);
        }
    }

    public void validateBoth(Population population, Population populationOfSelected) {
        Assertions.assertThat(populationOfSelected).isNotNull();

        Assertions.assertThat(populationOfSelected.getIndividuals()).isNotNull();
        Assertions.assertThat(populationOfSelected.getIndividuals()).isNotEmpty();
        Assertions.assertThat(populationOfSelected.getIndividuals().size()).isEqualTo(population.getIndividuals().size());

        int count = 0;
        for (int i = 0; i < population.getIndividuals().size(); i++) {
            Individual old = population.getIndividual(i);
            Individual selected = populationOfSelected.getIndividual(i);
            if (!selected.getPhenotype().equals(old.getPhenotype())) { //to sa inne osobniki, a wiec select zadzialal
                count++;
            }
        }
        System.out.println("New individuals in selected population = " + count);
    }
}
