// https://github.com/kdn251/interviews/tree/03fdcb2703ce72dc0606748733d0c13f09d41d21/leetcode/dynamic-programming/EditDistance.java#L10-L40
public class TempClass {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for(int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int a = dp[i][j];
                    int b = dp[i][j + 1];
                    int c = dp[i + 1][j];
                    
                    dp[i + 1][j + 1] = Math.min(a, Math.min(b, c));
                    dp[i + 1][j + 1]++;
                }
            }
        }
        
        return dp[m][n];
    }

}