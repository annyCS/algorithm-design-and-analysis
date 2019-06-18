package examenes;

import java.util.Scanner;

public class Exam {

	static final String SOLUCION = "OK";
	static final String NO_SOLUCION = "NO HAY SUFICIENTE";
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int nodos = teclado.nextInt();		// alumnos
		int aristas = teclado.nextInt();	// relaciones
		int numMod = teclado.nextInt();	// numero de modelos de examen
		boolean[][] grafo = new boolean[nodos][nodos];
		
		for( int i = 0; i < aristas; i ++ ) {
			
			int v = teclado.nextInt();
			int u = teclado.nextInt();
			
			grafo[v][u] = true;
			grafo[u][v] = true;
		}
		
		solver(grafo, numMod);
	}

	private static void solver(boolean[][] grafo, int numMod) {
		
		int n = grafo.length;
		
		int[] solucion = new int[n];	// solucion parcial con los modelos de examen
		
		if ( solverAux(grafo, 0, numMod, solucion) ) {
			System.out.println(SOLUCION);
		}
		
		else {
			System.out.println(NO_SOLUCION);
		}
		
	}

	private static boolean solverAux(boolean[][] grafo, int nodoAct, int numMod, int[] solucion) {
		
		if ( nodoAct == grafo.length-1 ) {
			return true;
		}
		
		else {
			for ( int m = 1; m <= numMod; m++) {
				
				if ( esFactible(grafo, m, nodoAct, solucion) ) {
					solucion[nodoAct] = m;
					
					if ( solverAux(grafo, nodoAct+1, numMod, solucion) )
						return true;
				}
			}
			
			solucion[nodoAct] = 0;
		}
		
		return false;
	}

	private static boolean esFactible(boolean[][] grafo, int m, int nodoAct, int[] solucion) {
		
		for (int adj = 0; adj < grafo.length; adj++ ) {
			if ( grafo[nodoAct][adj] && solucion[adj] == m ) {
				return false;
			}
		}
		
		return true;
	}

}
