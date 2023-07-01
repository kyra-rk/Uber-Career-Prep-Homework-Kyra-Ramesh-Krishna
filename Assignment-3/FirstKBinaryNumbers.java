/* Spec: Given a number, k, return an array of the first k binary numbers, represented as strings.
 * Time taken:  40 minutes exactly
 * Strategy used: None?
 * Time Complexity: O(k*log(k)) ? Since we need to go over all numbers from 1... to k, 
   and for each, we need to iterate through all of its digits in binary (which is log(k))
 * Space Complexity: O(log(k)) ? Since we are storing an char arrat with all digits of k 
   in binary, aka we need log base 2 (k) digits to show k's binary representation
 */
 import java.util.*;

 public class FirstKBinaryNumbers{

    public static void main(String[] args){
        // add 1 to ""
        System.out.println("add 1 to : " + addOneTo(""));
        // add 1 to 0
        System.out.println("add 1 to 0: " + addOneTo("0"));
        // add 1 to 1
        System.out.println("add 1 to 1: " + addOneTo("1"));
        // add 1 to 10
        System.out.println("add 1 to 10: " + addOneTo("10"));
        // add 1 to 11
        System.out.println("add 1 to 11: " + addOneTo("11"));
        // add 1 to 100
        System.out.println("add 1 to 100: " + addOneTo("100"));
        // add 1 to 101
        System.out.println("add 1 to 101: " + addOneTo("101"));
        // add 1 to 111
        System.out.println("add 1 to 111: " + addOneTo("111"));

        System.out.println("\nFirst K Binary Numbers\n");
        int k = 0;
        System.out.format("First %d binary numbers: " + Arrays.toString(firstK(k)) + "\n", k);
        k = 1;
        System.out.format("First %d binary numbers: " + Arrays.toString(firstK(k)) + "\n", k);
        k = 5;
        System.out.format("First %d binary numbers: " + Arrays.toString(firstK(k)) + "\n", k);
        k = 10;
        System.out.format("First %d binary numbers: " + Arrays.toString(firstK(k)) + "\n", k);
    }

    // 0 10 11 100 101 111 1000 1001 1010 1011 1100 1101 1110 1111 etc.
    public static String[] firstK(int k){
        /* 
        how do I do this? 
        start with 0, then add 1. 
        how to add in binary? need to carry over 1 
          1
        0001
       +0001
       ------
        11
        0010
       +0110
       ------
        1000 
         */
         if (k <= 0){
            return null;
         } 
         String[] arr = new String[k];
         arr[0] = "0";
         for (int i = 1; i < k; i++){
            arr[i] = addOneTo(arr[i-1]);
         }
         return arr;
    }

    private static String addOneTo(String num){
        char[] binary = num.toCharArray();
        String output = "";
        // least significant digit, right most digit
        int lsd = binary.length - 1;
        boolean carryOver = true;
        int i = lsd; // 2
        //     0 1 2 
        //   [ 1 1 1 ]
        // "000"
        // System.out.println("lsd: " + lsd);
        while (i >= 0){
            // System.out.println("i: " + i);
            if (binary[i--] == '0' && carryOver == true){
                output = "1" + output;
                carryOver = false;
                break;
            } else {
                // System.out.println("in");
                output = "0" + output;
            }
        }
        // System.out.println("i: " + i + ", output: " + output);
        for (;i >= 0; i--){
            output = binary[i] + output;
        }
        if (carryOver == true){
            output = "1" + output;
        }
        return output;
    }

 }