package datastructure.hash;

public class MyHashTable<K, V> {
    // Mảng chứa các chuỗi liên kết (buckets)
    private HashNode<K, V>[] buckets;
    private int capacity; // Số lượng bucket
    private int size;     // Số lượng phần tử hiện tại

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        @SuppressWarnings("unchecked")
        HashNode<K, V>[] newBuckets = (HashNode<K, V>[]) new HashNode[capacity];
        this.buckets = newBuckets;
        this.size = 0;
    }

    // Hàm băm (Hash function) đơn giản
    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }

    // TODO: Sinh viên triển khai thêm phần tử vào Hash Table
    public void put(K key, V value) {
        // ...
    }

    // TODO: Sinh viên triển khai tìm kiếm phần tử theo Key (O(1) trong trường hợp lý tưởng)
    public V get(K key) {
        // ...
        return null;
    }

    // TODO: Sinh viên triển khai xóa phần tử
    public void remove(K key) {
        // ...
    }
}
