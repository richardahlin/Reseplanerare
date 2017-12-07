public class Reseplanerare {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertice("V0");
        graph.addVertice("V1");
        
        graph.addEdge("V0", "V1", 0);

        Graph.Path path1 = graph.shortestPath("V0","V0");
        System.out.println(path1.toString());

        path1 = graph.shortestPath("V0","V1");
        System.out.println(path1.toString());
    }

}
