package algVoraces;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class CurrencyProblem {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int num_currency = teclado.nextInt();
		double change = teclado.nextDouble();
		
		Queue<Currency> queue= new PriorityQueue<Currency>(); 
		
		for (int i = 0; i < num_currency; i++) {
			double value = teclado.nextDouble();
			Currency c = new Currency(value);
			queue.add(c);
		}
		
		List<Currency> result = solveGreedyAlgorithm(queue, change);
		printResult(result);
	}
	
	private static List<Currency> solveGreedyAlgorithm(Queue<Currency> queue, double change) {
		List<Currency> result = new ArrayList<Currency>();
		double sum = 0;		
		
		while ( !queue.isEmpty() && sum < change) {
			Currency c = queue.poll();
			
			if ( isFeasible(change, sum, c) ) {
				sum += c.getValue();
				result.add(c);
			}
		}
		
		return result;
	}

	private static boolean isFeasible(double change, double sum, Currency c) {
		return (sum + c.getValue()) <= change;
	}

	private static void printResult(List<Currency> result) {
		System.out.println(">Max: " + result.size());
		System.out.print("> ");
		
		for (Currency c: result) {
			System.out.print(c.getValue() + " ");
		}
	}

	public static class Currency implements Comparable<Currency> {

		private double value;
	
		public Currency(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
		
		@Override
		public int compareTo(Currency c) {
			if (this.value > c.value) return -1;
			if (this.value < c.value) return 1;
			return 0;
		}
		
	}

}
