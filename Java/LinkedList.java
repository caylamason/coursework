/*
Creates a circular linked list with both a head and tail pointer
 */

public class LinkedList<E> {

    class Node<E> {
        E datum;
        Node<E> next;

        public Node(E obj){
            datum = obj;
            next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int currSize;

    public int getCurrSize(){
        return currSize;
    }

    public LinkedList(){
        head = tail = null;
        currSize = 0;
    }

    //with head complexity is O(1)
    //without head complexity is O(n)
    public void addFirst(E obj){
        Node<E> node = new Node<>(obj);
        node.next = head;
        head = node;

        //list is empty
        if(tail == null) {
            tail = node;

        }

        currSize++;
    }

    //without tail complexity is O(n)
    //with tail complexity is O(1)
    public void addLast(E obj){
        Node<E> node = new Node<>(obj);

        //list is empty
        if(head == null){
            head = tail = node;
            //makes it circular
            node.next = head;

            currSize++;
            return;
        }

        //list is not empty
        /*
        Node<E> temp = head;
        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = node;
        currSize++;
        */

        tail.next = node;
        tail = node;
        tail.next = head;
        currSize++;
        return;
    }

    //with head complexity is O(1)
    //without head complexity is O(n)
    public E removeFirst(){
        //list is empty
        if(head == null)
            return null;

        //list is not empty
        E temp = head.datum;

        if(head == tail)
            head = tail = null;
        else head = head.next;

        currSize--;
        return temp;
    }

    public E removeLast(){
        //list is empty
        if(head == null)
            return null;

        //list is not empty
        //list has only 1 node
        if(head == tail)
            return removeFirst();

        Node<E> current = head, previous = null;
        while(current != tail){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        currSize--;
        return current.datum;

    }

}
