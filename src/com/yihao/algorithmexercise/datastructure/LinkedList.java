package com.yihao.algorithmexercise.datastructure;

/**
 * 链表数据结构的实现
 * Created by yihao on 2018/12/2.
 */
public class LinkedList {
    public static class Node {
        public int value = 0;
        public Node prev = null;
        public Node next = null;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 双向链表
     */
    public static class DoublyLinkedList {
        private Node head = null;

        public DoublyLinkedList(int headValue) {
            head = new Node(headValue);
        }

        public void insert(Node node) {
            if (null != node) {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        public Node search(int key) {
            Node node = head;
            while (null != node && node.value != key) {
                node = node.next;
            }
            return node;
        }

        public void delete(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }
    }

    /**
     * 带哨兵的双向链表
     */
    public static class SentinelDoublyLinkedList {
        // 哨兵节点
        private Node NIL = null;

        public SentinelDoublyLinkedList() {
            NIL = new Node(0);
            // 初始化时，哨兵的next和prev都指向自己
            NIL.next = NIL;
            NIL.prev = NIL;
        }

        public void insert(Node node) {
            if (null != node) {
                node.next = NIL.next;
                node.prev = NIL;
                NIL.next.prev = node;
                NIL.next = node;
            }
        }

        public Node search(int value) {
            Node node = NIL.next;
            while (node != NIL && node.value != value) {
                node = node.next;
            }
            return node;
        }

        public void delete(Node node) {
            if (node != NIL) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }

}
