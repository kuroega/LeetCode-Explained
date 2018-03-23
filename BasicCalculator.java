public class BasicCalculator {
    /*  
        Basic Calculator I
        exp:
            "(1+(4+5+2)-3)+(6+8)" = 23

        use stacks
    */
    public int calculate(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        s = s.trim();
        char[] ca = s.toCharArray();
        int i = 0;
        while (i < ca.length) {
            char c = ca[i];

            if (c == ' ') {
                i++;
                continue;
            } // skip white space
            // deal with the calculation in paranthesis
            if (c == ')') {
                nums.push(eval(ops, nums));
                ops.pop(); // pop out '('
            }
            // other than ')'
            else if (c >= '0' && c <= '9') {
                int res = c - '0';
                // 123
                while (i + 1 < ca.length && ca[i + 1] >= '0' && ca[i + 1] <= '9') {
                    res = res * 10 + (ca[i + 1] - '0');
                    i++;
                }
                nums.push(res);
            } else
                ops.push(c);

            i++;
        }
        // deal with calculate outside of parathesis
        if (!ops.empty() && !nums.empty()) {
            return eval(ops, nums);
        }
        // no more calculations 
        return nums.pop();
    }

    private int eval(Stack<Character> ops, Stack<Integer> nums) {
        Stack<Character> tmpOps = new Stack<>();
        Stack<Integer> tmpNums = new Stack<>();

        // reverse the sequence of numbers and signs
        // because '+' and '-' are left associative
        while (!ops.empty() && ops.peek() != '(') {
            tmpOps.push(ops.pop());
            tmpNums.push(nums.pop());
        }
        if (!nums.empty())
            tmpNums.push(nums.pop());

        // eval
        while (!tmpOps.empty()) {
            char sign = tmpOps.pop();
            int n1 = tmpNums.pop();
            int n2 = tmpNums.pop();

            if (sign == '+')
                tmpNums.push(n1 + n2);
            else if (sign == '-')
                tmpNums.push(n1 - n2);
        }

        return tmpNums.pop(); // return the final result in stack
    }

    /*
        faster -> switch case 
    */
    public int calculate(String s) {
        int[] pair = helper(s.toCharArray(), 0);
        return pair[0];
    }    
    private int[] helper(char[] ca, int start) {
        int num = 0;
        int sign = 1;
        int result = 0;
        int i = start;
        EVAL_LOOP: while (i < ca.length) {
            char c = ca[i];
            switch(c) {
                case ' ':
                case '+':
                case '-':
                    result += sign * num;
                    num = 0;
                    sign = (c == '-') ? -1 : 1;
                    break;
                case '(':
                    int[] rec = helper(ca, i + 1);
                    num = rec[0];
                    i = rec[1];
                    result += sign * num;
                    num = 0;
                    sign = 1;
                    break;
                case ')':
                    break EVAL_LOOP;
                default:
                    num = num * 10 + (c - '0');
            }
            i++;
        }
        result += sign * num;
        return new int[]{result, i};
    }


    /*
        Basic Calculator II
        Follow-Up:
          only non-negative integers, +, -, *, /, and empty spaces
          integer division should truncate to zero

        Stack Approach
    */
    public int calculate(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        s = s.trim();
        char[] ca = s.toCharArray();
        int i = 0;
        // push numbers and operators to different stacks
        while (i < ca.length) {
            char c = ca[i];
            if (c == ' ') {
                i++;
                continue;
            }
            if ('0' <= c && c <= '9') {
                int res = c - '0';
                // 123
                while (i + 1 < ca.length && ca[i + 1] >= '0' && ca[i + 1] <= '9') {
                    res = res * 10 + (ca[i + 1] - '0');
                    i++;
                }
                nums.push(res);
            } else if (c == '/' || c == '*') { // eval sequentially to avoid denominator being zero
                // skip empty spaces
                while (i + 1 < ca.length && ca[i + 1] == ' ') i++; 
                i++;
                
                // next complete number
                int n2 = ca[i] - '0';
                while (i + 1 < ca.length && ca[i + 1] >= '0' && ca[i + 1] <= '9') {
                    n2 = n2 * 10 + (ca[i + 1] - '0');
                    i++;
                }
                
                int n1 = nums.pop();
                if (c == '*') nums.push(n1 * n2);
                else nums.push(n1 / n2);
            } else {
                ops.push(c);
            }
            i++;
        }
        // Set<Integer> s1 = new LinkedHashSet<>(nums);
        // for (int n : s1)
        //     System.out.print(n + " ");
        // eval
        return eval(ops, nums);
    }
    private int eval(Stack<Character> ops, Stack<Integer> nums) {
        Stack<Character> tmpOps = new Stack<>();
        Stack<Integer> tmpNums = new Stack<>();
        while (!ops.empty()) {
            char sign = ops.pop();
            if (sign == '*') {
                int n2 = nums.pop();
                int n1 = nums.pop();
                nums.push(n1 * n2);
            } else {
                tmpOps.push(sign);
                tmpNums.push(nums.pop());
            }
        }
        if (!nums.empty()) tmpNums.push(nums.pop());
        while (!tmpOps.empty()) {
            char sign = tmpOps.pop();
            int n1 = tmpNums.pop();
            int n2 = tmpNums.pop();
            if (sign == '+') tmpNums.push(n1 + n2);
            else if (sign == '-') tmpNums.push(n1 - n2); 
        }
        return tmpNums.peek(); // finish
    }

    /*
        Improvement:
            switch case approach
    */
     public int calculate(String s) {
        int product = 1;
        int sign = 1;
        int num = 0;
        int result = 0;
        boolean wasDivide = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            switch(c) {
                case '+':
                case '-':
                    if (wasDivide) result += sign * product / num;
                    else result += sign * product * num;
                    product = 1;
                    num = 0;
                    wasDivide = false; // VERY IMPORTANT !! otherwise ÷ 0 may happen
                    sign = (c == '+') ? 1 : -1;
                    break;
                case '*':
                case '/':
                    if (wasDivide) product /= num;
                    else product *= num;
                    num = 0;
                    wasDivide = (c == '/');
                    break;
                default:
                    num = num * 10 + (c - '0');
            }
        }
        // eval the rest
        if (wasDivide) result += sign * product / num;
        else result += sign * product * num;
        return result;
     }       
}