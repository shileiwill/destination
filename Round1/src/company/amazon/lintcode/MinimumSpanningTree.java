package company.amazon.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of Connections, which is the Connection class (the city name at
 * both ends of the edge and a cost between them), find some edges, connect all
 * the cities and spend the least amount. Return the connects if can connect all
 * the cities, otherwise return empty list.
 * 
 * Notice
 * 
 * Return the connections sorted by the cost, or sorted city1 name if their cost
 * is same, or sorted city2 if their city1 name is also same.
 * 
 * Have you met this question in a real interview? Yes Example Gievn the
 * connections = ["Acity","Bcity",1], ["Acity","Ccity",2], ["Bcity","Ccity",3]
 * 
 * Return ["Acity","Bcity",1], ["Acity","Ccity",2]
 */
public class MinimumSpanningTree {

	class Connection {
		public String city1, city2;
		public int cost;

		public Connection(String city1, String city2, int cost) {
			this.city1 = city1;
			this.city2 = city2;
			this.cost = cost;
		}
	}

	// Map could be used as parent array
	public List<Connection> lowestCostBetter(List<Connection> connections) {
		Comparator<Connection> com = new Comparator<Connection>() {
			public int compare(Connection c1, Connection c2) {
				if (c1.cost != c2.cost) {
					return c1.cost - c2.cost;
				}
				if (!c1.city1.equals(c2.city1)) {
					return c1.city1.compareTo(c2.city1);
				}

				return c1.city2.compareTo(c2.city2);
			}
		};

		Collections.sort(connections, com);

		Map<String, String> cityToRoot = new HashMap<String, String>();
		List<Connection> res = new ArrayList<Connection>();

		for (Connection con : connections) {
			// By default, root is itself
			if (!cityToRoot.containsKey(con.city1)) {
				cityToRoot.put(con.city1, con.city1);
			}
			if (!cityToRoot.containsKey(con.city2)) {
				cityToRoot.put(con.city2, con.city2);
			}

			String root1 = findRoot(con.city1, cityToRoot); // Find
			String root2 = findRoot(con.city2, cityToRoot);

			if (!root1.equals(root2)) { // Not joint yet
				cityToRoot.put(root1, root2); // Union
				res.add(con); // Connection is already sorted by cost
			}
		}

		return res;
	}

	String findRoot(String city, Map<String, String> cityToRoot) {
		while (!cityToRoot.get(city).equals(city)) {
			city = cityToRoot.get(city);
		}
		return city;
	}

	/**
	 * @param connections
	 *            given a list of connections include two cities and cost
	 * @return a list of connections from results
	 */
	public List<Connection> lowestCost(List<Connection> connections) {
		Comparator<Connection> com = new Comparator<Connection>() {
			public int compare(Connection c1, Connection c2) {
				if (c1.cost != c2.cost) {
					return c1.cost - c2.cost;
				}
				if (!c1.city1.equals(c2.city1)) {
					return c1.city1.compareTo(c2.city1);
				}

				return c1.city2.compareTo(c2.city2);
			}
		};

		Collections.sort(connections, com);
		int n = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();

		// Give each city a number, use this number to union find
		for (Connection con : connections) {
			if (!map.containsKey(con.city1)) {
				map.put(con.city1, n++);
			}
			if (!map.containsKey(con.city2)) {
				map.put(con.city2, n++);
			}
		}

		UnionFind uf = new UnionFind(n);
		List<Connection> res = new ArrayList<Connection>();

		for (Connection con : connections) {
			int city1 = map.get(con.city1);
			int city2 = map.get(con.city2);

			int root1 = uf.find(city1);
			int root2 = uf.find(city2);

			if (root1 != root2) { // Not joint yet
				uf.union(root1, root2);
				res.add(con); // Connection is already sorted by cost
			}
		}

		if (res.size() != n - 1) {
			return new ArrayList<Connection>();
		}
		return res;
	}

	class UnionFind {
		int[] parent = null;

		UnionFind(int n) {
			parent = new int[n];
			Arrays.fill(parent, -1);
		}

		void union(int id1, int id2) {
			int root1 = find(id1);
			int root2 = find(id2);
			if (root1 != root2) {
				parent[root1] = root2;
			}
		}

		int find(int id) {
			while (parent[id] != -1) {
				id = parent[id];
			}
			return id;
		}
	}
}