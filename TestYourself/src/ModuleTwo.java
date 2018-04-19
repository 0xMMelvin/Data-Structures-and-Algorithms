public class ModuleTwo {

    /*
     ************************** Algorithm Analysis ******************************************
     An algorithm is a finite sequence of steps that solves a problem.

     ************** Experimental Analysis **************
     Experimental running times of different algorithms are difficult to compare accurately unless they are measured
        in the identical hardware and software environment, which is not always possible.
     We can perform such experiments only on a limited number of inputs. If the same experiments are performed on
        different inputs, we may obtain different results. SO, the conclusion we draw from an experimental result
        cannot be generalized.
    To obtain and compare elapsed time, we need to fully implement the algorithms of which we want to compare the
        performance. However, most of the time, we want to compare the efficiency of different algorithms before we
        fully implement them. If we can determine which algorithm is more efficient with a high-level analysis without
        implementing either, we can save time.

    ************** High Level Analysis **************
    When comparing the efficiency of two algorithms, it is not necessary to know and compare their actual run times.
        it would be sufficient to learn which one was faster.
    The decision is independent of hardware and software environment.
    The decision is made by analyzing high-level descriptions of algorithms without having to implement the algorithms.
    The decision is independent of input variations. In other words, a high-level analysis takes into consideration all
        possible inputs when making the determination.

    Primitive Operations - assignment, access via reference, arithmetic operations, comparing two numbers, accessing an
        array element by index, method invocation and returns.
        We assume all primitive operations take constant time.

    We usually only perform worst case analysis because the result of average and worst case are usually similar, the
        worst case analysis is usually much easier to perform, and the worst case analysis gives us an upper bound on
        the running time of the algorithm.

    ************** Mathematical Functions **************
    omitted

    ************** Big-Oh Notation **************
    O(g(n)) = {f(n): there exist positive constants c and n0 such that f(n) <= cg(n) for n >= n0}
    This states that informally, g(n) is an upper bound on f(n) with a constant factor c. We say that f(n) is big-oh of
        g(n) and we write f(n) = O(g(n)), even though f(n) is a member of the set O(g(n)), and the correct expression is
        f(n) ∈ O(g(n)). We also say that g(n) is an asymptotic upper bound of f(n).
    Another way is to say this is that: cg(n) is always >= f(n) for all n that is greater than of equal to some constant
        n0, up to a constant factor c.

    Examples:
    f(n) = 3n + 2 => f(n) = O(n)
        Let g(n) = n, c = 4, and n0 = 2. Then
        f(n) = 3n + 2 <= 4n for all n >= 2, or
        f(n) <= cg(n) for all n >= n0,
        So, f(n) = O(n)
    f(n) = 5n^3 + 2n^2 + 8n + 4 => f(n) = O(n^3)
         f(n) = 5n^3 + 2n^2 + 8n + 4 <= 5n^3 + 2n^3 + 8n^3 + 4n^3 = 19n^3
         Let g(n) = n^3, c = 19, and n0 = 1
         So,
          f(n) = 5n^3 + 2n^2 + 8n + 4 <= 19n^3 for n >= 1,
          f(n) = O(n^3)

    ************** Big-Omega Notation **************
    Ω(g(n)) = {f(n): there exist positive constants c and n) such that f(n) >= cg(n) for n >= n0}
    We say that f(n) is big-omega of g(n) and we write f(n) = Ω(g(n)). We also say that g(n) is an asymptotic lower
        bound of f(n).

    ************** Big-Theta Notation **************
    Θ(g(n)) = {f(n): there exist positive constants c1, c2, and n0 such that 0 ≤ c1 g(n) ≤ f(n) ≤ c2 g(n) for all n ≥ n0}
    We say that f(n) is big-theta of g(n), and we write f(n) = Θ(g(n)). We also say that g(n) is an asymptotic tight
        bound of f(n).

    ************** Examples **************
    * Finding the Maximum in an Array
    public static double arrayMax(double[] data) {
        int n = data.length;
        double currentMax = data[0];
        for (int j = 1; j < n; j++) {
            if (data[j] > currentMax) {
                currentMax = data[j];
            }
        }
        return currentMax;
    }
    f(n) = c1 + c2 + c3(n - 1) + c4 = c3n + c5 = O(n)

    * Composing Long Strings
    public static String repeat1(char c, int n) {
        String answer = "";
        for (int i = 0; i< n; i++) {
            answer += c;
        }
        return answer;
    }
    Strings are immutable objects so c is not simply concatenated to answer. A new String is actually created to include
    c. The time to perform this is proportional to the length of the string.
    f(n) = 1 + 2 + 3 + ... + n = O(n^2)

    * Three-way Set Disjointness Problem
    public static boolean disjoint1(int[] groupA, int[] groupB, int[] groupC) {
        for (int a : groupA)
            for (int b : groupB)
                for (int c : groupC)
                    if ((a == b) && (b == c))
                        return false;
        return true;
    }
    f(n) = O(n^3)
    public static boolean disjoint2(int[] groupA, int[] groupB, int[] groupC) {
        for (int a : groupA)
            for (int b : groupB)
                if (a == b)
                    for (int c: groupC)
                        if (b == c)
                            return false
        return true;
    }
    Line 4 is executed n^2 times. Line 5 can only be executed a max of n times.
    f(n) = O(n^2)

    * Element Uniqueness
    public static boolean unique1(int[] data) {
        int n = data.length;
        for (int j = 0; j < n - 1; j++)
            for (int k = j + 1; k < n; k++)
                if (data[j] == data[k])
                    return false;
        return true;
    }
    f(n) = O(n^2)
    public static boolean unique2(int[] data) {
        int n = data.length;
        int[] temp = Arrays.copyOf(data, n);
        Arrays.sort(temp);
        for (int j = 0; j < n - 1; j++)
            if (temp[j] == temp[j + 1]
                return false;
        return true;
    }
    f(n) = O(n log n) because or the Arrays.sort() method.

    ************** Proof Methods **************
    *** look over this before exam ***

    ************************** Recursion ******************************************
    Factorial
    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException("Argument must be non negative");
        else if (n == 0) return 1;
        else return n * factorial(n - 1);
    }
    factorial(4) -> 4 * factorial(3) -> 3 * factorial(2) -> 2 * factorial(1) -> 1 * factorial(0)
    -> returns 1, returns 1 * 1 = 1, returns 2 * 1 = 2, returns 3 * 2 = 6, returns 4 * 6 = 24

    Binary Search
    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high)
            return false;
        else {
            int mid = (low + high) / 2;
            if (target == data[mid])
                return true;
            else if (target < data[mid])
                return binarySearch(data, target, 0, mid - 1);
            else
                return binarySearch(data, target, mid + 1, high);
            }
        }

     */
}
