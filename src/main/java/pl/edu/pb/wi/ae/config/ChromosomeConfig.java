package pl.edu.pb.wi.ae.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.edu.pb.wi.ae.evolution.Gene;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "chromosome")
public class ChromosomeConfig {
    private Double mutationChance;
    private List<GeneConfig> geneConfigs;
    private Gene.Type type;

    public Integer getNumberOfGenes() {
        return geneConfigs.size();
    }

    public void addGeneConfig(GeneConfig geneConfig) {
        if (geneConfigs == null) {
            geneConfigs = new ArrayList<>();
        }
        geneConfigs.add(geneConfig);
    }

    public Double getMutationChance() {
        return mutationChance;
    }

    public void setMutationChance(Double mutationChance) {
        this.mutationChance = mutationChance;
    }

    public List<GeneConfig> getGeneConfigs() {
        return geneConfigs;
    }

    public void setGeneConfigs(List<GeneConfig> geneConfigs) {
        this.geneConfigs = geneConfigs;
    }

    public Gene.Type getType() {
        return type;
    }

    public void setType(Gene.Type type) {
        this.type = type;
    }
}
