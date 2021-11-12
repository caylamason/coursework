/*
Cayla Mason
cssc0973
CS 310
Shawn Healey
Section 2

Lab 3
*/
/*
import java.util.*;

public class Graph {

    private int numVerts;
    private int maxNumVerts;
    private int numEdges;
    private int anIndex;
    private int maxCost = 0;
    private List<String> selfEdges;
    private List<String> restrictedTravel;
    private List<String> priceyCities;
    private ArrayList<BinaryTree> adjList;



    Graph() {
        adjList = new ArrayList<>();
        numVerts = 0;
        numEdges = 0;
        selfEdges = null;
        priceyCities = null;
        restrictedTravel = null;

    }



    //sets a key value for a given city
    //assigns a source's position in the array
    int setKey(String source){
        int key = source.hashCode() % maxNumVerts;

        if (adjList == null) {
            return key;
        }

        else {
            while (adjList.get(key) != null)
                key = (key + 1) % maxNumVerts;
            return key;
        }

    }



    int setCost (String cost){
        if (cost == null)
            return 0;
        else return Integer.parseInt(cost);
    }



    void setIndex (int num){
        anIndex = num;
    }



    int getIndex(){
        return anIndex;
    }



    void addVertex (String source){
        //root contains the source city
        int index = setKey(source);

        //ensure no duplicates
        if (adjList.get(index) == null){
            BinaryTree tree = new BinaryTree();
            BinaryTree.Node node = null;
            //setting the price for a root node at zero. If there is a self-edge the CSV should read:
            // "city x,city x,cost"
            tree.insert(node,source,index,0);

            adjList.add(index,tree);

            numVerts++;
        }
    }



    //creates a connection from the source to its destination
    void addEdge (String source, String destination, String cost){
        //confirm vertices exist
        if (contains(source)) {
            int srcIndex = getIndex();
            if (contains(destination)){
                if (!contains(source,destination)) {
                    //self-edge
                    if (source.equalsIgnoreCase(destination)) {
                        adjList.get(srcIndex).insert(adjList.get(srcIndex).getRoot(), destination, -1,
                                setCost(cost));
                        selfEdges.add(destination);
                    }
                    //not a self-edge
                    else {
                        adjList.get(srcIndex).insert(adjList.get(srcIndex).getRoot(), destination, setKey(destination),
                                setCost(cost));
                    }

                    //adjListByCost.put(setKey(source),)
                    numEdges++;
                } //else edge already exists
            } else addVertex(destination);
        } else addVertex(source);
    }



    //deletes all edges from the source node the destination node
    //returns false is either vertex name is invalid
    boolean removeEdges (String source, String destination){
        if (contains(source) && contains(destination)) {
            //self-edge has local tree key = -1
            if (source.equalsIgnoreCase(destination)) {
                adjList.get(setKey(source)).delete(adjList.get(setKey(source)).getRoot(), -1);
                selfEdges.remove(destination);
                numEdges--;
            }
            else {
                adjList.get(setKey(source)).delete(adjList.get(setKey(source)).getRoot(), setKey(destination));
                numEdges--;
            }
            return true;
        } else return false;
    }



    //deletes the vertex and all of its references
    //returns false if the vertex does not appear in the graph
    boolean removeVertex (String name){
        //if the vertex exists
        if (contains(name)){
            //iterate through each tree, delete the destination vertices
            for (int i = 0; i < adjList.size(); i++){
                adjList.get(i).delete(adjList.get(i).findNode(adjList.get(i).getRoot(), setKey(name)), setKey(name));
            }
            //delete the source vertex
            adjList.set(getIndex(), null);
            numVerts--;
            return true;
        } else return false;
    }



    //return true if the specified vertex appears in the graph
    boolean contains (String name) {
        int start = name.hashCode() % numVerts;

        for (int i = start; i < adjList.size(); i++){
            if (adjList.get(i).getRoot().value.equalsIgnoreCase(name)) {
                setIndex(i);
                return true;
            }
        }

        return false;
    }



    //returns true if the specified edge between the two vertices exists in the graph
    boolean contains (String source, String dest){
        if(contains(source)) {
            //self-edge
            if (source.equalsIgnoreCase(dest) && (adjList.get(getIndex()).findNode(adjList.get(getIndex()).getRoot(),
                    -1) != null))
                return true;
                //not a self-edge
            else if (adjList.get(getIndex()).findNode(adjList.get(getIndex()).getRoot(), setKey(dest)) != null)
                return true;
                //edge does not exist
            else return false;
        }
        //source is not a vertex
        else return false;
    }


    //can you travel to a given city?
    List<String> cannotTravel (){

        int k = 0;

        while(k < adjList.size()) {

            String city = adjList.get(0).getRoot().value;

            //search through each tree
            for (int i = 0; i < adjList.size(); i++) {
                //self-edge
                if (i == setKey(city) && (adjList.get(i).findNode(adjList.get(i).getRoot(), -1) == null))
                    restrictedTravel.add(city);
                    //not a self-edge
                else if (i != setKey(city) && (adjList.get(i).findNode(adjList.get(i).getRoot(),
                        setKey(city)) == null))
                    restrictedTravel.add(city);
            }

            k++;
        }

        return restrictedTravel;
    }

    int getNumVerts (){
        return numVerts;
    }

    int getNumEdges (){
        return numEdges;
    }

    List<String> getSelfEdges (){
        return selfEdges;
    }

    void setMaxCost(String price){
        if (setCost(price) > maxCost)
            maxCost = setCost(price);
    }



    int getMaxCost (){
        return maxCost;
    }



    void setPriceyCities (String city) {
        priceyCities.add(city);
    }



    List<String> getPriceyCities (){
        return priceyCities;
    }


    void setMaxNumVerts (int number){
        maxNumVerts = number;
    }



    int getMaxNumVerts (){
        return maxNumVerts;
    }


    public void loadGraph (List<String[]> list){

        for (int i = 0; i < list.size(); i++) {
            //setting a starting point should prevent duplicate vertex entries
            int start;

            //new source
            if (i == 0 || ((list.get(i+1) != null) && !list.get(i)[0].equalsIgnoreCase(list.get(i+1)[0])))
                start = 0;
                //not a new source
            else start = 1;

            //fill in the graph
            for (int j = start; j < list.get(i).length; j++) {
                if (j != 2) addVertex(list.get(i)[j]);
                else {
                    addEdge(list.get(i)[0], list.get(i)[1], list.get(i)[j]);
                    //looking for most expensive edge
                    setMaxCost(list.get(i)[j]);
                    if (getMaxCost() == setCost(list.get(i)[j]))
                        setPriceyCities(list.get(i)[1]);
                }
            }
        }
    }




} //end Graph class
*/