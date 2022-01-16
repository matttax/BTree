public class BTree {
    private Node root;
    private final int t;
    public BTree(int degree) {
        t = degree;
    }

    private void insertValue(Node node, int value) {
        int index = node.size;
        for (int i = node.size - 1; i >= 0; i--) {
            if (value <= node.values[i]) {
                node.values[i + 1] = node.values[i];
                index = i;
            }
            else break;
        }
        node.values[index] = value;
        node.size++;
    }


    private Node split(Node node, int key){
        Node left = new Node(t);
        Node right = new Node(t);
        int mid = getMid(node, left, right);
        int midValue = node.values[mid];

        Node parent = node.parent;
        if (parent == null) {
            midValue = node.values[mid];
            node.values = new int[2 * t - 1];
            node.values[0] = midValue;
            node.size = 1;
            node.isLeaf = false;
            node.children[0] = left;
            node.children[1] = right;
            left.parent = node;
            right.parent = node;
            return key > midValue ? right : left;
        }

        shiftNode(left, right, midValue, parent);
        parent.size++;
        left.parent = parent;
        right.parent = parent;
        return key > midValue ? right : left;
    }

    private int getMid(Node node, Node left, Node right) {
        int mid = node.size / 2;
        for (int i = 0; i < mid; i++) {
            left.values[i] = node.values[i];
            left.children[i] = node.children[i];
            left.size++;
        }
        left.children[mid] = node.children[mid];
        left.isLeaf = node.isLeaf;
        for (int i = mid + 1, j = 0; i < node.size; i++, j++) {
            right.values[j] = node.values[i];
            right.children[j] = node.children[i];
            right.size++;
        }
        right.children[mid] = node.children[node.size];
        right.isLeaf = node.isLeaf;
        return mid;
    }

    private void shiftNode(Node left, Node right, int midValue, Node parent) {
        int index = parent.size;
        for (int i = parent.size - 1; i >= 0; i--) {
            if (midValue < parent.values[i]) {
                parent.values[i + 1] = parent.values[i];
                parent.children[i + 2] = parent.children[i + 1];
                index = i;
            }
            else break;
        }
        parent.values[index] = midValue;
        parent.children[index] = left;
        parent.children[index + 1] = right;
    }


    public void insert(int value){
        if (root == null) {
            Node node = new Node(t);
            insertValue(node, value);
            root = node;
            return;
        }
        Node current = root;
        while (!current.isLeaf ||
                (current.size == 2 * t - 1 && current.children[0] == null)) {
            if (current.size == 2 * t - 1) {
                current = split(current, value);
                continue;
            }
            if (value < current.values[0]) {
                current = current.children[0];
                continue;
            }
            if (value > current.values[current.size - 1]) {
                current = current.children[current.size];
                continue;
            }
            for (int i = 1; i < current.size; i++) {
                if (value < current.values[i]) {
                    current = current.children[i];
                    break;
                }
            }
        }
        insertValue(current, value);
    }


    public void print(Node node, int level){
        if (node == null)
            return;
        System.out.print(level + "\t");
        for (int s = 0; s < level - 1; s++)
            System.out.print("\t");
        for (int i = 0; i < node.size; i++) {
            System.out.print(node.values[i] + " ");
        }
        System.out.println();
        if (!node.isLeaf) {
            for (int i = 0; i < node.size + 1; i++) {
                print(node.children[i], level + 1);
            }
        }
    }
    public void print() {
        print(root, 1);
    }
}