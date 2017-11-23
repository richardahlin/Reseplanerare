
package reseplanerare;

public class Reseplanerare {

    public static void main(String[] args) {
        APrioMap<String, Integer> prioMap = new APrioMap<>();
        prioMap.put("A", 8);
        prioMap.put("B", 9);
        System.out.println(prioMap.get("B"));
        System.out.println(prioMap.poll());
        System.out.println(prioMap.poll());
        System.out.println(prioMap.poll());
    }
    
}
