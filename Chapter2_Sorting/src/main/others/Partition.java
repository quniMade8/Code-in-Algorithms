package main.others;

import main.infrastructure.StdRandom;

import java.util.Arrays;

public class Partition {
    private static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = cut(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * @param a
     * @param lo
     * @param hi
     */
    private static int cut(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo]; //切分元素
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Character[] a = new Character[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) StdRandom.uniform(65, 123);
        }
        System.out.println(Arrays.toString(a));
        Partition.sort(a);
        System.out.println(Arrays.toString(a));

    }
}
