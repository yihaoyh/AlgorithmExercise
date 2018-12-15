package com.yihao.algorithmexercise.datastructure;

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
        public int N;

        public Node(Integer key, Integer value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }


    }

    private Node root = null;

    public boolean isRed(Node h) {
        if(h == null) {
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
     * @return
     */
    @Override
    public String toString(){
        return print(root);
    }

    private String print(Node node){
        if(null == node){
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
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
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
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 转换两个红色子节点和该节点的颜色
    private void flipColors(Node h) {
        if (null != h) {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
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

        h.N = size(h.left) + size(h.right) + 1;
        return h;
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
