// Linked List Class
class DoublyLinkedList {

    DNode head;
    
    public DoublyLinkedList() {
        head = null;
    }

    public static void main(String[] args) {
        System.out.println("hello world");

        /* QUESTIONS: 
            - how should I go about testing my methods? 

            - also, isn't is neccesary for me to create a linked list class 
            since my methods aren't static, I need to make an instance of a linked list.

            - none of my methods allow me to create and add a DNode. 
            yet I take in a DNode in order to insert after. this seems like a problem. 
            
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

        System.out.println("Delete DNode list.head.next");
        list.head = list.deleteDNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Delete DNode list.head.next");
        list.head = list.deleteDNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));
        list.checkPointers(list.head);
        System.out.println("");

        System.out.println("Length: " + list.length(list.head));

    }

    public DNode getHead(){
        return head;
    }

    public void setHead(DNode newNode) {
        head = newNode;
    }

    String toString(DNode head){
        String output = "";
        if (head == null) {
            return output;
        }
        DNode ptr = head;
        output += ptr.data;
        while(ptr.next != null){
            output = output + " --> " + ptr.next.data;
            ptr = ptr.next;
        }
        return output;
    }

    String nodeToString(DNode n) {
        if (n == null) {
            return "null";
        }
        return "" + n.data;
    }

    void checkPointers(DNode head) {
        System.out.println("--- checking pointers ---");
        if (head == null) {
            return;
        }
        DNode curr = head;
        DNode prev = curr.prev;
        DNode next = curr.next;
        while(curr != null) {
            prev = curr.prev;
            next = curr.next;
            System.out.println(nodeToString(prev) + " <-- " + nodeToString(curr) + " --> " 
            + nodeToString(next));
            curr = curr.next;
        }
        System.out.println("--- end check ---");
    }

    // creates new DNode with data val at front; returns new head
    DNode insertAtFront(DNode head, int val) {
        DNode temp = new DNode(val);
        temp.next = head;
        if (head != null) {
            head.prev = temp;
        }
        head = temp;
        return head;
    }

    // creates new DNode with data val at end
    void insertAtBack(DNode head, int val) {
        DNode temp = new DNode(val);
        if (head == null){
            head = temp;
            return;
        }
        DNode ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        ptr.next = temp;
        temp.prev = ptr;
        return;
    }

    // QUESTION: what if either head is null or DNode loc doesn't exist? 
    // creates new DNode with data val after DNode loc
    void insertAfter(DNode head, int val, DNode loc) {
        if (head == null){
            return;
        }
        DNode temp = new DNode(val);
        DNode ptr = head;
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

    // removes first DNode; returns new head
    DNode deleteFront(DNode head) {
        head.next.prev = null;
        return head.next;
    }

    // removes last DNode
    void deleteBack(DNode head) {
        if (head == null){
            return;
        }
        DNode prev = null;
        DNode curr = head;
        while (curr.next != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
    }

    // deletes DNode loc; returns head
    DNode deleteDNode(DNode head, DNode loc) {
        if (head == null){
            return null;
        }
        if (head.data == loc.data) {
            head.next.prev = null;
            return head.next;
        }
        DNode prev = null;
        DNode curr = head;
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
    int length(DNode head) {
        int count = 0;
        DNode ptr = head;
        while(ptr != null){
            ptr = ptr.next;
            count++;
        }
        return count;
    }

    // reverses the linked list iteratively
    DNode reverseIterative(DNode head) {
        if (head == null || head.next == null){
            return head;
        }
        DNode prev = null;
        DNode curr = head;
        DNode after = head.next;
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
    DNode reverseRecursive(DNode head) {
        return recursiveHelper(head, null);
    }

    DNode recursiveHelper(DNode original, DNode output){
        if (original == null) {
            output.prev = null;
            return output;
        }
        DNode curr = original; 
        original = original.next;
        curr.next = output;
        if (output != null) {
            output.prev = curr;
        }
        output = curr;
        return recursiveHelper(original, output);
    }
}