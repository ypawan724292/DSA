package dsa.trees

class SegmentTree(private val arr: IntArray) {

    private val n = arr.size

    /**
     * Todo : Check why tree is 4*n
     */
    private val tree = IntArray(4 * n) // Segment Tree array (4 * n is sufficient space)

    init {
        buildTree(0, 0, n - 1)
    }

    // Build the tree
    private fun buildTree(node: Int, start: Int, end: Int) {
        if (start == end) {
            // Leaf node
            tree[node] = arr[start]
        } else {
            val mid = (start + end) / 2
            val leftChild = 2 * node + 1
            val rightChild = 2 * node + 2

            // Recursively build left and right subtrees
            buildTree(leftChild, start, mid)
            buildTree(rightChild, mid + 1, end)

            // Internal node is the sum of its children
            tree[node] = tree[leftChild] + tree[rightChild] // for sum
//            tree[node] = minOf(tree[leftChild], tree[rightChild]) // for min
//            tree[node] = maxOf(tree[leftChild], tree[rightChild]) // for max
            // for frequency
            tree[node] = tree[leftChild] + tree[rightChild]
        }
    }


    fun sumQueryRange(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
        // If the range is outside the bounds
        if (right < start || left > end) return 0

        // If the current segment is completely within the range
        if (left <= start && end <= right) return tree[node]

        // Partial overlap
        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        val leftSum = sumQueryRange(leftChild, start, mid, left, right)
        val rightSum = sumQueryRange(rightChild, mid + 1, end, left, right)

        return leftSum + rightSum
    }


    fun updateValue(node: Int, start: Int, end: Int, idx: Int, value: Int) {
        if (start == end) {
            // Leaf node
            arr[idx] = value
            tree[node] = value
        } else {
            val mid = (start + end) / 2
            val leftChild = 2 * node + 1
            val rightChild = 2 * node + 2

            if (idx <= mid) {
                // Update in the left subtree
                updateValue(leftChild, start, mid, idx, value)
            } else {
                // Update in the right subtree
                updateValue(rightChild, mid + 1, end, idx, value)
            }

            // Update the current node
            tree[node] = tree[leftChild] + tree[rightChild]
        }
    }

    fun minQueryRange(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
        // If the range is outside the bounds
        if (right < start || left > end) return Int.MAX_VALUE

        // If the current segment is completely within the range
        if (left <= start && end <= right) return tree[node]

        // Partial overlap
        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        val leftMin = minQueryRange(leftChild, start, mid, left, right)
        val rightMin = minQueryRange(rightChild, mid + 1, end, left, right)

        return minOf(leftMin, rightMin)
    }


    fun maxQueryRange(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
        // If the range is outside the bounds
        if (right < start || left > end) return Int.MIN_VALUE

        // If the current segment is completely within the range
        if (left <= start && end <= right) return tree[node]

        // Partial overlap
        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        val leftMax = maxQueryRange(leftChild, start, mid, left, right)
        val rightMax = maxQueryRange(rightChild, mid + 1, end, left, right)

        return maxOf(leftMax, rightMax)
    }

    fun kthMinimumQuery(node: Int, start: Int, end: Int, k: Int): Int {
        if (start == end) return start

        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        return if (tree[leftChild] >= k) {
            kthMinimumQuery(leftChild, start, mid, k)
        } else {
            kthMinimumQuery(rightChild, mid + 1, end, k - tree[leftChild])
        }
    }


    fun kthMaximumQuery(node: Int, start: Int, end: Int, k: Int): Int {
        if (start == end) return start

        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        return if (tree[rightChild] >= k) {
            kthMaximumQuery(rightChild, mid + 1, end, k)
        } else {
            kthMaximumQuery(leftChild, start, mid, k - tree[rightChild])
        }
    }

    fun frequencyQuery(node: Int, start: Int, end: Int, left: Int, right: Int, value: Int): Int {
        // If the range is outside the bounds
        if (right < start || left > end) return 0

        // If the current segment is completely within the range
        if (left <= start && end <= right) return tree[node]

        // Partial overlap
        val mid = (start + end) / 2
        val leftChild = 2 * node + 1
        val rightChild = 2 * node + 2

        val leftFreq = frequencyQuery(leftChild, start, mid, left, right, value)
        val rightFreq = frequencyQuery(rightChild, mid + 1, end, left, right, value)

        return leftFreq + rightFreq
    }


}

// Example usage
fun main() {
    val arr = intArrayOf(1, 3, 5, 7, 9, 11)
    val segTree = SegmentTree(arr)

}
