package data_structures;

/**
 * Basic core implementation of a binary tree data structure.
 */
public class BinaryTree {

    protected Node root = null;

    /**
     * Used to print/return etc the entire tree in sorted order
     *
     * @param n root node
     */
    public void inorderTraversal(Node n) {
        if (n == null) {
            return;
        }
        inorderTraversal(n.leftChild);
        visit(n);
        inorderTraversal(n.rightChild);
    }

    private void visit(Node n) {
        System.out.println(n.value);
    }

    public boolean binaryTreeSearch(Node n, int val) {
        if (n == null) {
            return false;
        }
        if (n.value == val) {
            return true;
        } else if (n.value > val) {
            return binaryTreeSearch(n.leftChild, val); // recursively search the left side
        } else {
            return binaryTreeSearch(n.rightChild, val); // or right, and return the result of that search
        }
    }

    class Node {

        int value;
        Node leftChild = null;
        Node rightChild = null;

        Node(int value) {
            this.value = value;
        }

    }

}
