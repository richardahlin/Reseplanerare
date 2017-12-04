import java.util.*;

public class BinHeap<E> implements PrioQueue<E>{

    private ArrayList<E> queue = new ArrayList<>();

    public BinHeap(Comparator<? super E> comp) {

    }

    void add(E e){
        queue.add(e);
        bubbleUp(queue.size() - 1);
    }

    E peek(){
        return queue.get(0);
    }

    E poll(){
        if(queue.get(0) == null){
            return null;
        }

        E returnElement = queue.get(0);
        queue.remove(0);

        if(queue.size() == 0){
            return returnElement;
        }

        E swapElement = queue.get(queue.size() - 1);
        queue.add(0,swapElement);
        queue.remove(queue.size() - 1);

        bubbleDown(0);

        return returnElement;
    }

    void remove(E e){
        int equalsIndex;
        int i = 0;

        for(E element : queue){
            if(element == e){
                equalsIndex = i;
                break;
            }
            i++;
        }

        if(equalsIndex == null){
            return;
        }

        queue.remove(equalsIndex);

        if(queue.size() != 0){
            queue.add(equalsIndex,queue.get(queue.size() - 1));
            queue.remove(queue.size() - 1);
            bubbleUpOrDown(equalsIndex);
        }
    }

    void remove(int index){
        if(queue.size() > 1){
            queue.remove(index);
            queue.add(index, queue.size() - 1);
            queue.remove(queue.size() - 1);
            bubbleUpOrDown(index);
        }
    }

    void bubbleUp(int index){
        int position = index;
        while(position != 0){
            E upElement = queue.get(position);
        }
    }

    void bubbleDown(int index){

    }

    void bubbleUpOrDown(int index){

    }
}