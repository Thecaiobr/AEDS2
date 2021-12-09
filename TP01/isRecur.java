public class isRecur {
    public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }
    
     //método para verificar se a string é composta apenas por vogais, caso houver algum 
     //digito diferente de vogal a função retorna falso
     public static boolean isVogal(String s,int i){               
        
            if(s.charAt(i) =='a'|| s.charAt(i) == 'e'||s.charAt(i) == 'i'||s.charAt(i) == 'o'||s.charAt(i) == 'u'||s.charAt(i) == 'A'||s.charAt(i) == 'E'||s.charAt(i) == 'I'||s.charAt(i) == 'O'||s.charAt(i) == 'U'){
                ;
            }else{
                return false;
            }if(s.length()-1 <= i){
                return isVogal(s, i=i+1);
            }
            else{
                return true;
            }
    }

    //método para verificar se a string é composta apenas por consoantes, caso houver algum 
     //digito vogal a função retorna falso
    public static boolean isConsoante(String s, int i){

            if(s.charAt(i) == 'a'|| s.charAt(i) == 'e'||s.charAt(i) == 'i'||s.charAt(i) == 'o'||s.charAt(i) == 'u'||s.charAt(i) == 'A'||s.charAt(i) == 'E'||s.charAt(i) == 'I'||s.charAt(i) == 'O'||s.charAt(i) == 'U'||(s.charAt(i)>48 && s.charAt(i)<57)){
                return false;
                
            }else{
                if(s.length()-1 <= i){
                    return isConsoante(s, i=i+1);
                }
                else{
                    return true;
                }
            }
    }

    //método para verificar se a string é composta apenas por numeros inteiros, caso houver algum 
     //digito diferente de numeros da tabela ASCII a função retorna falso
    public static boolean isInteiro(String s, int i){
        
            if(s.charAt(i)<48 || s.charAt(i)>57){
                return false;
            }else{
                if(s.length()-1 <= i){
                    return isInteiro(s, i=i+1);
                }
                else{
                    return true;
                }
            }

    }
    
    //método para verificar se a string é composta apenas por numeros reais, caso houver algum 
     //digito diferente de numeros e valores representados na função da tabela ASCII, ela retornará falso
    public static boolean isReal(String s,int i){

        
            if(s.charAt(i)>= 48 && s.charAt(i)<= 57||s.charAt(i) == 44 || s.charAt(i) == 46){
                ;
            }else{
                return false;
            }if(s.length()-1 <= i){
                return isReal(s, i=i+1);
            }
            else{
                return true;
            }
                
    }

    //método main para chamar todas às funções e imprimir as respostas 
     public static void main(String[] args){
        String str = "";
        int i = 0;
        str = MyIO.readLine();
        while(isFim(str) != true){
            if(isVogal(str,i)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isConsoante(str,i)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isInteiro(str,i)){
                MyIO.print("SIM ");
            }else{
                MyIO.print("NAO ");
            }
            if(isReal(str,i)){
                MyIO.print("SIM\n");
            }else{
                MyIO.print("NAO\n");
            }

            str = MyIO.readLine();
           }
        }
     }

