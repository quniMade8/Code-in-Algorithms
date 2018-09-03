package main.algorithm;

import main.infrastructure.StdDraw;
import main.infrastructure.StdRandom;

/**
 * 希尔排序是一种初级排序算法，是对插入排序的一种改进
 * 一般系统排序函数无法解决问题时，可先考虑希尔排序，以后再考虑更复杂的算法
 * description:以间隔为h的元素为一组分别进行插入排序，直到h变为1,
 * h的递增序列: while (h < N / 3) h = 3 * h + 1;
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;// 1, 4, 13, 40, ....
        while (h >= 1) {
            // 将数组变为h有序
            for (int i = h; i < N; i++) {
                // 将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = (h - 1) / 3;// 或者h = h / 3;
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

    public static void sortWithGraphics(Comparable[] a) {
        int N = a.length;
        StdDraw.setPenRadius(0.01);
        StdDraw.setYscale(-10, 100);
        StdDraw.setXscale(0, N);
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                StdDraw.clear();
                for (int j = 0; j < N; j++) {
                    if (j == i) StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.line(j, 0, j, (Integer) a[j]);
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
        StdDraw.clear();
        for (int j = 0; j < N; j++) {
            StdDraw.line(j, 0, j, (Integer) a[j]);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 100);
        }
        Shell.sortWithGraphics(a);
    }
}
