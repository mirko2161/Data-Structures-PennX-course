package data_structures;

import java.util.LinkedList;

/**
 * Basic hash set implementation using separate chaining to avoid collisions. The hast set resizes
 * itself based on the load factor ratio to make sure the access/search etc time stays close to
 * O(1).
 *
 */
public class HashSet {

    // all the elements with the same hash will be together in a bucket
    private LinkedList<String>[] buckets;
    private int currentSize = 0;
    private double loadFactor; // balance between time and space tradeoffs

    public HashSet(int size, double loadFactor) {
        this.buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.loadFactor = loadFactor;
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
            currentSize++;

            double averageLoad = currentSize / (double) buckets.length;
            if (averageLoad > loadFactor) {
                reinsertAll();
            }
            return true;
        }
        return false;
    }

    private void reinsertAll() {
        LinkedList<String> oldBuckets[] = buckets;
        buckets = new LinkedList[buckets.length * 2]; // the new array will be twice the size the old one

        for (LinkedList<String> bucket : oldBuckets) {
            for (String element : bucket) {
                // because of the new array length some of the element will have a new index
                int index = hashCode(element) % buckets.length;
                LinkedList<String> newBucket = buckets[index];
                newBucket.addFirst(element);
            }
        }
    }

    public boolean contains(String value) {
        int index = hashCode(value) % buckets.length;
        LinkedList<String> bucket = buckets[index];
        return bucket.contains(value);
    }

}
