package ejercicios;

import java.util.Arrays;

public class E6_PredominantNumber {
    //Un numero mayoritario es aquel que está repetido más de n/2 veces.

    public static void main(String[] asdf) {
        //int[] v1 = {1, 1, 2};
        int[] v2 = {1, 2, 3, 5, 5, 4, 5, 5, 6, 5, 5};
        System.out.println(hasPredominantElement(v2));

    }

    public static boolean hasPredominantElement(int v[]) {
        int aux[] = Arrays.copyOf(v, v.length);
        if (auxHasCandidate(aux, aux.length)) {
            int candidate = aux[0];
            int count = 0;
            for (int i : v) {
                if (i == candidate)
                    count++;
            }
            //System.out.print("Candidate:" + aux[0]);
            return count > v.length / 2;
        }
        return false;
    }

    public static boolean auxHasCandidate(int v[], int length) {
        if (length == 1)
            return true;
        if (length == 0)
            return false;

        if (length % 2 == 0) {
            int k = 0;
            for (int i = 0; i < length; i += 2) {
                if (v[i] == v[i + 1]) {
                    v[k] = v[i];
                    k++;
                }
            }
            length = k;
            return auxHasCandidate(v, length);
        } else {
            if (!auxHasCandidate(v, length - 1))
                v[0] = v[length - 1];
            return true;
        }

    }
}
