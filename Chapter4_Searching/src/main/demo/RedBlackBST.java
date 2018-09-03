package main.demo;


/**
 * 红黑二叉查找树:
 * 永远平衡的二叉树
 * 用红链接连接的两个2-节点表示一个3-节点
 * 红链接总数左链接
 * 用黑链接表示普通链接
 * 每个空节点到根节点的路径上黑节点数是一样的
 * 这是一种表示2-3树的数据结构
 *  *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color; // 指向该节点的链接颜色
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    /**
     * 将一个红色右链接转变为红色左链接
     * 用法: p.left = rotateLeft(p.left)
     *
     * @param h 子树的旧根节点
     * @return 子树的新根节点，h的父节点必须指向它才算旋转成功
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 将h的两个子节点变为黑色，将红色节点传递到上层
     * @param h
     */
    private void flipColor(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * 插入操作，首先进行正常的插入，然后按规则进行红黑变换
     * @param h
     * @param key
     * @param val
     * @return
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key, val, RED, 1);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        // 插入后的变换规则，可以保证树的完美平衡性
        if (isRed(h.right) && !isRed(h.left))
            // 左子节点为黑，右子节点为红，则左旋转
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            // 左子节点以及左子节点的左子节点都是红，形成了一个4-节点，
            // 则右旋转
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            // 左右子节点均为红节点，则变色，将红节点传递给父节点
            flipColor(h);

        h.size = size(h.right) + size(h.left) + 1;
        return h;
    }
}
