/* Node Class */
    class DNode {
        int data;
        DNode next;
        DNode prev;
  
        // Constructor to create a new node
        DNode(int d)
        {
            data = d;
            next = null;
            prev = null;
        }

        public String toString(DNode n){
            if (n == null){
                return "null";
            }
            return "" + n.data;
        }
    }