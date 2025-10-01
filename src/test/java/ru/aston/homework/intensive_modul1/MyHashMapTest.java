package ru.aston.homework.intensive_modul1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MyHashMapTest {
    private MyHashMap<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MyHashMap<>();
    }

    @Test
    void testInitialCapacity() {
        assertEquals(16, map.getTableSize());
        assertEquals(0, map.size());
    }

    @Test
    void testPutAndGet() {
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        assertEquals(3, map.size());
        assertEquals(1, map.get("key1"));
        assertEquals(2, map.get("key2"));
        assertEquals(3, map.get("key3"));
    }

    @Test
    void testPutSameKey() {
        map.put("key", 1);
        map.put("key", 2);

        assertEquals(1, map.size());
        assertEquals(2, map.get("key"));
    }

    @Test
    void testNullKey() {
        map.put(null, 1);
        assertNull(map.get(null));
    }

    @Test
    void testRemove() {
        assertNull(map.remove(null));

        map.put("one", 1);
        map.put("two", 2);

        assertEquals(2, map.remove("two"));
        assertEquals(1, map.size());
        assertNull(map.get("two"));
        assertNull(map.remove("nonexistent"));
    }

    @Test
    void testRemoveMiddleElement() {
        map.put("first", 1);
        map.put("middle", 2);
        map.put("last", 3);

        assertEquals(3, map.size());
        assertEquals(1, map.get("first"));
        assertEquals(2, map.get("middle"));
        assertEquals(3, map.get("last"));
        assertEquals(2, map.remove("middle"));
        assertEquals(2, map.size());
        assertEquals(1, map.get("first"));
        assertEquals(3, map.get("last"));
    }

    @Test
    void testRemoveFromLongChain() {
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, i);
            map.put("esta" + i, i);
            map.put("test" + i, i);
        }

        assertEquals(30, map.size());

        map.remove("esta5");

        assertNull(map.get("esta5"));
        assertEquals(29, map.size());

        assertEquals(6, (int) map.get("esta6"));
        assertEquals(4, (int) map.get("esta4"));
    }

    @Test
    void testResize() {
        for (int i = 0; i < 12; i++) {
            map.put("key" + i, i);
        }
        assertEquals(32, map.getTableSize());
    }

    @Test
    void testEmptyMap() {
        assertNull(map.get("nonexistent"));
        assertEquals(0, map.size());
        assertNull(map.remove("nonexistent"));
    }

    @Test
    void testLargeValues() {
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }
        assertEquals(100, map.size());
        assertEquals(99, map.get("key99"));
    }

    @Test
    void testConcurrentModification() {
        map.put("one", 1);
        map.put("two", 2);

        map.remove("one");
        map.put("three", 3);

        assertEquals(2, map.size());
        assertEquals(3, (int) map.get("three"));
    }

    @Test
    void testEmptyStringKey() {
        map.put("", 1);
        assertEquals(1, (int) map.get(""));

        map.remove("");
        assertNull(map.get(""));
    }
}

