public class LetterCombinationOfPhoneNumber {
    /*
       recursion DFS Solution
   */

   private static String[] KEYS = new String[]{"", "", "abc", "def", "ghi",
       "jkl", "mno", "pqrs", "tuv", "wxyz"};

   public List<String> letterCombinations(String digits) {
       List<String> res = new ArrayList<>();
       helper(digits, res, "", 0);
       return res;

   }
   private void helper(String digits, List<String> res, String s, int offset) {
       if (offset < digits.length()) { // next digit
           String letters = KEYS[(digits.charAt(offset) - '0')]; // abc
           // find letters
           for (int i = 0; i < letters.length(); i++) {
               helper(digits, res, s + letters.charAt(i), offset + 1);
           } // combine
       }
       else {
           if (!s.isEmpty())
               res.add(s);
           return;
       }
   }

   /*
        iterative Solution: Queue FIFO
   */
   private static String[] KEYS = new String[]{"", "", "abc", "def", "ghi",
       "jkl", "mno", "pqrs", "tuv", "wxyz"};

   public List<String> letterCombinations(String digits) {
       LinkedList<String> res = new LinkedList<>(); // use LinkedList not List
       // create a Queue
       if (digits.length() == 0) return res;
       res.add(""); // base element

       for (int i = 0; i < digits.length(); i++) { // iteration
           while (res.peek().length() == i) { // peek length
               String s = res.remove();   // remove head
               for (char c : KEYS[(digits.charAt(i) - '0')].toCharArray())
                    res.add(s + c); // add into res
           }
       }
       return res;
   }
}
