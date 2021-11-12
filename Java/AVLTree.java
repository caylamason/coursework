// AVL Tree setup

public class AVLTree<E> {

    class Node<E>{
        E data;
        Node<E> right;
        Node<E> left;
        Node<E> parent;
        int height;

        //Node constructor
        Node(E obj){
            data = obj;
            parent = left = right = null;
            height = 0;
        }

    }

    Node root;
    int currSize;

    //Tree constructor
    AVLTree(){
        root = null;
        currSize = 0;
    }

    int max (int a, int b){
        return (a > b) ? a : b;
    }

    int height (Node node){
        if(node == null)
            return 0;
        else return node.height;
    }

    void add (E obj){
        Node<E> node = new Node(obj);
        if (root == null){
            root = node;
            currSize++;
            return;
        }
        insert (root, node);
    }

    void insert (Node<E> parent, Node<E> newNode){
        if(){

        }
    }


}
