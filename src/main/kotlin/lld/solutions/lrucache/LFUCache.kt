package lld.solutions.lrucache

import java.util.*

class LFUCache<K, V>(private val capacity: Int) {
    private val cache: HashMap<K, Node<K, V>> = HashMap(capacity)
    private val frequencyMap: HashMap<Int, LinkedHashSet<K>> = HashMap()  // Map of frequency -> Keys with that frequency
    private val frequencyCount: HashMap<K, Int> = HashMap()  // Map of Key -> Frequency
    private var minFreq = 0
    private var size = 0

    init {
        if (capacity <= 0) throw IllegalArgumentException("Capacity must be greater than zero")
    }

    // Get value for a key
    fun get(key: K): V? {
        cache[key]?.let {
            increaseFrequency(key)
            return it.value
        }
        return null
    }

    // Put value in the cache
    fun put(key: K, value: V) {
        if (capacity == 0) return  // Edge case if cache capacity is zero

        // If key exists, update the value and frequency
        if (cache.containsKey(key)) {
            cache[key]?.value = value
            increaseFrequency(key)
        } else {
            if (size >= capacity) {
                evictLeastFrequent()
            }
            cache[key] = Node(key, value)
            frequencyCount[key] = 1
            frequencyMap.computeIfAbsent(1) { LinkedHashSet() }.add(key)
            minFreq = 1
            size++
        }
    }

    // Increase the frequency of the given key
    private fun increaseFrequency(key: K) {
        val freq = frequencyCount[key] ?: return
        val newFreq = freq + 1
        frequencyCount[key] = newFreq

        // Move the key to the new frequency set
        frequencyMap[freq]?.remove(key)
        frequencyMap.computeIfAbsent(newFreq) { LinkedHashSet() }.add(key)

        // If the current frequency set is empty, we might need to update minFreq
        if (frequencyMap[freq]?.isEmpty() == true) {
            frequencyMap.remove(freq)
            if (minFreq == freq) {
                minFreq++
            }
        }
    }

    // Evict the least frequently used item
    private fun evictLeastFrequent() {
        val leastFrequentKeys = frequencyMap[minFreq] ?: return
        // Evict the first key in the set
        val evictKey = leastFrequentKeys.iterator().next()
        leastFrequentKeys.remove(evictKey)

        // Remove from cache
        cache.remove(evictKey)
        frequencyCount.remove(evictKey)

        // If frequency list for minFreq is empty, we update minFreq
        if (leastFrequentKeys.isEmpty()) {
            frequencyMap.remove(minFreq)
        }
        size--
    }
}
