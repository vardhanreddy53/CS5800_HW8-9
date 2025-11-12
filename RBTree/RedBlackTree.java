class Node{
    int key;
    Node left,right,parent;
    Boolean color;//true=red;false=black
    public Node(int key){
         this.key = key;
        this.color = true; // new nodes start red
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

public class RedBlackTree {
    public Node root;
    Node leaf;

    public RedBlackTree() {
    leaf = new Node(-1);
    leaf.color = false; // black
    leaf.left = leaf.right = leaf.parent = leaf;
    root = leaf;
    }
     public Node minimum(Node x) {
        while (x.left != leaf)
            x = x.left;
        return x;
    }

    public Node maximum(Node x) {
        while (x.right != leaf)
            x = x.right;
        return x;
    }
    public Node search(Node x,int key){
        if(x==leaf||key==x.key)
        return x;
        if(x.key<key)
        return search(x.right,key);
        else
        return search(x.left,key);
    }
     public void inorder(Node x) {
        if (x == leaf) return;
        inorder(x.left);
        System.out.print(x.key + "(" + (x.color?"RED":"BLACK") + ") ");
        inorder(x.right);
    }
    public Node successor(Node x){
        if(x.right!=leaf){
            return minimum(x.right);
        }
        Node y=x.parent;
        while(y!=leaf&&x==y.right){
            x=y;
            y=y.parent;
        }
        return y;
    }
    public Node predecessor(Node x){
        if(x.left!=leaf)
        return maximum(x.left);
        Node y=x.parent;
        while(y!=leaf&&x==y.left){
            x=y;
            y=y.parent;
        }
        return y;
    }

    public int height(Node x){
        if(x==leaf)
        return 0;
        return 1+Math.max(height(x.left),height(x.right));
    }


    public void insert(int key) {
    Node z = new Node(key);
    Node y = leaf;
    Node x = root;
    while (x != leaf) {
        y = x;
        if (z.key < x.key)
            x = x.left;
        else
            x = x.right;
    }

    z.parent = y;
    if (y == leaf)
        root = z;
    else if (z.key < y.key)
        y.left = z;
    else
        y.right = z;

    z.left = leaf;
    z.right = leaf;
    z.color = true; // true = RED

    insertFixup(z);
    }

//method to replace the subtree rooted at u with the subtree rooted at v
    private void rbTransplant(Node u, Node v) {
        if (u.parent == leaf) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }


    public void delete(int key) {
        Node z = search(root, key);
        if (z == leaf) return; // Node not found

        Node y = z;
        boolean y_original_color = y.color;
        Node x;

        if (z.left == leaf) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == leaf) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right); // y is z's successor
            y_original_color = y.color;
            x = y.right;
            
            if (y.parent == z) {
                x.parent = y; // Case where successor is direct child
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        
        // Only fix if a black node was removed/replaced
        if (y_original_color == false) {
            DeleteFixup(x);
        }
    }

    public void DeleteFixup(Node x){
        while (x != root && x.color == false) {
            if (x == x.parent.left) {
                Node w = x.parent.right; // w is sibling
                if (w.color == true) { // Case 1: Sibling w is Red
                    w.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                
                if (w.left.color == false && w.right.color == false) { // Case 2: Sibling w is Black, both children Black
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.right.color == false) { // Case 3: Sibling w is Black, left child Red, right child Black
                        w.left.color = false;
                        w.color = true;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // Case 4: Sibling w is Black, right child Red
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.right.color = false;
                    leftRotate(x.parent);
                    x = root; // Terminate loop
                }
            } else { // Symmetric (x is a right child)
                Node w = x.parent.left; // w is sibling
                if (w.color == true) { // Case 1: Sibling w is Red
                    w.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                
                if (w.right.color == false && w.left.color == false) { // Case 2: Sibling w is Black, both children Black
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.left.color == false) { // Case 3: Sibling w is Black, right child Red, left child Black
                        w.right.color = false;
                        w.color = true;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    // Case 4: Sibling w is Black, left child Red
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.left.color = false;
                    rightRotate(x.parent);
                    x = root; // Terminate loop
                }
            }
        }
        x.color = false;
    }

    public void insertFixup(Node z) {
    while (z.parent.color == true) { // parent is red
        if (z.parent == z.parent.parent.left) {//left side
            Node y = z.parent.parent.right; // uncle
            if (y.color == true) { // Case 1
                z.parent.color = false;
                y.color = false;
                z.parent.parent.color = true;
                z = z.parent.parent;
            } else {
                if (z == z.parent.right) { // Case 2
                    z = z.parent;
                    leftRotate(z);
                }
                // Case 3
                z.parent.color = false;
                z.parent.parent.color = true;
                rightRotate(z.parent.parent);
            }
        } else { // same logic for right side
            Node y = z.parent.parent.left; // uncle
            if (y.color == true) { // Case 1
                z.parent.color = false;
                y.color = false;
                z.parent.parent.color = true;
                z = z.parent.parent;
            } else {
                if (z == z.parent.left) { // Case 2
                    z = z.parent;
                    rightRotate(z);
                }
                // Case 3
                z.parent.color = false;
                z.parent.parent.color = true;
                leftRotate(z.parent.parent);
            }
        }
    }
    root.color = false; // root is always black
}
public void rightRotate(Node x){
    Node y = x.left;
    x.left = y.right;
    if (y.right != leaf)
        y.right.parent = x;

    y.parent = x.parent;
    if (x.parent == leaf)
        root = y;
    else if (x == x.parent.right)
        x.parent.right = y;
    else
        x.parent.left = y;

    y.right = x;
    x.parent = y;

}
public void leftRotate(Node x){
     Node y = x.right;
    x.right = y.left;
    if (y.left != leaf)
        y.left.parent = x;

    y.parent = x.parent;
    if (x.parent == leaf)
        root = y;
    else if (x == x.parent.left)
        x.parent.left = y;
    else
        x.parent.right = y;
    y.left = x;
    x.parent = y;
}


}
