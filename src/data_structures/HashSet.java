package data_structures;

import java.util.LinkedList;

/**
 * Basic hash set implementation using separate chaining to avoid collisions.
 *
 */
public class HashSet {

    // all the elements with the same hash will be together in a bucket
    private LinkedList<String>[] buckets;

    public HashSet(int size) {
        this.buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Computes the hash code to be used as an index for storing the value.
     */
    private int hashCode(String value) {
        return value.length();
    }

    public boolean add(String value) {
        if (!contains(value)) {
            int index = hashCode(value) % buckets.length; // mod to prevent going outside of array range
            LinkedList<String> bucket = buckets[index];
            bucket.addFirst(value);
            return true;
        }
        return false;
    }

    public boolean contains(String value) {
        int index = hashCode(value) % buckets.length;
        LinkedList<String> bucket = buckets[index];
        return bucket.contains(value);
    }

}
