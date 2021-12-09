public class is {
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }
    
     //método para verificar se a string é composta apenas por vogais, caso houver algum 
     //digito diferente de vogal a função retorna falso
     public static boolean isVogal(String s){
        boolean resp = true;                 
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) !='a'|| s.charAt(i) != 'e'||s.charAt(i) != 'i'||s.charAt(i) != 'o'||s.charAt(i) != 'u'||s.charAt(i) != 'A'||s.charAt(i) != 'E'||s.charAt(i) != 'I'||s.charAt(i) != 'O'||s.charAt(i) != 'U'){
                resp = false;
            }
        }
        return resp;
    }

    //método para verificar se a string é composta apenas por consoantes, caso houver algum 
     //digito vogal a função retorna falso
    public static boolean isConsoante(String s){
        boolean resp = true;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'a'|| s.charAt(i) == 'e'||s.charAt(i) == 'i'||s.charAt(i) == 'o'||s.charAt(i) == 'u'||s.charAt(i) == 'A'||s.charAt(i) == 'E'||s.charAt(i) == 'I'||s.charAt(i) == 'O'||s.charAt(i) == 'U'||s.charAt(i)<48 || s.charAt(i)>57){
                return false;
            }
        }
        return resp;
    }

    //método para verificar se a string é composta apenas por numeros inteiros, caso houver algum 
     //digito diferente de numeros da tabela ASCII a função retorna falso
    public static boolean isInteiro(String s){
        boolean resp = true;
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)<48 || s.charAt(i)>57){
                resp = false;
            }
        }
        return resp;
    }
    
    //método para verificar se a string é composta apenas por numeros reais, caso houver algum 
     //digito diferente de numeros e valores representados na função da tabela ASCII, ela retornará falso
    public static boolean isReal(String s){
        boolean resp = true;
        for(int i=0; i<s.length();i++){
            if(s.charAt(i)>= 48 && s.charAt(i)<= 57||s.charAt(i) == 44 || s.charAt(i) == 46){
                ;
            }else{
                return false;
            }
        }
        return resp;
    }

    //método main para chamar todas às funções e imprimir as respostas 
     public static void main(String[] args){
        String str = "";
        str = MyIO.readLine();
        while(isFim(str) != true){
            if(isVogal(str)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isConsoante(str)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isInteiro(str)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isReal(str)){
                MyIO.print("SIM\n");
            }else{
                MyIO.print("NAO\n");
            }

            str = MyIO.readLine();
           }
        }
     }

