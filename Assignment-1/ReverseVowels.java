// technique: forward-backward two-pointers (meet in the "middle")
// time complexity: O(n)
    // --> but multiplied by whatever the time complexity of contains is
// space complexity: O(n) 

public class ReverseVowels {

    public static void main(String arg[]){
        String input = "Uber Career Prep";
        String output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "xyz";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "flamingo";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "no";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "ex";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);

        input = "butter";
        output = reverseVowels(input);
        System.out.println("Input String: " + input);
        System.out.println("Modified String: " + output);
    }

    // Given a string, reverse the order of the vowels in the string.
    public static String reverseVowels(String input){

        char[] arr = input.toCharArray();

        int startIx = 0;
        int endIx = input.length() - 1;
        while ((startIx < endIx) && (startIx < arr.length) && (endIx >= 0)) {

            while(startIx < arr.length && !isVowel(arr[startIx])){
                startIx++;
            }
            while(endIx >= 0 && !isVowel(arr[endIx])){
                endIx--;
            }
            if (startIx < arr.length && startIx < endIx) {
                System.out.println("startIx: " + startIx + ", endIx: " + endIx);
                System.out.println("swap " + arr[startIx] + " with " + arr[endIx]);
                char temp = arr[startIx];
                arr[startIx] = arr[endIx];
                arr[endIx] = temp;
            }

            startIx++;
            endIx--;
        }
        String output = new String(arr);
        return output;
    }

    private static boolean isVowel(char c){
        String vowels = "aeiou";
        //System.out.println("is " + c + " a vowel?");
        return vowels.contains(Character.toLowerCase(c) + "");
    }
}

// time taken to complete: 38 min
