package company.uber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 设计一个函数使得local和server的数据同步， 其中delete, persist函数已经给出
 * server: 1 , 2 , 4 
 * local : 1, 3 
 * 那么就要 delete 3, persist 2, 4
 */
public class ListSyncServerClient {

	public static void main(String[] args) {
		Integer[] arr1 = {1, 2, 4};
		List<Integer> server = Arrays.asList(arr1);
		
		Integer[] arr2 = {1, 3};
		List<Integer> client = Arrays.asList(arr2);
		
		sync(server, client);
	}

	static void sync(List<Integer> server, List<Integer> client) {
		Collections.sort(server);
		Collections.sort(client);
		
		int pos1 = 0, pos2 = 0;
		while (pos1 < server.size() && pos2 < client.size()) {
			int val1 = server.get(pos1);
			int val2 = client.get(pos2);
			
			if (val1 == val2) {
				pos1++;
				pos2++;
				continue;
			}
			
			if (val1 < val2) { // Exists in server, but not in client
				System.out.println("Persist " + val1);
				pos1++;
			} else {
				System.out.println("Delete " + val2);
				pos2++;
			}
		}
		
		while (pos1 < server.size()) {
			int val1 = server.get(pos1);
			System.out.println("Persist " + val1);
			pos1++;
		}
		
		while (pos2 < client.size()) {
			int val2 = client.get(pos2);
			System.out.println("Delete " + val2);
			pos2++;
		}
	}
}
