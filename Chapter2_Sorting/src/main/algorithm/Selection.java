package main.algorithm;

/**
 * 选择排序是一种初级排序算法
 * description: 从索引0开始，从0的右边选择最小的值与索引为0的值交换，
 * 索引+1，以此类推。
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
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
        Integer[] a = {3, 2, 6, 4, 5, 10, 22, 4, 1, 0, -1, 44, 3, 2, 4};
        Selection.sort(a);
        assert Selection.isSorted(a);
        Selection.show(a);
    }
}
