package pl.edu.pb.wi.ae.evolution;

import pl.edu.pb.wi.ae.config.ChromosomeConfig;
import pl.edu.pb.wi.ae.config.GeneConfig;
import pl.edu.pb.wi.ae.evolution.gene.binary.BinaryGene;
import pl.edu.pb.wi.ae.evolution.gene.floatingpoint.FloatingPointGene;
import pl.edu.pb.wi.ae.tool.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {
    private List<Gene> genes;

    public Chromosome() {
        this.genes = new ArrayList<>();
    }

    public Chromosome(ChromosomeConfig chromosomeConfig) {
        this.genes = new ArrayList<>(chromosomeConfig.getNumberOfGenes());
        Gene.Type geneType = chromosomeConfig.getType();
        for (GeneConfig geneConfig : chromosomeConfig.getGeneConfigs()) {
            Gene gene = null;
            switch (geneType) {
                case BINARY:
                    BinaryGene.Details binaryGeneDetails = evaluateBinaryGeneDetails(
                            geneConfig.getMinValue().intValue(),
                            geneConfig.getMaxValue().intValue());
                    gene = new BinaryGene(
                            geneConfig.getMinValue(),
                            geneConfig.getMaxValue(),
                            chromosomeConfig.getMutationChance(),
                            binaryGeneDetails);
                    break;
                case FLOATING_POINT:
                    gene = new FloatingPointGene(
                            geneConfig.getMinValue(),
                            geneConfig.getMaxValue(),
                            chromosomeConfig.getMutationChance());
            }
            genes.add(gene);
        }
    }

    public Chromosome(Chromosome chromosome) {
        this.genes = new ArrayList<>(chromosome.genes.size());
        for (Gene gene : chromosome.genes) {
            this.genes.add(gene.clone());
        }
    }

    public void mutate() {
        for (Gene gene : genes) {
            gene.mutate();
        }
    }

    private BinaryGene.Details evaluateBinaryGeneDetails(Integer minRange, Integer maxRange) {
        BinaryGene.Details details = new BinaryGene.Details();
        if (minRange == 0 && maxRange == 0) { //"0" length = 1
            details.setSupportsNegative(false);
            details.setGeneSize(1);
            return details;
        }
        if (minRange < 0 || maxRange < 0) {
            details.setSupportsNegative(true);
        }
        int len1 = details.isSupportsNegative() ? 1 : 0;
        while (maxRange > 0) {
            len1++;
            maxRange /= 2;
        }
        int len2 = details.isSupportsNegative() ? 1 : 0;
        while (minRange < 0) {
            len2++;
            minRange /= 2;
        }
        int size = len1 > len2 ? len1 : len2;
        details.setGeneSize(size);
        return details;
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public void addGene(Gene gene) {
        this.genes.add(gene);
    }

    public int getLength() {
        return this.genes.size();
    }

    @Override
    public String toString() {
        return "Chromosome{" +
                "genes=" + genes +
                '}';
    }

    public String getGenotype() {
        StringBuilder sb = new StringBuilder("<");
        for (Gene gene : genes) {
            sb.append(gene.getGenotype()).append("-");
        }
        sb.setLength(sb.length() - 1);
        sb.append(">");
        return sb.toString();
    }

    public String getPhenotype() {
        StringBuilder sb = new StringBuilder("<");
        for (Gene gene : genes) {
            String phenotypeStr = Formatter.formatDouble(gene.getPhenotype());
            sb.append(phenotypeStr).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(">");
        return sb.toString();
    }
}
