// technique: sort then solve and then two-pointer
// OK NOW IN HINDSIGHT I THINK I NEED TO USE A HASHMAP
// New strategy: create a hashmap for each string and ensure that the counts are no different than length - k
// time complexity: O(n log n) because of the sorting
// space complexity:

import java.util.Arrays;

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
    }
}

// time taken: 40 min (timed out)
// it works with the given answers BUT I don't think it's a correct solution
