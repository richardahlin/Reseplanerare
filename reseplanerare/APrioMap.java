import java.util.*;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {

    private PriorityQueue<ComparablePair<K,V>> priorityQueue = new PriorityQueue<>();
    private HashMap<K, ComparablePair<K, V>> hashMap = new HashMap<>();

    public APrioMap() {

    }

    public void put(K k, V v) {
        ComparablePair<K, V> comparablePair = new ComparablePair<K, V>(k, v);

        if(hashMap.containsKey(k)) {
            ComparablePair<K, V> value = hashMap.get(k);
            priorityQueue.remove(value);
        }

        priorityQueue.add(comparablePair);
        hashMap.put(k, comparablePair);
    }

    public V get(K k) {
        if(hashMap.containsKey(k)) {
            return hashMap.get(k).b;
        } else {
            return null;
        }
    }

    public Pair<K, V> peek() {
        ComparablePair<K, V> comparablePair = priorityQueue.peek();
        return comparablePair;
    }

    public Pair<K, V> poll() {
        ComparablePair<K, V> comparablePair = priorityQueue.poll();
        if(comparablePair != null) {
            K key = comparablePair.a;
            hashMap.remove(key);
        }
        return comparablePair;
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
