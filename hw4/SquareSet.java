import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
/**
 * Class designed to create a set containing all squares on chess board
 *
 * @author Peter Herman
 * @version 1.0
 */
public class SquareSet implements Set<Square> {


    private Square[] squares;
    /**
     * Creates an empty Set of type Square
     */
    public SquareSet() {
        squares = new Square[10];
    }

    /**
     * Adds the specific square to this set if it is not already present
     *
     * @param s - the square to be added
     * @return a boolean of whether or not the square was added
     */
    public boolean add(Square s) {
        if (this.contains(s)) {
            return false;
        } else {
            if (needExpand()) {
                expand();
            }
            for (int i = 0; i < squares.length; i++) {
                if (squares[i] == null) {
                    squares[i] = s;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the backing structure needs to be expanded in size
     * and expands the
     * @return boolean - true if backing structure needs to be expanded
     */
    public boolean needExpand() {
        for (Square s : squares) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * expands size of Set by creating an array that is 10 positions longer
     * than previous array. Then copies entries from shorter array to newer
     * array
     */
    public void expand() {
        Square[] longerArr = new Square[squares.length + 10];
        for (int i = 0; i < squares.length; i++) {
            longerArr[i] = squares[i];
        }
        squares = longerArr;
    }

    /**
     * Adds all of the elements in the specified collection to this
     * set if they're not already present
     *
     * @param a Collection containing all squares to be added to the set
     * @return a boolean of whether or not all squares were added
     */
    public boolean addAll(Collection<? extends Square> c) {

        return false;
    }

    /**
     * Removes all of the elements from this set
     */
    public void clear() {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = null;
        }
    }

    /**
     * Returns true if this set contains the specified element.
     *
     * @param o - Object to be checked for in the Set
     * @return boolean of whether or not the given object was found
     */
    public boolean contains(Object o) {
        for (Square s : squares) {
            if (s == null) {
                return false;
            } else if (s.equals(o)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a Square at a specified index in the Set
     *
     * @param i - the specified index
     * @return a square at the specified index
     */
    public Square get(int i) {
        if (i < squares.length) {
            return squares[i];
        }
        return null;
    }
    /**
     * Returns true if this set contains all of the elements
     * of the specified collection.
     *
     * @param c collection containing elements to be checked for in the set
     * @return true if all elements in the given collection are in the set
     */
    public boolean containsAll(Collection<?> c) {
        SquareSet set = (SquareSet)c;
        for (int i = 0; i < set.size() && set.get(i) != null; i++) {
            if (!(this.contains(set.get(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares the specified object with this set for equality.
     *
     * @param o - the object to check for equivalence for
     * @return boolean of whether or not the elements are equal
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (!(o instanceof SquareSet)) {
            return false;
        }
        SquareSet set = (SquareSet)o;
        if (squares.length != set.size()) {
            return false;
        }
        return containsAll(set);
    }

    /**
     * Returns the hash code value for this set.
     *
     * @return int - the hash code value for this set
     */
    public int hashCode() {
        return -1;
    }

    /**
     * Returns true if this set contains no elements
     *
     * @return boolean of whether or not set is empty
     */
    public boolean isEmpty() {
        return (this.size() > 0) ? false : true;
    }

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return Iterator<E> which is capable of iterating over elements
     * in this set
     */
    public Iterator<Square> iterator() {
        return new SquareSetIterator();
    }

    /**
     * Removes the specified element from this set if it
     * is present (optional operation)
     *
     * @param o - the object to be removed
     * @return true if the object is successfully removed from the set
     */
    public boolean remove(Object o) {
        int remInd = 0;
        if (!(this.contains(o))) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (squares[i] == null) {
                return true;
            }
            if (squares[i].equals(o)) {
                remInd = i;
                squares[i] = null;
            }
        }
        for (int i = remInd; i < squares.length - 1; i++) {
            squares[i] = squares[i+1];
        }
        return true;
    }

    /**
     * Removes from this set all of its elements that are
     * contained in the specified collection
     *
     * @param c - collection of elements to be removed from set
     * @return true if ALL elements of collecetion are successfully removed
     */
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    /**
     * Retains only the elements in this set that are contained
     * in the specified collection.
     * a.k.a removes from this set all of its elements that are
     * not contained in the specified collection
     *
     * @param c - collection from which to populate set
     * @return true if set is altered as a result of the call
     */
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Returns the number of elements in this set
     *
     * @return int - the number of squares in the set
     */
    public int size() {
        SquareSetIterator ss = (SquareSetIterator)iterator();
        int count = 0;
        while (ss.hasNext()) {
            count++;
            ss.next();
        }
        return count;
    }

    /**
     * Returns an array containing all of the elements in this set.
     *
     * @return an array of type Object containing all Squares in the set
     */
    public Object[] toArray() {
        Object[] set = new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            set[i] = (Object)squares[i];
        }
        return (Object[])squares;
    }

    /**
     * Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param s -  the array into which the elements of this set are to
     * be stored, if it is big enough;
     * otherwise, a new array of the same runtime type is allocated
     * for this purpose.
     * @return an array of type Square that contains all Squares in the Set
     */
    public <Square> Square[] toArray(Square[] s) {
        return  s;
    }
    /**
     * Organizes set into a printable format
     *
     * @return String a printable format of Set
     */
    public String toString() {
        String result = "[";
        SquareSetIterator ss = (SquareSetIterator)iterator();
        while (ss.hasNext()) {
            result += " " + ss.next().toString() + ",";
        }
        result += "]";
        return result;
    }

    /**
     * Class designed to create an iterator capable of iterating
     * over a Set of type Square
     *
     * @author Peter Herman
     * @version 1.0
     */
    public class SquareSetIterator implements Iterator<Square> {

        private int ind;
        /**
         * Creates a new SquareSetIterator object which begins its
         * iteration at index 0
         */
        public SquareSetIterator() {
            ind = 0;
        }
        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if there is another square in the set
         */
        public boolean hasNext() {
            if (ind < squares.length
                && squares[ind] != null) {
                return true;
            }
            return false;
        }

        /**
         * Returns next element in the iteration
         *
         * @return next Square in set
         */
        public Square next() {
            if (hasNext()) {
                return squares[ind++];
            }
            return null;
        }
    }

}
