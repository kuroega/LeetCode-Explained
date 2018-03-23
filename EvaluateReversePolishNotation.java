public class EvaluateReversePolishNotation {
    /*
        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
    */

    /*
        Stack solution
    */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b + a);
            } else if (token.equals("-")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if (token.equals("*")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b * a);
            } else if (token.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(token)); // numbers
            }
        }
        return stack.pop();
    }
    /*
        Or... more elegant
    */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (isOps(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(eval(b, a, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    private boolean isOps(String t) {
        switch (t) {
            case "+": case "-": case "*": case "/": return true;
        }
        return false;
    }
    private int eval(int a, int b, String op) {
        switch (op) {
            case "+":   return a + b;
            case "-":   return a - b;
            case "*":   return a * b;
            case "/":   return a / b;
        }
        return 0;
    }
}
