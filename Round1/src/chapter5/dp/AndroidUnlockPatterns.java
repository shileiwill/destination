package chapter5.dp;
/**
 * 351. Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
Rules for a valid pattern:
Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.

 
Explanation:
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |

Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.
Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.
Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
Example:
Given m = 1, n = 1, return 9. 
 */
public class AndroidUnlockPatterns {
    int res = 0;
    public int numberOfPatterns(int m, int n) {
        boolean[] visited = new boolean[10];
        dfs(0, 0, m, n, visited);
        return res;
    }
    
    void dfs(int cur, int len, int m, int n, boolean[] visited) {
        if (len >= m && len <= n) {
            res++;
        }
        if (len > n) {
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            
            switch (cur) {
                case 1:
                    if ((i == 3 && !visited[2]) || (i == 7 && !visited[4]) || (i == 9 && !visited[5])) {
                        continue;
                    }
                    break;
                case 2:
                    if (i == 8 && !visited[5]) {
                        continue;
                    }
                    break;
                case 3:
                    if ((i == 1 && !visited[2]) || (i == 7 && !visited[5]) || (i == 9 && !visited[6])) {
                        continue;
                    }
                    break;
                case 4:
                    if (i == 6 && !visited[5]) {
                        continue;
                    }
                    break;
                case 5:
                    // Can go to anywhere without crossing any other element
                    break;
                case 6:
                    if (i == 4 && !visited[5]) {
                        continue;                        
                    }
                    break;
                case 7:
                    if ((i == 1 && !visited[4]) || (i == 3 && !visited[5]) || (i == 9 && !visited[8])) {
                        continue;                        
                    }
                    break;
                case 8:
                    if (i == 2 && !visited[5]) {
                        continue;
                    }
                    break;
                case 9:
                    if ((i == 1 && !visited[5]) || (i == 3 && !visited[6]) || (i == 7 && !visited[8])) {
                        continue;
                    }
                    break;
            }
            
            visited[i] = true;
            dfs(i, len + 1, m, n, visited);
            visited[i] = false;
        }
    }
    
    // A cleaner way by using jump 2-dimentional array
    public int numberOfPatterns2(int m, int n) {
        boolean[] visited = new boolean[10];
        
        int[][] jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2; // From a to b, need to go through c
        jumps[1][7] = jumps[7][1] = 4;
        jumps[1][9] = jumps[9][1] = 5;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][7] = jumps[7][3] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        
        dfs2(0, 0, m, n, visited, jumps);
        return res;
    }
    
    void dfs2(int cur, int len, int m, int n, boolean[] visited, int[][] jumps) {
        if (len >= m && len <= n) {
            res++;
        }
        if (len > n) {
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (jumps[cur][i] != 0 && !visited[jumps[cur][i]]) {
                continue;
            }
            
            visited[i] = true;
            dfs2(i, len + 1, m, n, visited, jumps);
            visited[i] = false;
        }
    }
    
    // With return
    public int numberOfPatterns3(int m, int n) {
        int res = 0;
        boolean[] visited = new boolean[10];
        
        int[][] jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2; // From a to b, need to go through c
        jumps[1][7] = jumps[7][1] = 4;
        jumps[1][9] = jumps[9][1] = 5;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][7] = jumps[7][3] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        
        for (int i = m; i <= n; i++) {
            res += dfs3(0, i, visited, jumps);
        }
        return res;
    }
    
    int dfs3(int cur, int remain, boolean[] visited, int[][] jumps) {
        if (remain < 0) {
            return 0;
        }
        if (remain == 0) {
            return 1;
        }

        int result = 0;
//        visited[cur] = true; This will pass, but i dont think this is correct
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (jumps[cur][i] != 0 && !visited[jumps[cur][i]]) {
                continue;
            }
            
            visited[i] = true;
            result += dfs3(i, remain - 1, visited, jumps);
            visited[i] = false;
        }
//        visited[cur] = false;
        return result;
    }
    
    // Even better. 1,3,7, 9 are systematic
    public int numberOfPatterns4(int m, int n) {
        int res = 0;
        boolean[] visited = new boolean[10];
        
        int[][] jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2; // From a to b, need to go through c
        jumps[1][7] = jumps[7][1] = 4;
        jumps[1][9] = jumps[9][1] = 5;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][7] = jumps[7][3] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        
        for (int i = m; i <= n; i++) {
            res += dfs4(1, i - 1, visited, jumps) * 4; // 1, 3, 7, 9 are symmetric
            res += dfs4(2, i - 1, visited, jumps) * 4; // 2, 4, 6, 8 are symmetric
            res += dfs4(5, i - 1, visited, jumps);
        }
        return res;
    }
    
    int dfs4(int cur, int remain, boolean[] visited, int[][] jumps) {
        if (remain < 0) {
            return 0;
        }
        if (remain == 0) {
            return 1;
        }

        visited[cur] = true;
        int result = 0;
        for (int i = 1; i <= 9; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (jumps[cur][i] != 0 && !visited[jumps[cur][i]]) {
                continue;
            }
            
            // visited[i] = true;
            result += dfs4(i, remain - 1, visited, jumps);
            // visited[i] = false;
        }
        visited[cur] = false;
        return result;
    }
}
