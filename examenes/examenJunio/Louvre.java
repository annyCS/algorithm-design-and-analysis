package examenes.examenJunio;

import java.util.Scanner;

public class Louvre {
	
	static final int ENTRADA = 1;
	static final int SALIDA = 3;
	static final int INTERES = 2;
	static final int TRANSITABLE = 0;
	static final int NO_TRANSITABLE = 4;
	
	static int r_salida = 0;
	static int c_salida = 0;
	
	static int[][] direcciones = {{0,1},{0,-1},{1,0},{-1,0}};
	static int ptosInteresTot = 0;
	static int bestSol = Integer.MAX_VALUE;
	static int ptosVisitados = 0;
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int rows = teclado.nextInt();
		int cols = teclado.nextInt();
		
		int[][] board = new int[rows][cols];
		int r_entrada = 0;
		int c_entrada = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int casilla = teclado.nextInt();
				board[i][j] = casilla;
				
				if ( casilla == ENTRADA ) {
					r_entrada = i;
					c_entrada = j;
				}
				
				if ( casilla == SALIDA ) { 
					r_salida = i;
					c_salida = j;
				}
				
				if ( casilla == INTERES ) {
					ptosInteresTot++;
				}
			}
		}
		
		resolveByBacktraking(board, r_entrada, c_entrada);
	}

	private static void resolveByBacktraking(int[][] board, int r_entrada, int c_entrada) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		bt(board, r_entrada, c_entrada, 1, 0, visited);

		System.out.println(ptosInteresTot + " " + bestSol);
	}

	private static void bt(int[][] board, int r, int c, int sol, int nPtos, boolean[][] visited) {
		
		visited[r][c] = true;
		if ( board[r][c] == INTERES) { 
			ptosVisitados++;
		}
		
		if ( r == r_salida && c == c_salida ) {
			if ( isSolution(ptosVisitados) && sol < bestSol) {
				bestSol = sol;
			}
		}
		
		else {
			for (int k = 0; k < direcciones.length; k++) {
				int nfila = r + direcciones[k][0]; 
				int ncolumn = c + direcciones[k][1];
				 
				if ( isFeasible(nfila, ncolumn, sol, board) && !visited[nfila][ncolumn]) {
					bt(board, nfila, ncolumn, sol+1, ptosVisitados, visited);
				}
			}
		}
		
		visited[r][c] = false;
		ptosVisitados--;
	} 
	

	private static boolean isFeasible(int nfila, int ncolumn, int solucion, int[][] board) {
		if ( nfila < board.length && nfila >= 0 && ncolumn < board[0].length && ncolumn >= 0 ) {
			return board[nfila][ncolumn] != NO_TRANSITABLE;
		}
		
		return false;
	}
	
	private static boolean isSolution(int solucion) {
		return solucion >= ptosInteresTot;
	}
}
