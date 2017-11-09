package data_structures;

import java.util.LinkedList;

/**
 * Basic core implementation of a stack data structure.
 *
 * @author mirko
 */
public class Stack {

    protected LinkedList list = new LinkedList();

    public void push(Object value) {
        list.addFirst(value);
    }

    public Object pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public Object peek() {
        return list.getFirst();
    }

}
