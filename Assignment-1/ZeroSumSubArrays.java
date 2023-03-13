// technique: growing/shrinking sliding window with variable size
// time complexity: not super sure but I think O(n^2)
/*
a b c d e   -- n
(n + n - 1 + n - 2 + ... 1 )
(n - 1 + n - 1 + n - 2 ... 1)
...
1
 */
// space complexity: O(n)

import java.util.Arrays;

public class ZeroSumSubArrays {

    public static void main(String[] args) {
        int[] input = {4, 5, 2, -1, -3, -3, 4, 6, -7};
        int output = zeroSumSubArrays(input);
        System.out.println("Input Array: " + Arrays.toString(input));
        System.out.println("Expected output: 2");
        System.out.println("Output: " + output);

        int[] input1 = {1, 8, 7, 3, 11, 9};
        output = zeroSumSubArrays(input1);
        System.out.println("Input Array: " + Arrays.toString(input1));
        System.out.println("Expected output: 0");
        System.out.println("Output: " + output);

        int[] input2 = {8, -5, 0, -2, 3, -4};
        output = zeroSumSubArrays(input2);
        System.out.println("Input Array: " + Arrays.toString(input2));
        System.out.println("Expected output: 2");
        System.out.println("Output: " + output);

        int[] input3 = {};
        output = zeroSumSubArrays(input3);
        System.out.println("Input Array: " + Arrays.toString(input3));
        System.out.println("Expected output: 0");
        System.out.println("Output: " + output);

        int[] input4 = {0};
        output = zeroSumSubArrays(input4);
        System.out.println("Input Array: " + Arrays.toString(input4));
        System.out.println("Expected output: 1");
        System.out.println("Output: " + output);

        int[] input5 = {-2, 2, -3, 3};
        output = zeroSumSubArrays(input5);
        System.out.println("Input Array: " + Arrays.toString(input5));
        System.out.println("Expected output: 3");
        System.out.println("Output: " + output);
    }

    // Given an array of integers, count the number of subarrays that sum to zero.
    public static int zeroSumSubArrays (int[] arr) {
        int start = 0, end = arr.length-1;
        int count = 0;
        while(start < arr.length) {
            while (end >= start) {
                int sum = sum(arr, start, end);
                if (sum == 0) {
                    count ++;
                }
                end --;
            }
            end = arr.length - 1;
            start++;
        }
        return count;
    }

    public static int sum(int[] arr, int start, int end) {
        int sum = 0;
        for(int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

}

// time taken: 28 min