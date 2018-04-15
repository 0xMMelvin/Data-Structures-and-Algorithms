import java.util.Scanner;

/**
 * @author Michael Melvin mjmelvin@bu.edu
 */
public class IntLinkedBinaryTree extends LinkedBinaryTree<Integer> {
    /**
     * Creates an empty IntLinkedBinaryTree
     */
    public IntLinkedBinaryTree() {
        super();
    }

    /**
     * Add a new key to the tree.
     * Precondition: none.
     * Postcondition: e has been added to the tree.
     *
     * @param p The root of the tree to which new node is added
     * @param e The element of the new node
     * @return If a node with e does not exist, a new node with e is added and
     * reference to the node is returned. If a node with e exists, null is returned.
     */
    public Position<Integer> add(Position<Integer> p, Integer e) {

        // if p == null, this is an empty tree, create a root
        if (p == null) {
            addRoot(e);
            System.out.println("\nThe key added successfully\n");
            return p;
        }
        // declare variables
        Position<Integer> traverse = p;
        Position<Integer> addAt = traverse;
        // loop through the tree until an external node is found
        while (traverse != null) {
            // if e is already in the tree, exit loop
            if (traverse.getElement().equals(e)) {
                System.out.println("\nThe key already exist\n");
                return null;
                // if e is smaller, traverse left
            } else if (traverse.getElement() > e) {
                addAt = traverse;
                traverse = left(traverse);
                // if e is larger, traverse right
            } else {
                addAt = traverse;
                traverse = right(traverse);
            }
        }   // end while
        // declare a temp position
        Position<Integer> temp;
        // if e is smaller, create a node with current external node as the parent and set e as left child
        if (addAt.getElement() > e) {
            temp = createNode(e, validate(addAt), null, null);
            addLeft(addAt, temp.getElement());
            // if e is larger, create a node with current external node as the parent and set e as right child
        } else {
            temp = createNode(e, validate(addAt), null, null);
            addRight(addAt, temp.getElement());
        }
        System.out.println("\nThe key added successfully\n");
        return temp;
    }

    /**
     * Recursively searches for the key in the tree and returns it if it exists, returns null otherwise.
     * Precondition: none.
     * Postcondition: The Position<Integer> of the element is returned if it is found.
     *
     * @param e The key to be found.
     * @param p The root of the tree.
     * @return The Position<Integer> of the element is returned if it is found, else returns null.
     */
    private Position<Integer> locate(Integer e, Position<Integer> p) {
        // initialize result
        Position<Integer> result = null;
        if (p == null)
            // tree is empty
            return null;
        // found a match
        if (p.getElement().equals(e))
            return p;
        // recursively search left subtree
        if (left(p) != null)
            result = locate(e, left(p));
        // recursively search right subtree
        if (result == null)
            result = locate(e, right(p));
        return result;
    }

    /**
     * Takes in the root position of the tree and searches for the element e in the tree to remove and return it.
     * If element e is not in the tree, null is returned.
     * Precondition: none.
     * Postcondition: If the element was in the tree, it was deleted.
     *
     * @param p The root of the tree from which a node is deleted.
     * @param e The integer key of the node to be deleted.
     * @return The integer key of the node deleted, or null if e is not in the tree.
     */
    public Integer delete(Position<Integer> p, Integer e) {
        Position<Integer> r = locate(e, p);
        // if e was not found
        if (r == null) {
            // e is not in the tree
            System.out.println("\nThe key does not exist\n");
            return null;
        } else {
            // count the children
            int count = 0;
            if (left(r) != null) count++;
            if (right(r) != null) count++;
            switch (count) {
                // if it has no children just remove it
                case 0:
                    remove(r);
                    break;
                // if it has one child
                case 1:
                    // if left child is null, there is only a right child
                    if (left(r) == null) {
                        // set r to the right child's element
                        set(r, right(r).getElement());
                        // set the parent
                        validate(right(r)).setParent(validate(r));
                        // remove r
                        remove(r);
                        // set the left child
                    } else {
                        // set r to the left child's element
                        set(r, left(r).getElement());
                        // set the parent
                        validate(left(r)).setParent(validate(r));
                        // remove r
                        remove(r);
                    }
                    break;
                case 2:
                    // let r the right-most internal position of the left sub-tree of p
                    if (left(p) != null) {
                        r = left(p);
                        while (right(r) != null) {
                            r = right(r);
                        }
                    } else {
                        delete(r, r.getElement());
                        return r.getElement();
                    }
                    // set r's element to position p
                    set(p, r.getElement());
                    if (isRoot(p)) {
                        root = validate(p);
                    }
                    // remove r
                    remove(r);
                    break;
            }
            // notify user
            System.out.println("\nThe key deleted successfully\n");
            // return the element
            return e;
        }
    }

    /**
     * Displays the main menu of the program.
     * Precondition: none.
     * Postcondition: The main menu is output to the console.
     */
    public static void displayMainMenu() {
        System.out.println("Choose an option:\n");
        System.out.println("1. Add a key\n2. Remove a key\n3. Print the tree\n4. Exit");
    }

    /**
     * Gets the users input.
     * Precondition: The user must enter an integer.
     * Postcondition: selection has been set to an integer.
     *
     * @return The selected option.
     */
    public static int getSelection() {
        Scanner option = new Scanner(System.in);
        int selection = Integer.parseInt(option.nextLine());
        return selection;
    }

    /**
     * Calls private printAscending function with the root of the tree.
     * Precondition: none.
     * Postcondition: The elements of the tree are printed to the console in increasing order.
     */
    public void printAscending() {
        printAscending(root);
    }

    /**
     * A recursive function to print the tree in increasing order of elements.
     * Precondition: none.
     * Postcondition: The elements of the tree are printed to the console in increasing order.
     *
     * @param p The root of the tree to be printed.
     */
    private void printAscending(Position<Integer> p) {
        if (size() == 0) {
            System.out.println("\nTree is empty");
            return;
        }
        // if p is null the tree is empty
        if (p != null) {
            // recursively get left nodes
            printAscending(left(p));
            // print the element
            System.out.println(p.getElement());
            // recursively get right nodes
            printAscending(right(p));
        }
    }   //----------    END IntLinkedBinaryTree    ----------//

    public static void main(String[] args) {

        // create a new binary tree instance
        IntLinkedBinaryTree t = new IntLinkedBinaryTree();
        // display options 1st time
        System.out.println("Choose an option:\n");
        System.out.println("1. Add a key\n2. Remove a key\n3. Print the tree\n4. Exit");
        // get user input
        int selection = getSelection();
        // continually loop
        while (selection != 4) {
            switch (selection) {
                // adding a key
                case 1:
                    // prompt user and get the key
                    System.out.print("Enter an integer: ");
                    int toAdd = getSelection();
                    // add the key
                    t.add(t.root(), toAdd);
                    // show the main menu
                    displayMainMenu();
                    break;
                // removing a key
                case 2:
                    // prompt the user and get the key
                    System.out.print("Enter an integer: ");
                    int toRemove = getSelection();
                    // remove the key
                    t.delete(t.root(), toRemove);
                    // show the main menu
                    displayMainMenu();
                    break;
                // print the tree in increasing order
                case 3:
                    // print the tree
                    t.printAscending();
                    // show the main menu
                    displayMainMenu();
            }
            // get the next selection
            selection = getSelection();
        }
        // user selected 4
        System.out.print("Goodbye");
    }
}
