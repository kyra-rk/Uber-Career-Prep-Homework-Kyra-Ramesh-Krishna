/* Given a singly linked list, move the nth from the last element to the front of the list.
Time taken: 24 min to set up test cases and solve it 
Time complexity: O(n) since I am iterating through the entire list (insert at front is O(1))
Space complexity: O(1) to store variables 
*/ 

public class MoveNthLastToFront{

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // test case 1
        // list.setHead(list.insertAtBack(list.getHead(), 15));
        // list.insertAtBack(list.getHead(), 2);
        // list.insertAtBack(list.getHead(), 8);
        // list.insertAtBack(list.getHead(), 7);
        // list.insertAtBack(list.getHead(), 20);
        // list.insertAtBack(list.getHead(), 9);
        // list.insertAtBack(list.getHead(), 11);
        // list.insertAtBack(list.getHead(), 6);
        // list.insertAtBack(list.getHead(), 19);
        // int k = 2;
        // test case 2 
        list.setHead(list.insertAtBack(list.getHead(), 15));
        list.insertAtBack(list.getHead(), 2);
        list.insertAtBack(list.getHead(), 8);
        list.insertAtBack(list.getHead(), 7);
        list.insertAtBack(list.getHead(), 20);
        list.insertAtBack(list.getHead(), 9);
        list.insertAtBack(list.getHead(), 11);
        list.insertAtBack(list.getHead(), 6);
        list.insertAtBack(list.getHead(), 19);
        int k = 7;
        System.out.println(list.toString(list.getHead()));

        list.setHead(moveNthLastToFront(list, k));
        System.out.println(list.toString(list.getHead()));
    }

    public static LNode moveNthLastToFront(LinkedList list, int n) {
        if (list == null) {
            return null; 
        }
        LNode head = list.getHead();
        if (head == null) {
            return null; 
        }
        LNode prev = null; 
        LNode trailing = head; 
        LNode leading = head; 
        LNode toAdd; 
        int count = 0; 
        // moves leading to be n elements ahead of trailing 
        while (leading != null && count < n) {
            leading = leading.next; 
            count++;
        }
        /* move both leading and trailing one by one until leading is null 
           at this point, we will know that trailing is n elements from the end
        */
        while (leading != null) {
            leading = leading.next; 
            prev = trailing; 
            trailing = trailing.next; 
        }
        // if leading is null, then prev would be null 
        // this means that n was larger than the length of the linked list 
        if (prev != null && trailing != null) {
            int addToFront = trailing.data; 
            prev.next = trailing.next; 
            // ^ if trailing is null then we inputted n as zero 
            list.setHead(list.insertAtFront(list.getHead(), addToFront));
        }
        return list.getHead();
    }
}