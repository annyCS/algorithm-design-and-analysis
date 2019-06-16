package algVoraces;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class SystemWaitTime {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int n_customer = teclado.nextInt();
		
		Queue<Customer> queue = new PriorityQueue<Customer>();
		
		for (int i = 0; i < n_customer; i++) {
			double time = teclado.nextDouble();
			Customer c = new Customer(time);
			queue.add(c);
		}
		
		double result = solveGreedyAlgorithm(queue);
		System.out.println(result);

	}

	private static double solveGreedyAlgorithm(Queue<Customer> queue) {
		double totalTime = 0;
		double acum = 0;
		
		while( !queue.isEmpty() ) {
			Customer c = queue.poll();
			
			acum = c.getTime()+acum;	// time waited to be serviced
			totalTime += acum;			// total time
		}
		
		return totalTime;
	}

	public static class Customer implements Comparable<Customer> {
		private double time;
		
		public Customer(double time) {
			this.time = time;
		}
		
		public double getTime() {
			return this.time;
		}

		@Override
		public int compareTo(Customer c) {
			if (this.time < c.time) return -1;
			if (this.time > c.time) return 1;
			return 0;
		}
	}

}
