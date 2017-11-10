package data_structures;

/**
 * Basic hash set implementation.
 *
 */
public class HashSet {

    private String[] values;

    public HashSet(int size) {
        this.values = new String[size];
    }

    /**
     * Computes the hash code to be used as an index for storing the value.
     */
    private int hashCode(String value) {
        return value.length();
    }

    public boolean add(String value) {
        int index = hashCode(value) % values.length; // mod to prevent going outside of array range
        if (values[index] == null) {
            values[index] = value;
            return true;
        }
        return false;
    }

    public boolean contains(String value) {
        int index = hashCode(value) % values.length;
        return value.equals(values[index]);
    }

}
