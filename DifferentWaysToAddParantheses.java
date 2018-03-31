public class DifferentWaysToAddParantheses {
    Map<String, List<Integer>> map = new HashMap<>(); // use map to memoization for avoid repeated computing
    public List<Integer> diffWaysToCompute(String input) {
        
        return ways(input); // original method name too long...
    }

    private List<Integer> ways(String input) {
        if (map.containsKey(input)) return map.get(input);
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char op = input.charAt(i);
            if (op == '+' || op == '-' || op == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1); // skip 'op' otherwise "" string causes integer parsing failure
                List<Integer> l = ways(left);
                List<Integer> r = ways(right);
                
                for (int a : l)
                    for (int b : r)
                        switch(op) {
                            case '+': ans.add(a + b);
                                      break;
                            case '-': ans.add(a - b);
                                      break;
                            case '*': ans.add(a * b);
                                      break;
                        }

            }
        }
        if (ans.size() == 0) // single character '1'
            ans.add(Integer.parseInt(input));
        map.put(input, ans);
        return ans;
    }
}

/*
    Similar ideas to Word Breaker I && II
        计划递归

        every time we see a operator,
        we split to 2 sub-expressions
        then calculate first part and second part

*/