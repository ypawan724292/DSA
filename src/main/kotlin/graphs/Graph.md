kotlin
```
    fun dfs(graph : Map<Int,List<Int>>, start: Int, visited: MutableSet<Int>) {
        visited.add(start)
        for (neighbor in graph[start]!!) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited)
            }
        }
        
    }
    
    
    fun shortestPath(graph : Array<IntArray>, start: IntArray, end : IntArray): Int{
        val queue = ArrayDeque<IntArray>()
        queue.add(start)
        graph[start[0]][start[1]] = 0
        
        val dir = listOf(intArrayOf()
        var dist = 0 
        
    }
```