import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinomialHeap heap = new BinomialHeap();

        System.out.print("Enter number of random elements: ");
        int n = sc.nextInt();

        Random rand = new Random();
        System.out.println("Inserting random keys:");
        for (int i = 0; i < n; i++) {
            int val = rand.nextInt(100);
            heap.insert(val);
            System.out.print(val + " ");
        }
        System.out.println();
        heap.display();

        while (true) {
            System.out.print("\nCommand (insert x | extract | min | decrease old new | delete x | quit): ");
            String cmd = sc.next();

            if (cmd.equals("insert")) {
                int x = sc.nextInt();
                heap.insert(x);
            } else if (cmd.equals("extract")) {
                heap.extractMin();
            } else if (cmd.equals("min")) {
                System.out.println("Minimum: " + heap.findMin());
            } else if (cmd.equals("decrease")) {
                int oldKey = sc.nextInt(), newKey = sc.nextInt();
                heap.decreaseKey(oldKey, newKey);
            } else if (cmd.equals("delete")) {
                int x = sc.nextInt();
                heap.delete(x);
            } else if (cmd.equals("quit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
            heap.display();
        }
    }
}
