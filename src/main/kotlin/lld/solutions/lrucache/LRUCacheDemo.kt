package lld.solutions.lrucache

import LRUCache


fun main() {
    val lruCache = LRUCache<Int, String>(3)
    lruCache.put(1, "A")
    lruCache.put(2, "B")
    println(lruCache.get(1))  // Should print "A"
    lruCache.put(3, "C")
    lruCache.put(4, "D")  // This will evict "B"
    println(lruCache.get(2))  // Should print "null", as it was evicted

    val lfuCache = LFUCache<Int, String>(3)
    lfuCache.put(1, "A")
    lfuCache.put(2, "B")
    lfuCache.get(1)
    lfuCache.put(3, "C")
    lfuCache.put(4, "D")  // This will evict "2" as it's the least frequently used
    println(lfuCache.get(2))  // Should print "null", as it was evicted
}

