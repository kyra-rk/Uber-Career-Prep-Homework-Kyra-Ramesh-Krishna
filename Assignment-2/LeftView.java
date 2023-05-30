/* Given a binary tree, create an array of the left view 
(leftmost elements in each level) of the tree.
Time taken: honestly more than 40 min. wasn't sure how to do this one 
and also ended up haiving to look things up. 
Time complexity: O(n^2) I think? I am unsure since I have a for loop inside my recursive method
Space complexity: O(n) since I am using recursion. maybe O(n*k) 
since I have an array of length k where k is levels
*/ 
import java.util.*;

public class LeftView{

    static int maxLevel = 0;

    public static void main(String[] args) {
        // test 1
        // BinarySearchTree tree = new BinarySearchTree();
        // /* Let us create following BST
        //       15
        //    /     \
        //   8       23
        //          /  \
        //         19    29 
        //        /     /  
        //       20    24
        //              \
        //               26

        // Expected Output: [15, 8, 19, 20, 26]
        // */
        // tree.insert(15);
        // tree.insert(8);
        // tree.insert(23);
        // tree.insert(19);
        // tree.insert(29);
        // tree.insert(20);
        // tree.insert(24);
        // tree.insert(26);

        // test 2 
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              15
           /     \
          4       20
        /  \     /  \
       2    6   19   29 
        Expected Output: [15, 4, 2]
        */
        tree.insert(15);
        tree.insert(4);
        tree.insert(20);
        tree.insert(2);
        tree.insert(6);
        tree.insert(19);
        tree.insert(29);
 
        System.out.println(
            "Inorder traversal of the given tree");
        tree.inorder();

        int ct = countLevels(tree.getHead(), 0);
        System.out.println("count levels: " + ct);

        System.out.println("Left View:");
        int[] leftView = leftView(tree);
        System.out.println(Arrays.toString(leftView));
    }

    // since I have a BinarySearchTree implementation, I will be using those 
    // so a general binary tree would not work neccesarily 
    public static int[] leftView(BinarySearchTree tree) {
        // count levels - O(n), but recursive so space is also n
        Node root = tree.getHead();
        int levels = countLevels(root, 0);
        // create array with levels items    
        int[] leftView = new int[levels];
        return recursiveHelper(root, leftView, 1);  
    }

    private static int[] recursiveHelper(Node root, int[] leftView, int level){
        // base case
        if (root == null){
            return leftView;
        }

        // if this is the first node of its level
        // max level is a global variable
        if (level > maxLevel) {
            leftView[level - 1] = root.data;
            maxLevel = level;
        }
 
        int[] arrayLeft = recursiveHelper(root.left, leftView, level + 1);
        int[] arrayRight = recursiveHelper(root.right, leftView, level + 1);
        for (int i = 0; i < arrayLeft.length; i++) {
            if (arrayLeft[i] == 0) {
                arrayLeft[i] = arrayRight[i];
            }
        }
        return arrayLeft;
    }

    public static int countLevels(Node root, int count) {
        if (root == null) {
            return count; 
        } 
        return Math.max(countLevels(root.left, count), countLevels(root.right, count)) + 1;
    }
}