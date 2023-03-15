// technique: forward-backward two-pointer
// time complexity: O(n^2)
// space complexity: O(n)
public class ShortestSubstring {

    public static void main(String[] args) {
        String input = "abracadabra";
        String req = "abc";
        System.out.println("Expected Output: 4");
        System.out.println("Output: " + shortestSubstring(input, req));

        input = "zxycbaabcdwxyzzxwdcbxyzabccbazyx";
        req = "zzyzx";
        System.out.println("Expected Output: 10");
        System.out.println("Output: " + shortestSubstring(input, req));

        input = "dog";
        req = "god";
        System.out.println("Expected Output: 3");
        System.out.println("Output: " + shortestSubstring(input, req));
    }

    /*
    Given a string and a second string representing required characters,
    return the length of the shortest substring containing all the required characters.
    */
    public static int shortestSubstring(String str, String req) {
        int len = str.length();
        char[] arr = str.toCharArray();
        boolean doesContain = false;
        int start = 0, end = str.length() - 1;
        while (start < str.length()) {
            while(end >= start) {
                if (containsAll(str.substring(start, end + 1), req) && ((end - start + 1) < len)) {
                    len = end - start + 1;
                    doesContain = true;
                }
                end --;
            }
            end = str.length() - 1;
            start++;
        }
        if (doesContain) {
            return len;
        }

        // does not contain 0
        return 0;
    }

    public static boolean containsAll(String str, String req) {
        for (int i = 0; i < req.length(); i++) {
            if (!str.contains(req.charAt(i) + "")){
                return false;
            }
        }
        return true;
    }
}
// I am struggling with the second and third test case
// how do I account for multiple of the same characters in the required string?
