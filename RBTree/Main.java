import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Scanner sc = new Scanner(System.in);
        String filename = "input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String cmd = parts[0].toLowerCase();

                // Handle possible commands in file
                switch (cmd) {
                    case "insert":
                        if (parts.length > 1) {
                            int x = Integer.parseInt(parts[1]);
                            tree.insert(x);
                            System.out.println("Inserted " + x);
                        }
                        break;

                    case "delete":
                        if (parts.length > 1) {
                            int x = Integer.parseInt(parts[1]);
                            tree.delete(x);
                            System.out.println("Deleted " + x);
                        }
                        break;

                    case "search":
                        if (parts.length > 1) {
                            int x = Integer.parseInt(parts[1]);
                            Node res = tree.search(tree.root, x);
                            if (res == tree.leaf)
                                System.out.println("Not found: " + x);
                            else
                                System.out.println("Found " + x + " (Color: " + (res.color ? "Red" : "Black") + ")");
                        }
                        break;

                    case "sort":
                        System.out.println("Sorted order (inorder):");
                        tree.inorder(tree.root);
                        System.out.println();
                        break;

                    case "height":
                        System.out.println("Current height: " + tree.height(tree.root));
                        break;

                    default:
                        // If it's not a command, assume it's a list of numbers
                        for (String s : parts) {
                            try {
                                int val = Integer.parseInt(s);
                                tree.insert(val);
                            } catch (NumberFormatException ignored) {}
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Final tree (inorder):");
        tree.inorder(tree.root);
        System.out.println("\nHeight: " + tree.height(tree.root));
        while (true) {
            System.out.print("\nEnter command (insert x / delete x / search x / sort / height / exit): ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("insert")) {
                int x = sc.nextInt();
                tree.insert(x);
                System.out.println("Inserted " + x);
            } 
            else if (cmd.equalsIgnoreCase("delete")) {
                int x = sc.nextInt();
                tree.delete(x);
                System.out.println("Deleted " + x);
            } 
            else if (cmd.equalsIgnoreCase("search")) {
                int x = sc.nextInt();
                Node res = tree.search(tree.root, x);
                if (res == tree.leaf)
                    System.out.println("Not found");
                else
                    System.out.println("Found " + x + " (Color: " + (res.color ? "Red" : "Black") + ")");
            } 
            else if (cmd.equalsIgnoreCase("sort")) {
                System.out.println("Sorted order:");
                tree.inorder(tree.root);
                System.out.println();
            } 
            else if (cmd.equalsIgnoreCase("height")) {
                System.out.println("Height: " + tree.height(tree.root));
            } 
            else if (cmd.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } 
            else {
                System.out.println("Invalid command!");
            }

            System.out.println("Current height: " + tree.height(tree.root));
        }
    }
}
