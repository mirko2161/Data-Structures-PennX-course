package data_structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic core implementation of a binary tree data structure.
 */
public class BinaryTree {

    protected Node root = null;
    private List<Integer> orderedList = new ArrayList<>();

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
        orderedList.add(n.value);
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

    public boolean add(Node n, int val) {
        if (n.value == val) {
            return false; // can't have dublicate values
        } else if (n.value > val) {
            if (n.leftChild == null) {
                n.leftChild = new Node(val);
                return true;
            } else {
                return add(n.leftChild, val);
            }
        } else {
            if (n.rightChild == null) {
                n.leftChild = new Node(val);
                return true;
            } else {
                return add(n.rightChild, val);
            }
        }
    }

    /**
     *
     * @param n the node to start at, root at first
     * @param parent the parent of the current node
     * @param val value to remove
     * @return true if successfully removed, false if the value wasn't in the tree
     */
    public boolean remove(Node n, Node parent, int val) {
        // if the root or the left/right side where we determined that the value must be if null,
        // then the value is not in the tree
        if (n == null) {
            return false;
        }
        if (val < n.value) {
            // start at the right child node as the root, with the current node beeing the parent
            return remove(n.rightChild, n, val);
        } else if (val > n.value) {
            return remove(n.leftChild, n, val);
        } else { // found the value to remove

            if (n.leftChild != null && n.rightChild != null) {
                // the value of the current node(the one to remove) becomes the biggest in its left side tree
                n.value = maxValue(n.leftChild);
                // and that biggest value if removed and the process repeated for any of its children
                remove(n.leftChild, n, n.value);
            } else if (parent.leftChild == n) { // else if at least one of them is null
                // we repoint the patents left/right child to the one that is not null
                parent.leftChild = (n.leftChild != null) ? n.leftChild : n.rightChild; // and if both null, parent will point to null
            } else {
                parent.rightChild = (n.leftChild != null) ? n.leftChild : n.rightChild;
            }

            return true; // regardless of the case above, the value is removed
        }
    }

    public int maxValue(Node n) {
        inorderTraversal(n);
        return orderedList.get(orderedList.size() - 1);
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
