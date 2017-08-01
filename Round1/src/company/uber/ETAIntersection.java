package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给一个json input，列了一个list的intersection。每个intersection有一个list的road。每条road有通向的intersection，cost和名字。
 * 求给一个起始intersection和一个终点intersection，找到最短的路径并且打印出来。
ex.

intersection A: [ 
	{name: "road1", cost: 3, destination: "intersection B"}, 
	{name: "road2", cost: 2, destination: "intersection B"}, 
	{name: "road3", cost: 1, destination: "intersection B"} 
]
intersection B: [ 
	{name: "road4", cost: 4, destination: "intersection C"} 
]
intersection C: []

最短路径从A到C：road3 -> road4

面试官先问如何把这个json转化成数据结构。楼主选择先建一个intersection class和一个road class，然后再用一个HashMap建一个adjacent list。算法是用dfs去搜索

参考DijkstraShortestPath.java
 */
public class ETAIntersection {

    static class Road {
        String name;
        int cost;
        String end;
        public Road( String name, int cost, String end ) {
            this.name = name;
            this.cost = cost;
            this.end = end;
        }
    }
    
    List<Node> nodes = new ArrayList<Node>();
    List<Edge> edges = new ArrayList<Edge>();
    
    Map<Node, Integer> distance = new HashMap<Node, Integer>();
    Map<Node, Node> predecessor = new HashMap<Node, Node>();
    
    Set<Node> settledNodes = new HashSet<Node>();
    PriorityQueue<Node> unsettledNodes = new PriorityQueue<Node>(new Comparator<Node>(){
    	public int compare(Node node1, Node node2) {
    		return distance.get(node1) - distance.get(node2);
    	}
    });

    // Not really required in Dijkstra
    Map<String, Node> map = new HashMap<String, Node>(); // From string name to Node
    Map<String, List<Road>> roadMap = null;
    
    public List<Node> computeShortestRoad(Map<String, List<Road>> roadMap, String start, String end) {
    	this.roadMap = roadMap;
    	
    	// Prepare nodes and edges
        for (Map.Entry<String, List<Road>> entry : roadMap.entrySet()) {
        	String from = entry.getKey();
        	List<Road> roads = entry.getValue();
        	
        	Node fromNode = new Node(from);
        	nodes.add(fromNode);
        	map.put(from, fromNode);
        	distance.put(fromNode, Integer.MAX_VALUE);
        	
        	for (Road road : roads) {
        		Node toNode = new Node(road.end);
        		Edge edge = new Edge(road.name, road.cost, fromNode, toNode);
        		
        		nodes.add(toNode);
        		edges.add(edge);
        		map.put(road.end, toNode);
        		distance.put(toNode, Integer.MAX_VALUE);
        	}
        }

        executeDijkstra(map.get(start), map.get(end));
        
        List<Node> res = transform(map.get(start), map.get(end));
        
        for (Node node : res) {
        	System.out.print(node.name + " -- ");
        }
		return res;
    }

    private List<Node> transform(Node start, Node end) {
    	LinkedList<Node> list = new LinkedList<Node>();
    	
    	while (end != start) {
    		list.addFirst(end);
    		end = predecessor.get(end);
    	}
    	
    	list.addFirst(start);
    	
		return list;
	}

	private void executeDijkstra(Node start, Node end) {
        distance.put(start, 0);
        unsettledNodes.offer(start);
        
        while (!unsettledNodes.isEmpty()) {
        	Node now = unsettledNodes.poll();
        	settledNodes.add(now);
        	
        	if (now == end) {
        		return; // 现在只需要找两个固定的点，所以找到了end就直接return就行了
        	}
        	
        	updateNeighbors(now);
        }
    }

    private void updateNeighbors(Node node) {
		List<Node> unsettledNeighbors = findUnsettledNeighbors(node);
		
		for (Node nei : unsettledNeighbors) {
			int dist1 = distance.get(nei);
			int dist2 = distance.get(node) + getDistanceBetween(node, nei);
			
			if (dist2 < dist1) {
				distance.put(nei, dist2);
				unsettledNodes.add(nei);
				predecessor.put(nei, node);
			}
		}
	}

	private List<Node> findUnsettledNeighbors(Node node) {
		List<Node> list = new ArrayList<Node>();
		
		for (Edge edge : edges) {
			if (edge.start.equals(node) && !settledNodes.contains(edge.end)) {
				list.add(edge.end);
			}
		}
		return list;
	}

	private Integer getDistanceBetween(Node node1, Node node2) {
		// Option 2, find by roadMap<>
		// Option 1, search all edges
		for (Edge edge : edges) {
			if (edge.start.equals(node1) && edge.end.equals(node2)) {
				return edge.cost;
			}
		}
		
		throw new RuntimeException("How come these 2 nodes are not connected?");
	}

	static class Node {
        String name;
        
        public Node(String name) {
            this.name = name;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			Node other = (Node) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
    }
    
    static class Edge {
        String name;
        int cost;
        Node start;
        Node end;
        
        public Edge(String name, int cost, Node start, Node end) {
            this.name = name;
            this.cost = cost;
            this.start = start;
            this.end = end;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
    }
    
    public static void main(String[] args) {
    	ETAIntersection uberIntersection = new ETAIntersection();

        Map<String, List<Road>> map = new HashMap<>();
        map.put( "A", Arrays.asList(  new Road("road1", 3, "B"), new Road( "road2", 2, "B" ), new Road( "road3", 1, "B" ) ) );
        map.put( "B", Arrays.asList( new Road( "road4", 4, "C" ) ) );
        map.put( "C", new ArrayList<>() );
        System.out.println( uberIntersection.computeShortestRoad( map, "A", "C" ) );
    }
}