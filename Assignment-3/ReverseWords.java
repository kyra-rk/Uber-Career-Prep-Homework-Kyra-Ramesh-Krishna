/* Spec: Given a string, return the string with the order of the space-separated words reversed
 * Time taken: 15 min 
 * Data Structure/Algorithm: Stack? (I could have also done it without a stack. is there a more efficient way?)
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
 import java.util.*;

 public class ReverseWords{

    public static void main(String[] args){
        String input = "Uber Career Prep";
        String output = reverseWords(input);
        System.out.println("\nInput: " + input);
        System.out.println("Output: " + output + "\n");

        input = "Emma lives in Brooklyn, New York.";
        output = reverseWords(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output + "\n");

        input = "Kyra";
        output = reverseWords(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output + "\n");

        input = "";
        output = reverseWords(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output + "\n");

        input = "hello, world";
        output = reverseWords(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output + "\n");
    }

    public static String reverseWords(String input){
        String[] arr = input.split(" ");
        Stack<String> stack = new Stack<>();
        for (String s : arr){
            stack.push(s);
        }
        String output = "";
        while (stack.size() != 0){
            output += stack.pop() + " ";
        }
        // remove the extra zero 
        if (output.charAt(output.length() - 1) == ' '){
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }

 }