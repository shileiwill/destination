package company.amazon;
/**
 * Compare two version numbers version1 and version2.

If version1 > version2 return 1,
If version1 < version2 return -1,
otherwise return 0.
You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 1.13 < 1.13.4
 */
public class CompareVersionNumbers {
    int compare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return s1.length() > s2.length() ? 1 : -1;
        }    
        
        if (s1.compareTo(s2) == 0) {
            return 0;
        }
        
        return s1.compareTo(s2) < 0 ? -1 : 1;  
    }
    
	public int compareVersion(String a, String b) {
	    String[] arr1 = a.split("\\.");
	    String[] arr2 = b.split("\\.");
	    
	    int i = 0, j = 0;
	    while (i < arr1.length && j < arr2.length) {
	        
	        if (arr1[i].compareTo(arr2[j]) != 0) {
	            String s1 = removeLeadingZeros(arr1[i]);
	            String s2 = removeLeadingZeros(arr2[j]);
	            
	            if (compare(s1, s2) != 0) {
	                return compare(s1, s2);
	            }
	        }
	        
	        i++;
	        j++;
	    }
	    
	    if (i == arr1.length && j == arr2.length) {
	        return 0;
	    }
	    
	    if (i < arr1.length) {
	        if (onlyZeroLeft(arr1, i)) {
	            return 0;
	        }
	        return 1;
	    } else {
	        if (onlyZeroLeft(arr2, j)) {
	            return 0;
	        }
	        return -1;
	    }
	}
	
	boolean onlyZeroLeft(String[] arr, int start) {
	    for (int i = start; i < arr.length; i++) {
	        if (!arr[i].equals("0")) {
	            return false;
	        }
	    }
	    return true;
	}
	
	String removeLeadingZeros(String s) {
	    int i = 0;
	    for (; i < s.length(); i++) {
	        if (s.charAt(i) != '0') {
	            break;
	        }
	    }
	    
	    return s.substring(i);
	}
}
