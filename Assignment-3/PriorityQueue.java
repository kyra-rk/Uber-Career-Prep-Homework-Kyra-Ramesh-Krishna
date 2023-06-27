/** A priority queue functions like a queue except that elements are 
removed in order of priority rather than insertion. This is typically 
implemented as a max heap that stores pairs of elements and numeric 
weights, with the weights used as the values in the heap. Implement 
a priority queue according to the following API using a heap as the 
underlying data structure. For simplicity, you can assume the priority 
queue stores string elements with integer priorities. Start by copy-pasting 
your heap implementation from question 2 and making modifications.
 */

public class PriorityQueue {
    private QPair<String, int>> arr; // the underlying array
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

    public Heap(int capacity){
        arr = new int[capacity];
        size = 0;
    }

    public int[] getArr(){
        return arr;
    }

    public int size(){
        return size;
    }

    // returns the highest priority (first) element in the PQ
    // TO DO 
    public int top(){
        return size > 0? arr[1] : Integer.MIN_VALUE;
        // changed to min value since we now want a MAX heap
    }

    // adds string str to the PQ with priority weight 
    // TO DO 
    public void insert(String str, int weight) {
        if (size == 0){ // first node 
            arr[1] = x;
            size++;
            return;
        } 
        if (size == arr.length - 1){ // at max capacity, must do -1 since we start at index 1
            System.out.println("At max capacity. " + x + " could not be added to the min heap.");
            return;
        } 
        // if not the first node and not at capacity 
        size++;
        int curr = size;
        arr[curr] = x;
        while (arr[parent(curr)] > arr[curr]){
            swap(parent(curr), curr);
            curr = parent(curr);
        }
    }

    public void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // removes the highest priority (first) element in the PQ. returns the value of the removed element
    // TO DO 
    public int removeTop(){
        if (size < 1){
            System.out.println("No elements left in the heap to remove!");
            return -1; // to indicate that the heap has no elements
        } else if (size == 1){
            size--;
            return arr[1];
        }
        int min = arr[1];
        // replace the top of the heap (min) with the last element (right most leaf)
        arr[1] = arr[size]; 
        size--;
        minHeapify(1);
        return min;
    }

    public void minHeapify(int ix){
        int left = left(ix);
        int right = right(ix);
        int min = ix;
        if (left <= size && arr[left] < arr[ix]){
            min = left;
        } 
        if (right <= size && arr[right] < arr[min]){
            min = right;
        }
        // System.out.println("ix: " + ix + ", min: " + min);
        if (ix != min){
            swap(ix, min);
            ix = min;
            minHeapify(ix);
        }
    }
      
}

