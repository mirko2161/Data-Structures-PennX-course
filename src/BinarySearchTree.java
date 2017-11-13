
/**
 * Note: Homework tests require that the class being tested is in the default package, and also that
 * the test files and .jar are in the root directory.
 *
 * This assignment asks you to use and modify a binary search tree (BST) implementation in order to
 * determine whether a tree is balanced. In completing this assignment, you will:
 *
 * Apply what you have learned about how binary search trees represent and store data
 * Implement tree traversal algorithms for determining the structure of a tree
 * Gain an understanding of additional tree-related concepts
 *
 * We have provided you with a generic implementation of the BinarySearchTree. This implementation
 * ensures that all elements in the tree are distinct, i.e. have different values.
 *
 * First, implement the findNode method in BinarySearchTree.java. Given a value that is stored in
 * the BST, it returns the corresponding Node that holds it. If the value does not exist in this
 * BST, this method should return null.
 *
 * Then, implement the depth method. Given a value, this method should return the “depth” of its
 * Node, which is the number of ancestors between that node and the root, including the root but not
 * the node itself. The depth of the root is defined to be 0; the depth of its two children (if any)
 * is defined to be 1; the depth of the root’s grandchildren (if any) is defined to be 2; and so on.
 * If the value is null or does not exist in this BST, this method should return -1.
 *
 * Next, implement the height method. Given a value, this method should return the “height” of its
 * Node, which is the greatest number of nodes between that node and any descendant node that is a
 * leaf, including the leaf but not the node itself. The height of a leaf node (i.e., one which has
 * no children) is defined to be 0. If the input value is null or does not exist in this BST, this
 * method should return -1.
 *
 * Next, implement the isBalanced(Node) method. Given a Node, return true if the absolute value of
 * the difference in heights of its left and right children is 0 or 1, and return false otherwise.
 * If the Node is null or does not exist in this BST, this method should return false.
 * Note that if a Node's child is null, then the height of that child should be considered as -1.
 *
 * Finally, implement isBalanced() so that it returns true if isBalanced(Node) returns true for all
 * Nodes in the tree. This method then allows the user of the BST to determine whether the BST is
 * balanced, using the methods you’ve implemented above. Note that the root being balanced does not
 * imply that the entire tree is balanced.
 *
 * @author mirko
 */
public class BinarySearchTree<E extends Comparable<E>> {

    protected Node root = null;

    protected void visit(Node n) {
        System.out.println(n.value);
    }

    public boolean contains(E val) {
        return contains(root, val);
    }

    protected boolean contains(Node n, E val) {
        if (n == null) {
            return false;
        }

        if (n.value.equals(val)) {
            return true;
        } else if (n.value.compareTo(val) > 0) {
            return contains(n.leftChild, val);
        } else {
            return contains(n.rightChild, val);
        }
    }

    public boolean add(E val) {
        if (root == null) {
            root = new Node(val);
            return true;
        }
        return add(root, val);
    }

    protected boolean add(Node n, E val) {
        if (n == null) {
            return false;
        }
        int cmp = val.compareTo(n.value);
        if (cmp == 0) {
            return false; // this ensures that the same value does not appear more than once
        } else if (cmp < 0) {
            if (n.leftChild == null) {
                n.leftChild = new Node(val);
                return true;
            } else {
                return add(n.leftChild, val);
            }
        } else {
            if (n.rightChild == null) {
                n.rightChild = new Node(val);
                return true;
            } else {
                return add(n.rightChild, val);
            }
        }
    }

    public boolean remove(E val) {
        return remove(root, null, val);
    }

    protected boolean remove(Node n, Node parent, E val) {
        if (n == null) {
            return false;
        }

        if (val.compareTo(n.value) == -1) {
            return remove(n.leftChild, n, val);
        } else if (val.compareTo(n.value) == 1) {
            return remove(n.rightChild, n, val);
        } else {
            if (n.leftChild != null && n.rightChild != null) {
                n.value = maxValue(n.leftChild);
                remove(n.leftChild, n, n.value);
            } else if (parent == null) {
                root = n.leftChild != null ? n.leftChild : n.rightChild;
            } else if (parent.leftChild == n) {
                parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
            } else {
                parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
            }
            return true;
        }
    }

    protected E maxValue(Node n) {
        if (n.rightChild == null) {
            return n.value;
        } else {
            return maxValue(n.rightChild);
        }
    }

    /**
     * Given a value that is stored in the BST, it returns the corresponding Node that holds it.
     *
     * @param val the value whose node to find
     * @return the node with the value, or null if the value does not exist
     */
    public Node findNode(E val) {
        if (root == null || val == null) {
            return null;
        } else {
            return binaryTreeSearch(root, val);
        }
    }

    private Node binaryTreeSearch(Node n, E val) {
        if (n == null) {
            return null;
        }
        if (n.value.equals(val)) {
            return n;
        } else if (n.value.compareTo(val) > 0) {
            return binaryTreeSearch(n.leftChild, val);
        } else {
            return binaryTreeSearch(n.rightChild, val);
        }
    }

    /**
     * Given a value, this method returns the “depth” of its Node, which is the number of ancestors
     * between that node and the root, including the root but not the node itself.
     *
     * @param val value of the Node whose depth to find
     * @return the depth of the desired Node, or -1 if the value is null or does not exist
     */
    protected int depth(E val) {
        if (val == null || !contains(val)) {
            return -1;
        } else {
            return calculateDepth(root, val);
        }
    }

    private int calculateDepth(Node n, E val) {
        if (n == null) {
            return 0;
        }
        if (n.value.equals(val)) {
            return 0;
        } else if (n.value.compareTo(val) > 0) {
            return 1 + calculateDepth(n.leftChild, val);
        } else {
            return 1 + calculateDepth(n.rightChild, val);
        }
    }

    // Method #3.
    protected int height(E val) {

        /* IMPLEMENT THIS METHOD! */
        return -2; // this line is here only so this code will compile if you don't modify it
    }

    // Method #4.
    protected boolean isBalanced(Node n) {

        /* IMPLEMENT THIS METHOD! */
        return true; // this line is here only so this code will compile if you don't modify it
    }

    // Method #5. .
    public boolean isBalanced() {

        /* IMPLEMENT THIS METHOD! */
        return false; // this line is here only so this code will compile if you don't modify it
    }

    class Node {

        E value;
        Node leftChild = null;
        Node rightChild = null;

        Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if ((obj instanceof BinarySearchTree.Node) == false) {
                return false;
            }
            @SuppressWarnings("unchecked")
            Node other = (BinarySearchTree<E>.Node) obj;
            return other.value.compareTo(value) == 0
                    && other.leftChild == leftChild && other.rightChild == rightChild;
        }

    }

}
