public class ValidParantheses {
    /*
        Queue/Stack Solution
        For General Case: can validate "[()]{[]}" (nested)
        Time: >> O(N)
        Space: >> O(N)
    */
    private static Map<Character, Character> map = new HashMap<>();

    public boolean isValid(String s) {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        if (s.length() == 0) return true;

        LinkedList<Character> parans = new LinkedList<>();
        for (char c : s.toCharArray()) parans.add(c);
        // queue for parantheses

        if (parans.peek() == ')' || parans.peek() == ']' || parans.peek() == '}')
            return false;

        LinkedList<Character> stack = new LinkedList<>(); // stack for check

        while (parans.size() != 0) {

            while (parans.size() != 0 && map.get(stack.peekLast()) == parans.peekFirst()) {
                stack.pollLast();
                parans.pollFirst();
            } // cancel all matches

            if (parans.size() == 0 && stack.size() == 0) return true;

            stack.add(parans.pollFirst()); // add if no match
        }
        return stack.size() == 0 ? true : false;
    }

    /*
        Switch Case Solution [ELEGENT]
        Time: O(N)
        Space: O(N)
    */
    public boolean isValid(String s) {
        char[] validate = new char[s.length()];
        int head = 0;

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':  validate[head++] = c;
                           break;
                case ')':  if (head == 0 || validate[--head] != '(')
                               return false;
                           break;
                case ']':  if (head == 0 || validate[--head] != '[')
                               return false;
                           break;
                case '}':  if (head == 0 || validate[--head] != '{')
                               return false;
                           break;
            }
        }
        return head == 0;
    }

}
