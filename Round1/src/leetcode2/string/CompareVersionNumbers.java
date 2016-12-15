package leetcode2.string;
/**
 * 165. Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {
    public int compareVersion2(String version1, String version2) {
        String[] arr1 = version1.split("\\."); // dot is meaningful in regular expression, so must translate
        String[] arr2 = version2.split("\\.");
        
        if (arr1.length == 0) {
            arr1 = new String[]{version1};
        }
        
        if (arr2.length == 0) {
            arr2 = new String[]{version2};
        }
        
        int cur1 = 0, cur2 = 0;
        while (cur1 < arr1.length && cur2 < arr2.length) {
            int val1 = Integer.valueOf(arr1[cur1]);
            int val2 = Integer.valueOf(arr2[cur2]);
            if (val1 == val2) {
                cur1++;
                cur2++;
                continue;
            } else if (val1 < val2) {
                return -1;
            } else {
                return 1;
            }
        }
        
        while (cur1 < arr1.length) {
            if (Integer.valueOf(arr1[cur1]) == 0) {
                cur1++;
                continue;
            }
            return 1;
        }
        
        while (cur2 < arr2.length) {
            if (Integer.valueOf(arr2[cur2]) == 0) {
                cur2++;
                continue;
            }
            return -1;
        }
        
        return 0;
    }
    
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\."); // dot is meaningful in regular expression, so must translate
        String[] arr2 = version2.split("\\.");
        
        int len = Math.max(arr1.length, arr2.length);
        
        for (int i = 0; i < len; i++) {
            // 默认用0补
            Integer val1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            Integer val2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;
            
            int comp = val1.compareTo(val2);
            
            if (comp == 0) {
                continue;
            }
            
            return comp;
        }
        
        return 0;
    }
}
