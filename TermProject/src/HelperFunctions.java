import datastructures.AdjacencyMapGraph;
import datastructures.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class HelperFunctions {

    /**
     * Reads in the adjacency matrix from the text file and returns a representation of the adjacency matrix without
     * row labels.
     * Precondition: "graph_input.txt" must exist in the root directory.
     * Postcondition: An ArrayList<String[]> Object is created.
     *
     * @return Representation of the adjacency matrix without row labels.
     */
    public static ArrayList<String[]> readFile() {
        // string read from file
        String edgeString;
        // array from line in file
        String[] edgeArray;
        // array of arrays of lines in file
        ArrayList<String[]> edgeList = new ArrayList<>();
        // trimmed
        ArrayList<String[]> edgeListTrimmed = new ArrayList<>();
        // create graph
        try {
            // get data
            File file = new File("TermProject/graph_input.txt");
            Scanner data = new Scanner(file);
            while (data.hasNextLine()) {
                // get line
                edgeString = data.nextLine();
                // convert line to array
                edgeArray = edgeString.split(" +");
                // add to ArrayList
                Collections.addAll(edgeList, edgeArray);
            }
            // trim and save to edgeListTrimmed
            edgeList.trimToSize();
            for (String[] arr : edgeList) {
                arr = Arrays.copyOfRange(arr, 1, arr.length);
                edgeListTrimmed.add(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edgeListTrimmed;
    }

    /**
     * Loads and returns an AdjacencyMapGraph<String, Integer> from the adjacency matrix.
     * Precondition: The adjacency matrix must be represented by an ArrayList<String[]> with row labels removed, and
     * the graph passed in should be empty.
     * Postcondition: An AdjacencyMapGraph<String, Integer> has been created.
     *
     * @param graph    An empty AdjacencyMapGraph<String, Integer> Object to be loaded.
     * @param edgeList The adjacency matrix represented by an ArrayList<String[]> with row labels removed.
     * @return An undirected AdjacencyMapGraph<String, Integer> Object with Vertices and weighted Edges.
     */
    @SuppressWarnings("unchecked")
    public static AdjacencyMapGraph<String, Integer> loadGraph(AdjacencyMapGraph graph, ArrayList<String[]> edgeList) {
        // create vertices
        ArrayList<Vertex<String>> vertices = createVertices(graph, edgeList);
        // create edges
        graph = createEdges(graph, vertices, edgeList);
        return graph;
    }

    /**
     * Creates and returns a HashMap<String, Integer> of the direct distance to the destination Vertex
     * for each Vertex in the graph.
     * Precondition: "direct_distance.txt" must exist in the root directory.
     * Postcondition: java.util.HashMap has been created.
     *
     * @return java.util.HashMap of the direct distance to the destination Vertex for each Vertex
     * in the graph.
     */
    public static Map<String, Integer> createDirectDistanceMap() {
        // create an empty HashMap
        Map<String, Integer> directDistMap = new HashMap<>();
        // create variables to hold data from the file
        String directDistString;
        String[] directDistArr;
        try {
            // get data from the file
            File file = new File("TermProject/direct_distance.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                directDistString = scanner.nextLine();
                directDistArr = directDistString.split(" ");
                // add data to HashMap
                directDistMap.put(directDistArr[0], Integer.parseInt(directDistArr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directDistMap;
    }

    /**
     * Sets and returns the destination Vertex to "Z".
     * Precondition: The graph should contain the Vertex<"Z">.
     * Postcondition: The destination Vertex has been set to Vertex<"Z">.
     *
     * @param graph An AdjacencyMapGraph<String, Integer> Object.
     * @return The destination Vertex<"Z">.
     */
    public static Vertex<String> setDestination(AdjacencyMapGraph<String, Integer> graph) {
        Vertex<String> destination;
        for (Vertex<String> vertex : graph.vertices()) {
            String destinationLabel = vertex.getElement();
            if (destinationLabel.equals("Z")) {
                destination = vertex;
                return destination;
            }
        }
        return null;
    }

    /**
     * Adds the user selected starting Vertex to the list of visited Vertices if the Vertex exists in g, if it does not
     * exists, user is alerted and the program exits.
     * Precondition: AdjacencyMapGraph g should not be empty, ArrayList v should be empty.
     * Postcondition: currentVertex is set in memory.
     *
     * @param graph An AdjacencyMapGraph<String, Integer> Object.
     * @param start The Vertex label to be set.
     * @return The starting Vertex.
     */
    public static Vertex<String> getStartingVertex(AdjacencyMapGraph<String, Integer> graph, String start) {
        // iterate through vertices
        for (Vertex<String> vertex : graph.vertices()) {
            // if we find a match, return it
            if (vertex.getElement().equals(start)) {
                return vertex;
                // if not a match, go to next vertex
            } else if (!vertex.getElement().equals(start)) {
                continue;
                // no match found
            } else {
                System.out.println("That Vertex is not in the graph!");
                System.exit(0);
            }
        }
        return null;
    }

    /**
     * Creates the Vertices of graph and returns them as an ArrayList<Vertex<String>> representation of the
     * Vertices of g.
     * Precondition: The graph should be an empty undirected AdjacencyMapGraph.
     * Postcondition: All Vertices were inserted into graph.
     *
     * @param graph  An undirected AdjacencyMapGraph<String, Integer> Object with weighted Edges.
     * @param matrix The adjacency matrix represented by an ArrayList<String[]> with row labels removed.
     * @return An ArrayList<Vertex<String>> representation of the Vertices of the graph.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Vertex<String>> createVertices(AdjacencyMapGraph graph, ArrayList<String[]> matrix) {
        // insert Vertices into graph
        for (int i = 0; i < matrix.get(0).length; i++) {
            graph.insertVertex(matrix.get(0)[i]);
        }
        // create ArrayList of vertices
        ArrayList<Vertex<String>> vertices = new ArrayList<>();
        // add Vertices to vertices
        for (Object v : graph.vertices()) {
            vertices.add((Vertex<String>) v);
        }
        return vertices;
    }

    /**
     * Creates the weighted Edges of the graph and returns the graph.
     * Precondition: The graph should have all of the Vertices already inserted.
     * Postcondition: The weighted Edges of the graph have been created.
     *
     * @param graph    An undirected AdjacencyMapGraph<String, Integer> Object with weighted Edges.
     * @param vertices An ArrayList<Vertex<String>> representation of the Vertices of g.
     * @param matrix   The adjacency matrix represented by an ArrayList<String[]> with row labels removed.
     * @return A directed AdjacencyMapGraph<String, Integer> Object with Vertices and weighted Edges.
     */
    @SuppressWarnings("unchecked")
    public static AdjacencyMapGraph<String, Integer> createEdges(AdjacencyMapGraph graph, ArrayList vertices,
                                                                 ArrayList<String[]> matrix) {
        // loop through edgeList
        for (int i = 0; i < matrix.get(0).length; i++) {
            for (int j = 0; j < matrix.get(0).length; j++) {
                // i + 1 because edgeList.get(0) is a "Vertex" not an "Edge"
                int edge = Integer.parseInt(matrix.get(i + 1)[j]);
                Vertex<String> a = (Vertex<String>) vertices.get(i);
                Vertex<String> b = (Vertex<String>) vertices.get(j);
                // if there is no edge, go to top of j loop
                if (edge == 0) {
                    continue;
                    // add edge if it doesn't already exist
                } else if (graph.getEdge(a, b) == null) {
                    graph.insertEdge(a, b, edge);
                }
            }
        }
        return graph;
    }

    /**
     * Outputs the results of the graph traversal.
     * Precondition: none.
     * Postcondition: Results have been output to the console.
     *
     * @param allNodes  All of the nodes visited in the traversal.
     * @param shortest  Only the node along the shortest path.
     * @param distance  The sum of the edges along the shortest path.
     */
    public static void displayResults(Stack<Vertex<String>> allNodes, Stack<Vertex<String>> shortest,
                                      int distance) {

        // reverse the stacks
        Stack<Vertex<String>> all = new Stack<>();
        Stack<Vertex<String>> path = new Stack<>();
        while (!allNodes.empty()) {
            all.push(allNodes.pop());
        }
        while (!shortest.empty()) {
            path.push(shortest.pop());
        }
        // build the results
        StringBuilder allString = new StringBuilder();
        StringBuilder pathString = new StringBuilder();

        while (!all.empty()) {
            allString.append(all.pop().getElement() + " -> ");
        }
        while (!path.empty()) {
            pathString.append(path.pop().getElement() + " -> ");
        }
        allString.delete(allString.length() - 4, allString.length());
        pathString.delete(pathString.length() - 4, pathString.length());

        // output results
        System.out.println();
        System.out.println("Sequence of all nodes: " + allString.toString());
        System.out.println("Shortest path: " + pathString.toString());
        System.out.println("Distance traveled: " + distance);
    }
}
