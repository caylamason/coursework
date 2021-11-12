
public class Heap {

    private int[] heap;
    private int maxSize;
    private int current;

    Heap(int max){
        current = 0;
        maxSize = max;
        heap = new int[maxSize];
    }

    //provide indices of two numbers to swap
    void swap (int posA, int posB){
        int temp = heap[posA];
        heap[posA] = heap[posB];
        heap[posB] = temp;
    }

    //provide child's index, returns the index of the parent node
    int parent(int child){
        return (child - 1)/2;
    }

    int leftChild (int parent){
        return (2*parent)+1;
    }

    int rightChild (int parent){
        return (2*parent)+2;
    }

    void resize (){
        int[] newHeap = new int [heap.length * 2];
        for (int i = 0; i < heap.length; i++)
            newHeap[i] = heap[i];
        heap = newHeap;
    }

    void insert (int num){

        heap[current] = num;

        while(heap[current] < heap[parent(current)]){
            swap (current, parent(current));
            //now that parent and child have switched places, overwrite child for recursion
            current = parent(current);
        }

        current++;

        //if (current == maxSize)
          //  resize();

        for (int j = 0; j < heap.length; j++)
            System.out.print(heap[j] + ",");
        System.out.println();
    }

    public static void main (String[] args){
        Heap myHeap = new Heap(10);

        myHeap.insert(88);
        myHeap.insert(16);
        myHeap.insert(52);
        myHeap.insert(91);
        myHeap.insert(1);
        myHeap.insert(84);
        myHeap.insert(12);
        myHeap.insert(58);
        myHeap.insert(9);
        myHeap.insert(2);
    }



}
