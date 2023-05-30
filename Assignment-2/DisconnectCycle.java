/* Given a singly linked list, disconnect the cycle, if one exists.
Time taken: 
Time complexity: 
Space complexity: 
*/ 
import java.util.*;

public class DisconnectCycle{
    public static void main(String[] args) {
        // test case 1 
        System.out.println("test 1");
        LinkedList list1 = new LinkedList();
        list1.setHead(list1.insertAtBack(list1.getHead(), 10));
        list1.insertAtBack(list1.getHead(), 18);
        list1.insertAtBack(list1.getHead(), 12);
        list1.insertAtBack(list1.getHead(), 9);
        list1.insertAtBack(list1.getHead(), 11);
        list1.insertAtBack(list1.getHead(), 4);
        list1.setNextTo(list1.getHead(), 4, 12);
        System.out.println(list1.toString(list1.getHead()));

        list1.setHead(disconnectCycle(list1)); 
        System.out.println("New disconnected linked list: ");
        System.out.println(list1.toString(list1.getHead()));
        
        // test case 2 
        System.out.println("");
        System.out.println("test 2");
        LinkedList list2 = new LinkedList();
        list2.setHead(list2.insertAtBack(list2.getHead(), 10));
        list2.insertAtBack(list2.getHead(), 18);
        list2.insertAtBack(list2.getHead(), 12);
        list2.insertAtBack(list2.getHead(), 9);
        list2.insertAtBack(list2.getHead(), 11);
        list2.insertAtBack(list2.getHead(), 4);
        list2.setNextTo(list2.getHead(), 4, 4);
        System.out.println(list2.toString(list2.getHead()));

        list2.setHead(disconnectCycle(list2)); 
        System.out.println("New disconnected linked list: ");
        System.out.println(list2.toString(list2.getHead()));
    }

    // takes in list and returns new head of linked list with no cycle 
    // this solution assumes that there are unique elements in the list 
    public static LNode disconnectCycle(LinkedList list) {
        if (list == null) {
            return null; 
        }
        LNode head = list.getHead();
        if (head == null) {
            return null; 
        }
        Set<Integer> set = new HashSet<Integer>();
        LNode moving = head.next; 
        LNode trailing = head; 
        while (moving != null) {
            boolean returnVal = set.add(moving.data);
            // add() returns false if set did contain moving.data
            if (!returnVal) {
                trailing.next = null; 
                return head; 
            }
            moving = moving.next; 
            trailing = trailing.next; 
        }
        return head; 
    }
}