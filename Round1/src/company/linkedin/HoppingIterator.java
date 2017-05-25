package company.linkedin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
* Implement an iterator that hops specified number of times and then returns the next
* element after the hop. Note: the iterator always returns the first element as
* it is, and starts hopping only after the first element.
*
* Examples:
*
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 3, 5] in order when the hop value is 1.
*
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 4] in order when the hop value is 2.
*
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 5] in order when the hop value is 3.
* 
* Methods expected to be implemented:
* public class HoppingIterator<T> implements Iterator<T> {
*                 public HoppingIterator(Iterator<T> iterator, int numHops) {...}
*                 public boolean hasNext() {...}
*                 public T next() {...}
* }
*/
public class HoppingIterator<T> implements Iterator<T> {
	Iterator<T> iterator = null;
	int hops = -1;
	boolean firstTime = false;
	
	public HoppingIterator(Iterator<T> iterator, int hops) {
		this.iterator = iterator;
		this.hops = hops;
		this.firstTime = true;
	}

	public boolean hasNext() {
		if (firstTime) {
			firstTime = false;
			return iterator.hasNext();
		} else {
			int count = 0;
			while (count < hops && iterator.hasNext()) {
				iterator.next();
				count++;
			}
			
			return iterator.hasNext();
		}
	}

	public T next() {
		return iterator.next();
	}
	
	public static void main(String[] args) {
		Integer[] arr = {1, 2, 3, 4, 5};
		List<Integer> list = Arrays.asList(arr);
		
		HoppingIterator<Integer> hi = new HoppingIterator<Integer>(list.iterator(), 3);
		
		while (hi.hasNext()) {
			System.out.println(hi.next());
		}
	}
}
