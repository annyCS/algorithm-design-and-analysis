package practicas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cycles {

	static Scanner teclado = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		int num_nodes = teclado.nextInt();
		int num_edges = teclado.nextInt();
		
		List<Integer>[] graph = new List[num_nodes];
		for (int i = 0; i < num_nodes; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < num_edges; i++) {
			int u = teclado.nextInt();
			int v =  teclado.nextInt();
			
			graph[u-1].add(v-1);
			graph[v-1].add(u-1);
		}

		solveCycles(graph);

	}

	private static void solveCycles(List<Integer>[] graph) {
		boolean isCycle = false;
		
		for (int i = 0; i < graph.length && !isCycle; i++) {
			
			boolean[] visited = new boolean[graph.length];
			isCycle = dfsCycle(graph, visited, i, i, i);
		}
		
		System.out.println(isCycle);
	}

	private static boolean dfsCycle(List<Integer>[] graph, boolean[] visited, int currentNode, int initNode, int beforeNode) {
		
		visited[currentNode] = true;
		
		if ( graph[currentNode].contains(initNode) && beforeNode != initNode )
			return true;
		
		boolean result = false;
		
		for (int adj: graph[currentNode]) {
			
			if ( !visited[adj] ) {
				result = dfsCycle(graph, visited, adj, initNode, currentNode);
			}
		}
		
		return result;		
	}

}
