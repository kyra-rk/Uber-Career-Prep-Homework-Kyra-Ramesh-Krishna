// technique:
// time complexity:
// space complexity:

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args){
        int[] input1 = {1, 10, 8, 3, 2, 5, 7, 2, -2, -1};
        int k = 10;
        int output = twoSum(input1, k);
        System.out.println("Input: " + Arrays.toString(input1));
        System.out.println("Expected Output: 3");
        System.out.println("Output: " + output);

        int[] input2 = {1, 10, 8, 3, 2, 5, 7, 2, -2, -1};
        k = 9;
        output = twoSum(input2, k);
        System.out.println("Input: " + Arrays.toString(input2));
        System.out.println("Expected Output: 4");
        System.out.println("Output: " + output);

        int[] input3 = {4, 3, 3, 5, 7, 0, 2, 3, 8, 6};
        k = 6;
        output = twoSum(input3, k);
        System.out.println("Input: " + Arrays.toString(input3));
        System.out.println("Expected Output: 5");
        System.out.println("Output: " + output);

        int[] input4 = {4, 3, 3, 5, 7, 0, 2, 3, 8, 6};
        k = 1;
        output = twoSum(input4, k);
        System.out.println("Input: " + Arrays.toString(input4));
        System.out.println("Expected Output: 0");
        System.out.println("Output: " + output);
    }

    /*
    Given an array of integers and a target integer, k, return the number of pairs
    of integers in the array that sum to k. In each pair, the two items must be distinct
    elements (come from different indices in the array).
     */
    public static int twoSum(int[] input, int k){
        ArrayList<Pair> list = new ArrayList<>();
        int i = 0, j = 1;
        while (i < input.length) {
            while (j < input.length) {
                if ((input[i] + input[j]) == k) {
                    Pair t = new Pair(input[i], input[j]);
                    list.add(t);
                }
                j++;
            }
            i++;
            j = i + 1;
        }
        return list.size();
    }
}

// time taken: