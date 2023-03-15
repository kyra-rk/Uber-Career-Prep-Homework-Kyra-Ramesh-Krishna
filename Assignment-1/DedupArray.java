// technique: hash the elements
// time complexity: O(1)
// space complexity: O(n)

import java.util.Arrays;
import java.util.HashMap;

public class DedupArray {
    public static void main (String[] args){
        int[] array1 = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        int[] modified1 = dedupArray(array1);
        System.out.println("Input Array: " + Arrays.toString(array1));
        System.out.println("Expected Array: [1, 2, 3, 4]");
        System.out.println("Modified Array: " + Arrays.toString(modified1));

        int[] array2 = {0, 0, 1, 4, 5, 5, 5, 8, 9, 9, 10, 11, 15, 15};
        int[] modified2 = dedupArray(array2);
        System.out.println("Input Array: " + Arrays.toString(array2));
        System.out.println("Expected Array: [0, 1, 4, 5, 8, 9, 10, 11, 15]");
        System.out.println("Modified Array: " + Arrays.toString(modified2));

        int[] array3 = {1, 3, 4, 8, 10, 12};
        int[] modified3 = dedupArray(array3);
        System.out.println("Input Array: " + Arrays.toString(array3));
        System.out.println("Expected Array: [1, 3, 4, 8, 10, 12]");
        System.out.println("Modified Array: " + Arrays.toString(modified3));
    }
    /*
    Given a sorted array of non-negative integers, modify the array by removing duplicates
    so each element only appears once. If arrays are static (aka, not dynamic/resizable) in
    your language of choice, the remaining elements should appear in the left-hand side of
    the array and the extra space in the right-hand side should be padded with -1s.
     */
    public static int[] dedupArray(int[] input){
        HashMap<Integer, Integer> hash = new HashMap<>();
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            if (hash.get(input[i]) == null) {
                hash.put(input[i], 1);
                count++;
            }
            else {
                hash.put(input[i], hash.get(input[i]) + 1);
            }
        }
        int[] output = new int[count];
        int ix = 0;
        for (Integer i : hash.keySet()) {
            output[ix] = i;
            ix++;
        }
        return output;
    }
}

// time taken: 28 min