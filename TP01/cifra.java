public class cifra{
	public static boolean isFim(String str){
		return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
	}

   //função que irá fazer a codificar a entrada usando a chave 3
	public static String ciframento(String str){
		String resp = "";
		for(int i = 0; i < str.length(); i++){
			resp += (char)((int)str.charAt(i) + 3);
		}
		return resp;
	}
   //função main na qual irá mostrar a entrada de dados codificada da maneira correta
	public static void main(String[] args){
		String str = "";
		str = MyIO.readLine();
		while(isFim(str) != true){
			MyIO.println(ciframento(str)+"");
			str = MyIO.readLine();
		}
	}
}