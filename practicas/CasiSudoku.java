package practicas;

import java.util.Scanner;

public class CasiSudoku {
	
	static final int SIZE	= 9;
	static final int EMPTY	= 0;
	
	static Scanner teclado = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		int[][] board = new int[SIZE][SIZE];
		
		for ( int i = 0; i < SIZE; i++ ) {
			for ( int j = 0; j < SIZE; j++ ) {
				board[i][j] = teclado.nextInt();
			}
		}
		
		sudokuSolver(board);
	}
	
	private static void sudokuSolver(int[][] board) {
		
		int n = board.length;
		int[][] solucion = new int[n][n];
		
		boolean noCasiSudoku = auxSolver(board, 0, 0, solucion);
		
		if ( !noCasiSudoku ) {
			if ( solucion[0][0] == 0 ) {
				System.out.println("imposible");
			}
			
			else {
				printBoard(solucion);
			}
		}
	}


	public static boolean auxSolver(int[][] board, int r, int c, int[][] solucion) {
		
		if ( r == board.length ) {
			
			if ( solucion[0][0] == board[0][0]) {
				System.out.println("casi sudoku");
				return true;
			}
			
			else {
				// copiar la solucion
				for ( int i = 0; i < SIZE; i++ ) {
					for ( int j = 0; j < SIZE; j++ ) {
						solucion[i][j] = board[i][j];
					}
				}
			}
		}
		
		else {
			
			if ( board[r][c] == EMPTY )		// comprueba si hay que rellenar esa casilla
			{
				for ( int n = 1; n <= 9; n++ )
				{		
					if ( isFeasible(board, r, c, n) )
					{
						board[r][c] = n;							// anoto numero
						
						int[] ncell = advanceCell(board, r, c);		// avanzo casilla
						
						if ( auxSolver(board, ncell[0], ncell[1], solucion) )	// avanzo para rellenar siguiente
							return true;
					}
				}
				
				board[r][c] = 0;
			}
			
			else {
				
				int[] ncell = advanceCell(board, r, c);	// avanzo casilla
				
				if ( auxSolver(board, ncell[0], ncell[1], solucion) )	// avanzo para rellenar siguiente
					return true;
			}
		}
		
		return false;
	}

	
	private static boolean isFeasible(int[][] board, int r, int c, int n) {
		
		// buscar en fila
		for ( int j = 0; j < board.length; j ++ ) {
			if ( board[r][j] == n ) {
				return false;
			}
		}
		
		// buscar en columna
		for ( int i = 0; i < board.length; i ++ ) {
			if ( board[i][c] == n ) {
				return false;
			}
		}
		
		// buscar en el cuadrante
		return checkCuadrante(board, r, c, n);
	}
	
	private static boolean checkCuadrante(int[][] board, int row, int col, int n) {
		
		int nrow = (row/3) * 3;
		int ncol = (col/3) * 3;
		
		for ( int i = nrow; i < (nrow+3); i++ ) {
			for ( int j = ncol; j < (ncol+3); j++ ) {
				
				if ( board[i][j] == n ) {
					return false;
				}
			}
		}
		
		return true;
	}


	private static int[] advanceCell(int[][] board, int r, int c) {
		
		int[] nextCell = new int[2];
		
		if( c == board.length-1 ) {
			r++;
			c = 0;
		}
		
		else {
			c++;
		}
		
		nextCell[0] = r;
		nextCell[1] = c;
		
		return nextCell;
	}


	private static void printBoard(int[][] board) {
		
		for ( int i = 0; i < board.length; i++ ) {
			
			for ( int j = 0; j < board[i].length; j++ ) {
				System.out.print(board[i][j] + " ");
			}
			
			System.out.println();
		}
	}

}
