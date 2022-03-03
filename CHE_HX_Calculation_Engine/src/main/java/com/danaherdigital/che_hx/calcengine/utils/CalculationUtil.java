package com.danaherdigital.che_hx.calcengine.utils;


import org.apache.poi.ss.util.NumberToTextConverter;



public class CalculationUtil {

	private CalculationUtil(){}

	/**
	 * Round.
	 *
	 * @param n the n
	 * @param p the p
	 * @return the double
	 */
	public static double round(double n, int p) {
        return round(n, p, java.math.RoundingMode.HALF_UP);
    }

	/**
	 * Round.
	 *
	 * @param n the n
	 * @param p the p
	 * @param rounding the rounding
	 * @return the double
	 */
	private static double round(double n, int p, java.math.RoundingMode rounding) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            return Double.NaN;
        }
        else {
            final String excelNumber = NumberToTextConverter.toText(n);
            return new java.math.BigDecimal(excelNumber).setScale(p, rounding).doubleValue();
        }
    }


}
