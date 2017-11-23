
package reseplanerare;

import java.util.*;

public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {
    
    private PriorityQueue<V> priorityQueue = new PriorityQueue<>();
    private HashMap<K, V> hashMapKV = new HashMap<>(); 
    private HashMap<V, K> hashMapVK = new HashMap<>(); 
    
    public APrioMap() {
        
    } 

    public void put(K k, V v) {
        hashMapKV.put(k, v);
        hashMapVK.put(v, k);
        priorityQueue.add(v);
    }

    public V get(K k) {
        return hashMapKV.get(k);
    }

    public Pair<K, V> peek() {
        V highestPriority = priorityQueue.peek();
        K key = hashMapVK.get(highestPriority);
        return null;
    }

    public Pair<K, V> poll() {
        return null;
    }
    
}
