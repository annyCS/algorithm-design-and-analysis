/**
 * Divide y venceras: busqueda binaria
 */

package examenes;

import java.util.Scanner;

public class KR {

	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int numJug = teclado.nextInt();
		int[] jugadores = new int[numJug];
		
		
		for( int i = 0; i < numJug; i++ )
		{
			int level = teclado.nextInt();
			jugadores[i] = level;
		}
		
		int numConsultas = teclado.nextInt();
		int posMenor = -1;
		int posMayor = -1;
		
		for(int i = 0; i < numConsultas; i++ ) {
			int menor = teclado.nextInt();
			int mayor = teclado.nextInt();
			
			posMenor = binarySearch(jugadores, menor);
			posMayor = binarySearch(jugadores, mayor);
			
			System.out.println(posMenor + " " + posMayor);
		}
	}
	
	public static int binarySearch(int[] v, int elemento) {
		
		int index = -1;
		index = binarySearchAux(v, 0, v.length - 1, elemento);
		
		return index;
	}

    private static int binarySearchAux(int[] v, int menor, int mayor, int element) {
    	
        if ( menor > mayor ) {
        	return menor - 1;
        }
            

        int mitad = (menor + mayor)/2;
        
        if ( v[mitad] == element ) {
        	return mitad;
        }
            
        else if (v[mitad] < element) {
            return binarySearchAux(v, mitad + 1, mayor, element);
        }
        
        else {
            return binarySearchAux(v, menor, mitad - 1, element);
        }
    }

}
