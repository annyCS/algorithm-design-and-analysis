/**
 * Resuelto con Algoritmo Voraz Prim
 */

package examenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Skills_Prim {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int numNodos = teclado.nextInt();
		int numAristas = teclado.nextInt();
		
		
		Map<Integer,ArrayList<Edge>> grafo = new HashMap<Integer,ArrayList<Edge>>(numNodos);
		for( int i = 0; i < numNodos; i++) {
			ArrayList<Edge> adj = new ArrayList<Edge>();
			grafo.put(i, adj);
		}
		
		for (int i = 0; i < numAristas; i++) {
			int origen = teclado.nextInt()-1;
			int destino = teclado.nextInt()-1;
			long peso = teclado.nextLong();
			
			Edge e1 = new Edge(origen, destino, peso);
			Edge e2 = new Edge(destino, origen, peso);
			grafo.get(origen).add(e1);
			grafo.get(destino).add(e2);
		}
		
		long costeTotal = resolverPrim(grafo, numNodos, numAristas, 0);
		
		System.out.println(costeTotal);
	}
	
	private static long resolverPrim(Map<Integer, ArrayList<Edge>> grafo, int numNodos, int numAristas, int nodoInit) {
		long costeTotal = 0;
		int numCaminos = 0;
		
		boolean[] visited = new boolean[numNodos];
		Queue<Edge> cola = new PriorityQueue<Edge>();
		
		visited[nodoInit] = true;				 
		ArrayList<Edge> adj = grafo.get(nodoInit);	
		for (Edge n: adj) {
			cola.add(n);
		}
		
		while ( numCaminos <= numNodos-1 && !cola.isEmpty( )) {
			Edge ed = cola.poll();		
			
			if ( !visited[ed.getDestino()]) {
				visited[ed.getDestino()] = true;
				costeTotal += ed.getCoste();
				numCaminos++;
				
				List<Edge> adjs = grafo.get(ed.getDestino());	
				for (Edge n: adjs) {
					cola.add(n);
				}
			}
		}
		
		return costeTotal;		
	}

	public static class Edge implements Comparable<Edge> {

		public int origen;
		public int destino;
		public long coste;
		
		public Edge(int origen, int destino, long coste) {
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
			if (this.coste < e.coste) return -1;
			if (this.coste > e.coste) return 1;
			return 0;
		}
	}

}
