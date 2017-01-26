package leetcode18.greedy;
/**
 * 406. Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {

    /*
    We first sort the people to make them stand from the highest to shortest. For people with same height, sort them according to the count of people before them from small to big.

    Then, we use the way similar to insert sorting to reorder the people. For a given person to insert, all the people already sorted are higher, so we just insert him in the "right" place to make the people before him as his "count" indicates. Since he is shorter than all the people in the sorted list, the "count" of the "existing" people does not be broken by the insertion.
    */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return people;
        }
        
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return b[0] - a[0];
            }
        });
        
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < people.length; i++) {
            list.add(people[i][1], people[i]);
        }        
        
        int[][] res= new int[people.length][people[0].length];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    // How insertion sort works
    void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int cur = i;
            while (cur > 0 && arr[cur] < arr[cur - 1]) {
                swap(arr, cur, cur - 1);
                cur--;
            }
        }
    }
    
    void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }

}
