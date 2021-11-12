/*
Cayla Mason
cssc0973
CS 310
Shawn Healey
Section 2

Lab 3
Due 2019-11-20
Create a one-way graph representing source cities and their destinations, along with statistical data.
 */

import java.io.*;
import java.util.*;

public class Driver {

    private Scanner scanner;

    //reads a CSV file, returns List of array of Strings
    public List<String[]> readCSV(String fileName) {

        try {
            scanner = new Scanner(new File(fileName));
            List<String[]> parts = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] sArray = line.split(",");
                parts.add(sArray);

            }
            scanner.close();

            return parts;

        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
        return null;
    }


    public void loadGraph (MyGraph g, List<String[]> list){

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                if (j < 2) g.addVertex(list.get(i)[j]);
                else {
                    g.addEdge(list.get(i)[j-2], list.get(i)[j-1], list.get(i)[j]);
                }
            }
        }

        g.setIslands();
    }


    public void getStats (MyGraph g){
        System.out.println("==================== GRAPH STATISTICS ====================");

        //number of vertices
        System.out.println("Total number of vertices: " + g.getVertices());

        //vertices with zero inbound edges
        if (!g.getIslands().isEmpty()) {
            g.getIslands().forEach(n -> {System.out.println("\t" + n);});
        } else System.out.println("There are no vertices with zero inbound edges.");

        //vertices with self-edges
        if (!g.getSelfEdges().isEmpty()) {
            g.getSelfEdges().forEach(n -> {System.out.println("\t" + n);});
        } else System.out.println("There are no vertices with self-edges.");

        //edges with heaviest weight
        System.out.println("Most expensive place(s) to travel: ");
        if (!g.getPriceyDests().isEmpty())
            g.getPriceyDests().forEach(n -> { System.out.println("\t" + n); });
    }


    public static void main (String[] args){

        Driver oDriver = new Driver();
        MyGraph graph = new MyGraph();

        try {
            //per Healey, the input data is assumed to be well-formed and correct
            List<String[]> list = oDriver.readCSV(args[0]);
            oDriver.loadGraph(graph,list);
            oDriver.getStats(graph);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
} //end Driver class


