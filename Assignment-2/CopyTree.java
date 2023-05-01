/* Given a binary tree, create a deep copy. Return the root of the new tree. 
Time taken: 28 min (time spent debugging class access, static method stuff 
and figuring out how to test)
Time complexity: O(n) if n nodes
Space complexity: O(n)? I am using recursion so there is some overhead. 

*/ 
public class CopyTree{

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

        System.out.println("Copy Tree:");
        Node newRoot = copyTree(tree.getHead());
        System.out.println(
            "Inorder traversal of the NEW tree");
        tree.inorderRec(newRoot);
        System.out.println("");
    }

    public static Node copyTree(Node root){
        if (root == null) {
            return null;
        }
        Node newRoot = copy(root);
        newRoot.left = copyTree(root.left);
        newRoot.right = copyTree(root.right);
        return newRoot;
    }

    public static Node copy (Node t) {
        return new Node(t.data);
    }
}