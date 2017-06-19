package company.facebook;

import java.util.HashSet;
import java.util.Set;
/**
 * 277. Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */
public class FindTheCelebrity {
	// Dude, set is useless! Not used at all.
    public int findCelebrity(int n) {
        Set<String> hash = new HashSet<String>();
        for (int i = 0; i < n; i++) { // Each person as potential celebrity
            int j = 0;
            for (; j < n; j++) { // Ask every one else
                if (i == j) { // Same person, pass
                    continue;
                }
                
                String key = i + ":" + j;
                if (hash.contains(key)) { // i knows j already, i cant be celebrity any more
                    break;
                }
                
                if (knows(i, j)) {
                    hash.add(key); // Just figured, i knows j, i cant be celebrity any more
                    break;
                }
                
                if (!knows(j, i)) { // j doesnt know i, i cant be celebrity any more
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int findCelebrity4(int n) {
        for (int i = 0; i < n; i++) { // Each person as potential celebrity
            int j = 0;
            for (; j < n; j++) { // Ask every one else
                if (i == j) { // Same person, pass
                    continue;
                }
                
                if (knows(i, j)) {
                    break;
                }
                
                if (!knows(j, i)) { // j doesnt know i, i cant be celebrity any more
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int findCelebrity2(int n) {
        for (int i = 0; i < n; i++) { // Each person as potential celebrity
            System.out.println("Candidate: " + i);
            // Ask every one else
            int j = 0;
            for (j = 0; j < n; j++) {
                if (i == j) { // Same person, pass
                    continue;
                }
                System.out.println(i + "---" + j);
                System.out.println(knows(j, i) + "---" + knows(i, j));
                if (!knows(j, i) || knows(i, j)) {
                    break;
                }
            }
            if (j == n) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * The first loop is to find the candidate as the author explains. 
     * In detail, suppose the candidate after the first for loop is person k, it means 0 to k-1 cannot be the celebrity, 
     * because they know a previous or current candidate. Also, since k knows no one between k+1 and n-1, k+1 to n-1 can not be the celebrity either. 
     * Therefore, k is the only possible celebrity, if there exists one.
	   The remaining job is to check if k indeed does not know any other persons and all other persons know k.
	   To this point, I actually realize that we can further shrink the calling of knows method. 
	   For example, we don't need to check if k knows k+1 to n-1 in the second loop, because the first loop has already done that.
     */
    public int findCelebrity3(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i)) {
                celebrity = i; // Find potential candidate
            }
        }
        // Verify
        for (int i = 0; i < n; i++) {
            if (i < celebrity && (!knows(i, celebrity) || knows(celebrity, i))) {
                return -1;
            }
            if (i > celebrity && !knows(i, celebrity)) {
                return -1;
            }
        }
        
        return celebrity;
    }
}
