package main.algorithm;

import main.infrastructure.StdRandom;

import java.util.Arrays;

/**
 * 归并排序是一种递归的排序算法,
 * 归并是将两组有序的数组何必为一个有序的数组(merge()方法).
 * 此算法是自顶向下的标准归并算法.
 */
public class Merge {
    private static Comparable[] aux; // 归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; // 分配空间
        sort(a, 0, a.length - 1);
    }

    /**
     * 排序
     * 可以改进的地方:小数组可以用插入排序完成，以减少递归次数
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        // 将数组a[lo,hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 将左半边排序
        sort(a, mid + 1, hi); // 将右半边排序
        if (less(a[mid], a[mid + 1]) || a[mid].equals(a[mid + 1]))
            return; // 如果左边的最后一个元素小于等于右边第一个元素,则已经有序不需要归并
        merge(a, lo, mid, hi); // 归并结果
    }

    /**
     * 将数组a[lo,mid]和a[mid+1,hi]归并为一个有序数组
     *
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
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
        Integer[] a = new Integer[200];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 100);
        }
        Merge.show(a);
        Merge.sort(a);
        System.out.println(Merge.isSorted(a));
        System.out.println(Arrays.toString(a));
    }
}
