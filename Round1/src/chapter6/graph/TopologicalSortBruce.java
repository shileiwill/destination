package chapter6.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalSortBruce {
	
	// 这其实是DFS, 看stack.push的位置。 stack->DFS, queue->BFS
	public Stack<V> topSortBFS(Set<V> graph) {
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
		
		stack.push(node);
	}
}

class V {
	Set<V> incomings;
	Set<V> outgoings;
}