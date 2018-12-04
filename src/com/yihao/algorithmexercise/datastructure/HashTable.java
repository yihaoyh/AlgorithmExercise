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
        private List<java.util.LinkedList<Pair<K, V>>> slots = new ArrayList<LinkedList<Pair<K, V>>>();

        public ChainHashTable() {
            for (int i = 0; i < M; i++) {
                slots.add(new LinkedList<Pair<K, V>>());
            }
        }

        public void put(K key, V value) {
            if (null != key && null != value) {
                int hashCode = key.hashCode() % M;
                slots.get(hashCode).addFirst(new Pair<K, V>(key, value));
            }
        }

        public V get(K key) {
            if (null != key) {
                int hashCode = key.hashCode() % M;
                LinkedList list = slots.get(hashCode);
                Iterator<Pair<K, V>> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    if (pair.getKey().equals(key)) {
                        return (V) pair.getValue();
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

    public static void main(String[] args) {
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

    }
}
