package company.facebook;
/**
 * 第三轮 given list of integers， give you a number k，rearrange the array to be like this： 
 * all numbers smaller than k should be on the left， those equals should be in the middle，and larger numbers on the right。
 * followup ：if given k category instead of 3（bucket sort?) 
 * 
 * 你可以像sort color一样，分两次partition, < K, >= K, then == k, > K
 */
public class SortColorPartitionArray {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 5, 2, 2, 8};
        partArray(arr, 5);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    // 2 Pass, like sort color
    public static void partArray2(int[] arr, int k) {
    	int left = 0;
    	int right = 0;
    	
    	while (left < arr.length && right < arr.length) {
    		while (left < arr.length && arr[left] < k) {
    			left++;
    		}
    		
    		right = Math.max(right, left + 1);
    		while (right < arr.length && arr[right] >= k) {
    			right++;
    		}
    		
    		if (right < arr.length) {
    			swap(arr, left, right);
    		}
    		
    		left++;
    	}
    	
    	right = left;
    	while (left < arr.length && right < arr.length) {
    		while (left < arr.length && arr[left] == k) {
    			left++;
    		}
    		
    		right = Math.max(right, left + 1);
    		while (right < arr.length && arr[right] > k) {
    			right++;
    		}
    		
    		if (right < arr.length) {
    			swap(arr, left, right);
    		}
    		
    		left++;
    	}
    }
    
    public static void partArray(int[] arr, int k) {
    	int small = 0;
    	int equal = 0;
    	int large = arr.length - 1;
    	
    	while (equal <= large) {
    		if (arr[equal] < k) {
    			swap(arr, small, equal);
    			small++;
    			equal++;
    		} else if (arr[equal] > k) {
    			swap(arr, equal, large);
    			large--;
    		} else {
    			equal++;
    		}
    	}
    }
    
    private static void swap(int[] arr, int j, int index) {
        int tmp = arr[j];
        arr[j] = arr[index];
        arr[index] = tmp;
    }
}
