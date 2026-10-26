class Solution {
    int m , n;
    int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}};

   private long dfs(int i, int j, int[][] grid){
    long s = grid[i][j];
    grid[i][j] = 0;

    for(int dir[] : direction){
        int i_ = i + dir[0];
        int j_ = j + dir[1];

        if(i_ >= 0 && i_ < m && j_ >= 0 && j_ < n && grid[i_][j_] > 0){
            s += dfs(i_, j_, grid);
        }
    }
    return s;

   }

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] > 0){
                     long sum = dfs(i,j,grid);
                    if(sum % k == 0){
                        count++;
                    }
                }
            }
        }

        return count;
        
    }
}