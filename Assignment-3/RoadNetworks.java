/* Spec: In some states, it is not possible to drive between any two towns 
 * because they are not connected to the same road network. Given a list of towns and 
 * a list of pairs representing roads between towns, return the number of road networks.  
 * (For example, a state in which all towns are connected by roads has 1 road network, 
 * and a state in which none of the towns are connected by roads has 0 road networks.)
 * Strategy: Depth First Search
 * Time taken: timed out 
 * Question: How do I work with Pair/Vertex objects in Java? This is proving to be much more complicated than I thought. 
 * Do I have to make a Pair class outside this file? How can I avoid making a new class? 
 * How do I avoid running into the same errors -- "non-static variable this cannot be referenced from a static context"
 * Time complexity: O(E+V) (for DFS using adjacency list)
 * Space complexity: O(V) because of the stack (for DFS)
 */
 import java.util.*;
 import java.lang.*;

 public class RoadNetworks{
    
    // private Map<String, Set<String>> graph;
    // private int towns;

    // public RoadNetworks(String[] towns, Pair[] roads){
    //     this.towns = towns.length;
    //     graph = new HashMap<>();
    //     for (int i = 0; i < numTowns; i++){
    //         graph.put(towns[i], new HashSet());
    //     }
    //     Set<String> adjacent;
    //     for (Pair connection : roads){
    //         adjacent = graph.get(connection.a);
    //         adjacent.add(connection.b);
    //         graph.put(connection.a, adjacent);
    //         adjacent = graph.get(connection.b);
    //         adjacent.add(connection.a);
    //         graph.put(connection.b, adjacent);
    //     }
    // }

    // public int towns(){
    //     return towns;
    // }

    public static void main(String[] args){
        String[] towns1 = {"Skagway", "Juneau", "Gustavus", "Homer", "Port Alsworth", "Glacier Bay", "Fairbanks", "McCarthy", "Copper Center", "Healy"};
        Pair p1 = new Pair("Anchorage", "Homer");
        Pair p2 = new Pair("Glacier Bay", "Gustavus");
        Pair p3 = new Pair("Copper Center", "McCarthy");
        Pair p4 = new Pair("Anchorage", "Copper Center");
        Pair p5 = new Pair("Copper Center", "Fairbanks");
        Pair p6 = new Pair("Healy", "Fairbanks");
        Pair p7 = new Pair("Healy", "Anchorage");
        Pair[] roads1 = {p1, p2, p3, p4 p5, p6, p7};
        System.out.println("Road Networks: " + roadNetworks(towns1, roads1));
    }

    public void printAdjacency(){
        
    }

    public static int roadNetworks(String[] towns, Pair[] roads){
        int networks = 0;
        int numTowns = towns.length;
        Map<String, Set<String>> graph = new HashMap<>();
        for (int i = 0; i < numTowns; i++){
            graph.put(towns[i], new HashSet());
        }
        Set<String> adjacent;
        for (Pair connection : roads){
            adjacent = graph.get(connection.a);
            adjacent.add(connection.b);
            graph.put(connection.a, adjacent);
            adjacent = graph.get(connection.b);
            adjacent.add(connection.a);
            graph.put(connection.b, adjacent);
        }
        Set<String> unvisited = new HashSet<>(graph.keySet());
        Stack<String> stack = new Stack<>();
        String current;
        while (unvisited.size() != 0){
            current = getUnvisited(unvisited);
            stack.push(current);
            while (!stack.isEmpty()){
                current = stack.pop();
                // go through and visit neighbours 
                for (String neighbour : graph.get(current)){
                    unvisited.remove(neighbour);
                    stack.push(neighbour);
                }
            }
            networks++;
        }
        return networks;
    }

    public static String getUnvisited(Set<String> unvisited){
        Iterator<String> itr = unvisited.iterator();
        if (itr.hasNext()){
            return itr.next();
        }
        return "";
    }

 }
 class Pair{
        String a;
        String b;
        public Pair(String a, String b){
            this.a = a;
            this.b = b;
        }
        public String toString(){
            return "(" + a + ", " + b + ")";
        }
    }