package main.demo;

import main.infrastructure.StopWatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThreeSum {
    public static int count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader("2Kints.txt"));
        String s = "";
        while ((s = reader.readLine()) != null) {
            sb.append(s.trim());
            sb.append(" ");
        }
        String[] a = sb.toString().trim().split(" ");
        int[] intA = new int[a.length];
        for (int j = 0; j < a.length; j++) {
            intA[j] = Integer.parseInt(a[j].trim());
        }
        StopWatch watch = new StopWatch();
        int count = count(intA);
        double time = watch.elapsedTime();
        System.out.println(count + " triples " + time + " seconds");
    }
}
