public class Reseplanerare {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertice("V0");
        g.addVertice("V1");
        g.addVertice("V2");
        g.addVertice("V3");
        g.addVertice("V4");
        g.addVertice("V5");
        g.addVertice("V6");
        g.addVertice("V7");
        g.addVertice("V8");
        g.addEdge("V2", "V4", 6);
        g.addEdge("V3", "V4", 0);
        g.addEdge("V8", "V5", 8);
        g.addEdge("V4", "V5", 5);
        g.addEdge("V1", "V3", 1);
        g.addEdge("V1", "V8", 7);
        g.addEdge("V8", "V4", 6);
        g.addEdge("V7", "V2", 7);
        g.addEdge("V3", "V2", 5);
        System.out.println(g.shortestPath("V7", "V1").toString());
    }

}
