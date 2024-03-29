// technique: not sure
// OK in hindsight, this can probably be done with
// a simultaneous iteration two-pointer technique and using indexOf()
// time complexity: O(n + m) where n and m are the lengths of each string
// space complexity: O(n + m) where n and m are the lengths of each string

import java.util.ArrayList;
import java.lang.*;

public class BackspaceStringCompare {
    public static void main (String[] args) {
        String a = "abcde";
        String b = "abcde";
        System.out.println("Expected Output: true");
        System.out.println("Output: " + backspaceStringCompare(a, b));

        a = "Uber Career Prep";
        b = "u#Uber Careee#r Prep";
        System.out.println("Expected Output: true");
        System.out.println("Output: " + backspaceStringCompare(a, b));

        a = "abcdef###xyz";
        b = "abcw#xyz";
        System.out.println("Expected Output: true");
        System.out.println("Output: " + backspaceStringCompare(a, b));

        a = "abcdef###xyz";
        b = "abcdefxyz###";
        System.out.println("Expected Output: false");
        System.out.println("Output: " + backspaceStringCompare(a, b));
    }
    /*
    Given two strings representing series of keystrokes, determine whether the resulting text is the same.
    Backspaces are represented by the '#' character so "x#" results in the empty string ("").
     */
    public static boolean backspaceStringCompare(String a, String b){
        ArrayList<Character> arrA = new ArrayList<>();
        ArrayList<Character> arrB = new ArrayList<>();
        char c;
        for (int i = 0; i < a.length(); i++) {
            c = a.charAt(i);
            if(c == '#') {
                arrA.remove(arrA.size() - 1);
            }
            else {
                arrA.add(c);
            }
        }
        for (int i = 0; i < b.length(); i++) {
            c = b.charAt(i);
            if(c == '#') {
                arrB.remove(arrB.size() - 1);
            }
            else {
                arrB.add(c);
            }
        }
        if (arrA.size() != arrB.size()) {
            return false;
        }
        for (int i = 0; i < arrA.size(); i++){
            if (arrA.get(i) != arrB.get(i)) {
                return false;
            }
        }
        return true;
    }
}

// time taken: 37 min