package backtraking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack {

	static Scanner teclado = new Scanner(System.in);
	static int maxWeight = 0;
	static int maxValue = 0;
	static int bestValue = 0;
	
	public static void main(String[] args) {
		int num = teclado.nextInt();
		int totalWeight = teclado.nextInt();
		
		List<Data> list = new ArrayList<Data>();
		
		for (int i = 0; i < num; i++) {
			int w = teclado.nextInt();
			int v = teclado.nextInt();
			
			Data d = new Data(w, v);
			list.add(d);
		}
		
		knapsackBT(list, totalWeight);
	}
	
	
	private static void knapsackBT(List<Data> list, int totalWeight) {
		boolean[] solution = new boolean[list.size()];
		
		knapsackBTaux(list, 1, totalWeight, solution);		
	}


	private static void knapsackBTaux(List<Data> list, int k, int totalWeight, boolean[] solution) {
		
		if (k == list.size()) {
			System.out.println("termine 'k'=" + k);
			
			if (maxValue >= bestValue ) {
				bestValue = maxValue;
				System.out.println("> Mejor valor encontrado: " + maxValue);
				System.out.println("> Peso maximo: " + maxWeight);
			}
		}
		
		else {			
			for (int i = 0; i < list.size(); i++) {
				Data d = list.get(i);
				
				// el peso actual no sobrepase el peso de la mochila y además aún no lo haya añadido
				if ( ((d.getWeight() + maxWeight) <= totalWeight) && !solution[i] ) {
					solution[i] = true;
					maxWeight += d.getWeight();
					maxValue += d.getValue();
					
					knapsackBTaux(list, k+1, totalWeight, solution);
					
					solution[i] = false;
					maxWeight -= d.getWeight();
					maxValue -= d.getValue();
				}
			}
		}
	}


	private static class Data {
		private int value;
		private int weight;
		
		public Data(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public int getWeight() {
			return weight;
		}
	}
}


