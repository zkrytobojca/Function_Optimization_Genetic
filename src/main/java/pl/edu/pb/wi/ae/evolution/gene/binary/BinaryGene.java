package pl.edu.pb.wi.ae.evolution.gene.binary;

import pl.edu.pb.wi.ae.evolution.Gene;
import pl.edu.pb.wi.ae.tool.BinaryConverter;
import pl.edu.pb.wi.ae.tool.Formatter;

public class BinaryGene extends Gene {
    private String stringValue;
    private Integer intValue;
    private Details details;

    public BinaryGene(Double minValue, Double maxValue, Double mutationChance, Details details) {
        super(minValue, maxValue, mutationChance);
        this.details = details;
        Double randomValue = buildRandom();
        this.intValue = randomValue.intValue();
        this.stringValue = BinaryConverter.integerToBinaryString(randomValue.intValue(), details.geneSize);
    }

    public BinaryGene(Gene gene, String stringValue) {
        this((BinaryGene) gene);
        this.stringValue = stringValue;
        this.intValue = BinaryConverter.binaryStringToInteger(stringValue, details.supportsNegative);
    }

    private BinaryGene(BinaryGene binaryGene) {
        this.stringValue = binaryGene.stringValue;
        this.intValue = binaryGene.intValue;
        this.minValue = binaryGene.minValue;
        this.maxValue = binaryGene.maxValue;
        this.mutationChance = binaryGene.mutationChance;
        this.details = new BinaryGene.Details(binaryGene.details);
    }

    public static class Details {
        private boolean supportsNegative;
        private int geneSize;

        public Details() {
        }

        public Details(Details details) {
            this.supportsNegative = details.supportsNegative;
            this.geneSize = details.geneSize;
        }

        public boolean isSupportsNegative() {
            return supportsNegative;
        }

        public int getGeneSize() {
            return geneSize;
        }

        public void setSupportsNegative(boolean supportsNegative) {
            this.supportsNegative = supportsNegative;
        }

        public void setGeneSize(int geneSize) {
            this.geneSize = geneSize;
        }

        @Override
        public String toString() {
            return "Details{" +
                    "supportsNegative=" + supportsNegative +
                    ", geneSize=" + geneSize +
                    '}';
        }
    }

    @Override
    public String getGenotype() {
        return this.stringValue;
    }

    @Override
    public double getPhenotype() {
        return this.intValue;
    }

    @Override
    public void mutate() {
        boolean mutated = false;
        StringBuilder sb = new StringBuilder(stringValue);
        for (int i = 0; i < this.stringValue.length(); i++) {
            double r = rand.nextDouble();
            if (r < super.mutationChance) {
                mutated = true;
                int editedBit = Character.getNumericValue(stringValue.charAt(i));
                editedBit = (editedBit + 1) % 2;
                sb.setCharAt(i, (char)(editedBit + '0'));
            }
        }
        if (mutated) {
            this.stringValue = sb.toString();
            this.intValue = BinaryConverter.binaryStringToInteger(this.stringValue, this.details.supportsNegative);
            checkAndAdjustToLimits();
        }
    }

    public void checkAndAdjustToLimits() {
        double phenotype = getPhenotype();
        if (phenotype > maxValue) {
            this.intValue = maxValue.intValue();
            this.stringValue = BinaryConverter.integerToBinaryString(maxValue.intValue(), details.geneSize);
        } else if  (phenotype < minValue) {
            this.intValue = minValue.intValue();
            this.stringValue = BinaryConverter.integerToBinaryString(minValue.intValue(), details.geneSize);
        }
    }

    public Details getDetails() {
        return details;
    }

    @Override
    public Gene clone() {
        return new BinaryGene(this);
    }

    @Override
    public String toString() {
        return "BinaryGene{" +
                "genotype='" + getGenotype() + '\'' +
                ", phenotype=" + Formatter.formatDouble(getPhenotype()) +
                ", details=" + details +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(-4));
        System.out.println(new Double(-2.6).intValue());
    }
}


