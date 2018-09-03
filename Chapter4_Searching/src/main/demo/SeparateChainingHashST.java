package main.demo;


/**
 * 基于拉链法实现的散列表,数组每个元素都是一个链表
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
    private int N; // 键值对的总数
    private int M; // 散列表的大小
    private SequentialSearchST<Key, Value>[] st; // 存放链表的数组

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M; // &操作去除符号位
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        throw new UnsupportedOperationException();
    }
}
