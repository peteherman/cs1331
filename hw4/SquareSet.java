import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Arrays;
/**
 * Class designed to create a set containing all squares on chess board
 *
 * @author Peter Herman
 * @version 1.0
 */
public class SquareSet implements Set<Square> {

    public static final String POS_RANKS = "12345678";
    public static final String POS_FILES = "abcdefgh";
    private Square[] squares;

    /**
     * Creates an empty SquareSet and initializes backing array
     */
    public SquareSet() {
        squares = new Square[10];
    }

    /**
     * Creates a SquareSet containing all elements from given collection
     *
     * @param Collection - collection of squares to be added to Set
     * upon its creation
     */
    public SquareSet(Collection<Square> c) {
        squares = new Square[10];
        this.addAll(c);
    }

    @Override
    public boolean add(Square s) {
        if (s == null) {
            throw new NullPointerException();
        }
        if (s.getName().length() != 2) {
            throw new InvalidSquareException(s.getName());
        }
        if (POS_FILES.indexOf(s.getFile()) < 0
            || POS_RANKS.indexOf(s.getRank()) < 0) {
            throw new InvalidSquareException(s.getName());
        }
        if (contains(s)) {
            return false;
        } else {
            if ((squares[squares.length - 1] != null)) {
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
     * expands size of Set by creating an array that is 2 times longer
     * than previous array. Then copies entries from shorter array to newer
     * array
     */
    public void expand() {
        Square[] longerArr = new Square[squares.length * 2];
        for (int i = 0; i < squares.length; i++) {
            longerArr[i] = squares[i];
        }
        squares = longerArr;
    }

    @Override
    public boolean addAll(Collection<? extends Square> c) {
        boolean allAdded = false;
        for (Square square : c) {
            if(this.add(square)) {
                allAdded = true;
            }
        }
        return allAdded;
    }

    @Override
    public void clear() {
        for (int i = 0; i < squares.length; i++) {
            squares[i] = null;
        }
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Square)) {
            throw new ClassCastException();
        }
        for (int i = 0; i < size(); i++) {
            if (squares[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!(this.contains(o))) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        Set set = (Set)o;
        if (size() != set.size()) {
            return false;
        }
        return containsAll(set);
    }

    @Override
    public int hashCode() {
        int sumHash = 0;
        for (Square square : squares) {
            if (square != null) {
                sumHash += square.hashCode();
            }
        }
        return sumHash;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() > 0) ? false : true;
    }

    @Override
    public Iterator<Square> iterator() {
        return new SquareSetIterator();
    }

    @Override
    public boolean remove(Object o) {
        int pos = -1;
        for (int i = 0; i < size(); i++) {
            if (squares[i].equals(o)) {
                pos = i;
                squares[i] = null;
            }
        }
        if (pos >= 0) {
            for (int i = pos; i < squares.length - 1; i++) {
                squares[i] = squares[i + 1];
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the number of elements in this set
     *
     * @return int - the number of squares in the set
     */
    public int size() {
        if (squares.length > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) {
                return i;
            }
        }
        return squares.length;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Square[size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = squares[i];
        }
        return res;
    }

    @Override
    public <Square> Square[] toArray(Square[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }
        /**
           for (int i = 0; i < size(); i++) {
            if (!(squares[i] instanceof arr.getClass())) {
                throw new ArrayStoreException();
            }
        }
        */
        if (arr.length < size()) {
            // arr = (Square[])Array.
            //  newInstance(this.getClass(), size());
            return (Square[])toArray();
        } else{
            for (int i = 0; i < squares.length; i++) {
                arr[i] = (Square)squares[i];
                if (arr[i] == null) {
                    return arr;
                }
            }
        }
        return arr;
    }

    /**
     * Organizes set into a printable format
     *
     * @return String a printable format of Set
     */
    public String toString() {
        String result = "[";
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] != null) {
                result += " " + squares[i].toString() + ",";
            } else {
                result += " null,";
            }
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

        @Override
        public boolean hasNext() {
            if (ind < squares.length
                && squares[ind] != null) {
                return true;
            }
            return false;
        }

        @Override
        public Square next() {
            if (hasNext()) {
                return squares[ind++];
            }
            throw new NoSuchElementException();
        }
    }
    public static void main(String[] args) {
        Object[] arr = new Object[2];
        arr[0] = "dog";
        arr[1] = "cat";
        SquareSet ss = new SquareSet();
        ss.add(new Square('a', '2'));
        ss.add(new Square('a', '3'));
        ss.add(new Square('a', '4'));
        System.out.println(ss);
        arr = ss.toArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
