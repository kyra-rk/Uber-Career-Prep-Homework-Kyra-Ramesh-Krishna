// technique: binary search variation
// time complexity: O(log n)
// space complexity: O(n) -- not sure how to do this for sorting/recursion
// do I need to keep the stack frames (of recursion) into account?
// question: is a helper method allowed?

import java.util.Arrays;

public class MissingInteger {
    public static void main(String[] args){
        int[] arr1 = {1, 2, 3, 4, 6, 7};
        int output = missingInteger(arr1.length, arr1);
        System.out.println("Input Array: " + Arrays.toString(arr1));
        System.out.println("Expected Output: 5");
        System.out.println("Output: " + output);

        int[] arr2 = {1};
        output = missingInteger(arr2.length, arr2);
        System.out.println("Input Array: " + Arrays.toString(arr2));
        System.out.println("Expected Output: 2");
        System.out.println("Output: " + output);

        int[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12};
        output = missingInteger(arr3.length, arr3);
        System.out.println("Input Array: " + Arrays.toString(arr3));
        System.out.println("Expected Output: 9");
        System.out.println("Output: " + output);
    }
    // Given an integer n and a sorted array of integers of size n-1 which contains
    // all but one of the integers in the range 1-n, find the missing integer.
    public static int missingInteger(int n, int[] arr){
        return helperMissingInt(0, n - 1, arr);
        /* naive approach
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        return n;
        */
    }

    private static int helperMissingInt(int low, int high, int[] arr) {
        if (low >= high) {
            return high + 2; // high/low is the index of the item that is right before the missing integer
        }
        int middle = (high - low)/2 + low;
        if (arr[middle] == middle + 1) {
            return helperMissingInt(middle + 1, high, arr);
        }
        else {
            return helperMissingInt(low, middle - 1, arr);
        }
    }
}

// time taken: 37 minutes