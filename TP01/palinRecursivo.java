public class palinRecursivo {

    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }

    public static boolean isPalindromo(String s) {
        return isPalindromo(s, 0);
    }
    //função recursiva para verificar se a entrada corresponde a um palindromo
    private static boolean isPalindromo(String s, int pos) {      
        if (pos >= s.length()-1-pos)
           return true;
        return (s.charAt(pos) == s.charAt(s.length()-1-pos)) && isPalindromo(s, pos+1);
    }

    //função main que informará se a entreda é um palindromo ou não
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

