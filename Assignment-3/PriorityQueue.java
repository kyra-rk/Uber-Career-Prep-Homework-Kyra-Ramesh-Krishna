/** A priority queue functions like a queue except that elements are 
removed in order of priority rather than insertion. This is typically 
implemented as a max heap that stores pairs of elements and numeric 
weights, with the weights used as the values in the heap. Implement 
a priority queue according to the following API using a heap as the 
underlying data structure. For simplicity, you can assume the priority 
queue stores string elements with integer priorities. Start by copy-pasting 
your heap implementation from question 2 and making modifications.
 */

 import java.util.*;

public class PriorityQueue {
    private QPair[] arr; // the underlying array
    int size;

    private int left(int ix){
        return ix*2;
    }
    private int right(int ix){
        return ix*2 + 1;
    }

    private int parent(int ix){
        return ix/2;
    }

    public PriorityQueue(int capacity){
        arr = new QPair[capacity];
        size = 0;
    }

    public QPair[] getArr(){
        return arr;
    }

    public String printArray(){
        String output = "[";
        for (int i = 1; i < size; i++){
            if (arr[i] != null){
                output += arr[i].toString() + ", ";
            } else {
                output += "null, ";
            }
        }
        if (arr[size] != null){
            output += arr[size].toString();
        } else {
            output += "null";
        }
        output += "]";
        return output;
    }

    public int size(){
        return size;
    }

    // returns the highest priority (first) element in the PQ
    public QPair top(){
        return size > 0? arr[1] : new QPair("Integer Min", Integer.MIN_VALUE);
        // if the size is 0, then return MIN_VALUE as the top of our max heap
    }

    // adds string str to the PQ with priority weight 
    public void insert(String str, int weight) {
        QPair pair = new QPair(str, weight);
        if (size == 0){ // first node 
            arr[1] = pair;
            size++;
            return;
        } 
        if (size == arr.length - 1){ // at max capacity, must do -1 since we start at index 1
            System.out.println("At max capacity. " + pair.toString() + " could not be added to the min heap.");
            return;
        } 
        // if not the first node and not at capacity 
        size++;
        int curr = size;
        arr[curr] = pair;
        // System.out.println("curr: " + curr + ", parent: " + parent(curr));
        while (parent(curr) >= 1 && arr[parent(curr)].getWeight() < arr[curr].getWeight()){
            swap(parent(curr), curr);
            // System.out.println("curr: " + arr[curr] + ", parent: " + arr[parent(curr)]);
            curr = parent(curr);
        }
    }

    public void swap(int a, int b){
        QPair temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // removes the highest priority (first) element in the PQ. returns the value of the removed element
    public QPair removeTop(){
        if (size < 1){
            System.out.println("No elements left in the heap to remove!");
            return null; // to indicate that the heap has no elements
        } else if (size == 1){
            size--;
            return arr[1];
        }
        QPair max = arr[1];
        // replace the top of the heap (max) with the last element (right most leaf)
        arr[1] = arr[size]; 
        size--;
        maxHeapify(1);
        return max;
    }

    public void maxHeapify(int ix){
        int left = left(ix);
        int right = right(ix);
        int max = ix;
        if (left <= size && arr[left].getWeight() > arr[ix].getWeight()){
            max = left;
        } 
        if (right <= size && arr[right].getWeight() > arr[max].getWeight()){
            max = right;
        }
        if (ix != max){
            swap(ix, max);
            ix = max;
            maxHeapify(ix);
        }
    }

    public static void main(String[] args){
        PriorityQueue queue1 = new PriorityQueue(10);
        queue1.insert("Rose", 22);
        queue1.insert("Lily", 34);
        queue1.insert("Orchid", 44);
        queue1.insert("Marigold", 24);
        queue1.insert("Jasmine", 12);
        System.out.println(queue1.printArray());
        System.out.println("Get the top: " + queue1.top());
        QPair top = queue1.removeTop();
        System.out.println("Remove the " + top.getString() + ", you're left with: " + queue1.printArray());
        System.out.println("\nGet the top: " + queue1.top());
        top = queue1.removeTop();
        System.out.println("Remove the " + top.getString() + ", you're left with: " + queue1.printArray());
        System.out.println("\nGet the top: " + queue1.top());
        top = queue1.removeTop();
        System.out.println("Remove the " + top.getString() + ", you're left with: " + queue1.printArray());
        System.out.println("\nGet the top: " + queue1.top());
        top = queue1.removeTop();
        System.out.println("Remove the " + top.getString() + ", you're left with: " + queue1.printArray());
        System.out.println("\nGet the top: " + queue1.top());
        top = queue1.removeTop();
        System.out.println("Remove the " + top.getString() + ", you're left with: " + queue1.printArray());
    }
      
}

