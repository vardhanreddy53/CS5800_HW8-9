import java.util.*;
class Node{
    int key;
    Node[] forward;
    public Node(int key,int level){
        this.key=key;
        this.forward=new Node[level+1];
    }
}
public class skiplist {
    int Max_level;
    float p;
    int level;
    Node head;
    public skiplist(int maxlevel,float p){
        this.Max_level=maxlevel;
        this.p=p;
        this.level=1;
        this.head=new Node(-1,Max_level);
    }
    public int randomlevel(){
        int lvl=1;
        while(Math.random()<p && lvl<Max_level)
        lvl++;
        return lvl;
    }

    public Node search(int key){
        Node x=head;
        for(int i=level;i>=1;i--){
            while(x.forward[i]!=null&&x.forward[i].key<key){
                x=x.forward[i];
            }
        }
        x=x.forward[1];
        if(x!=null&&x.key==key){
            return x;
        }return null;
    }
    
     public void printList(){
        System.out.println("--- SkipList ---\n");
        for (int i = level; i >= 1; i--) {
            System.out.print("Level " + i + ": ");
            Node x=head.forward[i];
            while (x!=null) {
                System.out.print(x.key + " ");
                x = x.forward[i];
            }
            System.out.println();
        }
    }
    public int height(){
        return level;
    }

    public void insert(int key){
        Node[] update=new Node[Max_level+1];
        Node x=head;
          for (int i = level; i >= 1; i--) {
            while (x.forward[i] != null && x.forward[i].key < key)
                x = x.forward[i];
            update[i] = x;
        }

        x = x.forward[1];

        if (x == null || x.key != key) {
            int lvl = randomlevel();
            if (lvl > level) {
                for (int i = level + 1; i <= lvl; i++)
                    update[i] = head;
                level = lvl;
            }
            Node newNode = new Node(key, lvl);
            for (int i = 1; i <= lvl; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }
    public void delete(int key){
        Node[] update = new Node[Max_level + 1];
        Node x = head;

        for (int i = level; i >= 1; i--) {
            while (x.forward[i] != null && x.forward[i].key < key)
                x = x.forward[i];
            update[i] = x;
        }

        x = x.forward[1];

        if (x != null && x.key == key) {
            for (int i = 1; i <= level; i++) {
                if (update[i].forward[i] != x)
                    break;
                update[i].forward[i] = x.forward[i];
            }

            while (level > 1 && head.forward[level] == null)
                level--;
        }
    }
}
