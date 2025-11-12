public class HashFunctions {
    public static int hash(String key,int m){
        if (key == null || key.isEmpty()) return 0;
        int h=0,p=57;
        for(int i=0;i<key.length();i++){
            h=(p*h+(int)key.charAt(i))%m;
        }
        return Math.abs(h%m);
    }
    
}
