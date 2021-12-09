public class deci2bin{    
    public static int[] dec2bin(int decimal){  
        int binario[] = new int[40];
        int index = 0;
        while(decimal > 0){
            binario[index++]= decimal%2;
            decimal=decimal/2;
        }
        for(int i = index-1;i >= 0;i--){    
            System.out.print(binario[i]);    
          } 
        return binario;
    }    

    public static void main(String args[]){ 
        int num = Integer.MAX_VALUE;
        num = MyIO.readInt();
        dec2bin(num);
       }
}
