package com.yihao.algorithmexercise.datastructure;

import java.util.NoSuchElementException;

/**
 * 红黑树的实现
 * Created by yihao on 2018/12/15.
 */
public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node {
        public Integer key;
        public Integer value;
        public Node left, right;
        public boolean color;
        public int size;

        public Node(Integer key, Integer value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.size = N;
            this.color = color;
        }


    }

    private Node root = null;

    public boolean isRed(Node h) {
        if (h == null) {
            return false;
        }
        return h.color == RED;
    }

    public void put(Integer key, Integer value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 中序遍历，输出结果
     *
     * @return
     */
    @Override
    public String toString() {
        return print(root);
    }

    private String print(Node node) {
        if (null == node) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(print(node.left));
        builder.append("key ").append(node.key).append(" value ").append(node.value).append(" color is red ")
                .append(node.color).append('\n');
        builder.append(print(node.right));
        return builder.toString();
    }

    private Node rotateLeft(Node h) {
        if (null == h) {
            return null;
        }
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        if (null == h) {
            return null;
        }
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 转换两个红色子节点和该节点的颜色
    private void flipColors(Node h) {
        if (null != h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }
    }

    private int size() {
        return 0;
    }

    private int size(Node h) {
        return 0;
    }

    private Node put(Node h, Integer key, Integer value) {
        // h为null，则说明当前有空位，可以插入，并且和父节点用红色连接
        if (h == null) {
            return new Node(key, value, 1, RED);
        }

        // key值小于h的key值，向左子树探测
        if (key < h.key) {
            h.left = put(h.left, key, value);
        }
        // key值大于h的key值，向右子树探测
        else if (key > h.key) {
            h.right = put(h.right, key, value);
        }
        // key值等于h的key值，更新h
        else {
            h.value = value;
        }

        // 对插入后的状态进行修正
        // h的右子树为红色，则左旋转h
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        // h的左子树和左左子树为红色，则右旋转h
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        // h的左右子树均为红色，则翻转颜色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }


    // TODO @yihao
    // the code of functions below
    // copy from https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
    // please implementation by myself

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Integer key) {
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Integer get(Integer key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Integer get(Node x, Integer key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.value;
        }
        return null;
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Integer key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }


    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Integer min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.put(83, 83); // S
        tree.put(69, 69); // E
        tree.put(65, 65); // A
        tree.put(82, 82); // R
        tree.put(67, 67); // C

        tree.put(72, 72); // H

        tree.put(88, 88); // X
        tree.put(77, 77); // M
        tree.put(80, 80); // P
        tree.put(76, 76); // L
        System.out.println(tree.toString());


    }

}
