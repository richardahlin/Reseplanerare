

public class OurHeapTest{
    public static void main(String[] args){
        APrioMap<String, Integer> pm = new APrioMap<>();
        System.out.println(pm.toString());
        pm.get("K1");  // result: null
        System.out.println(pm.toString());
        pm.put("K5", 3);
        System.out.println(pm.toString());
        pm.put("K7", 1);
        System.out.println(pm.toString());
        pm.put("K1", 4);
        System.out.println(pm.toString());
        pm.peek();  // result: <K7,1>
        System.out.println(pm.toString());
        pm.put("K5", 3);
        System.out.println(pm.toString());
        pm.put("K0", 7);
        System.out.println(pm.toString());
        pm.get("K1");  // result: 3
        System.out.println(pm.toString());
        
        
        pm.swap(0,3);
        System.out.println(pm.toString());
    }
}
