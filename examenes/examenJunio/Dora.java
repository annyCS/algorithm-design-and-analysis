package examenes.examenJunio;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Dora {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int nNodos = scan.nextInt();
		int nAristas = scan.nextInt();
		
		Queue<Edge> q = new PriorityQueue<Edge>();
		
		for (int i = 0; i < nAristas; i++) {
			int origen = scan.nextInt();
			int destino = scan.nextInt();
			int coste = scan.nextInt();
			
			Edge e = new Edge(origen, destino, coste);
			q.add(e);
		}
		
		int coste = resolve(q, nNodos, nAristas);
		
		System.out.println(coste);
	}
	
	private static int resolve(Queue<Edge> q, int nNodos, int nAristas) {
		int costeTotal = 0;
		int nCaminos = 0;
		
		int[] ranking = new int[nNodos];
		int[] leader = new int[nNodos];
		for (int i = 0; i < nNodos; i++) {
			leader[i] = i;
		}
		
		while ( !q.isEmpty() && (nCaminos < nNodos-1) ) {
			Edge e = q.poll();
			
			int leadOr = find(e.origen, leader);
			int leadDest = find(e.destino, leader);
			
			if ( leadOr != leadDest ) {
				costeTotal += e.coste;
				nCaminos++;
				union(leadOr, leadDest, ranking, leader);
			}
		}
		
		return costeTotal;
	}

	private static void union(int x, int y, int[] ranking, int[] leader) {
		if (ranking[x] == ranking[y]) {
			leader[y] = x;
			ranking[x]++;
		}
		
		else {
			if(ranking[x] > ranking[y]) {
				leader[y] = x;
			} else {
				leader[x] = y;
			}
		}
	}

	private static int find(int i, int[] leader) {
		
		return (leader[i] == i) ? i : find(leader[i], leader);
	}

	public static class Edge implements Comparable<Edge> {
		public int origen;
		public int destino;
		public int coste;
		
		public Edge(int origen, int destino, int coste) {
			this.origen = origen;
			this.destino = destino;
			this.coste = coste;
		}
		
		@Override
		public int compareTo(Edge e) {
			if (this.coste < e.coste) return -1;
			if (this.coste > e.coste) return 1;
			return 0;
		}
	}
}
