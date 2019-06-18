package examenes;

import java.util.Scanner;

public class Ikea {

	static Scanner teclado = new Scanner(System.in);
	static int entrada_i = 0;
	static int entrada_j = 0;
	static int salida_i = 0;
	static int salida_j = 0;
	static int init = 3;
	static int bestSol = Integer.MAX_VALUE;
	static int[][] movements = {{1,0}, {0,-1}, {-1,0}, {0,1}};
	
	public static void main(String[] args) {
		
		int n_rows = teclado.nextInt();
		int n_cols = teclado.nextInt();
		int n_sections = teclado.nextInt();
		
		int[][] board = new int[n_rows][n_cols];
		
		for(int i = 0; i < n_rows; i++) {
			for(int j = 0; j < n_cols; j++) {
				int casilla = teclado.nextInt();
				
				if ( casilla == 0 ) {
					entrada_i = i;
					entrada_j = j;
				}
				
				if ( casilla == 1 ) {
					salida_i = i;
					salida_j = j;
				}
				
				board[i][j] = casilla;
			}
		}
		
		resolveBacktraking(board, n_sections);
		System.out.println(bestSol);
	}

	private static void resolveBacktraking(int[][] board, int n_sections) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		int[] solution = new int[n_sections];
		
		auxResolveBacktraking(board, entrada_i, entrada_j, visited, solution, 1);
	}
	
	private static void auxResolveBacktraking(int[][] board, int r, int c, boolean[][] visited, int[] solution, int k) {
		visited[r][c] = true;
		
		if ( board[r][c] > 2 ) {		
			solution[board[r][c] - 3]++; 	// porque el numero de seccion empieza por el 3
		}
		
		if ( board[r][c] == 1) {
			if ( isSolution(solution) && k <= bestSol) {
				bestSol = k;
			}
		}
		
		else {
			for (int i = 0; i < movements.length; i++) {
				
				int new_r = r + movements[i][0];
				int new_c = c + movements[i][1];
						
				if ( isFeasible(new_r, new_c, board) && !visited[new_r][new_c] ) {
					auxResolveBacktraking(board, new_r, new_c, visited, solution, k+1);
				}
			}
		}
		
		visited[r][c] = false;
		
		if ( board[r][c] > 2 ) {		
			solution[board[r][c] - 3]--; 	// porque el numero de seccion empieza por el 3
		}
	}

	private static boolean isFeasible(int new_r, int new_c, int[][] board) {
		return new_r < board.length && new_r >= 0 && new_c < board[0].length && new_c >=0 && board[new_r][new_c] != 2;
	}

	private static boolean isSolution(int[] solution) {
		
		for (int casilla: solution) {
			if (casilla == 0) return false;
		}
		
		return true;
	}

}
