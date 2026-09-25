class MyQueue {

    Stack<Integer> s = new Stack<>();

    public MyQueue() {
    }

    public void push(int x) {
        s.push(x);
    }

    public int pop() {
        if(s.size() == 1) {
            return s.pop();
        }

        int x = s.pop();

        int ans = pop();

        s.push(x);

        return ans;
    }

    public int peek() {
        if(s.size() == 1) {
            return s.peek();
        }

        int x = s.pop();

        int ans = peek();

        s.push(x);

        return ans;
    }

    public boolean empty() {
        return s.isEmpty();
    }
}