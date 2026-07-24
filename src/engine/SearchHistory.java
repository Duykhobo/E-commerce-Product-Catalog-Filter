package engine;

public class SearchHistory {

    private String[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public SearchHistory(int capacity) {
        this.capacity = capacity;
        this.queue = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void addSearchTerm(String term) {
        if (term == null || term.isEmpty()) {
            return;
        }

        if (size == capacity) {
            front = (front + 1) % capacity;
            size--;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = term;
        size++;
    }

    public String[] getRecentSearches() {
        String[] result = new String[size];
        int count = 0;
        int current = rear;

        while (count < size) {
            result[count++] = queue[current];
            current = (current - 1 + capacity) % capacity;
        }
        return result;
    }

    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
    }
}
