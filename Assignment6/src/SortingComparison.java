import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingComparison {

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int cur = data[k];
            int j = k;
            while (j > 0 && data[j - 1] > cur) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = cur;
        }
    }

    public static void merge(int[] s1, int[] s2, int[] s, Comparator comp) {
        int i = 0, j = 0;
        while (i + j < s.length) {
            if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
                s[i + j] = s1[i++];
            } else {
                s[i + j] = s2[j++];
            }
        }
    }

    public static void mergeSort(int[] s, Comparator comp) {
        int n = s.length;
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] s1 = Arrays.copyOfRange(s, 0, mid);
        int[] s2 = Arrays.copyOfRange(s, mid, n);
        merge(s1, s2, s, comp);
    }

    public static void quickSort(int[] s, Comparator comp, int a, int b) {
        if (a >= b) return;
        int left = a;
        int right = b - 1;
        int pivot = s[b];
        int temp;
        while (left <= right) {
            while (left <= right && comp.compare(s[left], pivot) < 0) left++;
            while (left <= right && comp.compare(s[left], pivot) < 0) right--;
            if (left <= right) {
                temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
        temp = s[left];
        s[left] = s[b];
        s[b] = temp;
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy,comp, 0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy, comp,0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy,comp, 0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy, comp,0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy, comp,0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy,comp, 0, n - 1);
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
        mergeSort(aCopy,comp);
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
        quickSort(aCopy, comp,0, n - 1);
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

        // n = 1,000,000
        n = 1000000;
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
        System.out.println("Insertion sort with n = 1,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy,comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 1,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp,0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 1,000,000:");
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
        System.out.println("Heap sort with n = 1,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 10,000,000
        n = 10000000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
//        // insertion sort
//        aCopy = a.clone();
//        startTime = System.currentTimeMillis();
//        insertionSort(aCopy);
//        endTime = System.currentTimeMillis();
//        System.out.println("Insertion sort with n = 10,000,000:");
//        System.out.println("Start: " + startTime);
//        System.out.println("End: " + endTime);
//        totalTime = endTime - startTime;
//        System.out.println("Total time: " + totalTime);
//        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy,comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 10,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy, comp,0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 10,000,000:");
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
        System.out.println("Heap sort with n = 10,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();

        // n = 100,000,000
        n = 100000000;
        a = new int[n];
        r = new Random();
        // fill array
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000000) + 1;
        }
//        // insertion sort
//        aCopy = a.clone();
//        startTime = System.currentTimeMillis();
//        insertionSort(aCopy);
//        endTime = System.currentTimeMillis();
//        System.out.println("Insertion sort with n = 100,000,000:");
//        System.out.println("Start: " + startTime);
//        System.out.println("End: " + endTime);
//        totalTime = endTime - startTime;
//        System.out.println("Total time: " + totalTime);
//        System.out.println();
        // merge sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        mergeSort(aCopy,comp);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort with n = 100,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
        // quick sort
        aCopy = a.clone();
        startTime = System.currentTimeMillis();
        quickSort(aCopy,comp, 0, n - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick sort with n = 100,000,000:");
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
        System.out.println("Heap sort with n = 100,000,000:");
        System.out.println("Start: " + startTime);
        System.out.println("End: " + endTime);
        totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime);
        System.out.println();
    }
}


