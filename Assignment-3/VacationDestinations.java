/* Spec: Given an origin city, a maximum travel time k, and pairs of destinations that 
can be reached directly from each other with corresponding travel times in hours, 
return the number of destinations within k hours of the origin. Assume that having a 
stopover in a city adds an hour of travel time.
 * Strategy: intuitively maybe DFS recursively
 * Time Taken: too long again, but I feel like I am SO close! I think I need to create a visited set and use that somehow. 
 * Time Complexity: O(V + E) 
 * Space Complexity: O(V + E) for the graph I build and then maybe multiplied by V since I have a recursive stack.
 */
 import java.util.*;
 
 public class VacationDestinations{

    public VacationDestinations(){

    }

    private Pair[] makeTest(){
        Pair[] pairs = new Pair[12];
        // important note: since my graph is directed, I need to add the same edges in both directions
        pairs[0] = new Pair("Boston", "New York", 4);
        pairs[1] = new Pair("New York", "Philadelphia", 2);
        pairs[2] = new Pair("Boston", "Newport", 1.5);
        pairs[3] = new Pair("Washington, D.C.", "Harper's Ferry", 1);
        pairs[4] = new Pair("Boston", "Portland", 2.5);
        pairs[5] = new Pair("Philadelphia", "Washington, D.C.", 2.5);

        pairs[6] = new Pair("New York", "Boston", 4);
        pairs[7] = new Pair("Philadelphia", "New York", 2);
        pairs[8] = new Pair("Newport", "Boston", 1.5);
        pairs[9] = new Pair("Harper's Ferry", "Washington, D.C.", 1);
        pairs[10] = new Pair("Portland", "Boston", 2.5);
        pairs[11] = new Pair("Washington, D.C.", "Philadelphia", 2.5);
        return pairs;
    }

    public static void main(String[] args){
        VacationDestinations vacation = new VacationDestinations();
        Pair[] pairs = vacation.makeTest();
        String origin = "New York";
        int k = 5;
        System.out.println("Origin: " + origin + ", k: " + k);
        System.out.println("Output: " + vacation.getDestinations(origin, k, pairs));

        k = 7;
        System.out.println("Origin: " + origin + ", k: " + k);
        System.out.println("Output: " + vacation.getDestinations(origin, k, pairs));

        k = 8;
        System.out.println("Origin: " + origin + ", k: " + k);
        System.out.println("Output: " + vacation.getDestinations(origin, k, pairs));
    }

    public class Pair{
        String from;
        String to;
        double distance;
        public Pair(String from, String to, double distance){
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
        public String toString(){
            return "(" + from + ", " + to + ", " + distance + ")";
        }
    }
    
    public int getDestinations(String origin, int k, Pair[] pairs){
        // make the graph 
        Map<String, ArrayList<Pair>> graph = new HashMap<>();
        ArrayList<Pair> temp;
        // System.out.println(Arrays.toString(pairs));
        for (Pair p : pairs){
            // System.out.println("pair: " + p.toString());
            if (graph.containsKey(p.from)){
                temp = graph.get(p.from);
                temp.add(p);
                // System.out.println(temp.toString());
                graph.replace(p.from, temp);
            } else {
                temp = new ArrayList<Pair>();
                temp.add(p);
                graph.put(p.from, temp);
            }
        }
        // for (String s : graph.keySet()){
        //     System.out.println("**" + s + ": " + graph.get(s).toString());
        // }
        // call helper 
        return recursiveHelper(origin, (double)k, graph);
    }

    private int recursiveHelper(String origin, double k, Map<String, ArrayList<Pair>> graph){
        System.out.println("origin: " + origin + ", k: " + k);
        // if graph does not contain origin or the origin has no neighbours, return 0 
        if (!graph.containsKey(origin) || graph.get(origin).size() == 0 || k == 0){
            // System.out.println(graph.get(origin));
            // System.out.println("in with " + origin + ", k: " + k);
            return 0;
        }
        // if the least far away neigbour is a distance away from the origin by more than k, then return 0 
        Pair leastFar = new Pair("", "", 0);
        // O(E) for that vertex 
        int count = 0;
        for (Pair p : graph.get(origin)){
            if (p.distance > leastFar.distance){
                leastFar = p;
            } 
            if (p.distance <= k){
                System.out.println("p: " + p.toString());
                count++;
            }
        }
        System.out.println("count: " + count);
        if ( leastFar.distance > k){
            return 0;
        }
        // otherwise, go through all neighbours and call recursive helper with k - 1 - distance (the distance from origin to this neighbours)
        for (Pair p : graph.get(origin)){
            count += recursiveHelper(p.to, k - 1.0 - p.distance, graph);
        }
        return count;
    }
 }