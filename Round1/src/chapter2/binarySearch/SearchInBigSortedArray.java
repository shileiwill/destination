package chapter2.binarySearch;
/**
 * Search an sorted array which is too big to get length directly, can access kth number only by reader.get(k)+

Example

Given [1, 3, 6, 9, 21, ...], and target = 3, return 1.
Given [1, 3, 6, 9, 21, ...], and target = 4, return -1.

Do a reverse binary search looking for rough ending position
With that ending position, do binary search look for target position

 */
public class SearchInBigSortedArray {

	public static void main(String[] args) {

	}

	public int searchBigSortedArray(ArrayReader reader, int target) {
	    if (reader.get(0) == -1)
	        return -1;

	    int start = 0;
	    int end = 0;

	    // find an ROUGH end to start with. This is the most important part
	    while(reader.get(end) != -1 && reader.get(end)<target) {
	        end = end * 2 + 1;
	    }

	    // binary search from start to end, search for target
	    while(start + 1 < end) {
	        int mid = (start+end)/2;
	        /** REMEMBER, WHEN target == mid
	         * LET end = mid INSTEAD OF RETURNING mid DIRECTLY
	         * SINCE WE ARE LOOKING FOR FIRST MATCHING POSITION
	         * NEED TO CONSIDER MULTIPLE TARGET VALUE IN INPUT
	         */
	        if (target > reader.get(mid)) {
	            start = mid;
	        }else{
	            end = mid;
	        }
	    }


	    // figure out its start, end or not at all
	    // ascending order, so start with end
	    if (target == reader.get(end))
	        return end;

	    if (target == reader.get(start))
	        return start;

	    return -1;
	}
}
