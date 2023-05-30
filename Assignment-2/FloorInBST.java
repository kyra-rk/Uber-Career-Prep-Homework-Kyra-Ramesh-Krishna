/* Given a target numeric value and a binary search tree, 
return the floor (greatest element less than or equal to the target) in the BST.
Time taken: 19 minutes to write and test 
Time complexity: O(n)
Space complexity: O(1)
*/ 

public class FloorInBST{
    public static void main(String[] args) {// test 1
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              10
           /     \
          8       16
           \     /  \
            9  13    17 
                        \
                        20

        Input: 13
        Expected Output: 13
        */
        tree.insert(10);
        tree.insert(8);
        tree.insert(16);
        tree.insert(9);
        tree.insert(13);
        tree.insert(17);
        tree.insert(20);

        System.out.println("test1\nOutput: " + floorInBST(tree, 13)); 

        System.out.println("\ntest 2");

        System.out.println("Output: " + floorInBST(tree, 15)); 

        System.out.println("\ntest 3");

        System.out.println("Output: " + floorInBST(tree, 11)); 

        System.out.println("\ntest 4");

        System.out.println("Output: " + floorInBST(tree, 19)); 
    }

    public static int floorInBST(BinarySearchTree tree, int target) {
        if (tree == null) {
            return -1;
        }
        Node parent = tree.getHead();  // root 
        Node child = tree.getHead(); 
        int floor = child.data; 
        while (child != null) {
            if (child.data <= target && Math.abs(child.data - target) < Math.abs(floor - target)) {
                floor = child.data; 
            }
            if (child.data == target) {
                return target; 
            } else if (target < child.data) {
                parent = child; 
                child = child.left; 
            } else { // target > child.data
                parent = child; 
                child = child.right;
            }
        }

        return floor;
    }


}