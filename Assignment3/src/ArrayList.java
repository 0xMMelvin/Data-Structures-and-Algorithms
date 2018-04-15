/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Additional methods added: addAll(ArrayList l), remove(E e), removeRange(int fromIndex, int toIndex), trimToSize()
 *
 * @author Michael Melvin
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realization of a list by means of a dynamic array. This is a simplified version
 * of the java.util.ArrayList class.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class ArrayList<E> implements List<E> {
    // instance variables
    /**
     * Default array capacity.
     */
    public static final int CAPACITY = 16;     // default array capacity

    /**
     * Generic array used for storage of list elements.
     */
    private E[] data;                        // generic array used for storage

    /**
     * Current number of elements in the list.
     */
    private int size = 0;                    // current number of elements

    // constructors

    /**
     * Creates an array list with default initial capacity.
     */
    public ArrayList() {
        this(CAPACITY);
    }   // constructs list with default capacity

    /**
     * Creates an array list with given initial capacity.
     */
    @SuppressWarnings({"unchecked"})
    public ArrayList(int capacity) {         // constructs list with given capacity
        data = (E[]) new Object[capacity];     // safe cast; compiler may give warning
    }

    // public methods

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the array list is empty.
     *
     * @return true if the array list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the element at index i.
     *
     * @param i the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    /**
     * Replaces the element at the specified index, and returns the element previously stored.
     *
     * @param i the index of the element to replace
     * @param e the new element to be stored
     * @return the previously stored element
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
     */
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length)               // not enough capacity
            resize(2 * data.length);             // so double the current capacity
        for (int k = size - 1; k >= i; k--)        // start by shifting rightmost
            data[k + 1] = data[k];
        data[i] = e;                           // ready to place the new element
        size++;
    }

    /**
     * Adds all elements in l to the end of this list, in the order that they are in l.
     * Postcondition: All elements in the list l have been added to this list.
     *
     * @param l the ArrayList to add.
     */
    public void addAll(ArrayList<E> l) {
        // if the capacity isn't big enough, double the capacity of the internal array
        if (data.length <= size + l.size()) {
            resize(2 * l.size());
        }
        // starting at the first empty index, add elements of l
        ArrayIterator iter = (ArrayIterator) l.iterator();
        while (iter.hasNext()) {
            data[size] = iter.next();
            size++;
        }
    }

    /**
     * Removes and returns the element at the given index, shifting all subsequent
     * elements in the list one position closer to the front.
     *
     * @param i the index of the element to be removed
     * @return the element that had be stored at the given index
     * @throws IndexOutOfBoundsException if the index is negative or greater than size()
     */
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++)         // shift elements to fill hole
            data[k] = data[k + 1];
        data[size - 1] = null;                   // help garbage collection
        size--;
        return temp;
    }

    /**
     * Searches for and removes the first occurrence of Element e from the list if it is found.
     *
     * @param e Element to be removed.
     * @return true if e is found, false otherwise.
     */
    public boolean remove(E e) {
        // iterate through the list until the element is found
        for (int i = 0; i < size; i++) {
            // when found, remove
            if (data[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes from the list all of the elements whose index is between fromIndex, inclusive.
     * and toIndex, exclusive.
     * Precondition: fromIndex and toIndex must be valid indexes.
     *
     * @param fromIndex index of the first element to be removed.
     * @param toIndex index after the last element to be removed
     */
    public void removeRange(int fromIndex, int toIndex) {
        // make sure fromIndex is before toIndex
        checkIndex(fromIndex, toIndex);
        // make sure toIndex is <= size
        checkIndex(toIndex, size);
        // make a copy of the internal array with indexes fromIndex to toIndex - 1 removed
        System.arraycopy(data, toIndex, data, fromIndex, size - toIndex);
        for (int to = size, i = (size -= toIndex - fromIndex); i < to; i++) {
            data[i] = null;
        }
    }

    /**
     * Resize the list to the number of elements in the list.
     */
    public void trimToSize() {
        resize(size);
    }

    // utility methods

    /**
     * Checks whether the given index is in the range [0, n-1].
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    /**
     * Resizes internal array to have given capacity >= size.
     */
    @SuppressWarnings({"unchecked"})
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];     // safe cast; compiler may give warning
        for (int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp;                               // start using the new array
    }

    //---------------- nested ArrayIterator class ----------------

    /**
     * A (nonstatic) inner class. Note well that each instance contains an implicit
     * reference to the containing list, allowing it to access the list's members.
     */
    private class ArrayIterator implements Iterator<E> {
        /**
         * Index of the next element to report.
         */
        private int j = 0;                   // index of the next element to report
        private boolean removable = false;   // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j < size;
        }   // size is field of outer instance

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true;   // this element can subsequently be removed
            return data[j++];   // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(j - 1);  // that was the last one returned
            j--;                         // next element has shifted one cell to the left
            removable = false;           // do not allow remove again until next is called
        }
    } //------------ end of nested ArrayIterator class ------------

    /**
     * Returns an iterator of the elements stored in the list.
     *
     * @return iterator of the list's elements
     */
    public Iterator<E> iterator() {
        return new ArrayIterator();     // create a new instance of the inner class
    }

    /**
     * Produces a string representation of the contents of the indexed list.
     * This exists for debugging purposes only.
     *
     * @return textual representation of the array list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int j = 0; j < size; j++) {
            if (j > 0) sb.append(", ");
            sb.append(data[j]);
        }
        sb.append(")");
        return sb.toString();
    }

    public void printList() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // initialize the test lists
        ArrayList<String> stringList1 = new ArrayList<>(10);
        ArrayList<String> stringList2 = new ArrayList<>(10);
        ArrayList<Integer> intList = new ArrayList<>(10);

        // add to test list 1
        stringList1.add(0, "Java");
        stringList1.add(0, "with");
        stringList1.add(0, "Structures");
        stringList1.add(0, "Data");
        System.out.print("stringList1 is initially:");
        stringList1.printList();
        System.out.println();

        stringList1.remove(2);
        System.out.print("After calling remove(2), stringList1 is:");
        stringList1.printList();
        System.out.println();

        stringList1.set(2, "Python");
        System.out.print("After calling set(2, \"Python\"), stringList1 is:");
        stringList1.printList();
        System.out.println();

        stringList2.add(0, "Java");
        stringList2.add(0, "with");
        stringList2.add(0, "Algorithms");
        stringList2.add(0, "and");
        stringList2.add(0, "Structures");
        stringList2.add(0, "Data");
        System.out.print("stringList2 is:");
        stringList2.printList();
        System.out.println();

        stringList1.addAll(stringList2);
        System.out.print("After calling addAll(stringList2), stringList1 is:");
        stringList1.printList();
        System.out.println();

        stringList1.remove("Data");
        System.out.print("After calling remove(\"Data\"), stringList1 is:");
        stringList1.printList();
        System.out.println();
        stringList1.remove("Data");
        System.out.print("After calling remove(\"Data\") again, stringList1 is:");
        stringList1.printList();
        System.out.println();

        // fill intList
        for (int i = 9; i > -1; i--) {
            intList.add(0, i);
        }
        System.out.print("intList is initially:");
        intList.printList();
        System.out.println();
        intList.removeRange(0, 4);
        System.out.print("After calling removeRange(0, 4), stringList1 is:");
        intList.printList();
    }
}