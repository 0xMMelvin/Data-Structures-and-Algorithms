import datastructures.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;
import java.util.Map;


public class Algorithms {

    public static void algorithmOne(AdjacencyMapGraph<String, Integer> graph, Vertex<String> src,
                                    Vertex<String> destination, Map<String, Integer> directDistMap) {

        // shortest path nodes for algorithm 1
        Stack<Vertex<String>> pathOne = new Stack<>();  // push and pop methods are O(1)
        // all nodes visited for algorithm 1
        Stack<Vertex<String>> allOne = new Stack<>();   // push and pop methods are O(1)
        // nodes not to revisit
        Set<String> deadOne = new HashSet<>();          // add and contains methods are O(1)

        // initialization
        Vertex<String> current = src;
        pathOne.push(current);
        allOne.push(current);
        // loop until we reach destination
        while (!current.equals(destination)) {
            // stores the adjacent nodes with closest node first
            PriorityQueue<Integer, Vertex<String>> pq = new HeapPriorityQueue<>();
            // loop through adjacent edges of current node
            for (Edge<Integer> edge : graph.outgoingEdges(current)) {
                // get the node
                Vertex<String> adj = graph.opposite(current, edge);
                // if this node is in the set, do not insert it into the pq
                if (!deadOne.contains(adj.getElement())) {
                    pq.insert(directDistMap.get(adj.getElement()), adj);    // O(log n) insertion
                }
            }
            // get closest node
            Vertex<String> next = pq.removeMin().getValue();    // O(log n) removal
            // if next is not in the set or in the shortest path
            if (!deadOne.contains(next.getElement()) && !pathOne.contains(next)) {
                // add to path
                pathOne.push(next);
                allOne.push(next);
                // if there is only one adjacent node and it is not the destination
                if (graph.outDegree(next) == 1 && !next.equals(destination)) {
                    // add to set
                    deadOne.add(next.getElement());
                }
                // if we are starting on a node that has one adjacent node
                if (graph.outDegree(current) == 1) {
                    // add to set
                    deadOne.add(current.getElement());
                    deadOne.add(next.getElement());
                    current = next;
                    continue;
                }
            } else {    // we are backtracking
                // pop from path
                pathOne.pop();
                // push next to all
                if (!allOne.peek().equals(next)) {
                    allOne.push(next);
                }
                // add current to set
                deadOne.add(current.getElement());
                deadOne.add(next.getElement());
            }
            // get next node
            current = pathOne.peek();
        }
        // calculate distance
        int distanceOne = 0;
        // make an array list from the stack
        ArrayList<Vertex<String>> distanceListOne = new ArrayList<>(pathOne);
        // create an array from the array list
        Vertex<String>[] distOneArr = new Vertex[distanceListOne.size()];
        distOneArr = distanceListOne.toArray(distOneArr);
        // sum the edge distances
        for (int i = 0; i < distanceListOne.size() - 1; i++) {
            Edge<Integer> edge = graph.getEdge(distOneArr[i], distOneArr[i+1]);
            distanceOne += edge.getElement();
        }
        // display results
        HelperFunctions.displayResults(allOne, pathOne, distanceOne, 1);
    }

    public static void algorithmTwo(AdjacencyMapGraph<String, Integer> graph, Vertex<String> src,
                                    Vertex<String> destination, Map<String, Integer> directDistMap) {
        // shortest path nodes for algorithm 2
        Stack<Vertex<String>> pathTwo = new Stack<>();  // push and pop methods are O(1)
        // all nodes visited for algorithm 2
        Stack<Vertex<String>> allTwo = new Stack<>();   // push and pop methods are O(1)
        // nodes not to revisit
        Set<String> deadTwo = new HashSet<>();          // add and contains methods are O(1)

        // initialization
        Vertex<String> current = src;
        pathTwo.push(current);
        allTwo.push(current);
        // loop until we reach destination
        while (!current.equals(destination)) {
            // stores the adjacent nodes with closest node first
            PriorityQueue<Integer, Vertex<String>> pq = new HeapPriorityQueue<>();
            // loop through adjacent edges of current node
            for (Edge<Integer> edge : graph.outgoingEdges(current)) {
                // get the node
                Vertex<String> adj = graph.opposite(current, edge);
                // if this node is in the set, do not insert it into the pq
                if (!deadTwo.contains(adj.getElement())) {
                    pq.insert(directDistMap.get(adj.getElement()) + edge.getElement(), adj);    // O(log n) insertion
                }
            }
            // get closest node
            Vertex<String> next = pq.removeMin().getValue();    // O(log n) removal
            // if next is not in the set or in the shortest path
            if (!deadTwo.contains(next.getElement()) && !pathTwo.contains(next)) {
                // add to path
                pathTwo.push(next);
                allTwo.push(next);
                // if there is only one adjacent node and it is not the destination
                if (graph.outDegree(next) == 1 && !next.equals(destination)) {
                    // add to set
                    deadTwo.add(next.getElement());
                }
                // if we are starting on a node that has one adjacent node
                if (graph.outDegree(current) == 1) {
                    // add to set
                    deadTwo.add(current.getElement());
                    deadTwo.add(next.getElement());
                    current = next;
                    continue;
                }
            } else {    // we are backtracking
                // pop from path
                pathTwo.pop();
                // push next to all
                if (!allTwo.peek().equals(next)) {
                    allTwo.push(next);
                }
                // add current to set
                deadTwo.add(current.getElement());
                deadTwo.add(next.getElement());
            }
            // get next node
            current = pathTwo.peek();
        }
        // calculate distance
        int distanceTwo = 0;
        // make an array list from the stack
        ArrayList<Vertex<String>> distanceListTwo = new ArrayList<>(pathTwo);
        // create an array from the array list
        Vertex<String>[] distTwoArr = new Vertex[distanceListTwo.size()];
        distTwoArr = distanceListTwo.toArray(distTwoArr);
        // sum the edge distances
        for (int i = 0; i < distanceListTwo.size() - 1; i++) {
            Edge<Integer> edge = graph.getEdge(distTwoArr[i], distTwoArr[i+1]);
            distanceTwo += edge.getElement();
        }
        // display results
        HelperFunctions.displayResults(allTwo, pathTwo, distanceTwo, 2);
    }
}
