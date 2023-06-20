/**
Given an array of pairs of values representing edges in an unweighted graph, 
create the equivalent adjacency list/set representation (a map from element 
to a list or set of elements). Pairs represent directed edges: (A, B) means 
there is an edge from A to B. If the pair (B, A) is also provided then there 
is an undirected edge between A and B. For simplicity, you may assume that 
each node of the graph stores an integer rather than a generic data type and 
that the elements are distinct. Implement a basic DFS and BFS searching for 
a target value and a topological sort (using either DFS or Kahn’s algorithm).
 */
import java.io.*;
import java.util.*;

 public class AdjacencyList{
    private Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    // Build an adjacency list representation given an array of directed edges 
    public AdjacencyList(Pair[] edges) {
        int a, b; // edge from a to b
        Set<Integer> adjacencySet;
        for (Pair item : edges){
            a = item.x; 
            b = item.y;
            if (!adjacencyList.containsKey(a)) {
                adjacencySet = new HashSet<Integer>();
            } else {
                adjacencySet = adjacencyList.get(a);
            }
            adjacencySet.add(b);
            adjacencyList.put(a, adjacencySet);
            if (!adjacencyList.containsKey(b)) {
                adjacencySet = new HashSet<Integer>();
                adjacencyList.put(b, adjacencySet);
            }
        }
    }

    public Set<Integer> getKey(int key){
        //System.out.println("get key: " + key);
        return adjacencyList.get(key);
    }

    public Set<Integer> keys(){
        return adjacencyList.keySet();
    }

    public Collection<Set<Integer>> values(){
        return adjacencyList.values();
    }

    public int size(){
        return adjacencyList.size();
    }

    public String printAdjacency(){
        String output = "";
        for (int key : adjacencyList.keySet()) {
            output += key + ": [";
            for (int value : adjacencyList.get(key)) {
                output += value + ", ";
            }
            output += "]\n";
        }
        return output;
    }

    /* Visit the nearest nodes first and then branch out. */
    public boolean bfs(int target, AdjacencyList list) {
        // need to create a deep clone/copy of the set so when we delete from unvisited, we don't delete the original
        Set<Integer> unvisited = new HashSet<>(list.keys()); 
        int total = unvisited.size();
        LinkedList<Integer> queue = new LinkedList<>();
        // since this is a directed graph, I need to keep checking that I have visited all vertices
        while (unvisited.size() != 0) {
            // get a vertex that has not been visited
            int curr = getItem(unvisited);
            if (curr == -1) {
                return false; // there are no vertices in the adjacency list
            }
            queue.add(curr);
            // start bfs - while the queue is nnot empty
            while (queue.size() != 0) {
                curr = queue.poll(); // get the first item in queue
                //System.out.println("curr: " + curr);
                if (curr == target) {
                    return true;
                }
                Set<Integer> neighbours = list.getKey(curr);
                //System.out.println("neighbours: " + neighbours);
                Iterator<Integer> itr = neighbours.iterator();
                while (itr.hasNext()) {
                    //System.out.println("in");
                    int n = itr.next();
                    if (unvisited.remove(n)) { // will remove only if it exists. otherwise returns false
                        // if unvisited (so it was initally in the unvisited set) then we add it to the queue
                        queue.add(n);
                    }
                }
                unvisited.remove(curr);
            }
        }
        return false; // was not found
    }

    public int getItem(Set<Integer> set) {
        Iterator<Integer> itr = set.iterator();
        if (itr.hasNext()) {
            // get the first item 
            return itr.next();
        } else {
            return -1; // the list is empty
        }
    }

    public boolean dfs(int target, AdjacencyList list) {
        // create a copy of all vertices 
        Set<Integer> unvisited = new HashSet<>(list.keys());
        // create a stack to represent dfs order
        Stack<Integer> stack = new Stack<Integer>();
        //System.out.println("stack: " + stack);
        // while there are still unvisited vertices and the stack is not empty: 
        while (unvisited.size() != 0){
            // pick one vertex and add it to the stack
            int curr = getItem(unvisited);
            stack.push(curr);
            if (curr == -1) {
                return false; // there are no vertices in this graph
            }
            while(stack.size() != 0) {
                // get the top vertex
                curr = stack.pop();
                // check for target
                if (curr == target) {
                    return true;
                }
                // process the neighbours
                Set<Integer> neighbours = list.getKey(curr);
                Iterator<Integer> itr = neighbours.iterator();
                while (itr.hasNext()) {
                    int n = itr.next(); // current neighbour
                    // set current vertex as visited - remove returns true if the item existed and is now removed
                    // aka, that the vertex was previously unvisited, then we add it to the stack
                    if (unvisited.remove(n)) { 
                        stack.push(n);
                    }
                }
                unvisited.remove(curr); // make sure to remove the current vertex even if it did not have any neighbours
            }
        }

        return false;
    }

    /*  The first vertex in topological sorting is always a vertex with an in-degree of 0 (no incoming edges).
        General strategy: 
        - store the in-degree of all vertices
        - start with a vertex of inorder 0
        - visit the neighbours of that vertex and subtract 1 from their in-order once "done"
        - keep going until no more vertices left 
     */
    public int[] topologicalSort(AdjacencyList list){
        HashMap<Integer, Integer> degrees = new HashMap<>();
        int[] topoSorted = new int[list.size()];
        // get the in-degree of all vertices is O(E+V)
        int count;
        for (Set<Integer> adjacent : list.values()){
            for (int vert : adjacent){
                if (degrees.get(vert) == null) {
                    degrees.put(vert, 1);
                } else {
                    degrees.put(vert, degrees.get(vert) + 1);
                }
            }
        }
        // go through all vertices to fill remaining degrees as 0 - O(V)
        for (int vert : list.keys()){
            if (!degrees.containsKey(vert)){
                degrees.put(vert, 0);
            }
            //System.out.println("key: " + vert + ", value: " + degrees.get(vert));
        }
        // find and enqueue all 0 degree vertices
        LinkedList<Integer> queue = new LinkedList<>();
        for (int vert : degrees.keySet()){
            if (degrees.get(vert) == 0){
                queue.add(vert);
            }
        }
        System.out.println("queue: " + queue.toString());
        // keep processing in-degree 0 vertices
        int curr;
        int index = 0;
        while (!queue.isEmpty()){
            curr = queue.poll();
            //System.out.println("curr: " + curr);
            topoSorted[index] = curr;
            index++;
            // decrement the counts that depend on it 
            for (int dependent : list.getKey(curr)){
                int degreeCount = degrees.get(dependent);
                //System.out.println("- " + dependent + ", " + degreeCount + " - ");
                degrees.put(dependent, degreeCount - 1);
                if (degrees.get(dependent) == 0) {
                    queue.add(dependent);
                }
            }
        }
        return topoSorted;
    }

    public static void main(String[] args) {
        /* example
        Input: [(1, 2), (2, 3), (1, 3), (3, 2), (2, 0), (8,8), (1,4)]
        Output:
        {
            0: []
            1: [2, 3, 4]
            2: [0, 3]
            3: [2]
            8: [8]
            4: []
        } */
        Pair a = new Pair(1, 2);
        Pair b = new Pair(1, 3);
        Pair c = new Pair(2, 0);
        Pair d = new Pair(2, 3);
        Pair e = new Pair(3, 2);
        Pair f = new Pair(8, 8);
        Pair g = new Pair(1, 4);
        Pair[] edges = {a, b, c, d, e, f, g};
        AdjacencyList list = new AdjacencyList(edges);
        System.out.println("List: ");
        System.out.println(list.printAdjacency());

        // bfs
        System.out.println("bfs(2, list) ");
        System.out.println(list.bfs(2, list));
        System.out.println("bfs(3, list) ");
        System.out.println(list.bfs(3, list));
        System.out.println("bfs(7, list) ");
        System.out.println(list.bfs(7, list));
        System.out.println("bfs(1, list) ");
        System.out.println(list.bfs(1, list));
        System.out.println("bfs(0, list) ");
        System.out.println(list.bfs(0, list));
        System.out.println("bfs(4, list) ");
        System.out.println(list.bfs(4, list));
        System.out.println("bfs(8, list) ");
        System.out.println(list.bfs(8, list));

        // dfs 
        System.out.println("dfs(2, list)");
        System.out.println(list.dfs(2, list));
        System.out.println("dfs(3, list)");
        System.out.println(list.dfs(3, list));
        System.out.println("dfs(4, list)");
        System.out.println(list.dfs(4, list));
        System.out.println("dfs(1, list)");
        System.out.println(list.dfs(1, list));
        System.out.println("dfs(6, list)");
        System.out.println(list.dfs(6, list));
        System.out.println("dfs(8, list)");
        System.out.println(list.dfs(8, list));

        // topo sort test 1
        Pair dag1 = new Pair(5, 0);
        Pair dag2 = new Pair(5,2);
        Pair dag3 = new Pair(4,0);
        Pair dag4 = new Pair(4,1);
        Pair dag5 = new Pair(2,3);
        Pair dag6 = new Pair(3,1);
        Pair[] edgesDag = {dag1, dag2, dag3, dag4, dag5, dag6};
        AdjacencyList dagList = new AdjacencyList(edgesDag);
        System.out.println("\nDirected Acyclic Graph for topo sort: ");
        System.out.println(dagList.printAdjacency());

        System.out.println("topo(dagList)");
        System.out.println(Arrays.toString(dagList.topologicalSort(dagList)));

        // topo sort test 2
        Pair pair1 = new Pair(7,2);
        Pair pair2 = new Pair(7,1);
        Pair pair3 = new Pair(2,3);
        Pair pair4 = new Pair(2,4);
        Pair pair5 = new Pair(1,4);
        Pair pair6 = new Pair(1,5);
        Pair pair7 = new Pair(8,6);
        Pair[] edgesDag2 = {pair1, pair2, pair3, pair4, pair5, pair6, pair7};
        AdjacencyList dagList2 = new AdjacencyList(edgesDag2);
        System.out.println("\nDirected Acyclic Graph for topo sort 2: ");
        System.out.println(dagList2.printAdjacency());

        System.out.println("topo(dagList2)");
        System.out.println(Arrays.toString(dagList2.topologicalSort(dagList2)));
    }

 }