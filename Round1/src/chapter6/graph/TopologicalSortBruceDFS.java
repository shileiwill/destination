package chapter6.graph;
/**
 * Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

 Notice

You can assume that there is at least one topological order in the graph.
 * @author Lei
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalSortBruceDFS {
	
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> res = new  ArrayList<DirectedGraphNode>();
        Stack<DirectedGraphNode> stack = new Stack<DirectedGraphNode>();
        Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
        
        for (DirectedGraphNode node : graph) {
            if (!visited.contains(node)) {
                visited.add(node);
                dfs(node, stack, visited);
            }            
        }
        
        while (!stack.isEmpty()) {
            DirectedGraphNode node = stack.pop();
            res.add(node);
        }
        
        return res;
    }
    
    void dfs(DirectedGraphNode node, Stack<DirectedGraphNode> stack, Set<DirectedGraphNode> visited) {
        for (DirectedGraphNode nei : node.neighbors) {
            if (!visited.contains(nei)) {
                visited.add(nei);
                dfs(nei, stack, visited);
            }
        }
        
        stack.push(node);
    }
    
    //Another version without using Stack
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> res = new  ArrayList<DirectedGraphNode>();
        Set<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();
        
        for (DirectedGraphNode node : graph) {
            if (!visited.contains(node)) {
                visited.add(node);
                dfs2(node, res, visited);
            }            
        }
        
        Collections.reverse(res);
        
        return res;
    }
    
    void dfs2(DirectedGraphNode node, ArrayList<DirectedGraphNode> res, Set<DirectedGraphNode> visited) {
        for (DirectedGraphNode nei : node.neighbors) {
            if (!visited.contains(nei)) {
                visited.add(nei);
                dfs2(nei, res, visited);
            }
        }
        
        res.add(node);
    }
    
	// 这其实是DFS, 看stack.push的位置。 stack->DFS, queue->BFS 
	// Stack here is also a storage. It is useful because we want incoming == 0 comes first
	public Stack<V> topSortDFS(Set<V> graph) {
		Stack<V> stack = new Stack<V>();
		Set<V> visited = new HashSet<V>();
		
		for (V node : graph) {
			if (!visited.contains(node)) {
				topologicalSort(node, stack, visited);
			}
		}
		
		return stack;
	}
	
	void topologicalSort(V node, Stack<V> stack, Set<V> visited) {
		visited.add(node);
		
		for (V out : node.outgoings) {
			if (!visited.contains(out)) {
				topologicalSort(out, stack, visited);
			}
		}
		// 层次结构最深的，最底层的先进入stack
		stack.push(node);
	}
}

class V {
	Set<V> incomings;
	Set<V> outgoings;
}