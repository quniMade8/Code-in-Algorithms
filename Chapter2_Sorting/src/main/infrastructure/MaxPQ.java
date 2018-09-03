package main.infrastructure;

import java.lang.reflect.Array;

/**
 * 基于堆的优先队列
 * 堆:用于表示完全二叉树的数组，根节点在索引1处，左子节点:2*k,右子节点:2k+1,父节点:k/2
 * 完全二叉树:任意父节点大于或等于它的子节点
 * 优先队列:出队的总是队列中的最大值
 *
 * @param <T> 堆中存储的数据类型
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] pq; // 基于堆的完全二叉树
    private int N = 0; // 存储于pq[1,N]中，pq[0]不使用

    @SuppressWarnings("unchecked")
    public MaxPQ(int maxN, Class<T> type) {
        // pq = (T[]) new Comparable[maxN+1]; 这是<算法>的写法
        pq = (T[]) Array.newInstance(type, maxN + 1); // <Java编程思想>的写法
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T v) {
        pq[++N] = v;
        swim(N);
    }

    public T delMax() {
        T max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    /**
     * 上浮节点k,直到堆有序
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉节点k,直到堆有序
     *
     * @param k
     */
    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
