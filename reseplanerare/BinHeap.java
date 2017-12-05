import java.util.*;

public class BinHeap<E> implements PrioQueue<E>{

    private ArrayList<E> queue = new ArrayList<>();
    private Comparator<? super E> comp;

    public BinHeap(Comparator<? super E> comp) {
        this.comp = comp;
    }

    public void add(E e){
        queue.add(e);
        bubbleUp(queue.size() - 1);
    }

    public E peek(){
        if(queue.size() == 0){
            return null;
        }
        return queue.get(0);
    }

    public E poll(){
        if(queue.size() == 0){
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

    public void remove(E e){
        int equalsIndex = -1;
        int i = 0;

        for(E element : queue){
            if(element == e){
                equalsIndex = i;
                break;
            }
            i++;
        }

        if(equalsIndex == -1){
            return;
        }

        remove(equalsIndex);
    }

    public void remove(int index){
        if(queue.size() == 0){
            return;
        }else if((queue.size() - 1) == index){
            queue.remove(index);
        }else if((queue.size() - 2) == index){
            queue.remove(index);
            bubbleUpOrDown(index);
        }else if(queue.size() > 1){
            queue.remove(index);
            queue.add(index, queue.get(queue.size() - 1));
            queue.remove(queue.size() - 1);
            bubbleUpOrDown(index);
        }
    }

    public void bubbleUp(int index){
        int position = index;
        while(position != 0){
            E upElement = queue.get((position - 1)/2);
            if(comp.compare(upElement,queue.get(position)) > 0){
                queue.remove((position - 1)/2);
                queue.add((position-1)/2,queue.get(position-1));
                queue.remove(position);
                queue.add(position,upElement);

                position = (position-1)/2;
            }else{
                return;
            }
        }
    }

    public void bubbleDown(int index){
        int position = index;
        while((queue.size()) > (position*2+1)){
            if((queue.size()) > ((position+1)*2)){
                int smallestIndex = -1;
                E smallestElement = queue.get(position);
                if(comp.compare(queue.get(position*2+1),smallestElement) < 0){
                    smallestElement = queue.get(position*2+1);
                    smallestIndex = position*2+1;
                }
                if(comp.compare(queue.get((position+1)*2),smallestElement) < 0){
                    smallestElement = queue.get((position+1)*2);
                    smallestIndex = (position+1)*2;
                }
                if(smallestIndex == -1){
                    return;
                }

                queue.remove(smallestIndex);
                queue.add(smallestIndex,queue.get(position));
                queue.remove(position);
                queue.add(position,smallestElement);

                position = smallestIndex;
            }else{
                if(comp.compare(queue.get(position*2+1),queue.get(position)) < 0){
                    int leftIndex = position*2+1;
                    E leftElement = queue.get(leftIndex);

                    queue.remove(leftIndex);
                    queue.add(leftIndex,queue.get(position));
                    queue.remove(position);
                    queue.add(position,leftElement);
                }
                return;
            }
        }
    }

    public void bubbleUpOrDown(int index){
        if(queue.size() <= 1){
            return;
        }else if(index == 0){
            bubbleDown(index);
        }else if(((index+1)*2) > (queue.size()-1)){
            bubbleUp(index);
        }else if(comp.compare(queue.get((index-1)/2),queue.get(index)) > 0){
            bubbleUp(index);
        }else if(comp.compare(queue.get(index*2+1),queue.get(index)) < 0){
            bubbleDown(index);
        }else if(queue.size() > ((index+1)*2)){
            if(comp.compare(queue.get((index+1)*2),queue.get(index)) < 0){
                bubbleDown(index);
            }
        }
    }

    public Iterator<E> iterator(){
        return queue.iterator();
    }

    @Override
    public String toString(){
        String finalString = "[";
        int i = 1;
        for(E e : queue){
            if(i != queue.size()){
                finalString += e.toString() + ",";
            }else{
                finalString += e.toString();
            }
            i++;
        }
        finalString += "]";
        return finalString;
    }
}