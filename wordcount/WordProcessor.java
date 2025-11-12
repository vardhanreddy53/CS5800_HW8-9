import java.io.*;

public class WordProcessor {
    public HashTable table;
    public WordProcessor(HashTable table){
        this.table=table;
    }
    public void processor(String filename){
        try(BufferedReader br=new BufferedReader(new FileReader(filename))){
            String line;
            while((line=br.readLine())!=null){
                line=line.toLowerCase().replaceAll("[^a-z0-9\\s]","");//removing all the special characters
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        table.increase(word);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }    }
    
}
