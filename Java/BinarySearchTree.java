// Binary Search Tree

public class BinarySearchTree {

    class Node {
        int key;
        String value;
        Node left;
        Node right;

        //Node constructor
        Node (int id, String val){
            key = id;
            value = val;
            left = right = null;
        }
    }

    private Node root;

    //BST constructor
    BinarySearchTree (){
        root = null;
    }


    private void insert (Node node, Node parent){
        if (node.key < parent.key){
            if (parent.left == null)
                parent.left = node;
            else insert(node, parent.left);
        }
        else if (node.key > parent.key)
            if (parent.right == null)
                parent.right = node;
            else insert(node, parent.right);
    }


    void add(int id, String val){
        //tree is empty, make root
        if (root == null){
            root = new Node(id, val);
        }
        else {
            Node node = new Node(id,val);
            insert(node, root);
        }
    }


    Node search (Node node, int id){
        //tree is empty, or reached the end of the tree
        if (node == null)
            return null;
        else if (node.key == id)
            return node;
        else if (id < node.key)
            return search(node.left, id);
        else return search(node.right,id);
    }

    boolean search (int id){
        Node node = search(root,id);
        if (node == null) {
            System.out.println("Cannot locate item.");
            return false;
        }
        else {
            System.out.println("Item: " + node.value + ". Key: " + node.key + ".");
            return true;
        }
    }

    Node getNode (int id){
        Node node = search(root,id);
        if (node == null)
            return null;
        else
            return node;
    }

    //pass in false for minimum, true for maximum
    //returns the smallest or largest key
    int findMax (boolean maxIsTrue){
        return findMax(root, maxIsTrue).key;
    }

    Node findMax (Node node, boolean max){
        if (!max){
            if (node.left == null)
                return node;
            else return findMax(node.left, max);
        }
        else {
            if (node.right == null)
                return node;
            else return findMax(node.right, max);
        }
    }

    //delete function
    // 1) call getNode
    // 2) if zero children, set node to null
    //    if one child, node = node.left
    //    if two children, call getSuccessor

    Node delete (int key){

        Node node = getNode(key);
        Node temp;

        if (node == null)
            return node;
        else {
            temp = node;
        }

        // 0 or 1 child
        if (node.left == null) {
            if (node == root)
                root = node.right;
            node = node.right;
            return temp;
        } else if (node.right == null) {
            if (node == root)
                root = node.left;
            node = node.left;
            return temp;
        }
        // 2 children
        else {
            Node successor = getSuccessor(node.left);

            if (getPredecessor(successor) != node)
                getPredecessor(successor).right = successor.left;

            if (node == root){
                node = root = successor;
                return temp;
            }
            else {
                Node predecessor = getPredecessor(node);

                if (node.key > predecessor.key){
                    predecessor.right = successor;
                    return temp;
                }
                else {
                    predecessor.left = successor;
                    return temp;
                }
            }
        }
    }

    Node getSuccessor (Node node){
        if (node.right == null)
            return node;
        else return getSuccessor(node.right);
    }

    Node getPredecessor (Node node){
        return getPredecessor(root, node);
    }

    Node getPredecessor (Node parent, Node node){
        if (parent.right == node || parent.left == node)
            return parent;
        else if (node.key > parent.key)
            return getPredecessor(parent.right, node);
        else return getPredecessor(parent.left, node);
    }


    public static void main (String[] args){

        BinarySearchTree bst = new BinarySearchTree();
        bst.add(37,"37");
        bst.add(42, "42");
        bst.add(12, "12");
        System.out.println(bst.root.value);
        bst.delete(37);
        System.out.println(bst.root.value);


    }
}
