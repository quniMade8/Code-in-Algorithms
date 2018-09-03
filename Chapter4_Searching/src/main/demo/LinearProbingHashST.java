package main.demo;

import java.lang.reflect.Array;

/**
 * 基于线性探测法的散列表，用双数组存储键值对。
 * 查找value时，从散列到的索引依次往后探测，如果探测到null，未命中；
 * 插入value时，从散列到的索引依次往后探测，若有重复的key则覆盖；否则探测到null时，将键值对放入此处。
 * 散列表不能全满，否则会无限探测下去，一般保证使用率在[1/8,1/2]，resize()调整大小
 *
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {
    private int N; // 键值对数
    private int M = 16; // 线性探测表大小
    private Key[] keys;
    private Value[] vals;
    private Class keyType;
    private Class valueType;

    public LinearProbingHashST(Class keyType, Class valueType) {
        keys = (Key[]) Array.newInstance(keyType, M);
        vals = (Value[]) Array.newInstance(valueType, M);
        this.keyType = keyType;
        this.valueType = valueType;
    }

    public LinearProbingHashST(Class keyType, Class valueType, int cap) {
        M = cap;
        keys = (Key[]) Array.newInstance(keyType, M);
        vals = (Value[]) Array.newInstance(valueType, M);
        this.keyType = keyType;
        this.valueType = valueType;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> st;
        st = new LinearProbingHashST<>(keyType, valueType, cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                st.put(keys[i], vals[i]);
        }
        keys = st.keys;
        vals = st.vals;
        M = st.M;
    }

    public void put(Key key, Value val) {
        if (N > M / 2) resize(2 * M); // 将M加倍
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) // i+1取余的好处是: 到边界会变为0
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return true;
        return false;
    }

    /**
     * 删除一个键值对后，需要将右侧的元素重新插入，以保证簇不断开
     *
     * @param key
     */
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) resize(M / 2);
    }
}
