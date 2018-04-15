import datastructures.*;

import java.util.*;
import java.util.Map;


/**
 * @author Michael Melvin mjmelvin@bu.edu
 */
public class project {

    public static void main(String[] args) {

        //----------    CREATION OF THE GRAPH AND DIRECT DISTANCE MAP  ----------//
        // create the adjacency matrix
        ArrayList<String[]> edgeList = HelperFunctions.readFile();
        // create empty graph
        AdjacencyMapGraph<String, Integer> graph = new AdjacencyMapGraph<>(false);
        // load the graph with Vertices and Edges
        graph = HelperFunctions.loadGraph(graph, edgeList);
        // create a HashMap of Vertices direct distance to destination
        Map<String, Integer> directDistMap = HelperFunctions.createDirectDistanceMap();

        //----------    GET USER INPUT  ----------//
        // get the starting vertex
        String start = "";
        // loop until valid vertex chosen
        while (true) {
            // used for checking validity
            ArrayList<String> vertString = new ArrayList<>();
            Scanner selection = new Scanner(System.in);
            // prompt user
            System.out.println("Enter a node: ");
            start = selection.nextLine().toUpperCase();
            for (Vertex<String> vertex : graph.vertices()) {
                vertString.add(vertex.getElement());
            }
            if (vertString.contains(start)) {
                break;
            } else {
                System.out.println("That vertex is not in the graph.");
                System.out.println();
            }
        }

        // the destination Vertex
        Vertex<String> destination = HelperFunctions.setDestination(graph);

        // Vertex to start on
        Vertex<String> src = HelperFunctions.getStartingVertex(graph, start);

        //----------    ALGORITHM ONE   ----------//
        Algorithms.algorithmOne(graph, src, destination, directDistMap);

        //----------    ALGORITHM TWO   ----------//

        Algorithms.algorithmTwo(graph, src, destination, directDistMap);

    }
}

