package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
 */
public class ETAIntersection {

    public static class Road {
        String name;
        int cost;
        String end;
        public Road( String name, int cost, String end ) {
            this.name = name;
            this.cost = cost;
            this.end = end;
        }
    }

    public String computeShortestRoad(Map<String, List<Road>> roadMap, String start, String end) {
        Map<String, Map<String, Road>> map = new HashMap<>();
        for( String key : roadMap.keySet() ) {
            if ( !map.containsKey( key ) ) {
                map.put( key, new HashMap<>() );
            }
            List<Road> roads = roadMap.get( key );
            Map<String, Road> wantedMap = map.get( key );
            for ( Road road : roads ) {
                if ( !wantedMap.containsKey( road.end ) ) {
                    wantedMap.put( road.end, road );
                }
                if ( road.cost < wantedMap.get( road.end ).cost ) {
                    wantedMap.put( road.end, road );
                }
            }
        }

        return dijkstra(map, start, end);
    }

    public static class Node {
        String name;
        int cost;
        public int getCost() {
            return cost;
        }
        public Node(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }
    private String dijkstra( Map<String, Map<String, Road>> map, String start, String end ) {
        String previousNode = "";
        String res = "";
        Map<String, Integer> hash = new HashMap<>();
		PriorityQueue<Node> heap = new PriorityQueue<>( Comparator.comparingInt( Node::getCost ) );
        hash.put( start, 0 );
        heap.offer( new Node( "A", 0 ) );

        while ( !heap.isEmpty() ) {
            Node cur = heap.poll();
            if (!previousNode.isEmpty()) {
                res += map.get( previousNode ).get( cur.name ).name + ", ";
            }
            if ( cur.name.equals( end ) ) {
                break;
            }
            previousNode = cur.name;
            for( String next: map.get( cur.name ).keySet() ) {
                if ( !hash.containsKey( next ) || hash.get( next ) < hash.get( cur.name ) + map.get( cur.name ).get( next ).cost ) {
                    hash.put( next, hash.get( cur.name ) + map.get( cur.name ).get( next ).cost );
                    heap.add( new Node(next, hash.get( cur.name ) + map.get( cur.name ).get( next ).cost) );
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
    	ETAIntersection uberIntersection = new ETAIntersection();

        Map<String, List<Road>> map = new HashMap<>();
        map.put( "A", Arrays.asList(  new Road("roda1", 3, "B"), new Road( "road2", 2, "B" ), new Road( "road3", 1, "B" ) ) );
        map.put( "B", Arrays.asList( new Road( "road4", 4, "C" ) ) );
        map.put( "C", new ArrayList<>() );
        System.out.println( uberIntersection.computeShortestRoad( map, "A", "C" ) );
    }
}