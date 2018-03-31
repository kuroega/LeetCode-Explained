class Solution {
	/*
		Adjcency List Solution

			check 2 conditions:
				1. has n - 1 edges && no cycle
				2. has n - 1 edges && all connected
	*/
    public boolean validTree(int n, int[][] edges) {
		List<List<Integer>> adjList = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			adjList.add(i, new ArrayList<Integer>());
		}
		// construct the adjcency list
		for (int[] e : edges) {
			adjList.get(e[0]).add(e[1]);
			adjList.get(e[1]).add(e[0]);
		}

		boolean[] visited = new boolean[n];

		// check cycles exist 
		if (hasCycle(adjList, 0, visited, -1))
			return false;

		// check all connected
		for (int i = 0; i < n; i++) {
			if (!visited[i])
				return false;
		}

		return true;
	}
	private boolean hasCycle(List<List<Integer>> adjList, int i, boolean[] visited, int parent) {
        visited[i] = true;
	
        for (int u : adjList.get(i)) {
			if ((visited[u] && u != parent) || (!visited[u] && hasCycle(adjList, u, visited, i)))
				return true;
		}
		return false;
	}

	/*
		Union find solution
	*/
	public boolean validTree(int n, int[][] edges) {
		int[] nums = new int[n];
		Arrays.fill(nums, -1);
		for (int e : edges) {
			int x = find(nums, x);
			int y = find(nums, y);

			if (x == y) return false;

			nums[x] = y; // 
		}
		return edges.length == n - 1;
	}
	private int find(int[] nums, int x) {
		return nums[x] == -1 ? x : find(nums, nums[x]);
	}
}