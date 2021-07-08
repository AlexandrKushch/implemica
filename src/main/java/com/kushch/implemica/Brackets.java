package com.kushch.implemica;

import java.util.Scanner;

public class Brackets {
	
	/*
	 * To calculate count of "Right brackets" we can use Catalan number.
	 * Formula is:
	 * 		From i=0 till n-1 when n >= 1
	 * 		C[n] = C[i] * C[n1-i]
	 * 		https://neerc.ifmo.ru/wiki/index.php?title=%D0%A7%D0%B8%D1%81%D0%BB%D0%B0_%D0%9A%D0%B0%D1%82%D0%B0%D0%BB%D0%B0%D0%BD%D0%B0
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter N: ");
		int n = in.nextInt() + 1; // Plus first element C0
		in.close();
		
		int c[] = new int[n];
		c[0] = 1;
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {	// We calculating every element of array. C1 C2 C3 till Cn.
				c[i] += c[j] * c[i - 1 - j];
			}
		}
		
		
		System.out.println("Output: " + c[n - 1]);
	}
}
