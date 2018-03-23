class MyStack {
	Queue<Integer> q1;
	Queue<Integer> q2;

	public MyStack() {
		q1 = new LinkedList<>();
		q2 = new LinkedList<>();
	}

	public void push(int x) {
		// migrate elements in q1 to q2
		while (!q1.isEmpty()) 
			q2.add(q1.remove());

		// value on top of stack
		q1.add(x);

		// retrive elements from q2 to form a stack structure (FILO)
		while (!q2.isEmpty())
			q1.add(q2.remove());
	}

	public int pop() {
		return q1.remove();
	}

	public int top() {
		return q1.peek();
	}

	public boolean empty() {
		return q1.isEmpty();
	}
}