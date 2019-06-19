package examenes.examenJunio;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Overcooked {
 
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int nPedidos = scan.nextInt();
		Queue<Pedido> queue = new PriorityQueue<Pedido>();
		
		for (int i = 0; i < nPedidos; i++) {
			double valor = scan.nextDouble();
			double peso = scan.nextDouble();
			
			Pedido p = new Pedido(valor, peso, (i+1));
			queue.add(p);
		}
		
		double pesoMax = scan.nextDouble();
		
		resolveByGreedy(queue, pesoMax);
	}
	
	
	private static void resolveByGreedy(Queue<Pedido> queue, double pesoMax) {
		double ptosTotales = 0;
		double pesoActual = 0;
		
		List<Pedido> realizados = new ArrayList<Pedido>();
		
		while ( pesoActual < pesoMax && !queue.isEmpty() ) {
			Pedido p = queue.poll();
			
			if (p.peso < pesoMax) {
				if ( pesoActual + p.peso > pesoMax) {
					ptosTotales += ((pesoMax-pesoActual) * p.valor)/p.peso;
					pesoActual += p.peso;
				}
				
				else {
					ptosTotales += p.valor;
					pesoActual += p.peso;
				}
				
				realizados.add(p);
			}
		}
		
		for (Pedido p: realizados) {
			System.out.print(p.indice + " ");
		}

		System.out.println("\n" + String.format("%.2f", ptosTotales));;
	}


	public static class Pedido implements Comparable<Pedido> {
		public double valor;
		public double peso;
		public int indice;
		
		public Pedido(double valor, double peso, int indice) {
			this.valor = valor;
			this.peso = peso;
			this.indice = indice;
		}
		
		@Override
		public int compareTo(Pedido p) {
			if (this.peso/this.valor < p.peso/p.valor) return -1;
			if (this.peso/this.valor > p.peso/p.valor) return 1;
			return 0;
		}
	}
}
