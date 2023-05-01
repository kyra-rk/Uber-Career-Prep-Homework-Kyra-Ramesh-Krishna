/* Definition: a binary tree in which every element is greater than all the 
elements in its left subtree and less than all the elements in its left subtree.
*/

class BinarySearchTree {

    // Root of BST
    Node root;
 
    // Constructor
    BinarySearchTree() { root = null; }

    // returns the minimum value in the BST
    int min() {
        if (root == null) {
            return 0;
        }
        Node move = root;
        while(move.left != null) {
            move = move.left;
        }
        return move.data;
    }

    public Node getHead(){
        return root;
    }

    // returns the maximum value in the BST
    int max() {
        if (root == null) {
            return 0;
        }
        Node move = root;
        while(move.right != null) {
            move = move.right;
        }
        return move.data;
    }

    // returns a boolean indicating whether val is present in the BST
    boolean contains(int val) {
        Node move = root;
        while(move != null) {
            if (val == move.data) {
                return true;
            }
            else if(val > move.data) {
                move = move.right;
            } else {
                move = move.left;
            }
        } 
        return false;
    }

    // creates a new Node with data val in the appropriate location
    // For simplicity, do not allow duplicates. If val is already present, insert is a no-op
    void insert(int val) {
        root = insertRec(root, val);
    }

    Node insertRec(Node t, int val) {
        if (t == null) {
            return new Node(val);
        } 
        else if (val < t.data) {
            t.left = insertRec(t.left, val);
        } else if (val > t.data) {
            t.right = insertRec(t.right, val);
        } // if it's the same then no change. stop recursing
        return t;
    }

    // deletes the Node with data val, if it exists
    int delete(int val) {
        return deleteRec(root, val).data;
    }

    /* Recursively deletes the Node with val data from the root t. */
    Node deleteRec(Node t, int val) {
        // if head is null, we didn't find val in the tree 
        if (t == null) {
            return t;
        }
        else if (val < t.data) {
            t.left = deleteRec(t.left, val);
        } else if (val > t.data) {
            t.right = deleteRec(t.right, val);
        } else { // t.data == val so delete!
            // is a leaf
            if (t.right == null && t.left == null) {
                t = null;
                return t;
            }
            // has only one child
            else if (root.left == null) {
                Node temp = root.right;
                return temp;
            }
            else if (root.right == null) {
                Node temp = root.left;
                return temp;
            } 
            // both children  
            // get the smallest Node on the right subtree 
            Node small = t.right;
            while (small.left != null) {
                small = small.left;
            }
            t.data = small.data;
            t.right = deleteRec(t.right, small.data);
        }
        return t;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println("");
    }

    public void inorderRec(Node t){
        if (t != null) {
            inorderRec(t.left);
            System.out.print(t.data + " ");
            inorderRec(t.right);
        }
    }

    /* gratefully provided by geeks for geeks :) */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
 
        System.out.println(
            "Inorder traversal of the given tree");
        tree.inorder();

        System.out.println("Min: " + tree.min() + ", Max: " + tree.max());
 
        System.out.println("\nDelete 20");
        tree.delete(20);
        System.out.println(
            "Inorder traversal of the modified tree");
        tree.inorder();
 
        System.out.println("\nDelete 30");
        tree.delete(30);
        System.out.println(
            "Inorder traversal of the modified tree");
        tree.inorder();
 
        System.out.println("\nDelete 50");
        tree.delete(50);
        System.out.println(
            "Inorder traversal of the modified tree");
        tree.inorder();
    }
}
