/* Given a sorted singly linked list, remove any duplicates so that no value appears more than once.
Time taken: 30 min taken to solve problem and 12 min to set up
(had to create LNode class and adjust the insertAtBack() methdd to return a node)
Time complexity: O(n)
Space complexity: O(1)
*/ 

public class DedupSortedList{

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // test case 1
        list.setHead(list.insertAtBack(list.getHead(), 8));
        list.insertAtBack(list.getHead(), 8);
        list.insertAtBack(list.getHead(), 8);
        list.insertAtBack(list.getHead(), 8);
        // test case 2 
        // list.setHead(list.insertAtBack(list.getHead(), 1));
        // list.insertAtBack(list.getHead(), 2);
        // list.insertAtBack(list.getHead(), 2);
        // list.insertAtBack(list.getHead(), 4);
        // list.insertAtBack(list.getHead(), 5);
        // list.insertAtBack(list.getHead(), 5);
        // list.insertAtBack(list.getHead(), 5);
        // list.insertAtBack(list.getHead(), 10);
        // list.insertAtBack(list.getHead(), 10);
        System.out.println(list.toString(list.getHead()));

        list.setHead(removeDupsSortedList(list));
        System.out.println(list.toString(list.getHead()));
    }

    public static LNode removeDupsSortedList(LinkedList list) {
        return recursiveHelper(list, list.getHead());
    }

    public static LNode recursiveHelper(LinkedList list, LNode head){
        if (head == null || head.next == null) {
            return head;
        }
        LNode trailing = head;
        LNode leading = head.next;
        while (leading != null && leading.data != trailing.data) {
            leading = leading.next;
            trailing = trailing.next;
        }
        if (leading == null) {
            return head;
        }
        int data = leading.data; 
        while (leading != null && leading.data == data) {
            LNode next = leading.next;
            //System.out.println("removed data: " + leading.data);
            list.head = list.deleteNode(list.head, leading);
            leading = next;
        }
        //System.out.println(list.toString(list.getHead()));
        if (leading != null) {
            return recursiveHelper(list, leading);
        }
        return list.head;
    }
}