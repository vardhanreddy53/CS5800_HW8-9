public class HashTable{
    Node[] table;
    int m;
    public HashTable(int m){
        this.m=m;
        this.table=new Node[m];
    }
    public void insert(String Key,int value){
        if (Key == null || Key.isEmpty()) return;//invalid insert
        int i = HashFunctions.hash(Key, m);
    Node head = table[i];

    // Case 1: empty bucket
    if (head == null) {
        table[i] = new Node(Key, value);
        return;
    }

    // Case 2: check if key exists
    Node curr = head;
    while (curr != null) {
        if (curr.key.equals(Key)) {
            curr.value += value;
            return;
        }
        curr = curr.next;
    }

    // Case 3: key not found insert at head
    Node newNode = new Node(Key, value);
    newNode.next = head;
    table[i] = newNode;
        
    }
    public void delete(String Key){
        int i=HashFunctions.hash(Key,m);
        Node curr=table[i];
        Node prev=null;
         while(curr!=null&&!curr.key.equals(Key)){
            prev=curr;
            curr=curr.next;
         }
         if(curr==null){
            return ;
         }
         if(prev==null){
            table[i]=curr.next;//if the element is the last inserted element
         }
         else
         prev.next=curr.next;
    }
    public Integer find(String key){
        int i = HashFunctions.hash(key, m);
        Node curr = table[i];
        while (curr != null) {
            if (curr.key.equals(key)) return curr.value;
            curr = curr.next;
        }
        return null;
    }
    public void increase(String key){
         if (key == null || key.isEmpty()) return;//invalid increase
        insert(key,1);//accomodated in the item not existing in the insert function
    }
    public void listallkeys(){
       for (int i = 0; i < m; i++) {
        Node curr = table[i];
        while (curr != null) {
            if (curr.key != null && !curr.key.isEmpty()) {
                System.out.println(curr.key + " " + curr.value);
            }
            curr = curr.next;
        }
    }
    }
    public int[] lengthoflist(){
        int[] lenarr=new int[m];
        for(int i=0;i<m;i++){
            int len=0;
            Node curr=table[i];
            while(curr!=null){
                len++;
                curr=curr.next;
            }
            lenarr[i]=len;
        }
        return lenarr;
    }
}
