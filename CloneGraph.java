public class CloneGraph {
    /*
        DFS solution:
            When we clone a node, it may contains a list of neighbor nodes has
            the node itself in there. Therefore, it will waste a lot time to
            clone duplicate nodes. Then the idea to avoid it is simply maintaining
            a HashMap of the value of visited nodes and an existed clone.
    */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }
    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null) {
            return null;
        } else if (map.containsKey(node.label)) {
            return map.get(node.label);
        } else {
            UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
            map.put(node.label, clone); // map add a (nodeValue, cloneNode) pair for later faster access.
            for (int i = 0; i < node.neighbors.size(); i++) {
                clone.neighbors.add(dfs(node.neighbors.get(i), map));
            }
            return clone;
        }
    }
}
