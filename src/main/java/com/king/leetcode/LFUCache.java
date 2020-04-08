package com.king.leetcode;


import java.util.*;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
class LFUCache {

    Map<Integer,Integer> map;
    List<Key> list;
    int size;

    public LFUCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new ArrayList<>(capacity);
        size = capacity;
    }

    public int get(int key) {
        if(size == 0){
            return -1;
        }
        if(map.containsKey(key)){
            plus(list,key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(size == 0){
            return;
        }
        if(map.containsKey(key)){
            plus(list,key);
        }else{
            if(map.size() >= size){
                Collections.sort(list);
                Key k = list.remove(0);
                map.remove(k.key);
            }
            list.add(new Key(key));
        }
        map.put(key,value);

    }

    private void plus(List<Key> list,int key){
        list.forEach(it->{
            if(it.key == key){
                it.plus();
                return;
            }
        });
    }

    static class Key implements Comparable<Key>{
        int key;
        int count;

        public Key(int key) {
            this.key = key;
        }

        public Key(int key, int count) {
            this.key = key;
            this.count = count;
        }

        public void plus(){
            count++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key1 = (Key) o;

            return key == key1.key;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public int compareTo(Key key) {
            if(count > key.count){
                return 1;
            }else if(count < key.count){
                return  -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    ", count=" + count +
                    '}';
        }
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
