public class SimplifyPath {
    /*
        Linear scanning

    e.g.    path = "/home/", => "/home"
            path = "/a/./b/../../c/", => "/c"
        Brute Force
        it is the worst algorithm
        1. String manipulation is slow
        2. To many if else makes algorithm complicated
        3. Easily failed if new test cases added
            30ms
    */
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("/"); // initial path
        int i = 0;
        while (i < path.length()) {
            if (path.charAt(i) == '.') {
                int dot = 0;
                while (i < path.length() && path.charAt(i) == '.') {
                    dot++;
                    i++;
                } // count '.'
                if (dot == 1 && (i == path.length() || path.charAt(i) == '/')) {       // single dot
                    continue;
                } else if (dot == 2 && (i == path.length() || path.charAt(i) == '/')){ // double dots
                    int slash = 0;
                    while (slash < 2 && sb.length() > 0) {
                        if (sb.charAt(sb.length() - 1) == '/')
                            slash++;
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.append("/");
                } else {             // many dots
                    while (dot > 0) {
                        sb.append(".");
                        dot--;
                    }
                }
            } else if (path.charAt(i) == '/') {
                i++;
                continue; // skip '//'
            } else {
                while (i < path.length() && path.charAt(i) != '.' && path.charAt(i) != '/') {
                    sb.append(path.charAt(i)); // append letters
                    i++;
                }
                sb.append("/");
            }
        }
        if (sb.length() > 1 && sb.charAt(sb.length() - 1) == '/')
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /*
        Smarter way?
        Use stack
        ...and use split
        Time: O(2N)
        Space: O(2N)
            15ms
    */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String p : path.split("/")) {
            if (p.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(p)) stack.push(p);
        }
        String res = ""; // OR we can use StringBuilder as well...
        for (String p : stack) res = "/" + p + res;
        return res.isEmpty() ? "/" : res;
    }

    /*
        similar way by using array
        Time: O(2N)
        Space: O(3N)
            9ms
    */
    public String simplifyPath(String path) {
        String[] dir = path.split("/");
        String[] stack = new int[dir.length];
        int head = 0;
        for (int i = 0; i < dir.length; i++) {
            if (dir[i] == "." || dir[i] == "")
                continue;
            else if (dir[i] == "..") {
                if (head > 0) head--;
            } else {
                stack[head] = dir[i];
                head++;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < head; i++) {
            res.append("/");
            res.append(stack[head]);
        }
        return res.length() == 0 ? "/" : res.toString();
    }


}
