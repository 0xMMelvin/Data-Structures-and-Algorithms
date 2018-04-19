public class ModuleOne {

    /*
    ************************** SinglyLinkedList ******************************************
    Algorithm addFirst(e): SinglyLinkedList
        newest = Node(e)    // new node with the reference to element e
        newest.next = head  // new node's next is set to refer to old head node
        head = newest       // head refers to to new node
        size += 1           // list size (node count) is incremented

     Algorithm addLast(e): SinglyLinkedList
        newest = Node(e)    // new node with the reference to element e
        newest.next = null  // new node's next is set to null
        tail.next = newest  // old tail's next points to new node
        tail = newest       // tail points to new node
        size += 1           // list size is incremented

     adding or removing a node from a SinglyLinkedList in inefficient and if those methods are needed
     a DoublyLinkedList should be used.

     Algorithm removeFirst(): SinglyLinkedList
        if head == null
            the list is empty
        head = head.next    // new head points to next node
        size -= 1           // decrement size

    ************************** CircularlyLinkedList ******************************************
    It is basically a SinglyLinkedList without the reference to a head node. Instead, tail.getNext()
    will return the head and only a reference to the tail is kept. It supports all SinglyLinkedList
    methods and modifies them, and also provides the rotate method.

    Algorithm rotate(): CircularlyLinkedList - rotates the first element to the back of the list
        if the tail is not null     // do nothing
            tail = tail.getNext()

    Algorithm addFirst(): CircularlyLinkedList
        if size is 0
            tail = new Node<>(e, null)
            tail.setNext(tail)          // link it to itself
        else
            newest = new Node<>(e, tail.getNext())
            tail.setNext(newest);
        size++

    ************************** DoublyLinkedList ******************************************
    Nodes now have to references, next and previous node. Also, header and trailer sentinels are used
    to make the implementation easier, for example, we don't have to write two different methods to delete
    a node if it is the first in the list behind the header, because a node always has one in front and
    one in back of it. Using sentinels also means that the head and tail references never have to be changed.

     Algorithm addBetween(e, Node predecessor, Node successor): DoublyLinkedList
        Node newest = new Node<>(e, predecessor, successor)
        predecessor.setNext(newest)
        successor.setPrev(newest)
        size++

    Algorithm remove(Node node): DoublyLinkedList
        Node predecessor = node.getPrev()
        Node successor = node.getNext()
        predecessor.setNext(successor)
        successor.setPrev(predecessor)
        size--

    ***
    * Test Yourself 1.2
    * Write a Java method the returns the middle node of a doubly linked list
    ***
    public Node<E> middle() {
        if (size == 0)
            throw new IllegalStateException("list must be nonempty");
        Node middle = header.next;
        Node partner = trailer.next;
        while (middle != partner && middle.next != parter) {
            middle = middle.getNext();
            partner = partner.getPrev();
       }
       return middle;
    }

    ************************** Equivalence Testing ******************************************
    To determine whether two references are equal, we can use the == operator or the equals() method
    inherited from the Object class. To test whether the the objects referenced by the reference variables
    are equal, we have to override the equals() method.

    public class StringTest {
	    public static void main(String[] args) {
	        String s1 = new String("data structure");
	        String s2 = s1;
	        String s3 = new String("data structure");
            System.out.println("string s1 equals string s2: " + s1.equals(s2));
	        System.out.println("string s1 equals string s3: " + s1.equals(s3));
	        System.out.println("reference s1 equals reference s2: " + (s1 == s2));
	        System.out.println("reference s1 equals reference s3: " + (s1 == s3));
	    }
    }
    string s1 equals string s2: true
    string s1 equals string s3: true
    reference s1 equals reference s2: true
    reference s1 equals reference s3: false

    To implement the equals() method to compare objects based on what they actually are instead of the
    references to those objects, four properties must be tested:
    Reflexivity - for any nonnull reference variable x, x.equals(x) returns true.
    Symmetry - for any nonnull reference variables x and y, x.equals(y) = y.equals(x)
    Transitivity - for any nonnull reference variable x, y, and z, if x.equals(y) is true and y.equals(z)
        is true, then x.equals(z) is true.
    null property - for any nonnull reference variable x, x.equals(null) returns false.

    ************** Equivalence Testing with Arrays **************
    a == b: Tests if a and b refer to the same array instance.
    a.equals(b): This is identical to a == b.
    Arrays.equals(a, b): Returns true if the arrays have the same number of elements and all pairs of
        corresponding elements are equal to each other. If elements are primitives, == operator is used. If
        elements are reference types, the a[k].equals(b[k]) us used.

    int[][] a = {{1,3,5}, {2,4,6}};
    int[][] b = {{1,3,5}, {2,4,6}};

    If we use Arrays.equals(a, b) with these arrays the result will be false because Arrays.equals compare the
    corresponding elements of the two arrays using a[k].equals(b[k]): a[0] = {1, 3, 5} and b[0] = {1, 3, 5}, since
    a[0] and b[0] are themselves references to two different arrays, this returns false. Arrays.deepEquals(a, b) however,
    would return true because it recursively calls itself.

    ************** Equivalence Testing with Linked Lists **************
    public boolean equals(Object o) {
        if (o == null) return false;                    // check if null
        if (getClass() != o.getClass()) return false;   // check if same class
        SinglyLinkedList other = (SinglyLinkedList) o;  // use nonparameterized type
        if (size != other.size) return false;           // check if same size
        Node walkA = head;                              // traverse the primary list
        Node walkB = other.head;                        // traverse the secondary list
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
                walkA = walkA.getNext();
                walkB = walkB.getNext();
        }
        return true;   // if we reach this, everything matched successfully
    }

    ************************** Cloning Data Structures ******************************************

    ************** Shallow Copy and Deep Copy **************
    When we do a shallow copy of an object, any reference variables in that object are copied as only
    those reference variables i.e. the reference variable in the copy points the the same instance as does
    the original reference variable. So, if the object that the reference variable is pointing to is changed,
    the object will be changed in both the original and the copy. Primitive variables do not suffer this.

    When we do a deep copy, primitives behave in the same way, but reference variables do not. In effect, new
    objects are created with the same data as the original.

    Java has the clone method defined in the Object class. Clone performs a shallow copy and the method is protected.
    If we want to use it, we have to make our class implement the Cloneable interface and then declare a public
    version of the clone() method.

    ************** Cloning Arrays **************
    int[] data = {1,3,5,7,9};
    int[] backup;
    backup = data;

    In this case, backup and data both point to the same array.

    backup = data.clone();

    This will cause a new array to be created and backup will point to that array.

    If the objects in the array are reference variables, if we do not implement Cloneable and override the clone()
    method, when we call arr.clone() the new array will store reference variables that point to the same instances
    as the old array. The same is true for 2-D arrays. We can implement a deepClone(int[][] original) method as follows:

    public static [][] deepClone(int[][] original) {
        int[][] backup = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            backup[i] = original[k].clone();
        }
        return backup;
    }

    ************************** Basic Math Facts ******************************************

    ************** Log and Exponent Identities **************
    Some omitted bc you're not math illiterate
    1. log_b (a) = log_c (a) / log_c (b)
    2. b^log_c (a) = a^log_c (b)


    Sum from i = 1 to n of (i) = n(n+1)/2
    Sum from i = 1 to n of (i^2) = n(n+1)(2n+1)/6
    Sum from i = 0 to n of (a^i) = (a^(n+1)−1)/(a−1)
    Sum from i = 0 to ∞ of (a^i) = 1/(1-a), where 0 < a < 1

    ***
    * Test Yourself 1.12
    * Write a Java method that counts the number of vowels in a given character string
    ***
    public int numVowels(String charString) {
        int count = 0;
        for (int i = 0; i < charString.length; i++) {
            switch (charString.charAt(i)) {
            case 'a':
            case 'A':
            ...
            case 'U':
                count++;
            }
        }
        return count;
    }

    ***
    * Test Yourself 1.16
    * Consider the Progression class in Code Segment 1.13 (in the textbook, it is defined in Code Fragment 2.2).
    * Rewrite the Progression class that produces a sequence of values of generic type T and has a single constructor
    * that accepts an initial value.
    ***
     public class Progression<T> {
        protected T current;
        public Progression() { this(0); }
        public Progression(T start) { current = start; }
        public T nextValue() {
            T answer = current;
            advance();
            return answer;
        }
        protected void advance() {
            current = current + 1;
        }
        public void printProgression(T n) {
            System.out.print(nextValue());
            for (int i = 1; i < n; i++) {
                System.out.print(" " + nextValue());
            }
            System.out.print();
    }

    ***
    * Test Yourself 1.17
    * Consider the following code segment:
    *
    * public static void insertionSort(char[] data) {
2   * int n = data.length;
3   * for (int k = 1; k < n; k++) {
4   *   char cur = data[k];
5   *   int j = k;
6   *   while (j > 0 && data[j-1] > cur) {
7   *     data[j] = data[j-1];
8   *     j--;
9   *   }
10  *   data[j] = cur;
11  * }
12  *}
    * This method sorts characters in non-decreasing order using the insertion-sort algorithm. Modify the code so that
    * characters are sorted in non-increasing order.
    ***

    In line 6, change data[j-1] > cur to data[j-1] < cur

    ***
    * Test Yourself 1.20
    * Consider the CircularlyLinkedList class. You can find the link to the CircularlyLinkedList.java file at the end
    * of Section 1.3.8. The code can also be found in Code Fragment 3.16 in the textbook. Write the size( ) method for
    * the CircularlyLinkedList class, assuming that we do not have an instance variable that keeps the size of a list.
    ***
    public int size() {
        if (tail = null) return 0;
        int size = 1;
        Node walk = tail.getNext();
        while (walk != tail) {
            walk = walk.getNext();
            size++;
        }
        return size;
    }

     */

}
