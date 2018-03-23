public class LongestValidParentheses {
    /*
        Use Stack
    */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int left = -1;
        for (int i = 0; i < s.length(); i++) { // scan the string
            if (s.charAt(i) == '(') stack.push(i); // meet '(', no doubt, add
            else { // meet ')'
                if (stack.isEmpty()) left = i; // no match available, update left
                else {  // there is a match
                    stack.pop(); // pop out the '('
                    if (stack.isEmpty()) max = Math.max(max, i - left); // no '(' left, update max
                    else max = Math.max(max, i - stack.peek()); // has '(' left, update left and max
                }
            }
        }
        return max;
    }
}
