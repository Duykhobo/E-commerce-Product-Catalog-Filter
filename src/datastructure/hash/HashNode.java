package datastructure.hash;

public class HashNode {
    private Object key;
    private Object value;
    private HashNode next;

    public HashNode(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public HashNode getNext() {
        return next;
    }

    public void setNext(HashNode next) {
        this.next = next;
    }
}
