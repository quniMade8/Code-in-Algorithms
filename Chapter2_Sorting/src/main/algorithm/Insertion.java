package main.algorithm;

import main.infrastructure.StdDraw;
import main.infrastructure.StdRandom;

import java.util.concurrent.TimeUnit;

/**
 * 插入排序是一种初级排序算法
 * description: 索引[0,0]的数组为有序数组，把有序数组的右边第一个插入到有序数组里，
 * 此时[0,1]为有序数组，以此类推
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将a[i]插入到a[i-1]、a[i-2]、a[i-3]...之中
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
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
            System.out.println(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void sortWithGraphics(Comparable[] a) throws InterruptedException {
        int N = a.length;
        StdDraw.setPenRadius(0.01);
        StdDraw.setYscale(-10, 100);
        StdDraw.setXscale(0, N);
        for (int i = 1; i < N; i++) {
            StdDraw.clear();
            for (int j = 0; j < N; j++) {
                if (j == i) StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(j, 0, j, (Integer) a[j]);
                StdDraw.setPenColor(StdDraw.BLACK);
            }
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
        }
        StdDraw.clear();
        for (int j = 0; j < N; j++)
            StdDraw.line(j, 0, j, (Integer) a[j]);
    }

    public static void main(String[] args) throws InterruptedException {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 100);
        }
        Insertion.sortWithGraphics(a);
    }
}
