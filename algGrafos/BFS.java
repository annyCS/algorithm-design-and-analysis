package algGrafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

	public static Scanner teclado = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		int nodes = teclado.nextInt();
		int edges = teclado.nextInt();
		int init = teclado.nextInt();

		List<Integer>[] graph = new List[nodes];
		
		for( int i = 0; i < nodes; i++ ) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for( int i = 0; i < edges; i++ ) {
			int u = teclado.nextInt();
			int v = teclado.nextInt();
			
			graph[u-1].add(v-1);
			graph[v-1].add(u-1);
		}

		bfs(graph, init-1);
	}

	private static void bfs(List<Integer>[] graph, int init) {
		
		boolean[] visited = new boolean[graph.length];
		List<Integer> traversal = new ArrayList<Integer>(graph.length);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		int cont = 0;
		queue.add(init);
		
		while( !queue.isEmpty() && cont < graph.length) {
			int node = queue.poll();
			
			if ( !visited[node] ) {
				visited[node] = true;
				traversal.add(node);
				cont++;
				
				for( int adj: graph[node] ) {
					if ( !visited[adj] )
						queue.add(adj);
				}
			}
		}
		
		printSolve(traversal);
	}
	
	private static void printSolve(List<Integer> traversal) {
		for (int n: traversal) {
			System.out.print( (n+1) + " ");
		}
	}
}
