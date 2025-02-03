import lld.solutions.lrucache.Node


/**
 * 1. LRU (Least Recently Used) Cache Design
 * The LRU Cache is based on the principle that the least recently accessed element should be removed when the cache reaches its capacity. It should support the operations:
 *
 * get(key): Returns the value associated with the key, and marks it as recently used.
 * put(key, value): Adds or updates the value for the key, and marks it as recently used.
 * To achieve O(1) time complexity for both get and put, we will use:
 *
 * A HashMap for fast lookup by key.
 * A Doubly Linked List to track the order of access (most recent to least recent).
 */
class LRUCache<K, V>(private val capacity: Int) {
    private val cache: HashMap<K, Node<K, V>> = HashMap(capacity)
    private var size = 0
    private val head = Node<K, V>(key = null, value = null) // Dummy head node
    private val tail = Node<K, V>(key = null, value = null) // Dummy tail node

    init {
        head.next = tail
        tail.prev = head
    }

    // Remove node from the linked list
    private fun remove(node: Node<K, V>) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    // Insert node at the end (most recently used)
    private fun add(node: Node<K, V>) {
        node.prev = tail.prev
        node.next = tail
        tail.prev?.next = node
        tail.prev = node
    }

    // Get value by key
    fun get(key: K): V? {
        cache[key]?.let { node ->
            remove(node)
            add(node)  // Move the node to the end (most recent)
            return node.value
        }
        return null
    }

    // Put value into the cache
    fun put(key: K, value: V) {
        cache[key]?.let { node ->
            // Remove the old node and insert a new one
            remove(node)
            node.value = value
            add(node)
        } ?: run {
            // If it's a new key
            val newNode = Node(key, value)
            if (size == capacity) {
                // Remove the least recently used (LRU) node
                cache.remove(head.next?.key)
                remove(head.next!!)
            } else {
                size++
            }
            add(newNode)
            cache[key] = newNode
        }
    }
}
