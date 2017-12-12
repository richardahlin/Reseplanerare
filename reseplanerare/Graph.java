import java.util.*;

public class Graph {

    private HashMap<String, Node> nodeMap = new HashMap<>();
    private LinkedList<Node> nodeList = new LinkedList<>();

    public Graph() {

    }

    public void addVertice(String label) {
        Node node = new Node(label);
        nodeMap.put(label, node);
        nodeList.add(node);
    }

    public void addEdge(String node1, String node2, int dist) {
        Node node10 = nodeMap.get(node1);
        Node node20 = nodeMap.get(node2);
        node10.addNeighbour(node20, dist);
        node20.addNeighbour(node10, dist);
    }

    public static class Path {
        public int totalDist;
        public List<String> vertices;
        public Path(int totalDist, List<String> vertices) {
            this.totalDist = totalDist;
            this.vertices = vertices;
        }

        @Override
        public String toString() {
            return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
        }
    }

    public static class Node implements Comparable<Node> {

        private String name;
        private int totalDistance = Integer.MAX_VALUE;
        private LinkedList<Pair<Node, Integer>> neighbours = new LinkedList<>();

        public Node(String name) {
            this.name = name;
        }

        public void addNeighbour(Node node, int distance) {
            for(Pair<Node, Integer> pair : neighbours) {
                String name = pair.a.getName();
                if(name.equals(node.getName())) {
                    pair.b = distance;
                    return;
                }
            }

            Pair<Node, Integer> newPair = new Pair<>(node, distance);
            neighbours.add(newPair);
        }

        public String getName() {
            return name;
        }

        public int getTotalDistance() {
            return totalDistance;
        }

        public LinkedList<Pair<Node, Integer>> getNeighbours() {
            return neighbours;
        }

        public void printNeighbours(){
            String neighbourList = "Neighbours: [";
            for(Pair<Node,Integer> pair : neighbours){
                neighbourList += pair.a.getName() + ",";
            }
            neighbourList += "]";
            System.out.println(neighbourList);
        }

        public void setTotalDistance(int totalDistance) {
            this.totalDistance = totalDistance;
        }

        @Override
        public int compareTo(Node o) {
            return totalDistance - o.getTotalDistance();
        }
    }

    public Path shortestPath(String start, String dest) {
        if(start.equals(dest)){
            LinkedList<String> path = new LinkedList<>();
            path.add(start);
            return new Path(0,path);
        }

        HashMap<String, Node> visitedNodes = new HashMap<>();
        APrioMap<String, Node> prioMap = new APrioMap<>();

        for(Node node : nodeList){
            node.setTotalDistance(Integer.MAX_VALUE);
        }

        Node startNode = nodeMap.get(start);
        startNode.setTotalDistance(0);

        for(Node node : nodeList) {
            //System.out.println("Nodename: " + node.getName());
            prioMap.put(node.getName(), node);
        }

        Node currentNode;
        int distanceToGoal;

        while(true) {
            //System.out.println("LOOP11111");

            Pair<String, Node> currentPair = prioMap.poll();
            currentNode = currentPair.b;

            //System.out.println("Current node: " + currentNode.getName());
            //System.out.println("Total Distance: " + currentNode.getTotalDistance());
            //currentNode.printNeighbours();

            if(currentNode.getName().equals(dest)){
                distanceToGoal = currentNode.getTotalDistance();
                break;
            }

            LinkedList<Pair<Node, Integer>> neighbours = currentNode.getNeighbours();
            for(Pair<Node, Integer> pair2 : neighbours) {
                Node neighbour = pair2.a;
                int distance = pair2.b;

                //System.out.println("neighbour: " + neighbour.getName());

                if(!visitedNodes.containsKey(neighbour.getName())) {
                    //System.out.println("Not Visited!");
                    int distanceFromCurrentNode = currentNode.getTotalDistance() + distance;
                    if(distanceFromCurrentNode < neighbour.getTotalDistance()) {
                        neighbour.setTotalDistance(distanceFromCurrentNode);
                        prioMap.put(neighbour.getName(), neighbour);
                    }
                }
            }

            //System.out.println("End of neighbour loop.");

            visitedNodes.put(currentNode.getName(), currentNode);

            if(prioMap.peek().b.getTotalDistance() == Integer.MAX_VALUE){
                return null;
            }
        }

        visitedNodes = new HashMap<>();
        LinkedList<String> nodePath = new LinkedList<>();
        LinkedList<Integer> pathDistance = new LinkedList<>();
        Node endNode = nodeMap.get(dest);
        nodePath.addFirst(dest);
        currentNode = endNode;
        visitedNodes.put(dest,endNode);
        pathDistance.addFirst(0);
        Node previousNode = null;

        //System.out.println("Distance to goal: " + distanceToGoal);

        while(!currentNode.getName().equals(start)) {

            String totalStringPath = "[";
            for(String nodeString : nodePath){
                totalStringPath += nodeString + ",";
            }
            totalStringPath += "]";
            //System.out.println(totalStringPath);

            //System.out.println("CurrentNode: " + currentNode.getName());

            LinkedList<Pair<Node, Integer>> neighbours = currentNode.getNeighbours();
            Node smallestNode = null;
            int smallestDistance = Integer.MAX_VALUE;
            int neighbourDistance = 0;

            for(Pair<Node, Integer> pair : neighbours) {
                //System.out.println("Neighbour: " + pair.a.getName());
                if(!(pair.a.getTotalDistance() == Integer.MAX_VALUE) && !(visitedNodes.containsKey(pair.a.getName()))){
                    //System.out.println("Non-visited Neighbour: " + pair.a.getName());
                    if((pair.a.getTotalDistance() + pair.b < smallestDistance)||
                        ((pair.a.getTotalDistance() + pair.b <= smallestDistance) && (pair.a.getName().equals(start)))) {
                        //System.out.println("Replace smallest node!");
                        smallestNode = pair.a;
                        smallestDistance = pair.a.getTotalDistance() + pair.b;
                        neighbourDistance = pair.b;
                    }
                }
            }

            String ints = "[";
            for(Integer val : pathDistance){
                ints += val + ",";
            }
            ints += "]";
            //System.out.println(ints);

            //System.out.println("Neighbour-distance: " + neighbourDistance);

            if(smallestNode == null){
                //System.out.println("Null neighbour.");
            }else{
                //System.out.println("Smallest neighbour: " + smallestNode.getName() + ", total distance: " +
                    //(/*pathDistance.getFirst() + */smallestDistance));
            }

            visitedNodes.put(currentNode.getName(),currentNode);

            if(((pathDistance.getFirst() + neighbourDistance) > distanceToGoal) ||
                (smallestDistance == Integer.MAX_VALUE)){
                if(previousNode != null){
                    visitedNodes.remove(previousNode.getName());
                }
                previousNode = currentNode;
                nodePath.removeFirst();
                pathDistance.removeFirst();
                currentNode = nodeMap.get(nodePath.getFirst());
            }else{
                previousNode = null;
                nodePath.addFirst(smallestNode.getName());
                currentNode = smallestNode;
                pathDistance.addFirst(neighbourDistance+pathDistance.getFirst());
            }
        }

        Path path = new Path(distanceToGoal, nodePath);
        return path;
    }
}
