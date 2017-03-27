package company.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
// From Bruce's Interview
public class MergeKSortedArray {

	public static void main(String[] args) {
		MergeKSortedArray merge = new MergeKSortedArray();
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> machine1 = new ArrayList<Integer>();
		machine1.add(1);
		machine1.add(4);
		machine1.add(7);
		machine1.add(23);
		machine1.add(67);
		
		List<Integer> machine2 = new ArrayList<Integer>();
		machine2.add(3);
		machine2.add(4);
		machine2.add(17);
		machine2.add(21);
		machine2.add(28);
		machine2.add(34);
		machine2.add(43);
		
		List<Integer> machine3 = new ArrayList<Integer>();
		machine3.add(11);
		machine3.add(14);
		machine3.add(17);
		machine3.add(21);
		machine3.add(67);
		machine3.add(100);
		machine3.add(421);
		machine3.add(427);
		machine3.add(428);
		machine3.add(567);
		
		list.add(machine1);
		list.add(machine2);
		list.add(machine3);
		
		List<Integer> res = merge.mergeKSortedArray(list);
		for (int val : res) {
			System.out.print(val + "==");
		}
	}

	class Wrapper {
		int val;
		int machineIndex, indexOnMachine;
		
		Wrapper (int val, int machineIndex, int indexOnMachine) {
			this.val = val;
			this.machineIndex = machineIndex;
			this.indexOnMachine = indexOnMachine;
		}
	}
	
	List<Integer> mergeKSortedArray(List<List<Integer>> list) {
		List<Integer> res = new ArrayList<Integer>();
		
		// min heap
		PriorityQueue<Wrapper> heap = new PriorityQueue<Wrapper>(list.size(), new Comparator<Wrapper>(){
			public int compare(Wrapper w1, Wrapper w2) {
				return w1.val - w2.val;
			}
		});
		
		// Add the head of each machine to heap
		for (int i = 0; i < list.size(); i++) {
			List<Integer> file = list.get(i);
			Wrapper w = new Wrapper(file.get(0), i, 0);
			heap.offer(w);
		}
		
		while (!heap.isEmpty()) {
			Wrapper curMin = heap.poll();
			res.add(curMin.val);
			
			List<Integer> machine = list.get(curMin.machineIndex);
			if (curMin.indexOnMachine < machine.size() - 1) { // If it is already the last element in one machine, this machine is done
				int nextIndexOnMachine = curMin.indexOnMachine + 1;
				Wrapper nextWrapper = new Wrapper(machine.get(nextIndexOnMachine), curMin.machineIndex, nextIndexOnMachine);
				heap.offer(nextWrapper);
			}
		}
		
		return res;
	}
}
