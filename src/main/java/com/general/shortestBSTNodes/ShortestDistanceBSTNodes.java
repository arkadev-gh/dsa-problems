package com.general.shortestBSTNodes;

public class ShortestDistanceBSTNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minDiffInBST(TreeNode root) {
        /** Test cases to cover all possible distances from -1 to 6 **/

        // Test between endpoints (exist)
        assert (distTwoNodes(root, 0, 9) == 6);

        // Test between endpoints (non-exist)
        assert (distTwoNodes(root, 0, 11) == -1);

        // Symmetry test
        assert (distTwoNodes(root, 0, 4) == 3);
        assert (distTwoNodes(root, 4, 0) == 3);

        // Symmetry test of nodes that don't exist
        assert (distTwoNodes(root, 11, 13) == -1);
        assert (distTwoNodes(root, 13, 11) == -1);

        // Reflexivity test
        assert (distTwoNodes(root, 0, 0) == 0);
        assert (distTwoNodes(root, 9, 9) == 0);

        // Reflexivity test of nodes that don't exist
        assert (distTwoNodes(root, 11, 11) == -1);
        assert (distTwoNodes(root, -1, -1) == -1);

        // Test random pairs
        assert (distTwoNodes(root, 11, 4) == -1);
        assert (distTwoNodes(root, 1, 3) == 2);
        assert (distTwoNodes(root, 2, 9) == 4);
        assert (distTwoNodes(root, 4, 6) == 1);
        assert (distTwoNodes(root, 1, 9) == 5);

        return 1;
    }

    public int distTwoNodes(TreeNode root, int node1, int node2) {
        int distance = -1;

        // Root is null
        if (root == null) return distance;

            // Both nodes lie in the left subtree
        else if (node1 < root.val && node2 < root.val) {
            distance = distTwoNodes(root.left, node1, node2);
        }

        // Both nodes lie in the right subtree
        else if (node1 > root.val && node2 > root.val) {
            distance = distTwoNodes(root.right, node1, node2);
        }

        // This is the point of divergence
        else {
            int node1Depth = depth(root, node1);
            int node2Depth = depth(root, node2);
            if (node1Depth >= 0 && node2Depth >= 0) distance = node1Depth + node2Depth;
        }

        return distance;
    }

    // Find depth of a particular node
    public int depth(TreeNode root, int node) {
        if (root == null) return -1;
        if (node == root.val) return 0;

        int depthVal = 0;
        if (node < root.val) depthVal = depth(root.left, node);
        if (node > root.val) depthVal = depth(root.right, node);

        return (depthVal >= 0) ? 1 + depthVal : -1;
    }
}
