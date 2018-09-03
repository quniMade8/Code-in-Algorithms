package main.demo;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val; // 命中
        return null; // 未命中
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
    }

    public Iterable<Key> keys() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new Iterator<Key>() {
                    Node current = first;
                    int count = 0;

                    @Override
                    public boolean hasNext() {
                        if (count == 0)
                            return current != null;
                        return current.next != null;
                    }

                    @Override
                    public Key next() {
                        if (count == 0) {
                            count++;
                            return current.key;
                        }
                        current = current.next;
                        return current.key;
                    }
                };
            }
        };
    }

    public boolean isEmpty() {
        return first == null;
    }
}
