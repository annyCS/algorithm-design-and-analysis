package ejer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Divide y vencerás: Ejercicio 2
 *
 * <p>Se pide encontrar la mediana de un grupo de elementos no ordenados
 * sin tener que ordenarlos. Vamos a considerar que la mediana sería
 * el elemento que ocuparía la posición (n+1)/2 si los elementos
 * estuvieran ordenados.
 * </p>
 * <p>
 * Ordenar los elementos sería O(nlogn) si usamos un método de
 * ordenación eficiente. Si utilizamos DyV podemos tener un algoritmo
 * de coste O(n) EN PROMEDIO [1].
 * </p>
 * <p>
 * [1] El peor caso de este algoritmo es O(n^2) en realidad,
 * normalmente con un buen pivote su coste sería líneal.
 * </p>
 */
public class E2_Median {

    public static void main(String[] asdf) {
        Random random = new Random();
        //Pregunta de teoría: ¿por qué para ciertos valores de n es más
        // lento DyV que ordenar y sacar la mediana si la complejidad de
        // DyV es menor que la de ordenar?
        int n = 100;
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(n * 2));
        }

        long time1 = -System.currentTimeMillis();
        int output = median(list);
        time1 += System.currentTimeMillis();

        long time2 = -System.currentTimeMillis();
        list.sort(null);
        int expected = list.get((list.size() - 1) / 2);
        time2 += System.currentTimeMillis();

        if (expected == output)
            System.out.printf("Median=%d, Size=%d (DyV=%dms, Sort=%dms)\n", output, n, time1, time2);
        else
            System.err.printf("Median=%d, Expected=%d, Size=%d (DyV=%dms, Sort=%dms)\n", output, expected, n, time1, time2);
        //System.out.println(list);

    }

    /**
     * Returns the median value of an unsorted array. We consider the median the kth-smallest element
     * for k = (n+1)/2 i.e.
     *
     * @param list
     * @return median
     */
    public static int median(List<Integer> list) {
        int relative_order = (list.size() - 1) / 2;
        return kthSmallestElement(list, relative_order);
    }

    /**
     * Returns the k-th smallest element from the given list.
     *
     * <p>k=0 would be the smallest element, k=1 the second smallest, ...
     * k=n-1 the largest element (or last smallest).</p>
     *
     * @param list
     * @param k
     * @return
     */
    private static int kthSmallestElement(List<Integer> list, int k) {
        int result, pivot;
        ArrayList<Integer> underPivot = new ArrayList<>(), overPivot = new ArrayList<>(), equalPivot = new ArrayList<>();

        // choosing a pivot is a whole topic in itself.
        // this implementation uses the simple strategy of grabbing something
        // from the middle of the ArrayList.
        pivot = list.get(list.size() / 2);

        // split coll into 3 lists based on comparison with the pivot
        for (Integer e : list) {
            if (e < pivot)
                underPivot.add(e);
            else if (e > pivot)
                overPivot.add(e);
            else
                equalPivot.add(e);
        }

        // recurse on the appropriate list
        if (k < underPivot.size())
            result = kthSmallestElement(underPivot, k);
        else if (k < underPivot.size() + equalPivot.size()) // equal to pivot; just return it
            result = pivot;
        else
            // everything in underPivot and equalPivot is too small. Adjust n
            // accordingly in the recursion.
            result = kthSmallestElement(overPivot, k - underPivot.size() - equalPivot.size());

        return result;
    }

}
