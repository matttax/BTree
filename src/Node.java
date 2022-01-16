class Node{
    int[] values;
    Node[] children;
    Node parent;
    int size;
    boolean isLeaf;

    public Node(int t){
        this.values = new int[2 * t - 1];
        this.children = new Node[2 * t];
        this.parent = null;
        this.isLeaf = true;
    }
}