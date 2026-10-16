class BrowserHistory {
    private List<String> history;
    private int currentIndex;
    private int maxHistoryIndex;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
        maxHistoryIndex = 0;
    }
    
    public void visit(String url) {
        currentIndex++;
       
        if (currentIndex < history.size()) {
            history.set(currentIndex, url);
        } else {
            history.add(url);
        }
       
        maxHistoryIndex = currentIndex;
    }
    
    public String back(int steps) {
       
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }
    
    public String forward(int steps) {
       
        currentIndex = Math.min(maxHistoryIndex, currentIndex + steps);
        return history.get(currentIndex);
    }
}


/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */