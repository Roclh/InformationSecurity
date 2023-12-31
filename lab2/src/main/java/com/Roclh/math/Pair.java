package com.Roclh.math;

public class Pair<K, V> {
    private final K left;
    private final V right;

    public Pair(K left, V right){
        this.left = left;
        this.right = right;
    }
    public K left(){
        return this.left;
    }

    public V right(){
        return this.right;
    }
}
