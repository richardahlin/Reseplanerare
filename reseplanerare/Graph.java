
package reseplanerare;

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
        
        public void setTotalDistance(int totalDistance) {
            this.totalDistance = totalDistance;
        }

        @Override
        public int compareTo(Node o) {
            return totalDistance - o.getTotalDistance();
        }
    }
    
    public Path shortestPath(String start, String dest) {
        HashMap<String, Node> visitedNodes = new HashMap<>();
        APrioMap<String, Node> prioMap = new APrioMap<>();
        
        Node startNode = nodeMap.get(start);
        startNode.setTotalDistance(0);
        
        for(Node node : nodeList) {
            prioMap.put(node.getName(), node);
        }
        
        Node currentNode;
        
        while(!visitedNodes.containsKey(dest)) {
            Pair<String, Node> pair = prioMap.poll();
            currentNode = pair.b;
            
            LinkedList<Pair<Node, Integer>> neighbours = currentNode.getNeighbours();
            for(Pair<Node, Integer> pair2 : neighbours) {
                Node neighbour = pair2.a;
                int distance = pair2.b;
                
                if(!visitedNodes.containsKey(neighbour.getName())) {
                    int distanceFromCurrentNode = currentNode.getTotalDistance() + distance;
                    if(distanceFromCurrentNode < neighbour.getTotalDistance()) {
                        neighbour.setTotalDistance(distanceFromCurrentNode);
                        prioMap.put(neighbour.getName(), neighbour);
                    }
                }
            }
            
            System.out.println(currentNode.getName());
            visitedNodes.put(currentNode.getName(), currentNode);            
        }
        
        int totalDistance = 0;
        LinkedList<String> nodePath = new LinkedList<>();
        Node endNode = nodeMap.get(dest);
        nodePath.addFirst(dest);
        currentNode = endNode;
        while(!currentNode.getName().equals(start)) {
            LinkedList<Pair<Node, Integer>> neighbours = currentNode.getNeighbours();
            Node smallestNode = null;
            int smallestDistance = Integer.MAX_VALUE;
            int neighbourDistance = 0;
            
            for(Pair<Node, Integer> pair : neighbours) {
                if(pair.a.getTotalDistance() + pair.b < smallestDistance) {
                    smallestNode = pair.a;
                    smallestDistance = pair.a.getTotalDistance() + pair.b;
                    neighbourDistance = pair.b;
                }
            }
            
            nodePath.addFirst(smallestNode.getName());
            currentNode = smallestNode;
            totalDistance += neighbourDistance;
        }
        
        Path path = new Path(totalDistance, nodePath);
        return path;
    }
}