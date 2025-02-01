package dsa.tries


class Problems {

    /**
     *Problem Statement: Implement a Trie data structure that supports the following methods:
     *
     * Insert (word): To insert a string `word` in the Trie.
     * Count Words Equal To (word): Return the count of occurrences of the string word in the Trie.
     * Count Words Starting With (prefix): Return the count of words in the Trie that have the string “prefix” as a prefix.
     * Erase (word): Delete one occurrence of the string word from the Trie.
     * Note:
     *
     * The Erase(word) function is guaranteed to be called only when a word is present in the Trie.
     * Release the memory associated with variables using dynamic memory allocation at the end of your solution.
     *
     * Example 1:
     * Input:
     * Insert: ‘apple’, ‘apps’, ‘apxl’
     * Count Number of Words Equal to: ‘apple’
     * Count Number of Words Starting with: ‘app’, ‘ap’
     * Erase word: ‘apxl’
     * Output:
     * Inserted ‘apple’, Inserted ‘apps’,Inserted, ‘apxl’.
     * Number of Words Equal to ‘apple’: 1
     * Number of Words Starting with ‘app’: 2 and ‘ap’: 3
     * Erased ‘apxl’
     *
     */
    data class TrieNode2(
        private val children: MutableMap<Char, TrieNode2> = mutableMapOf(),
        var prefixCount: Int = 0,
        var wordCount: Int = 0
    ) {

        fun containsKey(char: Char) = children.containsKey(char)

        fun put(char: Char, node: TrieNode2) {
            children[char] = node
        }

        fun get(char: Char) = children[char]

        fun incrementPrefixCount() {
            prefixCount++
        }

        fun incrementWordCount() {
            wordCount++
        }

        fun decrementPrefixCount() {
            prefixCount--
        }

        fun decrementWordCount() {
            wordCount--
        }
    }

    inner class Trie2 {

        private val root = TrieNode2()

        fun insert(word: String) {
            var node = root
            for (char in word) {
                if (!node.containsKey(char)) {
                    node.put(char, TrieNode2())
                }
                node = node.get(char)!!
                node.incrementPrefixCount()
            }
            node.incrementWordCount()
        }

        fun countWordsEqualTo(word: String): Int {
            var node = root
            for (char in word) {
                if (!node.containsKey(char)) {
                    return 0
                }
                node = node.get(char)!!
            }
            return node.wordCount
        }


        fun countWordsStartingWith(prefix: String): Int {
            var node = root
            for (char in prefix) {
                if (!node.containsKey(char)) {
                    return 0
                }
                node = node.get(char)!!
            }
            return node.prefixCount
        }

        fun erase(word: String) {
            var node = root
            for (char in word) {
                if (!node.containsKey(char)) {
                    return
                }
                node = node.get(char)!!
                node.decrementPrefixCount()
            }
            node.decrementWordCount()
        }
    }


    /**
     * Problem statement
     * Given an array of strings, ‘A’ of size ‘N’.
     * Each element of this array is a string. The teacher taught Ninja about prefixes in the past, so he wants to test his knowledge.
     *
     * A string is called a complete string if every prefix of this string is also present in the array ‘A’.
     * Ninja is challenged to find the longest complete string in the array ‘A’.
     * If there are multiple strings with the same length, return the lexicographically smallest one and if no string exists, return "None".
     *
     * Note :
     * String ‘P’ is lexicographically smaller than string ‘Q’, if :
     *
     * 1. There exists some index ‘i’ such that for all ‘j’ < ‘i’ , ‘P[j] = Q[j]’ and ‘P[i] < Q[i]’. E.g. “ninja” < “noder”.
     *
     * 2. If ‘P’ is a prefix of string ‘Q’, e.g. “code” < “coder”.
     * Example :
     * N = 4
     * A = [ “ab” , “abc” , “a” , “bp” ]
     *
     * Explanation :
     *
     * Only prefix of the string “a” is “a” which is present in array ‘A’. So, it is one of the possible strings.
     *
     * Prefixes of the string “ab” are “a” and “ab” both of which are present in array ‘A’. So, it is one of the possible strings.
     *
     * Prefixes of the string “bp” are “b” and “bp”. “b” is not present in array ‘A’. So, it cannot be a valid string.
     *
     * Prefixes of the string “abc” are “a”,“ab” and “abc” all of which are present in array ‘A’. So, it is one of the possible strings.
     *
     * We need to find the maximum length string, so “abc” is the required string.
     *
     *
     * Detailed explanation ( Input/output format, Notes, Images )
     * Constraints :
     * 1 <= T <= 10
     * 1 <= N <= 10^5
     * 1 <= A[i].length <= 10^5
     * A[i] only consists of lowercase english letters.
     * Sum of A[i].length <= 10^5 over all test cases
     *
     * Time Limit : 1 sec
     * Sample Input 1 :
     * 2
     * 6
     * n ni nin ninj ninja ninga
     * 2
     * ab bc
     * Sample Output 1 :
     * ninja
     * None
     * Explanation Of Sample Input 1 :
     * For test case 1 we have,
     *
     * All the prefixes of “ninja” -> “n”, “ni”, “nin”, “ninj” and “ninja” are present in array ‘A’.
     * So, “ninja” is a valid answer whereas for “ninga” , the prefix “ning” is not present in array ‘A’.
     *
     * So we output “ninja”.
     *
     * For test case 2 we have,
     *
     * The prefixes of “ab” are “a” and “ab”. “a” is not present in array ‘A’. So, “ab” is not a valid answer.
     *
     * The prefixes of “bc” are “b” and “bc”. “b” is not present in array ‘A’. So, “ab” is not a valid answer.
     *
     * Since none of the strings is a valid answer we output “None”.
     * Sample Input 2 :
     * 2
     * 5
     * g a ak szhkb hy
     * 4
     * kez vfj vfjq vfjqo
     * Sample Output 2 :
     * ak
     * None
     */
    fun longestCompleteString(arr: Array<String>): String {
        val trie = Trie()

        for (word in arr) {
            trie.insert(word)
        }

        var longestCompleteString = ""

        for (word in arr) {
            if (trie.checkIfAllPrefixExists(word)) {
                if (word.length > longestCompleteString.length) {
                    longestCompleteString = word
                } else if (word.length == longestCompleteString.length) {
                    longestCompleteString = if (word < longestCompleteString) word else longestCompleteString
                }
            }
        }
        return if (longestCompleteString.isEmpty()) "None" else longestCompleteString
    }

    /**
     * Problem statement
     * Given a string 'S', you are supposed to return the number of distinct substrings(including empty substring) of the given string.
     * You should implement the program using a trie.
     *
     * Note :
     * A string ‘B’ is a substring of a string ‘A’ if ‘B’ that can be obtained by deletion of,
     * several characters(possibly none) from the start of ‘A’ and several characters(possibly none) from the end of ‘A’.
     *
     * Two strings ‘X’ and ‘Y’ are considered different if there is at least one index ‘i’
     * such that the character of ‘X’ at index ‘i’ is different from the character of ‘Y’ at index ‘i’(X[i]!=Y[i]).
     *
     * Example :
     * Input: "ababa"
     * Distinct Substrings: ["a", "ab", "aba", "abab", "ababa", "b", "ba", "bab", "baba", "a", "ab", "aba", "b", "ba", "a"]
     * Output: 10
     *
     */
    fun countDistinctSubstrings(s: String): Int {

        /*
            Intution:
            1. We can find the number of distinct substrings of a string using a trie.
            2. We can insert all the suffixes of the string into the trie.
            3. The number of nodes in the trie will be the number of distinct substrings of the string.
                Because each node represents a distinct substring.

               Exapmle: "ababa"
               Suffixes: "ababa", "baba", "aba", "ba", "a"
               Distinct Substrings: ["a", "ab", "aba","abab", "ababa", "b", "ba", "bab", "baba"] +1 for empty string
               count = 10
         */
        data class TrieNode(
            val children: MutableMap<Char, TrieNode> = mutableMapOf(),
            var isEndOfWord: Boolean = false
        ) {
            fun containsKey(char: Char) = children.containsKey(char)
            fun get(char: Char) = children[char]
            fun put(char: Char, node: TrieNode) {
                children[char] = node
            }
        }

        val root = TrieNode()
        var count = 0
        for (i in s.indices) {
            var node = root
            for (j in i until s.length) {
                if (!node.containsKey(s[j])) {
                    node.put(s[j], TrieNode())
                    count++
                }
                node = node.get(s[j])!!
            }
        }

        return count + 1 // +1 for empty string or input string
    }

    /**
     * Longest Common Prefix
     * Problem Statement:
     * Given an array of strings, ‘A’ of size ‘N’.
     * Find the longest common prefix string amongst all the strings present in the array.
     *
     * Example:
     * Input: [“apple”, “ape”, “april”]
     * Output: “ap”
     */
    fun longestCommonPrefix(arr: Array<String>): String {
        data class TrieNode(
            val children: MutableMap<Char, TrieNode> = mutableMapOf(),
            var count: Int = 0
        ) {
            fun containsKey(char: Char) = children.containsKey(char)
            fun get(char: Char) = children[char]
            fun put(char: Char, node: TrieNode) {
                children[char] = node
            }
        }

        val root = TrieNode()


        fun insert(word: String) {
            var node = root
            for (char in word) {
                if (!node.containsKey(char)) {
                    node.put(char, TrieNode())
                }
                node = node.get(char)!!
                node.count++
            }
        }

        for (word in arr) {
            insert(word)
        }

        var prefix = ""
        var node = root
        while (node.count == arr.size) {
            val char = node.children.keys.first()
            prefix += char
            node = node.get(char)!!
        }

        return prefix
    }


    /**
     * Bits and Tries
     *
     * Bits Prequisite:
     *
     * 1. Bitwise &  -> 1 & 1 = 1, 0 & 0 = 0, 1 & 0 = 0, 0 & 1 = 0
     * 2. Bitwise | -> 1 | 1 = 1, 0 | 0 = 0, 1 | 0 = 1, 0 | 1 = 1
     * 3. Bitwise ^ -> 1 ^ 1 = 0, 0 ^ 0 = 0, 1 ^ 0 = 1, 0 ^ 1 = 1
     * 4. Right shift >>  -> 5 >> 1 = 2, 5 >> 2 = 1
     *          5 is 101 , 5 >> 1 = 010 = 2
     *                     5 >> 2 = 001 = 1
     *
     * 5. Left shift <<  -> 5 << 1 = 10, 5 << 2 = 20
     *
     */
    fun findIfBitIsSet(num: Int, i: Int): Boolean {
        return (num shr i) and 1 != 0
    }

    fun turnONBit(num: Int, i: Int): Int {
        return num or (1 shl i)
    }


    /**
     * Problem statement
     *
     * Given an array and number. find the maximum XOR of a number with any number in the array.
     *
     * Example:
     * Input: [3, 10, 5, 25, 2, 8], 5
     * Output: 28
     */
    fun findMaximumXOR(arr: IntArray, num: Int): Int {
        /**
         * Using Trie we can
         */
        var max = 0
        var mask = 0


        for (i in 31 downTo 0) {
            mask = mask or (1 shl i)
            val set = mutableSetOf<Int>()
            for (element in arr) {
                set.add(element and mask)
            }
            val temp = max or (1 shl i)
            for (prefix in set) {
                if (set.contains(temp xor prefix)) {
                    max = temp
                    break
                }
            }
        }
//        return max xor num


        // Another approach using Trie

        class Trie {
            inner class TrieNode(
                val children: MutableMap<Int, TrieNode> = mutableMapOf()
            ) {
                fun containsKey(char: Int) = children.containsKey(char)
                fun get(char: Int) = children[char]
                fun put(char: Int, node: TrieNode) {
                    children[char] = node
                }
            }

            //using map for children can give MLE for large inputs, bcuz map takes more space than array
            inner class TrieNode2(
                val children: Array<TrieNode?> = arrayOf(null, null)
            ) {
                fun containsKey(char: Int) = children[char] != null
                fun get(char: Int) = children[char]
                fun put(char: Int, node: TrieNode) {
                    children[char] = node
                }

            }


            private val root = TrieNode()

            fun insert(num: Int) {
                var node = root
                for (i in 31 downTo 0) {
                    val bit = (num shr i) and 1
                    if (!node.containsKey(bit)) {
                        node.put(bit, TrieNode())
                    }
                    node = node.get(bit)!!
                }
            }

            fun maxXOR(num: Int): Int {
                var node = root
                var maxXor = 0

                for (i in 31 downTo 0) {
                    val bit = (num shr i) and 1
                    if (node.containsKey(1 - bit)) {
                        maxXor = maxXor or (1 shl i)
                        node = node.get(1 - bit)!!
                    } else {
                        node = node.get(bit)!!
                    }
                }
                return maxXor
            }
        }


        val trie = Trie()
        for (element in arr) {
            trie.insert(element)
        }
//        return trie.maxXOR(num)
        /*
        If given two arrays and we need to find the maximum XOR of two numbers from the two arrays.

         */

        var max2 = 0
        var arr2 = intArrayOf(3, 10, 5, 25, 2, 8)

        for (element in arr2) {
            max2 = maxOf(max2, trie.maxXOR(element))
        }

        //If given array find maxOr of any two numbers in the array using trie , it gives MLE then we can use below approach
        var max3 = 0
        for (i in arr.indices) {
            max3 = maxOf(max3, trie.maxXOR(arr[i]))
        }




        return max2
    }


    /**
     *
     * Maximum Xor Queries | Trie
     *
     *
     * Problem Statement:
     * Given an array of non-negative integers and an array of queries where each query is a pair of two non-negative integers ie. [Xi, Ai]
     *
     * The answer to the ith query is the maximum bitwise XOR value of the Xi
     * with any integer less than or equal to Ai in the array.
     *
     * Return an array consisting of the results of these queries.
     * Note: If all integers are greater than ‘Ai’ in array/list ‘ARR’ then the answer to this with the query will be -1.
     *
     *
     * Example :
     * Input: Given Array: [3, 10, 5, 25, 2, 8], Queries: [(0, 1), (1, 2), (0, 3), (3, 3)]
     * Output:[-1, 3, 3, 1]
     * Explanation: Result of Max XOR Queries:
     * Query (0,1):
     *
     * We need to find the maximum XOR value of 0 with any integer less than or equal to 1 in the array.
     * The integers in the array less than or equal to 1 are: None.
     * Since there are no integers less than or equal to 1 in the array, the answer is -1.
     * Query (1, 2):
     *
     * We need to find the maximum XOR value of 1 with any integer less than or equal to 2 in the array.
     * The integers in the array less than or equal to 2 are: 2.
     * The XOR value is : 1 XOR 2 = 3.
     * The maximum XOR value is 3.
     * Query (0, 3):
     *
     * We need to find the maximum XOR value of 0 with any integer less than or equal to 3 in the array.
     * The integers in the array less than or equal to 3 are: 3, 2.
     * The XOR values are: 0 XOR 3 = 3, 0 XOR 2 = 2
     * The maximum XOR value is 3.
     * Query (3, 3):
     *
     * We need to find the maximum XOR value of 3 with any integer less than or equal to 3 in the array.
     * The integers in the array less than or equal to 3 are: 3, 2.
     * The XOR values are: 3 XOR 3 = 0, 3 XOR 2 = 1
     * The maximum XOR value is 1.
     *
     * Therefore, the output for the given example is [-1, 3, 3, 1].
     */
    fun maxXORWithQueries(arr: IntArray, queries: Array<IntArray>): IntArray {

        class Trie {

            inner class TrieNode(
                val children: MutableMap<Int, TrieNode> = mutableMapOf()
            ) {
                fun containsKey(value: Int) = children.containsKey(value)
                fun get(value: Int) = children[value]
                fun put(value: Int, node: TrieNode) {
                    children[value] = node
                }
            }

            private val root = TrieNode()
            fun insert(num: Int) {
                var node = root
                for (i in 31 downTo 0) {
                    val bit = (num shr i) and 1
                    if (!node.containsKey(bit)) {
                        node.put(bit, TrieNode())
                    }
                    node = node.get(bit)!!
                }
            }

            fun maxXOR(num: Int): Int {
                var node = root
                var maxXor = 0

                for (i in 31 downTo 0) {
                    val bit = (num shr i) and 1
                    if (node.containsKey(1 - bit)) {
                        maxXor = maxXor or (1 shl i)
                        node = node.get(1 - bit)!!
                    } else {
                        node = node.get(bit)!!
                    }
                }
                return maxXor
            }
        }

        val trie = Trie()

        arr.sort()
        val offlineQueries = mutableListOf<IntArray>()
        for (i in queries.indices) {
            val (x, a) = queries[i]
            offlineQueries.add(intArrayOf(x, a, i))
        }
        offlineQueries.sortBy { it[1] }

        val res = IntArray(queries.size) { -1 }

        for (query in offlineQueries) {
            var i = 0
            while (i < arr.size && arr[i] <= query[1]) {
                trie.insert(arr[i])
                i++
            }

            if (i != 0) {
                res[query[2]] = trie.maxXOR(query[0])
            }
        }

        return res
    }
}

fun main() {
    val problems = Problems()
    println(
        problems.maxXORWithQueries(
            intArrayOf(0, 1, 2, 3, 4), arrayOf(intArrayOf(3, 1), intArrayOf(1, 3), intArrayOf(5, 6))
        ).contentToString()
    )
}