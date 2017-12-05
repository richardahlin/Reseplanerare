import java.util.*;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {

    private ArrayList<ComparablePair<K,V>> queue = new ArrayList<>();
    private HashMap<K,Integer> hashMap = new HashMap<>();

    public APrioMap() {

    }

    public void put(K k, V v) {
        ComparablePair<K, V> comparablePair = new ComparablePair<K, V>(k, v);

        if(hashMap.containsKey(k)) {
            int value = hashMap.get(k);
            remove(value);
        }

        add(comparablePair);
    }

    public V get(K k) {
        if(hashMap.containsKey(k)) {
            return queue.get(hashMap.get(k));
        } else {
            return null;
        }
    }

    public Pair<K, V> peek() {
        return queue.get(0);
    }

    public Pair<K, V> poll() {
        if(queue.size() == 0){
            return null;
        }

        ComparablePair<K,V> returnElement = queue.get(0);
        queue.remove(0);
        hashMap.remove(returnElement.a);

        if(queue.size() == 0){
            return returnElement;
        }

        ComparablePair<K,V> swapElement = queue.get(queue.size() - 1);
        queue.add(0,swapElement);
        queue.remove(queue.size() - 1);

        hashMap.put(swapElement.a,0);

        bubbleDown(0);

        return returnElement;
    }

    public void remove(ComparablePair<K,V> e){
        int equalsIndex = -1;
        int i = 0;

        for(ComparablePair<K,V> element : queue){
            if(element.a.equals(e.a)){
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
            hashMap.remove(queue.get(index).a);
            queue.remove(index);
        }else if((queue.size() - 2) == index){
            hashMap.remove(queue.get(index).a);
            queue.remove(index);
            bubbleUpOrDown(index);
        }else if(queue.size() > 1){
            hashMap.remove(queue.get(index).a);
            queue.remove(index);
            hashMap.put(queue.get(queue.size() - 1).a,index);
            queue.add(index, queue.get(queue.size() - 1));
            queue.remove(queue.size() - 1);
            bubbleUpOrDown(index);
        }
    }


    public void bubbleUp(int index){
        int position = index;
        while(position != 0){
            ComparablePair<K,V> upElement = queue.get((position - 1)/2);
            if(upElement.compareTo(queue.get(position)) > 0){
                hashMap.put(upElement.a,position);
                hashMap.put(queue.get(position).a,(position - 1)/2);

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

                //Continue here!!!!!!!!!!!!!!!
                ComparablePair<K,V> smallestElement = queue.get(position);
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

    public Iterator<ComparablePair<K,V>> iterator(){
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

    private class ComparablePair<K, V extends Comparable<? super V>> extends Pair<K, V> implements Comparable<ComparablePair<K, V>> {

        public ComparablePair(K a, V b) {
            super(a, b);
        }

        @Override
        public int compareTo(ComparablePair<K, V> o) {
            return b.compareTo(o.b);
        }
    }
}
