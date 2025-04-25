// https://leetcode.com/problems/clone-graph/description/

class Solution {
    public Node cloneGraph(Node node) {
        HashMap<Integer, Node> map = new HashMap<>();
        return clone(node, map);
    }

    private Node clone(Node root, HashMap<Integer, Node> map) {
        if (root == null) {
            return null;
        }
        if (map.containsKey(root.val)) {
            return map.get(root.val);
        }

        Node newNode = new Node(root.val);
        map.put(newNode.val, newNode);
        for (Node n : root.neighbors) {
            newNode.neighbors.add(clone(n, map));
        }
        return newNode;
    }
}
