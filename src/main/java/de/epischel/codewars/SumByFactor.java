package de.epischel.codewars;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class SumByFactor {

	/** naive implementation */
	private static int[] calcPrimes(final int max) {
		if (max < 2)
			return new int[] {};
		final ArrayList<Integer> store = new ArrayList<>();
		store.add(2);
		outer: for (int i = 3; i <= max; i++) {
			for (int j = 2; j < ((int) Math.sqrt(i) + 1); j++) {
				if ((i % j) == 0) continue outer;
			}
			store.add(i);
		}
		return store.stream().mapToInt(Integer::intValue).toArray();
	}
	
	private static int[] calcPrimes2(final int max) {
		if (max < 2)
			return new int[] {};
		final ArrayList<Integer> store = new ArrayList<>();
		store.add(2);
		outer: for (int i = 3; i <= max; i++) {
			for (int j = 2; j < ((int) Math.sqrt(i) + 1); j++) {
				if ((i % j) == 0) continue outer;
			}
			store.add(i);
		}
		final int[] result = new int[store.size()];
		int counter = 0;
		for(Integer in : store) result[counter++]=in.intValue();
		return result;
	}


	private static int findAbsMax(final int[] l) {
		return IntStream.of(l).reduce((a, b) -> Math.max(Math.abs(a), Math.abs(b))).getAsInt();
	}

	public static String sumOfDivided(int[] lst) {
		int[] primes = calcPrimes(findAbsMax(lst));
		final StringBuilder result = new StringBuilder();
		for (int prime : primes) {
			int sum = 0, count = 0;
			for (int l : lst) {
				if (l % prime == 0) {
					sum += l;
					count++;
				}
			}
			if (count > 0) {
				result.append("(").append(prime).append(" ").append(sum).append(")");
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println(sumOfDivided(new int[] { 12, 14 }));
		System.out.println(sumOfDivided(new int[] { 15, 21, 24, 30, 45 }));
		System.out.println(sumOfDivided(new int[] { 107, 158, 204, 100, 118, 123, 126, 110, 116, 100 }));
		System.out.println(sumOfDivided(new int[] { -29804, -4209, -28265, -72769, -31744 }));
		System.out.println(sumOfDivided(new int[] { 17, -17, 51, -51 }));
		System.out.println(sumOfDivided(new int[] { 173471 }));
		System.out.println(sumOfDivided(new int[] { 100000, 1000000 }));
		System.out.println(sumOfDivided(new int[] { 366, 141, 152, 154, 489, 139, 329, 301, -95, 61, 348, 389, 357 }));
		System.out.println(sumOfDivided(new int[] { 311, 421, 2, 412, 422, 436, 224, 327, 160, 297, 300, 338, 192, 364,
				84, 119, 294, 213, -5, 15, 28 }));
		
		System.out.println(calcPrimes(22)[5]);

		System.out.println((System.currentTimeMillis() - time));
		
		time = System.currentTimeMillis();
		calcPrimes(1000000);
		System.out.println((System.currentTimeMillis() - time));

		time = System.currentTimeMillis();
		calcPrimes2(1000000);
		System.out.println((System.currentTimeMillis() - time));
	}

}
