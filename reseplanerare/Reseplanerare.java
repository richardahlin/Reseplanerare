public class Reseplanerare {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertice("V0");
        graph.addVertice("V1");
        //graph.addVertice("V2");

        //graph.addEdge("V0", "V2", 0);
        graph.addEdge("V0", "V1", 0);
        //graph.addEdge("V2", "V1", 2);

        Graph.Path path1 = graph.shortestPath("V0","V0");
        System.out.println(path1.toString());

        Graph.Path path2 = graph.shortestPath("V0","V1");
        System.out.println(path2.toString());

        //Graph.Path path3 = graph.shortestPath("V0","V1");
        //System.out.println(path3.toString());

        /*
        if(path == null){
            System.out.println("path1 null");
        }
        if(path2 == null){
            System.out.println("path2 null");
        }
        */



    }

}
