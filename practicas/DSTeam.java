package practicas;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class DSTeam {
	
	static final String LIGERO = "ligero";
	static final String MEDIO = "medio";
	static final String PESADO = "pesado";
	
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		int nPieces = teclado.nextInt();
		double maxWeight = teclado.nextDouble();
		String mode = teclado.next();
		
		maxWeight = Armor.adjustWeight(maxWeight, mode);
		
		Queue<Armor> queue = new PriorityQueue<Armor>();

		for (int i = 0; i < nPieces; i++) {
			String name = teclado.next();
			double weight = teclado.nextDouble();
			double protection = teclado.nextDouble();
			
			Armor a = new Armor(name, weight, protection);
			queue.add(a);
		}
		
		resolveGreedyAlgorithm(queue, maxWeight, mode);
		
	}
	
	private static void resolveGreedyAlgorithm(Queue<Armor> queue, double maxWeight, String mode) {
		
		double acumWeight = 0;
		double acumProtection = 0;
		Queue<String> colaNombres = new PriorityQueue<String>();
		
		while ( !queue.isEmpty() && acumWeight < maxWeight) {
			Armor a = queue.poll();
			
			if (acumWeight + a.getWeight() > maxWeight) {		// sobre pasa peso mochila, se parte el objeto
				acumProtection += ((maxWeight - acumWeight) * a.getProtection() ) / a.getWeight();
			}
			else {
				acumWeight += a.getWeight();
				acumProtection += a.getProtection();
			}
			
			colaNombres.add(a.getName());
		}
		
		System.out.println(String.format("%.2f", acumProtection));
		while( !colaNombres.isEmpty() ) {
			System.out.println(colaNombres.remove());
		}
	}

	public static class Armor implements Comparable<Armor> {

		private String name;
		private double weight;
		private double protection;
		
		public Armor(String name, double weight, double value) {
			this.name = name;
			this.weight = weight;
			this.protection = value;
		}
		
		public String getName() {
			return name;
		}
		
		public double getWeight() {
			return weight;
		}
		
		public double getProtection() {
			return protection;
		}
		
		@Override
		public int compareTo(Armor a) {
			if (this.protection/this.weight > a.protection/this.weight) return -1;
			if (this.protection/this.weight < a.protection/this.weight) return 1;
			return 0;
		}
		
		public static double adjustWeight(double weight, String mode) {
			if (mode.equals(LIGERO)) return weight * 0.5; 
			if (mode.equals(MEDIO)) return weight * 0.75;
			return weight;
		}
		
	}

}
