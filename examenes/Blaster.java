/**
 * Resuelto con Algoritmo Voraz Dijkstra
 */

package examenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Blaster {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int nodoInit = teclado.nextInt();
		int nodoObjetivo = teclado.nextInt();
		int potenciaTotal = teclado.nextInt();
		
		int numNodos = teclado.nextInt();
		int numAristas = teclado.nextInt();
		
		long[] costes = new long[numNodos];
		for (int i = 0; i < numNodos; i++) {
			costes[i] = teclado.nextLong();
		}
		
		Map<Integer, List<Edge>> grafo = new HashMap<Integer, List<Edge>>(numNodos);
		for (int i = 0; i < numNodos; i++) {
			List<Edge> adj = new ArrayList<Edge>();
			grafo.put(i, adj);
		}
		
		for (int i = 0; i < numAristas; i++) {
			int origen = teclado.nextInt()-1;
			int destino = teclado.nextInt()-1;
			
			Edge e1 = new Edge(origen, destino, costes[destino]);
			Edge e2 = new Edge(destino, origen, costes[destino]);
			grafo.get(origen).add(e1);
			grafo.get(destino).add(e2);
		}
		
		resolverDijkstra(grafo, numNodos, numAristas, nodoInit, nodoObjetivo, potenciaTotal);
	}
	
	private static void resolverDijkstra(Map<Integer, List<Edge>> grafo, int numNodos, int numAristas, int nodoInit, int nodoObjetivo, int pTotal) {
		int nodoActual = nodoInit;
		int pPerdida = 0;
		
		long[] distances = new long[numNodos];
		Arrays.fill(distances, Integer.MAX_VALUE);
		
		boolean[] visited = new boolean[numNodos];
		Queue<Edge> cola = new PriorityQueue<Edge>();
		
		visited[nodoInit] = true;
		distances[nodoInit] = 0;
		List<Edge> adjs = grafo.get(nodoInit);
		for (Edge e: adjs) {
			cola.add(e);
		}
		
		while ( !(nodoActual == nodoObjetivo) && pTotal - pPerdida > 0 && !cola.isEmpty() ) {
			Edge e1 = cola.poll();
			
			if ( !visited[e1.getDestino()] )
			{
				System.out.println(e1.getOrigen());
				System.out.println(e1.getDestino());
				visited[e1.getDestino()] = true;
				distances[e1.getDestino()] = e1.getCoste();

				pPerdida += e1.getCoste();
				nodoActual = e1.getDestino();
				
				List<Edge> adjsList = grafo.get(e1.getDestino());
				for (Edge e2: adjsList) {
					
					long newDist = e1.getCoste() + e2.getCoste();
					
					if ( newDist < distances[e2.getDestino()]) {
						cola.add(new Edge(e2.getOrigen(), e2.getDestino(), newDist));
						distances[e2.getDestino()] = newDist;
					}
				}
			}
		}
		
		System.out.println(pTotal - pPerdida);
	}

	public static class Edge implements Comparable<Edge> {

		private int origen;
		private int destino;
		private long coste;
		
		Edge(int origen, int destino, long coste) {
			this.origen = origen;
			this.destino = destino;
			this.coste = coste;
		}
		
		public int getOrigen() {
			return origen;
		}
		
		public int getDestino() {
			return destino;
		}
		
		public long getCoste() {
			return coste;
		}		
		
		@Override
		public int compareTo(Edge e) {
			if(this.coste < e.coste) return -1;
			if(this.coste > e.coste) return 1;
			return 0;
		}
	}

}
