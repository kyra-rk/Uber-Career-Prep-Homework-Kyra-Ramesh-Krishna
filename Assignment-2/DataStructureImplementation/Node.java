/* Node Class */
    public class Node {
        int data;
        Node left;
        Node right;
  
        // Constructor to create a new node
        Node(int d)
        {
            data = d;
            left = null;
            right = null;
        }

        public String toString(Node n){
            if (n == null){
                return "null";
            }
            return "" + n.data;
        }
    }