import java.util.Scanner;

public class Main {
    public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
        skiplist list = new skiplist(10, 0.5f); // maxLevel = 10, probability = 0.5

      System.out.println("Commands: insert x | delete x | search x | print | exit");

        while (true) {
            System.out.print("\n> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "insert":
                    if (parts.length < 2) {
                        break;
                    }
                    int keyToInsert = Integer.parseInt(parts[1]);
                    list.insert(keyToInsert);
                    System.out.println("Inserted " + keyToInsert);
                    break;

                case "delete":
                    if (parts.length < 2) {
                        System.out.println("Usage: delete <key>");
                        break;
                    }
                    int keyToDelete = Integer.parseInt(parts[1]);
                    list.delete(keyToDelete);
                    System.out.println("Deleted " + keyToDelete);
                    break;

                case "search":
                    if (parts.length < 2) {
                        System.out.println("Usage: search <key>");
                        break;
                    }
                    int keyToSearch = Integer.parseInt(parts[1]);
                    if (list.search(keyToSearch) != null)
                        System.out.println("Found " + keyToSearch);
                    else
                        System.out.println("Not Found");
                    break;

                case "print":
                    list.printList();
                    break;

                default:
                    System.out.println("Invalid command. Try: insert/delete/search/print/exit");
            }

            System.out.println("Current Height: " + list.height());
        }
    }
}
