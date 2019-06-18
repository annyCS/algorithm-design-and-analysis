/**
 * Divide y venceras: MergeSort
 */

package practicas;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
	
	public static final int UMBRAL = 2;

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int nElementos = teclado.nextInt();
		int[] vectorInt = new int[nElementos];
		
		for( int i = 0; i < vectorInt.length; i++ ) {
			vectorInt[i] = teclado.nextInt();
		}
		
		vectorInt = mergeSort(vectorInt);
		
		// - result
		for ( int elem: vectorInt ) {
			System.out.print(elem + " ");
		}
		
		teclado.close();
	}

	public static int[] mergeSort(int[] vector) {
		
		if ( vector.length <= UMBRAL ) {
			Arrays.sort(vector);
			
			return vector;
		}
		
		else {
			// - dividir el array por la mitad
			int[] rigth = new int[vector.length/2];
			int[] left = new int[vector.length - rigth.length];
			
			
			System.arraycopy(vector, 0, rigth, 0, rigth.length); // (origen, posIniOri, dest, posIniDest, numElementos)
			System.arraycopy(vector, rigth.length, left, 0, left.length);
			
			// - llamamos recursivamente a cada mitad del array para ordenarlo
			mergeSort(rigth);
			mergeSort(left);
			
			// - juntamos ambas partes del array, sobreescribiendo el array original
			merge(rigth, left, vector);
			
			return vector;
		}
	}
	
	public static void merge(int[]rigth, int[] left, int[] v) {
		int i = 0;	// indice para la parte izq
		int j = 0;	// indice para la parte der
		int k = 0;	// indice para el array resultante
		
		while ( i < rigth.length && j < left.length ) {
			
			if ( rigth[i] < left[j] ) {
				v[k] = rigth[i];
				i++;
			}
			
			else {
				v[k] = left[j];
				j++;
			}
			
			k++;
		}
		
		if ( i < rigth.length ) {
			System.arraycopy(rigth, i, v, k, rigth.length-i);	// (origen, posIniOri, dest, posIniDest, numElementos)
		}
		
		if ( j <  left.length ) {
			System.arraycopy(left, j, v, k, left.length-j);
		}
	}
	
	// https://aprendeyprogramablog.wordpress.com/2016/08/13/algoritmo-divide-y-venceras-mergesort/
	// http://jorgep.blogspot.com/2010/09/ordenacion-por-combinacion.html
}
