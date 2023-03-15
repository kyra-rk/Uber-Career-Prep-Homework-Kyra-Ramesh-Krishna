// technique: sort then solve and then two-pointer X NEW: increment/decrement counts with hashmap
// time complexity: O(n log n) because of the sorting X NEW: O(n)
// space complexity: O(1) X NEW: O(n)

import java.util.Arrays;
import java.util.HashMap;

public class KAnagrams {
    public static void main(String[] args) {
        String a = "apple";
        String b = "peach";
        int k = 1;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: false");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "apple";
        b = "peach";
        k = 2;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: true");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "cat";
        b = "dog";
        k = 3;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: true");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "cat";
        b = "dog";
        k = 2;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: false");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "debit curd";
        b = "bad credit";
        k = 1;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: true");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "baseball";
        b = "basketball";
        k = 2;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: false");
        System.out.println("Output: " + kAnagrams(a, b, k));

        a = "bridge";
        b = "smidgp";
        k = 3;
        System.out.println("Input: " + a + ", " + b + ", k: " + k);
        System.out.println("Expected output: true");
        System.out.println("Output: " + kAnagrams(a, b, k));
    }

    /* Two strings are considered to be “k-anagrams” if they can be made into anagrams
    by changing at most k characters in one of the strings. Given two strings and an integer k,
    determine if they are k-anagrams.
    */
    public static boolean kAnagrams(String a, String b, int k){
        // new solution:
        int mismatch = 0;
        if (a.length() != b.length()) {
            return false;
        }
        HashMap<Character, Integer> hash = new HashMap<>();
        // updated hash with a
        for (int i = 0; i < a.length(); i++) {
            if (hash.get(a.charAt(i)) == null){
                hash.put(a.charAt(i), 1);
            }
            else{
                hash.put(a.charAt(i), hash.get(a.charAt(i)) + 1);
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (hash.get(b.charAt(i)) == null){
                mismatch ++;
            }
            else{
                hash.put(b.charAt(i), hash.get(b.charAt(i)) - 1);
            }
        }
        for (Character c : hash.keySet()) {
            if (hash.get(c) != 0) {
                mismatch++;
            }
        }
        return mismatch/2 <= k;
        /*
        if (a.length() != b.length()) {
            return false;
        }
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        int i = 0, j = 0, mismatch = 0;
        while (i < arrA.length && j < arrA.length) {
            while(i < arrA.length && arrA[i] < arrB[j]) {
                i++;
                mismatch++;
            }
            while(i < arrA.length && j < arrA.length && arrB[j] < arrA[i]) {
                j++;
                mismatch++;
            }
            i++;
            j++;
        }
        return mismatch <= k || k == arrA.length;
        */

    }
}

// time taken: 40 min (timed out)
// creating my new version took around 10-15 min