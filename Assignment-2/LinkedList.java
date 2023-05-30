// Linked List Class
class LinkedList {

    LNode head;
    
    public LinkedList() {
        head = null;
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

        LinkedList list = new LinkedList();
        //list.head = list.insertAtFront(list.head, 1);
        list.head = list.insertAtBack(list.head, 3);
        list.insertAtBack(list.head, 4);
        list.insertAtBack(list.head, 5);
        System.out.println(list.toString(list.head));

        // test 

        System.out.println("end world");

        // insert 

        list.head = list.insertAtFront(list.head, 2);
        System.out.println(list.toString(list.head));
        list.head = list.insertAtFront(list.head, 3);
        System.out.println(list.toString(list.head));
        list.head = list.insertAtFront(list.head, 5);
        System.out.println(list.toString(list.head));

        list.insertAtBack(list.head, 1);
        System.out.println(list.toString(list.head));

        list.insertAfter(list.head, 4, list.head);
        System.out.println(list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));

        // reverse 
        list.head = list.reverseIterative(list.head);
        System.out.println("Reversed: " + list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));

        list.head = list.reverseRecursive(list.head);
        System.out.println("Reversed: " + list.toString(list.head));
        System.out.println("Length: " + list.length(list.head));

        // delete 

        list.head = list.deleteFront(list.head);
        System.out.println(list.toString(list.head));

        list.deleteBack(list.head);
        System.out.println(list.toString(list.head));

        list.head = list.deleteNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));

        list.head = list.deleteNode(list.head, list.head.next);
        System.out.println(list.toString(list.head));

        System.out.println("Length: " + list.length(list.head));

        // set next to 

        LinkedList list1 = new LinkedList();
        System.out.println("testing set next to "); 
        list1.setHead(list1.insertAtBack(list1.head, 10));
        System.out.println(list.toString(list1.head));
        list1.insertAtBack(list1.head, 18);
        System.out.println(list.toString(list1.head));
        list1.insertAtBack(list1.head, 12);
        System.out.println(list.toString(list1.head));
        list1.insertAtBack(list1.head, 9);
        System.out.println(list.toString(list1.head));
        list1.insertAtBack(list1.head, 11);
        System.out.println(list.toString(list1.head));
        list1.insertAtBack(list1.head, 4);
        System.out.println(list.toString(list1.head));
        System.out.println("------------");
        list1.head = list1.setNextTo(list1.head, 4, 12);
        System.out.println(list.toString(list1.head));
    }

    String toString(LNode head){
        String output = "";
        if (head == null) {
            return output;
        }
        LNode ptr = head;
        output += ptr.data;
        int count = 0; 
        while(ptr.next != null){
            output = output + " --> " + ptr.next.data;
            ptr = ptr.next;
            count++;
            // assume that no list will go above 100 elements
            if (count > 100) {
                break;
            }
        }
        return output;
    }

    LNode getHead(){
        return head;
    }

    void setHead(LNode newHead) {
        head = newHead;
    }

    // creates new LNode with data val at front; returns new head
    LNode insertAtFront(LNode head, int val) {
        LNode temp = new LNode(val);
        temp.next = head;
        head = temp;
        return head;
    }

    // creates new LNode with data val at end
    LNode insertAtBack(LNode head, int val) {
        LNode temp = new LNode(val);
        if (head == null){
            head = temp;
            return head;
        }
        LNode ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        ptr.next = temp;
        return head;
    }

    // QUESTION: what if either head is null or LNode loc doesn't exist? 
    // creates new LNode with data val after LNode loc
    void insertAfter(LNode head, int val, LNode loc) {
        if (head == null){
            return;
        }
        LNode temp = new LNode(val);
        LNode ptr = head;
        do {
            if (ptr.data == loc.data) {
                temp.next = ptr.next;
                ptr.next = temp;
                return;
            }
        } while(ptr.next != null);
    }

    LNode setNextTo(LNode head, int curr, int next) {
        if (head == null) {
            return null; 
        }
        LNode currNode = head; 
        while (currNode != null) {
            if (currNode.data == curr) {
                break; 
            }
            currNode = currNode.next; 
        }
        LNode newNext = head; 
        while (newNext != null) {
            if (newNext.data == next) {
                break; 
            }
            newNext = newNext.next; 
        }
        if (currNode == null || newNext == null) {
            // not found 
            return head; 
        }
        currNode.next = newNext; 
        return head; 
    }

    // removes first LNode; returns new head
    LNode deleteFront(LNode head) {
        return head.next;
    }

    // removes last LNode
    void deleteBack(LNode head) {
        if (head == null){
            return;
        }
        LNode prev = null;
        LNode curr = head;
        while (curr.next != null){
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
    }

    // deletes LNode loc; returns head
    LNode deleteNode(LNode head, LNode loc) {
        if (head == null){
            return null;
        }
        if (head.data == loc.data) {
            return head.next;
        }
        LNode prev = null;
        LNode curr = head;
        while (curr.next != null && curr.data != loc.data){
            prev = curr;
            curr = curr.next;
        }
        if (curr.data != loc.data) {
            return head;
        }
        prev.next = curr.next;
        return head;
    }

    // returns length of the list
    int length(LNode head) {
        int count = 0;
        LNode ptr = head;
        while(ptr != null){
            ptr = ptr.next;
            count++;
        }
        return count;
    }

    // reverses the linked list iteratively
    LNode reverseIterative(LNode head) {
        if (head == null || head.next == null){
            return head;
        }
        LNode prev = null;
        LNode curr = head;
        LNode after = head.next;
        while(after != null){
            curr.next = prev;
            prev = curr;
            curr = after;
            after = after.next;
        }
        curr.next = prev;
        return curr;
    }

    // reverses the linked list recursively (Hint: you will need a helper function)
    LNode reverseRecursive(LNode head) {
        return recursiveHelper(head, null);
    }

    LNode recursiveHelper(LNode original, LNode output){
        if (original == null) {
            return output;
        }
        LNode curr = original; 
        original = original.next;
        curr.next = output;
        output = curr;
        return recursiveHelper(original, output);
    }
}