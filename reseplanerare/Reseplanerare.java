
package reseplanerare;

import reseplanerare.Graph;

public class Reseplanerare {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertice("Fristad");
        graph.addVertice("Frufällan");
        graph.addEdge("Fristad", "Frufällan", 15);
        graph.addVertice("Nordskogen");
        graph.addEdge("Frufällan", "Nordskogen", 5);
    }
    
}
