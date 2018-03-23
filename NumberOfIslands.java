class NumberOfIslands {
    /*
        11000
        11000
        00100
        00011

        islands = 3
    */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue; // pre-validate
                check(grid, m, n, i, j);
                islands++;
            }
        }
        return islands;
    }

    // here we use the helper funtion in a similar problem: Surrounded Regions
    private void check(char[][] grid, int m, int n, int i, int j) {
        if (grid[i][j] == '1') {
            grid[i][j] = '0';
            if (i > 0) check(grid, m, n, i - 1, j);
            if (j > 0) check(grid, m, n, i, j - 1);
            if (i + 1 < m) check(grid, m, n, i + 1, j);
            if (j + 1 < n) check(grid, m, n, i, j + 1);
        }
    }

}
