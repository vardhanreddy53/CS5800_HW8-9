import java.util.NoSuchElementException;

public class BinomialHeap {
    BinomialNode head;

    public BinomialHeap() {
        head = null;
    }

    public BinomialNode merge(BinomialNode h1, BinomialNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        BinomialNode head;
        BinomialNode tail;
        BinomialNode a = h1, b = h2;

        if (a.degree <= b.degree) {
            head = a;
            a = a.sibling;
        } else {
            head = b;
            b = b.sibling;
        }
        tail = head;

        while (a != null && b != null) {
            if (a.degree <= b.degree) {
                tail.sibling = a;
                a = a.sibling;
            } else {
                tail.sibling = b;
                b = b.sibling;
            }
            tail = tail.sibling;
        }

        tail.sibling = (a != null) ? a : b;
        return head;
    }

    public void linkTrees(BinomialNode y, BinomialNode z) {
        y.parent = z;
        y.sibling = z.child;
        z.child = y;
        z.degree++;
    }

    public BinomialNode union(BinomialNode h1, BinomialNode h2) {
        BinomialNode newHead = merge(h1, h2);
        if (newHead == null)
            return null;

        BinomialNode prev = null, x = newHead, next = x.sibling;

        while (next != null) {
            if (x.degree != next.degree ||
                (next.sibling != null && next.sibling.degree == x.degree)) {
                prev = x;
                x = next;
            } else if (x.key <= next.key) {
                x.sibling = next.sibling;
                linkTrees(next, x);
            } else {
                if (prev == null)
                    newHead = next;
                else
                    prev.sibling = next;
                linkTrees(x, next);
                x = next;
            }
            next = x.sibling;
        }

        return newHead;
    }

    public void insert(int key) {
        BinomialNode temp = new BinomialNode(key);
        head = union(head, temp);
    }

    public int findMin() {
        if (head == null)
            throw new NoSuchElementException("Heap is empty");
        BinomialNode y = null, x = head;
        int min = Integer.MAX_VALUE;
        while (x != null) {
            if (x.key < min) {
                min = x.key;
                y = x;
            }
            x = x.sibling;
        }
        return y.key;
    }

    public void extractMin() {
        if (head == null)
            return;

        BinomialNode prevMin = null, minNode = head;
        BinomialNode temp = head;
        int min = head.key;
        while (temp.sibling != null) {
            if (temp.sibling.key < min) {
                min = temp.sibling.key;
                prevMin = temp;
                minNode = temp.sibling;
            }
            temp = temp.sibling;
        }
        if (prevMin != null)
            prevMin.sibling = minNode.sibling;
        else
            head = minNode.sibling;
        BinomialNode child = null;
        if (minNode.child != null) {
            child = minNode.child.reverse(null);
            BinomialNode t = child;
            while (t != null) {
                t.parent = null;
                t = t.sibling;
            }
        }
        head = union(head, child);
    }
    public void decreaseKey(int oldKey, int newKey) {
        if(head==null)
        return;
        BinomialNode node = head.findNode(oldKey);
        if (node == null || newKey > node.key)
            return;
        node.key = newKey;
        BinomialNode y = node, z = y.parent;
        while (z != null && y.key < z.key) {
            int temp = y.key;
            y.key = z.key;
            z.key = temp;
            y = z;
            z = y.parent;
        }
    }

    public void delete(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    public void display() {
        System.out.print("\nHeap: ");
        displayTree(head);
        System.out.println();
    }

    public void displayTree(BinomialNode h) {
        if (h == null) return;
        System.out.print(h.key + "(" + h.degree + ") ");
        displayTree(h.child);
        displayTree(h.sibling);
    }
}
