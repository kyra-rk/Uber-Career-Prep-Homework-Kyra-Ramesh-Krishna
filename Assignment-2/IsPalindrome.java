/* Given a doubly linked list, determine if it is a palindrome.
Time taken: 9 min to set up (created DNode class, 
added getter and setter to Doubly Linked List class) and 
19 min to solve and test the problem, total 28 min. 
Time complexity: O(n) since I iterate through the list to get to the end
Space complexity: O(1) since I am only keeping track of pointers
*/ 
public class IsPalindrome{

    public static void main(String[] args) {
        // test case 1
        DoublyLinkedList list1 = new DoublyLinkedList();
        list1.setHead(list1.insertAtFront(list1.getHead(), 9));
        list1.setHead(list1.insertAtFront(list1.getHead(), 2));
        list1.setHead(list1.insertAtFront(list1.getHead(), 4));
        list1.setHead(list1.insertAtFront(list1.getHead(), 2));
        list1.setHead(list1.insertAtFront(list1.getHead(), 9));
        System.out.println("Is it a palindrome: " + isPalindrome(list1));
        // test case 2 
        DoublyLinkedList list2 = new DoublyLinkedList();
        list2.setHead(list2.insertAtFront(list2.getHead(), 9));
        list2.setHead(list2.insertAtFront(list2.getHead(), 2));
        list2.setHead(list2.insertAtFront(list2.getHead(), 4));
        list2.setHead(list2.insertAtFront(list2.getHead(), 22));
        list2.setHead(list2.insertAtFront(list2.getHead(), 9));
        System.out.println("Is it a palindrome: " + isPalindrome(list2));
    }

    public static boolean isPalindrome(DoublyLinkedList list){
        if (list == null) {
            return true;
        } 
        DNode head = list.getHead();
        // if no elements or if only one element, then it is a palindrome
        if (head == null || head.next == null) {
            return true; 
        }
        DNode start = head; 
        DNode end = head; 
        int length = 1;
        while (end.next != null) {
            end = end.next; 
            length ++; 
        }
        /*
        DNode slow = head; 
        DNode faster = head; 
        DNode trailing = head; 
        int length = 0; 
        while (faster != null) {
            trailing = faster; 
            slow = slow.next; 
            faster = fast.next; 
            length++;
            if (faster != null) {
                trailing = faster; 
                faster = fast.next; 
                length++;
            }
        }
        if (trailing != null) {
            System.out.println("trailing is " + trailing.data);
        } */

        System.out.println("length: " + length);
        // end is now pointing to the last element 
        while (start != null && length > 0) {
            if (start.data != end.data) {
                return false; 
            }
            start = start.next; 
            end = end.prev; 
            length--; 
        }

        return true; 
    }
}