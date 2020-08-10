package com.general.largestBSTSubtree;

public class LargestBSTSubtree {
    public static void main(String[] args) {
        // Create the tree
        BinaryTree tree = new BinaryTree();
        tree.root = new BinaryTree.Node(50);
        tree.root.left = new BinaryTree.Node(10);
        tree.root.right = new BinaryTree.Node(60);
        tree.root.left.left = new BinaryTree.Node(5);
        tree.root.left.right = new BinaryTree.Node(20);
        tree.root.right.left = new BinaryTree.Node(55);
        tree.root.right.left.left = new BinaryTree.Node(45);
        tree.root.right.right = new BinaryTree.Node(70);
        tree.root.right.right.left = new BinaryTree.Node(65);
        tree.root.right.right.right = new BinaryTree.Node(80);

        System.out.println(BinaryTree.largestBst(tree.root));
    }
}

class BinaryTree {
    /**
     * Structures to maintain binary tree information. Not too relevant
     */
    Node root;

    // Nested static class to represent a node
    static class Node {
        int data;
        Node left, right;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }

    // Create a binary tree with a root node value
    public BinaryTree() {
        root = new Node();
    }

    /**
     * Initialization and wrapper class code for our solution
     */
    private static int maxBstSize = 0;
    private static final int EMPTY_TREE_SIZE = 0, INVALID_BST_SIZE = -1;

    // A static nested class wrapper for maintaining Node information
    static class NodeInfo {
        int max, min, size;

        public NodeInfo(int max, int min, int size) {
            this.max = max;
            this.min = min;
            this.size = size;
        }

        public NodeInfo() {
        }
    }

    /**
     * Actual solution
     */
    public static int largestBst(Node root) {
        // Geeks for geeks has a bug with their java backend for static variables
        // Redundant reassignment of maxSize solves this problem
        maxBstSize = 0;
        findLargestBst(root);
        return maxBstSize;
    }

    private static NodeInfo findLargestBst(Node root) {
        if (root == null) return new NodeInfo(0, 0, EMPTY_TREE_SIZE);

        NodeInfo left = findLargestBst(root.left);
        NodeInfo right = findLargestBst(root.right);
        NodeInfo current = new NodeInfo();

        // If maximum from left subtree is lesser than root OR the left subtree is empty then that's a BST
        boolean leftCheck = (left.size > 0 && left.max < root.data) || (left.size == EMPTY_TREE_SIZE);

        // If minimum from right subtree is greater than root OR the right subtree is empty then that's a BST
        boolean rightCheck = (right.size > 0 && right.min > root.data) || (right.size == EMPTY_TREE_SIZE);

        // If the current tree is a BST then information related to max, min are relevant
        if (leftCheck && rightCheck) {
            current.max = (right.size > 0) ? right.max : root.data;
            current.min = (left.size > 0) ? left.min : root.data;
            current.size = left.size + right.size + 1;
            maxBstSize = Math.max(maxBstSize, current.size);
        }

        // Else all those are irrelevant
        else current.size = INVALID_BST_SIZE;

        return current;
    }
}
