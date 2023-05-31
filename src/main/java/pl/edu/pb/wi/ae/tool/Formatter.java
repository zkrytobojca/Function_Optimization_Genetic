package pl.edu.pb.wi.ae.tool;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Formatter {
    private static final DecimalFormat DF = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    static {
        DF.setMaximumFractionDigits(12);
    }

    public static String formatDouble(double value) {
        return DF.format(value);
    }
}
