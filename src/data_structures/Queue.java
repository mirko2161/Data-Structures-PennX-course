package data_structures;

import java.util.LinkedList;

/**
 * Basic core implementation of a queue data structure.
 *
 */
public class Queue {

    protected LinkedList list = new LinkedList();

    public void add(Object value) {
        list.add(value); // adds to end
    }

    public Object remove() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst(); // removes from front
    }

}
