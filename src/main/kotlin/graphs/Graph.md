Algorithm	            Time Complexity	                Space Complexity
Single Source BFS	    O(V + E)	                    O(V + E)
Multi-Source BFS	    O(V + E)	                    O(V + E)
Dijkstra	            O((V + E) log V) (with PQ)	    O(V + E)
Kahn’s Topological Sort	O(V + E)	                    O(V + E)
Union-Find	            O(α(V)) (amortized for union/find)	O(V)   α(V) = Inverse Ackermann function, nearly constant in practical scenarios.
Kruskal’s MST	        O(E log E + E α(V))	            O(V + E)
Check Bipartite BFS	    O(V + E)	                    O(V)
Bellman-Ford	        O(V * E)	                    O(V)
Kosaraju’s Algorithm	O(V + E)	                    O(V + E)
Tarjan’s SCC	        O(V + E)	                    O(V)





Clarify the Problem
1. Graph Representation
   Input Format:

How is the graph represented in the input (e.g., adjacency matrix, adjacency list, edge list)?
Is the graph directed, undirected, or mixed?
Are the nodes labeled with integers, strings, or something else?
Weighted Graphs:

Are the edges weighted? If so, can the weights be negative or zero?
Should I assume all nodes/edges have weights if unspecified?
2. Graph Properties
   Type of Graph:

Is the graph connected, or could it be disconnected?
Is it a tree (acyclic connected graph)?
Could the graph have cycles, or is it guaranteed to be acyclic?
Is the graph sparse (few edges) or dense (many edges)?
Special Characteristics:

Is the graph directed acyclic (DAG)?
Are there any guarantees, such as all nodes being reachable from a specific node?
Are there self-loops or parallel edges?
3. Problem Constraints
   Scale of Input:

What is the maximum number of nodes and edges?
Should I optimize for time complexity, space complexity, or both?
Is the graph static, or will it change dynamically (e.g., adding/removing edges/nodes)?
Edge Cases:

How should I handle graphs with no edges or no nodes?
What if the graph has a single node or is fully connected?
Are there special rules for disconnected components?
4. Problem-Specific Clarifications
   Pathfinding:

Should I find the shortest path, the longest path, or all possible paths?
Do I need to return just the path length or the actual path?
Traversal:

Should I use BFS, DFS, or a specific traversal technique?
Are there specific traversal requirements (e.g., visiting nodes in a specific order)?
Cycle Detection:

Should I detect cycles, and if so, do I need to return the cycle or just confirm its existence?
Connected Components:

Should I return all connected components, or just the count of components?
5. Output Requirements
   Output Format:

Should I return a list, a single value, or a specific data structure?
Should the result be sorted or presented in a particular order?
Ambiguity in Results:

If multiple solutions are possible, does it matter which one I choose?
6. Algorithm Choice and Optimization
   Algorithm Constraints:

Should I implement a specific algorithm (e.g., Dijkstra, Bellman-Ford, Floyd-Warshall)?
Can I use prebuilt libraries, or should I implement from scratch?
Optimization:

Should I prioritize time complexity (e.g.,
𝑂
(
𝑉
+
𝐸
)
O(V+E)) or space usage?
Can I assume the graph fits in memory, or do I need to handle it incrementally?
7. Practical Application
   Real-World Context:

Does this problem represent a real-world use case (e.g., social network, transportation network)?
Are there domain-specific assumptions I should make?
Dynamic Graphs:

Will the graph be updated dynamically (e.g., adding/removing nodes or edges)?
Should I optimize for repeated queries on the same graph?
8. Validation and Testing
   Validation:

Should I validate the input for correctness (e.g., check for invalid edges or weights)?
Can the graph have isolated nodes with no edges?
Testing:

Are there specific test cases or scenarios you'd like me to cover (e.g., disconnected graph, negative weights)?