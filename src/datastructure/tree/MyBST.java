package datastructure.tree;

public class MyBST<T> {
    public TreeNode<T> root;

    public MyBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // TODO: Sinh viên tự triển khai phương thức insert vào cây (Ví dụ: insert theo Price hoặc Rating)
    public void insert(T data) {
        // ...
    }

    // TODO: Sinh viên triển khai duyệt cây (In-order, Pre-order, Post-order)
    public void inOrder() {
        // ...
    }

    // TODO: Sinh viên triển khai tìm kiếm trong BST
    public T search(double value) {
        // ...
        return null;
    }
}
