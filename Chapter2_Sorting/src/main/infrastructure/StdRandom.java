package main.infrastructure;

import java.util.Random;

public class StdRandom {
    private static long seed = System.currentTimeMillis();
    private static Random random = new Random(seed);

    /**
     * 设置随机数种子
     *
     * @param seed 随机数种子
     */
    public static void setSeed(long seed) {
        StdRandom.seed = seed;
    }

    public static void setSeed(int seed) {
        StdRandom.seed = seed;
    }

    /**
     * 返回[0,1]之间的随机实数
     *
     * @return [0, 1]之间的随机实数
     */
    public static double uniform() {
        return random.nextDouble();
    }

    /**
     * 返回[0,n)之间的随机整数
     *
     * @param n 右区间
     * @return [0, n)之间的随机整数
     */
    public static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("Illegal argument: " + n);
        return random.nextInt(n);
    }

    /**
     * 返回[lo,hi)之间的随机整数
     *
     * @param lo 左区间
     * @param hi 右区间
     * @return [lo, hi)之间的随机整数
     */
    public static int uniform(int lo, int hi) {
        if ((hi <= lo) || ((long) hi - lo >= Integer.MAX_VALUE))
            throw new IllegalArgumentException("Invalid range: [" + lo + "," + hi + ")");
        return lo + uniform(hi - lo);
    }

    public static double uniform(double lo, double hi) {
        if (!(lo < hi))
            throw new IllegalArgumentException("invalid range: [" + lo + ", " + hi + ")");
        return lo + uniform() * (hi - lo);
    }

    public static void shuffle(Object[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(double[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(int[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void shuffle(char[] a) {
        validateNotNull(a);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n - i);     // between i and n-1
            char temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    private static void validateNotNull(Object x) {
        if (x == null)
            throw new IllegalArgumentException("argument is null");
    }
}
