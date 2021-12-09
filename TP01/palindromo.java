
public class palindromo {

    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }
	 //função que verifica se a entrada é um palindromo
	public static boolean isPalindromo(String s) {
		int n = s.length();
		for (int i = 0; i < (n/2); ++i) {
		   if (s.charAt(i) != s.charAt(n - i - 1)) {
			   return false;
		   }
		}
	  
		return true;
	  }
	//função main na qual informará a saida de dados, no caso, se a entrada corresponde a um palindromo
	public static void main(String[] args)
	{
		String str = "";
         str = MyIO.readLine();
         while(isFim(str) != true){
		if (isPalindromo(str)){
			System.out.print("SIM\n");
		}
		else{
			System.out.print("NAO\n");
		}
		str = MyIO.readLine();
	}

	
}
}
