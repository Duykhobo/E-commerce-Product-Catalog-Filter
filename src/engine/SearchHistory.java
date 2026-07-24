package engine;

public class SearchHistory {

    private String[] history;
    private int size;
    private int capacity;

    public SearchHistory(int capacity) {
        this.capacity = capacity;
        this.history = new String[capacity];
        this.size = 0;
    }

    public void addSearchTerm(String term) {
        if (term == null || term.trim().isEmpty()) {
            return;
        }

        int targetLimit = (size < capacity) ? size : capacity - 1;
        for (int i = targetLimit; i > 0; i--) {
            history[i] = history[i - 1];
        }
        history[0] = term;
        if (size < capacity) {
            size++;
        }
    }

    public String[] getRecentSearches() {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = history[i];
        }
        return result;
    }

    public void clear() {
        size = 0;
    }
}

