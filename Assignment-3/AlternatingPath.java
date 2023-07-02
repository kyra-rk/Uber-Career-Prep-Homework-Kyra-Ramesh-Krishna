/* Spec: Given an origin and a destination in a directed graph in which edges can be 
blue or red, determine the length of the shortest path from the origin to the destination 
in which the edges traversed alternate in color. Return -1 if no such path exists.
 * Time taken: 40 min (timed out)
 * Data structure/Algorithm: Depth First Search (looking for paths)
 * Time Complexity: O(V + E)
 * Space Complexity: O(V) because of the Set 
 */
 public class AlternatingPath{

    Map<String, Set<Vertex>> graph;
    int edgesCount = 0;

    // edges always will be in format: (from, to, color)
    public AlternatingPath(String[][] edges){
        Vertex vert;
        String from;
        String to;
        String color;
        Set<Vertex> adjacent;
        graph = new HashMap<>();
        for (String[] edge : edges){
            from =  edge[0];
            to = edge[1];
            color = edge[2];
            vert = new Vertex(from, to, color);
            if (graph.contains(from)){
                adjacent = graph.get(from);
            } else {
                adjacent = new HashSet<>();
            }
            adjacent.add(vert);
            edgesCount++;
            graph.put(from, adjacent);
        }
    }

    class Vertex{
        String to;
        String color;
        public Vertex(String from, String to, String color){
            this.from = from;
            this.to = b;
            this.color = color;
        }
        public String getFrom(){
            return from;
        }
        public String getTo(){
            return to;
        }
        public String getColor(){
            return color;
        }
        @Override
        public boolean equals(Object obj){
            Vertex other = (Vertex) obj;
            if (this.from.equals(other.getFrom()) && this.to.equals(other.getTo()) && this.color.equals(other.getColor())){
                return true;
            }
            return false;
        }
        /* Why do I have to do this? Is there a way to avoid this?? */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + to;
            result = prime * result + from;
            result = prime * result + color;
            return result;
        }
    }

    // get length of shortest alternating path 
    public int getPath(String origin, String destination){
        int minPath = 0;
        int currPath = 0;
        Set<Vertex> visited = new HashSet<>();
        LinkedList<String> queue = new Stack<>();
        stack.push(origin);
        String current;
        String prevColor = "none";
        Set<Vertex> adjacent;
        while (!stack.isEmpty()){
            current = stack.pop();
            adjacent = = graph.get(current);
            for (Vertex vert : adjacency){
                // if we have not visited this vertex and the color is alternate of previous color, then continue on the path
                if (!visited.contains(vert.getTo()) && !prevColor.equals(vert.getColor())){
                    stack.push();
                }
            }
            if (currPath < minPath){
                minPath = currPath;
            }
        }
        return minPath;
    }

    public static void main(String[] args){
        String[][] edges1 = {{"A", "B", "blue"}, {"A", "D", "red"}, {"D", "C", "blue"}, {"B, C", "red"}};
    }
 }