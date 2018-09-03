package main.algorithm;

import main.infrastructure.StdRandom;

import java.util.Arrays;

/**
 * 快速排序是一种递归排序算法。
 * 将数组中的某个值作为切分元素a[j],把数组切分为a[lo,j-1]、a[j]、a[j+1,hi]三个部分
 * 切分元素的左边值比自己小(或等于)，右边值比自己大(或等于)
 * 对左右两边继续递归切分
 */
public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // 消除对输入的依赖
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        // 对小数组采用插入排序，提高性能
//        if (hi <= lo + M) {
//            Insertion.sort(a, lo, hi);
//            return;
//        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo,i-1],a[i],a[i+1,hi]
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo]; // 切分元素
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v))
                if (i == hi) break;
            while (less(v, a[--j]))
                if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); //将v = a[j] 放入正确的位置
        return j; //a[lo,j-1] <= a[j] <= a[j+1,hi] 达成
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
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 100);
        }
        Quick.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
