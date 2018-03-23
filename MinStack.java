class MinStack {
    class Element {
        int val;
        int min;
        public Element(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    Stack<Element> stack;

    public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            Element e = new Element(x, stack.isEmpty() ? x : Math.min(x, stack.peek().min));
            stack.push(e);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().val;
        }

        public int getMin() {
            return stack.peek().min;
        }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
