package pl.edu.pb.wi.ae.tool;

public class BinaryConverter {
    public static String integerToBinaryString(Integer intValue, Integer geneSize) {
        String binary = Long.toBinaryString(intValue);
        if (intValue >= 0) {
            StringBuilder prefix = new StringBuilder();
            if (binary.length() < geneSize) {
                for (int i = 0; i < geneSize - binary.length(); i++) {
                    prefix.append("0");
                }
            }
            return prefix + binary;
        } else {
            return binary.substring(binary.length() - geneSize);
        }
    }

    public static Integer binaryStringToInteger(String stringValue, boolean isSupportsNegative) {
        if (stringValue.length() == 1) {
            return Integer.valueOf(stringValue); //0 lub 1
        }
        int phenotype = 0;
        int valueOfBit = 1;
        for (int i = stringValue.length()-1; i >= 1; i--) {
            if (stringValue.charAt(i) == '1') {
                phenotype += valueOfBit;
            }
            valueOfBit *= 2;
        }
        if (stringValue.charAt(0) == '1' && isSupportsNegative) {
            phenotype -= valueOfBit;
        } else if (stringValue.charAt(0) == '1' && !isSupportsNegative) {
            phenotype += valueOfBit;
        }
        return phenotype;
    }
}
