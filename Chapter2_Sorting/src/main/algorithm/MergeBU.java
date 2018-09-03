package main.algorithm;

import main.infrastructure.StdRandom;

import java.util.Arrays;

/**
 * 自底向上的归并排序.
 * 先将大小为1的数组两两归并，然后2, 4, 8, ... 以此类推，最终归并整个数组.
 */
public class MergeBU {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // logN次两两归并
        int N = a.length;
        aux = new Comparable[N];
        for (int size = 1; size < N; size = size + size) {
            for (int lo = 0; lo < N - size; lo += size + size)
                merge(a, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1; // i指向aux的左子数组的头，j指向aux的右子数组的头
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            // 从aux里选择较小的元素填充回a
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[1000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 1000);
        }
        Merge.show(a);
        Merge.sort(a);
        System.out.println(Merge.isSorted(a));
        System.out.println(Arrays.toString(a));
    }
}
