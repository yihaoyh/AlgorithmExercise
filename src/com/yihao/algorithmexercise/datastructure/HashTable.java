package com.yihao.algorithmexercise.datastructure;

import javafx.util.Pair;

import java.util.*;
import java.util.LinkedList;

/**
 * 散列表的实现
 * Created by yihao on 2018/12/4.
 */
public class HashTable {

    /**
     * 使用除留法计算字符串的hash值
     *
     * @param string
     * @return
     */
    public static int hashCodeOfString(String string) {
        int hash = 0;
        int R = 10;
        int M = 31;
        if (null != string) {
            for (int i = 0; i < string.length(); i++) {
                hash = (R * hash + string.charAt(i)) % M;
            }
        }
        return hash;
    }

    /**
     * 一种计算日期hashCode的方法
     *
     * @param date
     * @return
     */
    public static int hashCodeOfDate(Date date) {
        int R = 10;
        int M = 31;
        return (((date.getDay() * R + date.getMonth()) % M) * R + date.getYear()) % M;
    }

    /**
     * 链式散列表
     */
    public static class ChainHashTable<K, V> {
        private int M = 31;
        private List<LinkedList<Pair<K, V>>> slots = new ArrayList<>();

        public ChainHashTable() {
            for (int i = 0; i < M; i++) {
                slots.add(new LinkedList<Pair<K, V>>());
            }
        }

        public void put(K key, V value) {
            if (null != key && null != value) {
                int hashCode = key.hashCode() % M;
                slots.get(hashCode).addFirst(new Pair<>(key, value));
            }
        }

        public V get(K key) {
            if (null != key) {
                int hashCode = key.hashCode() % M;
                LinkedList list = slots.get(hashCode);
                for (Pair<K, V> pair : (Iterable<Pair<K, V>>) list) {
                    if (pair.getKey().equals(key)) {
                        return pair.getValue();
                    }
                }
            }
            return null;
        }

        public void remove(K key) {
            if (null != key) {
                int hashCode = key.hashCode() % M;
                LinkedList list = slots.get(hashCode);
                Iterator<Pair<K, V>> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    if (pair.getKey().equals(key)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }

    }

    /**
     * 线性探测散列表，可能产生群集效应
     *
     * @param <Key>
     * @param <Value>
     */
    public static class LinearProbingHashTable<Key, Value> {
        private int N = 0; // 键值对的总数
        private int M = 8; // 线性探测表的大小
        private Key[] keys;
        private Value[] values;

        public LinearProbingHashTable() {
            keys = (Key[]) new Object[M];
            values = (Value[]) new Object[M];
        }

        private int hashCode(Key key) {
            if (null != key) {
                return (key.hashCode() & 0x7FFFFFFF) % M;
            } else {
                return -1;
            }
        }

        public void put(Key key, Value value) {
            if (null != key && null != value) {
                // 如果键值对个数超过一定比例，要增加探测表尺寸
                if (N >= M / 2) {
                    resize(M * 2);
                }
                int index;
                // 循环查找空的元素
                for (index = hashCode(key); keys[index] != null; index = (index + 1) % M) {
                    if (key.equals(keys[index])) {
                        values[index] = value;
                        return;
                    }
                }
                keys[index] = key;
                values[index] = value;
                N++;
            }
        }

        public Value get(Key key) {
            if (null != key) {
                for (int index = hashCode(key); null != keys[index]; index = (index + 1) % M) {
                    if (key.equals(keys[index])) {
                        return values[index];
                    }
                }
            }
            return null;
        }

        /**
         * 移除以key为键的键值对，删除某个元素造成的空位可能会使键簇后续的元素无法被访问到，
         * 所以要将键簇后续的元素重新插入，效率比较低
         *
         * @param key
         */
        public void remove(Key key) {
            if (null != key) {
                int index = hashCode(key);
                for (; keys[index] != null; index = (index + 1) % M) {
                    if (key.equals(keys[index])) {
                        break;
                    }
                }
                if (null != keys[index]) {
                    keys[index] = null;
                    values[index] = null;
                    index = (index + 1) % M;
                    while (null != keys[index]) {
                        Key keyToRedo = keys[index];
                        Value valueToRedo = values[index];
                        keys[index] = null;
                        values[index] = null;
                        N--;
                        put(keyToRedo, valueToRedo);
                        index = (index + 1) % M;
                    }
                    N--;
                    if (N > 0 && N == M / 8) {
                        resize(M / 2);
                    }
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < M; i++) {
                builder.append(String.format("index %d ", i));
                if (null != keys[i]) {
                    builder.append(String.format("key: %s value: %s", keys[i].toString(), values[i].toString()));
                } else {
                    builder.append("key: null value: null");
                }
                builder.append('\n');
            }
            return builder.toString();
        }

        private void resize(int i) {
        }

    }

    public static void main(String[] args) {
        // 拉链法测试用例
        System.out.println("ChainHashTable Test");
        ChainHashTable<String, Integer> table = new ChainHashTable<>();
        table.put("hello", 1);
        table.put("tom", 2);
        table.put("jim", 3);
        table.put("jim", 4);
        System.out.println(table.get("tom"));
        System.out.println(table.get("to"));
        System.out.println(table.get("hello"));
        System.out.println(table.get("jim"));

        table.remove("hello");
        System.out.println(table.get("hello"));

        // 线性探查法测试用例
        System.out.println("LinearProbingHashTable Test");
        TestKey key1 = new TestKey(1);
        TestKey key2 = new TestKey(2);
        TestKey key3 = new TestKey(3);
        TestKey key7 = new TestKey(7);
        TestKey key15 = new TestKey(15);
        TestKey key23 = new TestKey(23);
        LinearProbingHashTable probingHashTable = new LinearProbingHashTable();
        probingHashTable.put(key1, "hello");
        probingHashTable.put(key2, "tom");
        probingHashTable.put(key3, "jim");
        probingHashTable.put(key7, "sun");
        System.out.println(probingHashTable.toString());
        probingHashTable.put(key15, "moon");
        probingHashTable.put(key23, "star");
        System.out.println(probingHashTable.toString());
        probingHashTable.remove(key7);
        System.out.println(probingHashTable.toString());

    }

    private static class TestKey {
        private int hashCode;

        public TestKey(int hashCode) {
            this.hashCode = hashCode;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public String toString() {
            return String.valueOf(hashCode);
        }
    }
}
