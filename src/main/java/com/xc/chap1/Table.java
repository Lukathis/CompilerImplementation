package com.xc.chap1;

public class Table {

    String id;
    int value;
    Table tail;

    public Table(String id, int value, Table tail){
        this.id = id;
        this.value = value;
        this.tail = tail;
    }

    public static Table update(String i, int v, Table t) {
        return new Table(i, v, t);
    }

    public int lookup(String key) {
        if (id == key) {
            return value;
        }
        if (tail == null) {
            throw new RuntimeException(String.format("key [%s] not found.", key));
        }
        return tail.lookup(key);
    }
}
