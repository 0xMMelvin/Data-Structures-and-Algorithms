import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingComparison {

    /**
     * Insertion-sort of an array of integers into nondecreasing order.
     *
     * @param data Integer array.
     */
    public static void insertionSort(int[] data) {
        int n = data.length;
        // begin with second int
        for (int k = 1; k < n; k++) {
            // inserting cur
            int cur = data[k];
            // find correct index j for cur
            int j = k;
            // data[j-1] must go after cur
            while (j > 0 && data[j - 1] > cur) {
                // slide data[j-1] rightward
                data[j] = data[j - 1];
                // consider previous j for cur
                j--;
            }
            // this is the proper place for cur
            data[j] = cur;
        }
    }

    /**
     * Merges contets of arrays s1 and s2 into properly sized array s.
     *
     * @param s1 Array to be merged.
     * @param s2 Array to be merged.
     * @param s Array to merge into.
     * @param comp Default comparator.
     */
    public static void merge(int[] s1, int[] s2, int[] s, Comparator comp) {
        int i = 0, j = 0;
        while (i + j < s.length) {
            if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
                // copy ith element of s1 and increment i
                s[i + j] = s1[i++];
            } else {
                // copy jth element of s2 and increment j
                s[i + j] = s2[j++];
            }
        }
    }

    /**
     * Merge sort contents of array s.
     * @param s Integer array to sort.
     * @param comp Default comparator
     */
    public static void mergeSort(int[] s, Comparator comp) {
        int n = s.length;
        // array is trivially sorted
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        // copy of first half
        int[] s1 = Arrays.copyOfRange(s, 0, mid);
        // copy of second half
        int[] s2 = Arrays.copyOfRange(s, mid, n);
        // sort copy of first half
        mergeSort(s1, comp);
        // sort copy of second half
        mergeSort(s2, comp);
        // merge sorted halves back into original
        merge(s1, s2, s, comp);
    }

    /**
     * Sorts the array s.
     *
     * @param s Array to be sorted.
     * @param comp Default comparator.
     * @param a Start position.
     * @param b End position.
     */
    public static void quickSort(int[] s, Comparator comp, int a, int b) {
        // array is trivially sorted
        if (a >= b) return;
        int left = a;
        int right = b - 1;
        int pivot = s[b];
        // used for swapping
        int temp;
        while (left <= right) {
            // scan until reaching a value equal to or larger than pivot (or right marker)
            while (left <= right && comp.compare(s[left], pivot) < 0) left++;
            // scan until reaching value equal to or smaller than pivot (or left marker)
            while (left <= right && comp.compare(s[left], pivot) < 0) right--;
            // indices did not strictly cross
            if (left <= right) {
                // so swap values and shrink range
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
        // put pivot into its final place (currently marked by left index)
        temp = s[left];
        s[left] = s[b];
        s[b] = temp;
        // make recursive calls
        quickSort(s, comp, a, left - 1);
        quickSort(s, comp, left + 1, b);
    }

    /**
     * http://www.code2learn.com/2011/09/heapsort-array-based-implementation-in.html
     */
    // variables for heapsort
    private static int[] a;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    public static void buildheap(int[] a) {
        n = a.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    public static void maxheap(int[] a, int i) {
        left = 2 * i;
        right = 2 * i + 1;
        if (left <= n && a[left] > a[i]) {
            largest = left;
        } else {
            largest = i;
        }
        if (right < n && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {
            exchange(i, largest);
            maxheap(a, largest);
        }
    }

    public static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void heapSort(int[] a0) {
        a = a0;
        buildheap(a);
        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            maxheap(a, 0);
        }
    }


    public static void main(String[] args) {

        Comparator comp = new DefaultComparator();

        // n = 10,000
        int n = 10000;
        int[] a = new int[n];
        Random r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        int[] aCopy = a.clone();
        long startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        long endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 10,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 10,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 10,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 10,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 20,000
        n = 20000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 20,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 20,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 20,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 20,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 30,000
        n = 30000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 30,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 30,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 30,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 30,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 40,000
        n = 40000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 40,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 40,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 40,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 40,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 50,000
        n = 50000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 50,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 50,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 50,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 50,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 60,000
        n = 60000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 60,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 60,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 60,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 60,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 70,000
        n = 70000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 70,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 70,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 70,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 70,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 80,000
        n = 80000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 80,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 80,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 80,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 80,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 90,000
        n = 90000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 90,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 90,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 90,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 90,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 100,000
        n = 100000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
        // insertion sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        insertionSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with n = 100,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy, comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 100,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 100,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // heap sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        heapSort(aCopy);
        endTime = System.currentTimeMillis();
        System.out.println("Heap sort with n = 100,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
    }
}


