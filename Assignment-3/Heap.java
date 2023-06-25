/* Write a min heap class according to the following API using an array as 
the underlying data structure. (A max heap has the same implementation; you 
simply need to flip the direction of the comparators.) For simplicity, you 
can assume that the heap holds integers rather than generic comparables. */

import java.util.*;

public class Heap {
    private int[] arr; // the underlying array
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

    // returns the min (top) element in the heap
    public int min(){
        return size > 0? arr[1] : Integer.MAX_VALUE;
    }

    // adds int x to the heap in the appropriate position 
    public void insert(int x) {
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

    // removes the min (top) element in the heap. returns the value of the removed element
    public int removeMin(){
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

    public static void main(String[] args){
        Heap heap1 = new Heap(10);

        // testing insert and min
        System.out.println("testing insert() and min()");
        heap1.insert(7);
        System.out.println("insert 7: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());
        heap1.insert(2);
        System.out.println("insert 2: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());
        heap1.insert(9);
        System.out.println("insert 9: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());
        heap1.insert(4);
        System.out.println("insert 4: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());
        heap1.insert(1);
        System.out.println("insert 1: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());
        heap1.insert(1);
        System.out.println("insert 1: " + Arrays.toString(heap1.getArr()));
        System.out.println("min: " + heap1.min());

        // testing remove min 
        Heap heap2 = new Heap(10);
        System.out.println("\ntesting removeMin()");
        heap2.insert(5);
        System.out.println("insert 5: " + Arrays.toString(heap2.getArr()));
        heap2.insert(3);
        System.out.println("insert 3: " + Arrays.toString(heap2.getArr()));
        heap2.insert(6);
        System.out.println("insert 6: " + Arrays.toString(heap2.getArr()));
        heap2.insert(1);
        System.out.println("insert 1: " + Arrays.toString(heap2.getArr()));
        heap2.insert(9);
        System.out.println("insert 9: " + Arrays.toString(heap2.getArr()));
        heap2.insert(8);
        System.out.println("insert 8: " + Arrays.toString(heap2.getArr()));
        int removed = heap2.removeMin();
        // important: we don't actively delete values outside the scope of size. 
        // so just ignore numbers that are not at index less than or equal to size 
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
        removed = heap2.removeMin();
        System.out.println("removed " + removed + " :" + Arrays.toString(heap2.getArr()) + ", size: " + heap2.size());
    }
    
}

