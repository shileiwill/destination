package Facebook;

/**
 * Created by weixwu on 7/4/2017.
 */
public class SortKColors {
    public void sortKColors(int[] nums, int k) {
        int l = 0, r = nums.length - 1, min = 1, max = k;
        while (min < max){
            for(int i = l; i <= r; i++) {
                while (nums[i] == max && i < r) {
                    swap(nums, i, r--);
                }
                if (nums[i] == min) {
                    swap(nums, i, l++);
                }
            }
            min++;
            max--;
        }
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String args[]){
        SortKColors sc = new SortKColors();
        int[] nums = new int[]{2,3,4,1,2,3,4,5,7,2,5,2,6,2,7,5,4,2,3,4,5};
        sc.sortKColors(nums, 7);
        for(int num : nums){
            System.out.print(num + " ");
        }
    }
}
