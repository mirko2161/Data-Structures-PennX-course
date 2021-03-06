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

    public void replaceHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head.next;
        head = newNode;
    }

    public boolean contains(int value) {
        Node current = head;
        while (current != null) { // the last node will point to null
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int getByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            // you always start at the head because that is the only reference you can directly access
            // can't start at the tail because you only have a reference to the NEXT node!
            Node current = head;
            for (int i = 0; i < index; i++) {
                if (current == null || current.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            return current.value;
        }
    }

    public void removeFromFront() {
        if (head != null) {
            head = head.next;
        }
        if (head == null) { // either the list was empty or we just removed the last element
            tail = null;
        }
    }

    public void removeFromBack() {
        if (head == null) { // empty list
            return;
        } else if (head == tail) { // only one element
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
    }

    public void removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            removeFromFront();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null || current.next == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            if (current.next == null) {
                throw new IndexOutOfBoundsException();
            }

            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
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

}
