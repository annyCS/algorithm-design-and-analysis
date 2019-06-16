package algGrafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DFS {
	
	public static Scanner teclado = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		int nodes = teclado.nextInt();	// numero nodos
		int edges = teclado.nextInt();	// numero aristas
		int init = teclado.nextInt(); // nodo de inicio
		
		List<Integer>[] graph = new List[nodes];
		
		for ( int i = 0; i < nodes; i++ ) {
			graph[i] = new ArrayList<Integer>();
		}
		
		// fill graph
		for ( int i = 0; i < edges; i++ ) {
			int u = teclado.nextInt();
			int v = teclado.nextInt();
			
			graph[u-1].add(v);
			graph[v-1].add(u);
		}
		
		dfs(graph, init);
	}
	
	public static void dfs(List<Integer>[] graph, int init) {
		List<Integer> traversal = new ArrayList<Integer>(graph.length);
		boolean[] visited = new boolean[graph.length];

		dfs(graph, init, visited, traversal);
		
		printSolve(traversal);
	}

	private static void dfs(List<Integer>[] graph, int node, boolean[] visited, List<Integer> traversal) {
		
		visited[node-1] = true;
		traversal.add(node);
		
		for( int adj: graph[node-1] ) {
			
			if ( !visited[adj-1] ) {
				dfs(graph, adj, visited, traversal);
			}
		}
	}
	
	private static void printSolve(List<Integer> traversal) {
		for (int n: traversal) {
			System.out.print( n + " ");
		}
	}
	
	
	/* NOTA: se puede realizar con un caso base y un caso recursivo, pero para este se necesita un contador
	 * para saber cuantos nodos en total son los que se han visitado; NO se utiliza el nodo actual a visitar sino
	 * el contador. Ejemplo:
	 * 
	 * if ( cont == traversal.size() ) {
	 * 		printSolve(traversal);
	 * 		return true;
	 * } else {
	 * 		cont++;
	 * 		(caso recursivo)
	 * }
	 */
}

/* 
 * input
 * 5 5 2
 * 1 2
 * 1 5
 * 2 3
 * 3 4
 * 3 5
 * 
 * output
 * 2 1 5 3 4
*/
