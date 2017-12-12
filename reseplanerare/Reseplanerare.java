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
        g.addEdge("V2", "V4", 1);
        g.addEdge("V4", "V3", 5);
        g.addEdge("V5", "V3", 3);
        g.addEdge("V1", "V5", 0);
        g.addEdge("V1", "V4", 0);
        g.addEdge("V5", "V2", 1);
        g.addEdge("V2", "V6", 2);
        g.addEdge("V0", "V5", 4);
        g.addEdge("V6", "V1", 5);
        g.addEdge("V5", "V4", 3);
        g.addEdge("V0", "V3", 4);
        g.addEdge("V1", "V3", 5);
        g.addEdge("V0", "V1", 3);
        g.addEdge("V3", "V6", 1);
        g.addEdge("V3", "V2", 1);
        g.addEdge("V1", "V2", 0);
        g.addEdge("V4", "V6", 5);
        g.addEdge("V5", "V6", 3);
        g.shortestPath("V3", "V4");
    }

}
