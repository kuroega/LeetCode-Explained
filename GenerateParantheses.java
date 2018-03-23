public class GenerateParantheses {
    /*
        Brute Force solution    Time: O(2^(2n) * n)
            generate all and    Space: O(2^(2n) * n)
            validate them
    */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateAll(res, 2 * n, "");
        return res;
    }

    private void generateAll(List<String> res, int n, String s) { // recursion
        if (n == 0) {
            if (validate(s)) res.add(s);
            return; // base case
        }

        generateAll(res, n - 1, s + "(");
        generateAll(res, n - 1, s + ")");
    }

    private boolean validate(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (counter < 0) return false;
            if (s.charAt(i) == '(') counter++;
            else counter--;
        }
        return counter == 0 ? true : false;
    }

    /*
        Backtracking Solution

        Time: O(4^n / √n) Each valid sequence has at
            most n steps during the backtracking procedure.
        Space: O(4^n / √n)
    */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, n, 0, 0, "");
        return res;
    }
    private void backtracking(List<String> res, int n, int opens, int closes, String s) {
        if (opens == n && closes == n) {
            res.add(s);
            return;
        } // base case

        if (opens < n) { // add "("
            backtracking(res, n, opens + 1, closes, s + "(");
        }
        if (opens > closes) { // add ")"
            backtracking(res, n, opens, closes + 1, s + ")");
        }
    }

    /*

    */
}
