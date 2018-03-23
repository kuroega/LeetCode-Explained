public class BasicCalculator {
	/*
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
        	}
        	else 
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
   			tmpNums.push (nums.pop());
   		}
        if (!nums.empty())
            tmpNums.push (nums.pop());

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
}