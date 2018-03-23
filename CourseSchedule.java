class CourseSchedule {
    /*
        the problem can be solved by checking
        each edges in the list, whether cycle
        exists in the graph
            1. yes, cannot finish
            2. no, can finish
    */
    class Graph {
        private int V; // number of vertices
        private ArrayList<Integer>[] adj;
        private int[] in;

        public Graph(int v) {
            this.V = v;
            this.adj = new ArrayList[v];
            for (int i = 0; i < v; i++)
                adj[i] = new ArrayList<Integer>();
        }
        private void addEdge(int v, int w) {
            adj[v].add(w); // directed graph
        }
    }

    private boolean isCycle(Graph g) {
        int V = g.V;
        ArrayList[] adj = g.adj;
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (visited[i] == false)
                if (isCycleUtil(i, visited, recStack, adj) == true)
                    return true;
        }
        return false;
    }

    private boolean isCycleUtil(int v, boolean[] visited, boolean[] recStack, ArrayList<Integer>[] adj) {
        visited[v] = true; // mark as visited
        recStack[v] = true; // add into recursion stack

        // visit all unvisited neighbours
        // if a neighbour is visited
        // or in the recursion stack, then cycle found
        for (int neighbour : adj[v]) {
            if (visited[neighbour] == false) {
                if (isCycleUtil(neighbour, visited, recStack, adj) == true)
                    return true; // cycle found
            }
            else if (recStack[neighbour] == true) // neighbour is v's ancestor, because recStack already exists neighbour
                return true; // cycle found
        }
        recStack[v] = false; // recover recursion stack
        return false; // no cycle found
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // construct a graph from the input array
        Graph g = new Graph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            g.addEdge(prerequisites[i][0], prerequisites[i][1]);

        return !isCycle(g); // can only finish when cycle not exists
    }

    /*
        Follow-up:
            return the list of ordering of the course selection
    */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            g.addEdge(prerequisites[i][1], prerequisites[i][0]);

        return topologicalSort(g);
    }

    private int[] topologicalSort(Graph g) {
        int V = g.V;
        ArrayList[] adj = g.adj;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int v = 0; v < V; v++) {
            if (visited[v] == false)
                if (topologicalSortUtil(adj, v, visited,recStack, stack) == true) // has cycle
                    return new int[]{}; // return empty array
        }

        int[] res = new int[V];
        int i = 0;
        while (!stack.empty()) {
            res[i] = stack.pop();
            i++;
        }
        return res;
    }

    private boolean topologicalSortUtil(ArrayList<Integer>[] adj, int v, boolean[] visited, boolean[] recStack, Stack stack) {
        visited[v] = true; // mark as visited
        recStack[v] = true;
        for (int neighbour : adj[v]) {
            if (visited[neighbour] == false) {
                if (topologicalSortUtil(adj, neighbour, visited, recStack, stack) == true)
                    return true;
            }
            else if (recStack[neighbour] == true)
                return true;
        }

        stack.push(v); // store ordering topological sort

        recStack[v] = false;
        return false;
    }

}
