package ru.aston.homework.intensive_modul1;

import java.util.Map;
import java.util.Objects;

import static java.util.Objects.hash;

public class MyHashMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;

    private static class Node<K, V> {
        final K key;
        final int hash;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            return o instanceof Map.Entry<?, ?> e
                    && Objects.equals(key, e.getKey())
                    && Objects.equals(value, e.getValue());
        }
    }

    Node<K, V>[] table;
    int size;
    private int threshold;

    @SuppressWarnings("unchecked")
    MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
        this.threshold = (int) (DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private Node<K, V> findEntry(K key) {
        if (key == null) {
            return findNullEntry();
        }

        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);

        for (Node<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.hash == hash && (key.equals(entry.key))) {
                return entry;
            }
        }
        return null;
    }

    private Node<K, V> findNullEntry() {
        for (Node<K, V> entry = table[0]; entry != null; entry = entry.next) {
            if (entry.key == null) {
                return entry;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            return putNullKey(value);
        }

        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);

        Node<K, V> entry = findEntry(key);
        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }

        addNode(hash, key, value, index);
        if (++size >= threshold) {
            resize(2 * table.length);
        }
        return value;
    }

    private V putNullKey(V value) {
        Node<K, V> entry = findNullEntry();
        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }

        addNode(0, null, value, 0);
        if (++size >= threshold) {
            resize(2 * table.length);
        }
        return null;
    }

    public V get(K key) {
        Node<K, V> entry = findEntry(key);
        return entry != null ? entry.value : null;
    }

    public V remove(K key) {
        if (size == 0) {
            return null;
        }

        if (key == null) {
            return removeNullKey();
        }

        int hash = hash(key.hashCode());
        int index = indexFor(hash, table.length);

        Node<K, V> prev = null;
        for (Node<K, V> entry = table[index]; entry != null; prev = entry, entry = entry.next) {
            if (entry.hash == hash && (key.equals(entry.key))) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
        }
        return null;
    }

    private V removeNullKey() {
        Node<K, V> entry = table[0];
        Node<K, V> prev = null;

        while (entry != null) {
            if (entry.key == null) {
                if (prev == null) {
                    table[0] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    private void addNode(int hash, K key, V value, int index) {
        Node<K, V> e = table[index];
        table[index] = new Node<>(hash, key, value);
        table[index].next = e;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        Node<K, V>[] oldTable = table;
        table = new Node[newCapacity];
        threshold = (int) (newCapacity * LOAD_FACTOR);

        for (Node<K, V> kvEntry : oldTable) {
            Node<K, V> entry = kvEntry;
            while (entry != null) {
                int index = indexFor(entry.hash, newCapacity);
                Node<K, V> next = entry.next;
                entry.next = table[index];
                table[index] = entry;
                entry = next;
            }
        }
    }

    public int getTableSize() {
        return table.length;
    }

    public int size() {
        return size;
    }
}
