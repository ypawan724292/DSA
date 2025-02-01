package dsa.tries

class Trie {

    /**
     * Trie : A Trie is a tree-like data structure whose nodes store the letters of an alphabet
     * Problem Statement: Implement a Trie Data Structure which supports the following three operations:
     *
     * Search (word): To check if the string `word` is present in the Trie or not.
     * Insert (word): To insert a string `word` in the Trie.
     * Start With(word): To check if there is a string that has the prefix `word`.
     *

     * You may assume that all inputs are consist of lowercase letters a-z.
     *
     * We need ask ourselves the following questions:
     * 1. What is the structure of the Trie?
     *        Trie is a data structure that is like a tree data structure in its organisation.
     *        It consists of nodes that store letters or alphabets of words, which can be added, retrieved,
     *        and deleted from it in a very efficient way.
     * 2. How to insert a word into the Trie?
     *         To insert a word into the Trie, we start from the root node and check if the current node has the letter of the word.
     * 3. How to search a word in the Trie?
     *          To search a word in the Trie, we start from the root node and check if the current node has the letter of the word
     * 4. How to search a prefix in the Trie?
     *          To search a prefix in the Trie, we start from the root node and check if the current node has the letter of the prefix.
     */

    private data class TrieNode(
        private val children: MutableMap<Char, TrieNode> = mutableMapOf(),
        private var isEndOfWord: Boolean = false
    ) {
        fun containsKey(char: Char) = children.containsKey(char)

        fun get(char: Char) = children[char]

        fun put(char: Char, node: TrieNode) {
            children[char] = node
        }

        fun setEndOfWord() {
            isEndOfWord = true
        }

        fun isEnd() = isEndOfWord

        fun dfs(node: TrieNode, prefix: String, result: MutableList<String>) {
            if (node.isEnd()) {
                result.add(prefix)
            }
            for (ch in node.children.keys) {
                dfs(node.get(ch)!!, prefix + ch, result)
            }
        }

    }

    private val root = TrieNode()

    /**
     * Inserts a word into the trie.
     */
    fun insert(word: String) {
        var node = root
        for (char in word) {
            if (!node.containsKey(char)) {
                node.put(char, TrieNode())
            }
            node = node.get(char)!!
        }
        node.setEndOfWord()
    }

    /**
     * Search a word in the trie.
     * TC : O(n)
     */
    fun search(word: String): Boolean {
        var node = root
        for (char in word) {
            if (!node.containsKey(char)) {
                return false
            }
            node = node.get(char)!!
        }
        return node.isEnd()
    }

    /**
     * Search a prefix in the trie.
     * TC : O(n)
     */
    fun startsWith(prefix: String): Boolean {
        var node = root
        for (char in prefix) {
            if (!node.containsKey(char)) {
                return false
            }
            node = node.get(char)!!
        }
        return true
    }

    fun autocomplete(prefix: String): List<String> {
        val result = mutableListOf<String>()
        var node = root
        for (char in prefix) {
            if (!node.containsKey(char)) {
                return result
            }
            node = node.get(char)!!
        }
        node.dfs(node, prefix, result)
        return result
    }

    /**
     * Check if all the prefixes of the word exists in the trie.
     */
    fun checkIfAllPrefixExists(word: String): Boolean {
        var node = root
        for (ch in word) {
            if (!node.containsKey(ch)) {
                return false
            }
            node = node.get(ch)!!
            if (!node.isEnd()) {
                return false
            }
        }
        return true
    }

}

fun main() {
    val trie1 = Trie()
    trie1.insert("apple")
    println(trie1.search("apple")) // returns true
    println(trie1.search("app")) // returns false
    println(trie1.startsWith("app")) // returns true
    trie1.insert("app")
    println(trie1.search("app")) // returns true
}