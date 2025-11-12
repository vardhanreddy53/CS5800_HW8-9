public class Main{
    public static void main(String args[])throws NullPointerException{
        int m=1000;//change the size of table accordingly 30,300,1000
        HashTable table=new HashTable(m);
        WordProcessor wp = new WordProcessor(table);
        wp.processor("alice_in_wonderland.txt");

         int[] lengths = table.lengthoflist();
        double var = HashAnalysis.variance(lengths);
        table.listallkeys();
        

        System.out.println("Variance: " + var);
        HashAnalysis.printTopList(lengths);
        //HashAnalysis.printhist(lengths);  //{prints the length of each node}
        HashAnalysis.showHistogram(lengths);//graph using jpanel

    }
    
}
