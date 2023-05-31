package pl.edu.pb.wi.ae.validator;

import org.assertj.core.api.Assertions;
import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.evolution.Chromosome;
import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.evolution.Individual;
import pl.edu.pb.wi.ae.evolution.gene.binary.BinaryGene;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.FloatingPointGene;
import pl.edu.pb.wi.ae.tool.BinaryConverter;

import java.util.List;

public class IndividualValidator {

    public void validate(Individual individual, ChromosomeConfig chromosomeConfig) {
        switch (chromosomeConfig.getType()) {
            case BINARY:
                validateBinaryIndividual(individual, chromosomeConfig);
                return;
            case FLOATING_POINT:
                validateFloatingPointIndividual(individual, chromosomeConfig);
        }
    }

    private void validateBinaryIndividual(Individual individual, ChromosomeConfig chromosomeConfig) {
        //System.out.println("    " + individual);
        Assertions.assertThat(individual).isNotNull();

        Chromosome chromosome = individual.getChromosome();
        Assertions.assertThat(chromosome).isNotNull();

        List<Gene> genes = chromosome.getGenes();
        Assertions.assertThat(genes).isNotNull();
        Assertions.assertThat(genes).isNotEmpty();

        Assertions.assertThat(genes.size()).isEqualTo(chromosomeConfig.getNumberOfGenes());
        Assertions.assertThat(chromosomeConfig.getType()).isEqualTo(Gene.Type.BINARY);
        for (Gene gene : genes) {
            BinaryGene binaryGene = (BinaryGene)gene;
            Assertions.assertThat(binaryGene.getPhenotype()).isBetween(binaryGene.getMinValue(), binaryGene.getMaxValue());
            if (binaryGene.getMinValue() < 0 || binaryGene.getMaxValue() < 0) {
                Assertions.assertThat(binaryGene.getDetails().isSupportsNegative()).isTrue();
            } else {
                Assertions.assertThat(binaryGene.getDetails().isSupportsNegative()).isFalse();
            }
            Integer expectedPhenotype = BinaryConverter.binaryStringToInteger(binaryGene.getGenotype(), binaryGene.getDetails().isSupportsNegative());
            Integer realPhenotype = Double.valueOf(binaryGene.getPhenotype()).intValue();
            Assertions.assertThat(expectedPhenotype).isEqualTo(realPhenotype);

            String calculatedGenotype = BinaryConverter.integerToBinaryString(realPhenotype, binaryGene.getDetails().getGeneSize());
            Assertions.assertThat(binaryGene.getGenotype()).isEqualTo(calculatedGenotype);
            Assertions.assertThat(binaryGene.getGenotype().length()).isEqualTo(binaryGene.getDetails().getGeneSize());
        }
        System.out.println("\tgenotype: " + individual.getGenotype());
    }

    private void validateFloatingPointIndividual(Individual individual, ChromosomeConfig chromosomeConfig) {
        //System.out.println("    " + individual);
        Assertions.assertThat(individual).isNotNull();

        Chromosome chromosome = individual.getChromosome();
        Assertions.assertThat(chromosome).isNotNull();

        List<Gene> genes = chromosome.getGenes();
        Assertions.assertThat(genes).isNotNull();
        Assertions.assertThat(genes).isNotEmpty();

        Assertions.assertThat(genes.size()).isEqualTo(chromosomeConfig.getNumberOfGenes());
        Assertions.assertThat(chromosomeConfig.getType()).isEqualTo(Gene.Type.FLOATING_POINT);
        for (Gene gene : genes) {
            FloatingPointGene floatingPointGene = (FloatingPointGene)gene;
            Assertions.assertThat(floatingPointGene.getPhenotype())
                    .isBetween(floatingPointGene.getMinValue(), floatingPointGene.getMaxValue());
        }
        System.out.println("\tphenotype: " + individual.getPhenotype());
    }
}
