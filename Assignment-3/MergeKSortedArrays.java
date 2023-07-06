/* Spec: Given an array of k sorted arrays, merge the k arrays into a single sorted array.
 * Strategy: Hash Map to store current index "pointers" 
 * Time taken: timed out 
 * Time complexity: not fully sure. I am thinking maybe O(K*N)?
 * Space complexity: O(K)
 */
 import java.util.*;

 public class MergeKSortedArrays{
    public static void main(String[] args){
        int k = 2;
        int[][] input = {{1, 2, 3}, {2, 3, 4}};
        System.out.println("Output: " + Arrays.toString(mergeKSortedArrays(k, input)) + "\n");

        k = 2;
        int [][] input2 = {{1, 2, 3, 4, 5}, {1, 3, 5, 7, 9}};
        System.out.println("Output: " + Arrays.toString(mergeKSortedArrays(k, input2)) + "\n");
        // doesn't work! I need to somehow keep track of that element 
    }

   /* Note: in a more complicated version, we would have generic types 
      and use a compareTo() function to do this sorting. */
    public static int[] mergeKSortedArrays(int k, int[][] arrays){
      // get the total number of ints in the array 
      int length = 0; 
      // make one pointer for each array
      Map<int[], Integer> pointers = new HashMap<>();
      // max length keeps track of length of the longest array 
      int maxLen = 0;
      for (int[] arr : arrays){
         length += arr.length;
         pointers.put(arr, 0);
         if (arr.length > maxLen){
            maxLen = arr.length;
         }
      }
      int[] output = new int[length];
      int[] temp = new int[k]; // temp list will never be longer than k 
      int tempIx, pointer, size, lastValue;
      int ix = 0;
      System.out.println("length: " + length + ", maxLen: " + maxLen);
      while (!pointers.isEmpty()){
         // get current elements and build array 
         size = 0;
         tempIx = 0;
         lastValue = Integer.MAX_VALUE;
         for (int[] arr : pointers.keySet()){
            pointer = pointers.get(arr);
            // if the pointer is within the bounds of that array and it less than or equal to last value
            // then add it to the temporary list 
            System.out.println("last value: " + lastValue);
            if (pointer < arr.length && arr[pointer] <= lastValue){
               System.out.println("in - arr[pointer]: " + arr[pointer]);
               lastValue = arr[pointer];
               temp[tempIx] = arr[pointer]; // supposedly O(1) but is it actually? 
               pointers.replace(arr, pointer + 1);
               tempIx++;
               size++;
            } else {
               // we've reached the end of that array - remove it from the map
               pointers.remove(arr);
            }
         }
         Arrays.sort(temp);
         System.out.println("Sorted temp: " + Arrays.toString(temp));
         // size keeps track of number of items in temp that we need to add to the final output
         for (int j = 0; j < size; j++){
            output[ix] = temp[j];
            ix++;
         }
      }
      return output;
      // go through all arrays and add the int at the current index to an array 
      // sort the array and append it to the list O(k) to sort * N integers worst case 
    }
 }