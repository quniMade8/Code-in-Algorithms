package main.demo;

import main.infrastructure.StdDraw;
import main.infrastructure.StdRandom;
import main.infrastructure.StopWatch;

public class DoublingTest {
    public static double timeTrail(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        StopWatch timer = new StopWatch();
        int count = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 5000);
        StdDraw.setYscale(0, 20);
        StdDraw.setPenRadius(0.01);
        for (int N = 250; true; N += N) {
            double time = timeTrail(N);
            System.out.printf("%7d %5.1f\n", N, time);
            StdDraw.point(N, time);
        }
    }
}
