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
     */
}
