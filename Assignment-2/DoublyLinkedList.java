// Linked List Class
class DoublyLinkedList {

    private Node head;
    
    public DoublyLinkedList() {
        head = null;
    }
  
    /* Node Class */
    class Node {
        int data;
        Node next;
        Node prev;
  
        // Constructor to create a new node
        Node(int d)
        {
            data = d;
            next = null;
            prev = null;
        }

        public String toString(Node n){
            if (n == null){
                return "null";
            }
            return "" + n.data;
        }
    }

    public static void main(String[] args) {
        System.out.println("hello world");

        /* QUESTIONS: 
            - how should I go about testing my methods? 

            - also, isn't is neccesary for me to create a linked list class 
            since my methods aren't static, I need to make an instance of a linked list.

            - none of my methods allow me to create and add a node. 
            yet I take in a node in order to insert after. this seems like a problem. 
            
            - would you reccomend learning python for the next assignment and using python instead?
        */

        DoublyLinkedList list = new DoublyLinkedList();

        // insert 

        System.out.println("Insert 2 at front:");
        list.head = list.insertAtFront(list.head, 2);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Insert 3 at front:");
        list.head = list.insertAtFront(list.head, 3);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Insert 5 at front:");
        list.head = list.insertAtFront(list.head, 5);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        // insert back

        
        System.out.println("Insert 1 at back:");
        list.insertAtBack(list.head, 1);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Insert 4 after list head:");
        list.insertAfter(list.head, 4, list.head);
        System.out.println(list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        // reverse 
        System.out.println("Reverse iterative:");
        list.head = list.reverseIterative(list.head);
        System.out.println("Reversed: " + list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Reverse recursvie:");
        list.head = list.reverseRecursive(list.head);
        System.out.println("Reversed: " + list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        // delete 
        
        System.out.println("Delete front:");
        list.head = list.deleteFront(list.head);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Delete back:");
        list.deleteBack(list.head);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Delete node list.head.next");
        list.head = list.deleteNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Delete node list.head.next");
        list.head = list.deleteNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Length: " + list.length(list.head));

    }

    String toString(Node head){
        String output = "";
        if (head == null) {
            return output;
        }
        Node ptr = head;
        output += ptr.data;
        while(ptr.next != null){
            output = output + " --> " + ptr.next.data;
            ptr = ptr.next;
        }
        return output;
    }

    String nodeToString(Node n) {
        if (n == null) {
            return "null";
        }
        return "" + n.data;
    }

    void checkPointers(Node head) {
        System.out.println("--- checking pointers ---");
        if (head == null) {
            return;
        }
        Node curr = head;
        Node prev = curr.prev;
        Node next = curr.next;
        while(curr != null) {
            prev = curr.prev;
            next = curr.next;
            System.out.println(nodeToString(prev) + " <-- " + nodeToString(curr) + " --> " 
            + nodeToString(next));
            curr = curr.next;
        }
        System.out.println("--- end check ---");
    }

    // creates new Node with data val at front; returns new head
    Node insertAtFront(Node head, int val) {
        Node temp = new Node(val);
        temp.next = head;
        if (head != null) {
            head.prev = temp;
        }
        head = temp;
        return head;
    }

    // creates new Node with data val at end
    void insertAtBack(Node head, int val) {
        Node temp = new Node(val);
        if (head == null){
            head = temp;
            return;
        }
        Node ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        ptr.next = temp;
        temp.prev = ptr;
        return;
    }

    // QUESTION: what if either head is null or Node loc doesn't exist? 
    // creates new Node with data val after Node loc
    void insertAfter(Node head, int val, Node loc) {
        if (head == null){
            return;
        }
        Node temp = new Node(val);
        Node ptr = head;
        do {
            if (ptr.data == loc.data) {
                temp.next = ptr.next;
                temp.prev = ptr;
                temp.next.prev = temp;
                ptr.next = temp;
                return;
            }
        } while(ptr.next != null);
    }

    // removes first Node; returns new head
    Node deleteFront(Node head) {
        head.next.prev = null;
        return head.next;
    }

    // removes last Node
    void deleteBack(Node head) {
        if (head == null){
            return;
        }
        Node prev = null;
        Node curr = head;
        while (curr.next != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
    }

    // deletes Node loc; returns head
    Node deleteNode(Node head, Node loc) {
        if (head == null){
            return null;
        }
        if (head.data == loc.data) {
            head.next.prev = null;
            return head.next;
        }
        Node prev = null;
        Node curr = head;
        while (curr.next != null && curr.data != loc.data){
            prev = curr;
            curr = curr.next;
        }
        if (curr.data != loc.data) { // loc doesn't exist
            return head;
        }
        if (curr.next != null) {
            curr.next.prev = prev;
        }
        prev.next = curr.next;
        return head;
    }

    // returns length of the list
    int length(Node head) {
        int count = 0;
        Node ptr = head;
        while(ptr != null){
            ptr = ptr.next;
            count++;
        }
        return count;
    }

    // reverses the linked list iteratively
    Node reverseIterative(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        Node prev = null;
        Node curr = head;
        Node after = head.next;
        while(after != null){
            curr.next = prev;
            if (prev != null) {
                curr.next.prev = curr;
            }
            curr.prev = after;
            prev = curr;
            curr = after;
            after = after.next;
        }
        curr.next = prev;
        curr.prev = null;
        return curr;
    }

    // reverses the linked list recursively (Hint: you will need a helper function)
    Node reverseRecursive(Node head) {
        return recursiveHelper(head, null);
    }

    Node recursiveHelper(Node original, Node output){
        if (original == null) {
            output.prev = null;
            return output;
        }
        Node curr = original; 
        original = original.next;
        curr.next = output;
        if (output != null) {
            output.prev = curr;
        }
        output = curr;
        return recursiveHelper(original, output);
    }
}