/*
Cayla Mason
cssc0973
CS 310
Shawn Healey
Section 2
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGraph {

    private int vertices;
    private int edges;
    private int heaviestWeight;
    private ArrayList<Vertex> adjacencyList;
    private ArrayList<String> islands;
    private ArrayList<String> selfEdges;
    private ArrayList<String> priceyDests;


    class Edge {
        String source;
        String destination;
        int weight;

        public Edge(String src, String dest, int wt){
            source = src;
            destination = dest;
            weight = wt;
        }
    }


    class Vertex {
        String city;
        LinkedList<Edge> edges;

        public Vertex (String src){
            city = src;
            edges = new LinkedList<>();
        }
    }


    MyGraph(){
        adjacencyList = new ArrayList<>();
        islands = new ArrayList<>();
        selfEdges = new ArrayList<>();
        priceyDests = new ArrayList<>();
    }


    void addVertex (String source){
        if (adjacencyList.isEmpty() || !adjacencyList.contains(source)) {
            Vertex vertex = new Vertex(source);
            adjacencyList.add(vertex);
            vertices++;
        }
    }


    //deletes the vertex and all of its references
    //returns false if the vertex does not appear in the graph
    boolean removeVertex (String src){
        if (!contains(src))
            return false;
        else {
            adjacencyList.remove(getVertex(src));
            vertices--;
            //reset islands
            setIslands();
            return true;
        }
    }


    void addEdge(String src, String dest, String cost){
        //ensure source exists first
        if (contains(src) && contains(dest)) {
            //find the index at which the source resides in the ArrayList
            int index = adjacencyList.indexOf(getVertex(src));

            Edge edge = new Edge(src, dest, getCost(cost));
            adjacencyList.get(index).edges.add(edge);
            edges++;

            setHeaviestWeight(getCost(cost));
            if (getHeaviestWeight() == getCost(cost))
                setPriceyDests(src, dest);

            //append self-edges list
            setSelfEdges(src,dest);
        }
    }


    //deletes all edges from the source node the destination node
    //returns false is either vertex name is invalid
    boolean removeEdges (String source, String dest){
        if (!contains(source,dest))
            return false;
        else {
            Vertex vert = getVertex(source);
            for (int i = 0; i < vert.edges.size(); i++)
                if (vert.edges.get(i).destination == dest) {
                    vert.edges.remove(i);
                    edges--;

                    //reset self-edges
                    if (source.equalsIgnoreCase(dest))
                        selfEdges.remove(source);

                    //reset heaviest weight
                    setHeaviestWeight();
                    setPriceyDests();

                    //reset islands
                    setIslands();

                    return true;
                }
        }
        return false;
    }


    Vertex getVertex (String source){
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).city == source)
                return adjacencyList.get(i);
        }
        return null;
    }


    boolean contains (String source){
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).city.equalsIgnoreCase(source))
                return true;
        }
        return false;
    }


    boolean contains (String source, String dest){
        //if either locations are not vertices, or if the source has no destinations, return false
        Vertex vert = getVertex(source);
        if (!contains(source) || !contains(dest) || vert.edges == null)
            return false;
        else {
            for (int i = 0; i < vert.edges.size(); i++)
                if (vert.edges.get(i).destination == dest)
                    return true;
        }

        return false;
    }


    int getCost (String weight){
        int cost;
        if (weight == null)
            cost = 0;
        else cost = Integer.parseInt(weight);
        return cost;
    }

    int getVertices (){
        return vertices;
    }

    ArrayList<String> getSelfEdges (){
        return selfEdges;
    }

    void setSelfEdges (String source, String dest){
        if (source.equalsIgnoreCase(dest))
            selfEdges.add(source);
    }

    void setHeaviestWeight (){
        heaviestWeight = 0;

        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int j = 0; j < adjacencyList.get(i).edges.size(); j++) {
                int weight = adjacencyList.get(i).edges.get(j).weight;
                if (weight > heaviestWeight)
                    heaviestWeight = weight;
            }
        }
    }

    int getHeaviestWeight () {
        return heaviestWeight;
    }

    void setHeaviestWeight (int num){
        if (num > getHeaviestWeight())
            heaviestWeight = num;
    }

    void setPriceyDests (String source, String dest){
        priceyDests.add(source + " to " + dest);
    }

    //this method is here in case a vertex or edge is deleted, not ideal time complexity O(n^2)
    void setPriceyDests () {
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int j = 0; j < adjacencyList.get(i).edges.size(); j++) {
                int weight = adjacencyList.get(i).edges.get(j).weight;
                if (weight == getHeaviestWeight())
                    priceyDests.add(adjacencyList.get(i).edges.get(j).source + " to " +
                            adjacencyList.get(i).edges.get(j).destination);

            }
        }
    }

    ArrayList<String> getPriceyDests () {
        return priceyDests;
    }


    ArrayList<String> getIslands (){
        return islands;
    }


    //will return the first edge found containing the provided destination
    Edge getEdge  (String dest){
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).edges != null) {
                for (int j = 0; j < adjacencyList.get(i).edges.size(); j++) {
                    if (adjacencyList.get(i).edges.get(j).destination.equalsIgnoreCase(dest))
                        return adjacencyList.get(i).edges.get(j);
                }
            }
        }

        return null;
    }


    //crazy high complexity, would have liked to use a binary search tree for this reason
    //this method will, for each vertex, look through the whole graph for the vertex as a destination
    void setIslands (){
        for (int i = 0; i < adjacencyList.size(); i++)
            if(getEdge(adjacencyList.get(i).city) == null)
                islands.add(adjacencyList.get(i).city);
    }

}

