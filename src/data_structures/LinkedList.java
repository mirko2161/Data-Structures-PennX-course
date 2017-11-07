package data_structures;

public class LinkedList {

    protected Node head = null;
    protected Node tail = null;

    public void addToFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        if (newNode.next == null) { // if the node it the only one in the list
            tail = newNode;
        }
    }

    public void addToBack(int value) {
        Node newNode = new Node(value);
        if (tail == null) { // if it's the only node in the list
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    public void addAtIndex(int index, int value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) { // adding to head
            addToFront(value);
        } else {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.next; // traversing nodes until we get to the desired one
            }
            if (current.next == null) { // then it added to the end and will be the tail
                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.next = current.next; // new Node will point to the node that was in the insertion place
                current.next = newNode; // the node before the index will point to the newly added node
            }
        }
    }

    class Node {

        int value;
        Node next = null;

        Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        new LinkedList().addAtIndex(4, 5);
    }
}