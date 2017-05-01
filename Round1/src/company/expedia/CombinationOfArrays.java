package company.expedia;

import java.util.*;

class CombinationOfArrays
{
    public static void main (String[] args) throws java.lang.Exception
    {
    	CombinationOfArrays coa = new CombinationOfArrays();
    	
        ArrayList<List<Integer>> arrays = new ArrayList<List<Integer>>();
        
        Integer[] arr1 = new Integer[] { 1, 2, 3 };
        Integer[] arr2 = new Integer[] { 4, 5 };
        Integer[] arr3 = new Integer[] { 6, 7 };
        
        arrays.add(Arrays.asList(arr1));
        arrays.add(Arrays.asList(arr2));
        arrays.add(Arrays.asList(arr3));

        List<List<Integer>> res = coa.getCombination(0, arrays);
        
        for (List<Integer> list : res) {
        	for (int val : list) {
        		System.out.print(val + "==");
        	}
        	System.out.println();
        }
    }

    static ArrayList<Integer> combination(ArrayList<int[]> arrays)
    {
        if (arrays.size() == 0)
            return null;

        ArrayList<Integer> result = new ArrayList<Integer>();
        combination_sub(arrays, 0, result);
        return result;
    }
    
    static void combination_sub(ArrayList<int[]> arrays, int idx, ArrayList<Integer> result)
    {
        if (arrays.size() == idx)
        {
            result.add(1);
            return;
        }

        int[] ar = arrays.get(idx);

        ArrayList<Integer> sub_result = new ArrayList<Integer>();
        combination_sub(arrays, idx + 1, sub_result);

        for (int i : ar)
        {
            for (int sub_i : sub_result)
            {
                result.add(i * sub_i);
            }
        }
    }
    
    private List<List<Integer>> getCombination(int currentIndex, List<List<Integer>> containers) {
        if (currentIndex == containers.size()) {
            // Skip the items for the last container
            List<List<Integer>> combinations = new ArrayList<List<Integer>>();
            combinations.add(new ArrayList<Integer>());
            return combinations;
        }
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        // 先取出当前的来，一会儿得一个一个的添加，组成不同方案
        List<Integer> curList = containers.get(currentIndex);

        // Get combination from next index
        List<List<Integer>> suffixList = getCombination(currentIndex + 1, containers);
        
        for (int i = 0; i < curList.size(); i++) {
        	Integer item = curList.get(i);
        	
        	// 把当前的Item添加到所有的可能的组合中
            if (suffixList != null) {
                for (List<Integer> suffix : suffixList) {
                    List<Integer> nextCombination = new ArrayList<Integer>(); // 组建一个新的combination
                    nextCombination.add(item); // Put one
                    nextCombination.addAll(suffix); // Put all
                    res.add(nextCombination);
                }
            }
        }
        
        return res;
    }
}