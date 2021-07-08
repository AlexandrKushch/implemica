package com.kushch.implemica;

import java.math.BigInteger;

public class Factorial {
	public static void main(String[] args) {
		int num = 100;
		BigInteger factorial = BigInteger.ONE;	// For big number we need BigInteger
		
		// Calculating factorial.
		for(int i = 2; i <= num; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}
		
		System.out.println(factorial);

		String digits = factorial.toString();
		
		int sum = 0;
		
		for(int i = 0; i < digits.length(); i++) {
			// Adding to sum digits which are taken from string and converted to int.
			sum += Character.getNumericValue(digits.charAt(i));
		}
		
		System.out.println("Result: " + sum); // Printing result.
	}
}
