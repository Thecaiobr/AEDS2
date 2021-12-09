import java.util.Random;
public class changing {

    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }

     public static String troca(String s){
        String nova = "";
        for(int i = 0; i < s.length(); i++){
            Random gerador = new Random( );
            gerador . setSeed ( 4 ) ;
            char palavra = (char) ( 'a' + (Math. abs ( gerador . nextInt ( ) ) % 26 ) );
            char palavra2 = (char) ( 'a' + (Math. abs ( gerador . nextInt ( ) ) % 26 ) );
            if(s.charAt(i) == palavra){
                nova += palavra2;
            }else{
                nova += s.charAt(i);
            }
            }
        
        return nova;
    }
     public static void main(String[] args) {
        String str = "";
            str = MyIO.readLine();
            while(isFim(str) != true){
                MyIO.println(troca(str));
                str = MyIO.readLine();
            }
   
}
}
