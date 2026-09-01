class Solution {

    class State {
        int row;
        int col;
        int energy;
        int mask;
        int moves;

        State(int row, int col, int energy, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energy = energy;
            this.mask = mask;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Assign every L a bit number
        int[][] litterBit = new int[m][n];

        for (int[] row : litterBit) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char cell = classroom[i].charAt(j);

                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (cell == 'L') {
                    litterBit[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;

        Queue<State> queue = new LinkedList<>();

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << litterCount];

        queue.add(new State(startRow, startCol, energy, 0, 0));
        visited[startRow][startCol][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            State curr = queue.poll();

            // All litter collected
            if (curr.mask == targetMask) {
                return curr.moves;
            }

            for (int d = 0; d < 4; d++) {

                int newRow = curr.row + dr[d];
                int newCol = curr.col + dc[d];

                // Boundary check
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                char nextCell = classroom[newRow].charAt(newCol);

                // Obstacle
                if (nextCell == 'X') {
                    continue;
                }

                // Need energy to move
                if (curr.energy == 0) {
                    continue;
                }

                int newEnergy = curr.energy - 1;
                int newMask = curr.mask;

                // Recharge
                if (nextCell == 'R') {
                    newEnergy = energy;
                }

                // Collect litter
                if (nextCell == 'L') {
                    int bit = litterBit[newRow][newCol];
                    newMask = newMask | (1 << bit);
                }

                if (!visited[newRow][newCol][newEnergy][newMask]) {

                    visited[newRow][newCol][newEnergy][newMask] = true;

                    queue.add(new State(
                        newRow,
                        newCol,
                        newEnergy,
                        newMask,
                        curr.moves + 1
                    ));
                }
            }
        }

        return -1;
    }
}