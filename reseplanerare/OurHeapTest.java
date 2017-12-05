public class OurHeapTest{
    public static void main(String[] args){
        PrioQueue<Integer> pq = new BinHeap<>(new NaturalOrderComparator<Integer>());
        pq.peek();  // result: null
        System.out.println(pq.toString());
        pq.add(2);
        System.out.println(pq.toString());
        pq.remove(6);
        System.out.println(pq.toString());
        pq.add(3);
        System.out.println(pq.toString());
        pq.iterator();
        pq.iterator();
        pq.add(6);
        System.out.println(pq.toString());
        System.out.println("REMOOOVE");
        pq.remove(6);
        System.out.println(pq.toString());
    }
}