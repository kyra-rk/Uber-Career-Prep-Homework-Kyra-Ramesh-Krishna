/* Given a binary tree, determine if it is a binary search tree.
Time complexity: given n nodes, O(n) 
Space complexity: O(1)
Time taken: 15 min to solve it, and around 5-8 min to set up the test cases. 
I also added a method called changeData() that takes a node and changes it.
*/
public class IsBST{

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              10
           /     \
          8      16
            \    /  \
            9  13   17 
                      \
                      20
        */
                    
        tree.insert(10);
        tree.insert(8);
        tree.insert(16);
        tree.insert(9);
        tree.insert(13);
        tree.insert(17);
        tree.insert(20);
 
        System.out.println("IsBST Tree (inorder traversal): ");
        tree.inorder();

        System.out.println("Is a BST? " + isBST(tree.getHead()) + "\n");

        tree.changeData(tree.getHead().right.right.right, 15);

        System.out.println("Updated IsBST Tree (inorder traversal): ");
        tree.inorder();
        System.out.println("Is a BST? " + isBST(tree.getHead()));
        
    }


    public static boolean isBST(Node root){
        if (root == null) {
            return true;
        }
        if (root.right != null && root.right.data < root.data) {
            return false;
        }
        if (root.left != null && root.left.data > root.data) {
            return false;
        }
        return isBST(root.right) && isBST(root.left);
    }
}