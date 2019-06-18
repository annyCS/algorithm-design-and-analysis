package ejer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Divide y vencerás: Ejercicio 3
 * Obtener la mediana conjunta de dos arrays ordenados
 * sin mezclarlos utilizando DyV.
 * <p>
 * Mezclar los vectores tiene complejidad O(n+m). Si solo
 * queremos encontrar la mediana el algoritmo de DyV tiene
 * complejidad O( Log(min(n,m) ), es decir, complejidad
 * logaritmica sobre el tamaño del vector más pequeño.
 * <p>
 * Se utiliza búsqueda binaria para encontrar que partición
 * inferior (menores) de cada vector estaría en la mitad
 * inferior del array en caso de ordenarlo.
 * <p>
 * Ejemplo:
 * v1= a1 a2 a3 a4 a5
 * v2= b1 b2 b3 b4 b5
 * <p>
 * Si tomamos a3 entonces la mitad inferior de v1 tendría
 * 3 elementos (a1,a2,a3) y la mitad inferior de v2 podría
 * tener como máximo 2 elementos (b1,b2), ya que tenemos 10
 * elementos en total.
 * <p>
 * En este caso:
 * Si a3 =< b3 y b2 =< a4 habríamos terminado, la mediana sería
 * max(a3,b2).
 * Si a4<b2 b2 no podría pertenecer a la mitad inferior, buscaremos
 * la nueva mitad inferior de v1 entre (a4,a5)
 * Si a3>b3
 */
public class E3_MedianFromSortedArrays {

    public static void main(String[] asdf) {
        //1,3,4,5,6,7,10

        int v1[] = {1, 5, 6, 7};
        int v2[] = {3, 4, 10};
        System.out.println(findMedian(v1, v2));
        //randomizedTest();
    }


    /**
     * Median of two sorted arrays with different sizes. O( log(min(n,m) )
     *
     * <p>Precondition: At least one the arrays is not empty.</p>
     *
     * @param v1 first sorted array (size n)
     * @param v2 second sorted array (size m)
     * @return median element
     */
    public static int findMedian(int[] v1, int[] v2) {
        if (v1.length > v2.length)
            return findMedian(v2, v1);
        //low and high indexes for binary search [low, high)
        int low = 0;
        int high = v1.length;
        //number of elements equal or lower than the median
        int lower_elements = (v1.length + v2.length + 1) / 2;
        int median = 0;
        //less or equal ensures its executed al least once if v1.length=0.
        while (low <= high) {
            int lower1 = (low + high) / 2; //lower elements taken from v1 (equal or lower than the median)
            int lower2 = lower_elements - lower1; //lower elements taken from v2 (equal or lower than the median)

            if (lower1 < v1.length && lower2 > 0 && v1[lower1] < v2[lower2 - 1]) {
                // increase partition
                low = lower1 + 1;
            } else if (lower1 > 0 && lower2 < v2.length && v1[lower1 - 1] > v2[lower2]) {
                high = lower1;
            } else { //partition found
                if (lower1 == 0)
                    median = v2[lower2 - 1];
                else if (lower2 == 0)
                    median = v1[lower1 - 1];
                else
                    median = Math.max(v1[lower1 - 1], v2[lower2 - 1]);
                break;
            }
        }
        return median;

    }


    public static void randomizedTest() {
        Random random = new Random();
        int size = 100; //iteracionens esperadas log2(size)
        List<Integer> witness = new ArrayList<>();
        int[] v1, v2;
        for (int i = 0; i < 10000; i++) {
            random.setSeed(i);
            witness.clear();
            int n1 = random.nextInt(size / 2);
            int n2 = random.nextInt(size / 2) + 1;
            v1 = new int[n1];
            for (int j = 0; j < n1; j++) {
                int num = random.nextInt(size * 2);
                v1[j] = num;
                witness.add(num);
            }
            v2 = new int[n2];
            for (int j = 0; j < n2; j++) {
                int num = random.nextInt(size * 2);
                v2[j] = num;
                witness.add(num);
            }
            witness.sort(null);
            Arrays.sort(v1);
            Arrays.sort(v2);
            int expected = witness.get((witness.size() - 1) / 2);
            int output = findMedian(v1, v2);
            if (expected == output)
                System.out.println("Output: " + output);
            else
                System.err.println("Output: " + output + ", Expected: " + expected + ", v1=" + Arrays.toString(v1) + ", v2=" + Arrays.toString(v2));

        }
    }

}
