/**
 * Resuelto con Algoritmo Voraz Kruskal
 */

package examenes;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Skills_Kruskal {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int numNodos = teclado.nextInt();
		int numAristas = teclado.nextInt(); 
		
		Queue<Edge> cola = new PriorityQueue<Edge>();
		
		for (int i = 0; i < numAristas; i++) {
			int origen = teclado.nextInt();
			int destino = teclado.nextInt();
			int peso = teclado.nextInt();
			
			Edge e = new Edge(origen-1, destino-1, peso);
			cola.add(e);
		}
		
		int costeTotal = resolverKruskal(cola, numNodos, numAristas);
		
		System.out.println(costeTotal);
	}
	
	private static int resolverKruskal(Queue<Edge> cola, int numNodos, int numAristas) {
		int costMin = 0;
		int numCaminos = 0;
		
		int leader[] = new int[numNodos];
		int ranking[] = new int[numNodos];
		
		for (int i = 0; i < leader.length; i++) {
			leader[i] = i;
		}
		
		while ( numCaminos <= numNodos-1 && !cola.isEmpty() ) {
			Edge e = cola.poll();
			
			int leaderOrigen = find(e.getOrigen(), leader);
			int leaderDestino = find(e.getDestino(), leader);
			
			if ( leaderOrigen != leaderDestino ) {
				numCaminos++;
				costMin += e.peso;
				union(leaderOrigen, leaderDestino, leader, ranking);
			}
		}
		
		return costMin;
	}
	
	private static int find(int i, int [] leader) {
		return (leader[i] == i) ? i : find(leader[i], leader);
	}
	
	private static void union(int x, int y, int[] ranking, int[] leader) {
		if (ranking[x] == ranking[y]) {
			ranking[x]++;
			leader[y] = x;
		}
		
		else {
			if (ranking[x] > ranking[y]) {
				leader[y] = x;
			} else {
				leader[x] = y;
			}
		}
	}

	public static class Edge implements Comparable<Edge> {

		public int origen;
		public int destino;
		public int peso;
		
		public Edge(int origen, int destino, int peso) {
			this.origen = origen;
			this.destino = destino;
			this.peso = peso;
		}
		
		public int getOrigen() {
			return origen;
		}

		public int getDestino() {
			return destino;
		}
		
		@Override
		public int compareTo(Edge e) {
			if (this.peso < e.peso) return -1;
			if (this.peso > e.peso) return 1;
			return 0;
		}
	}

}
