import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Serie{
    String nome;
    String formato;
    String duracao;
    String pais;
    String idioma;
    String emissora;
    String transmissao;
    int temporadas;
    int episodios;


    public void setNome(String nome){
        this.nome = nome;
    }

    public void setFormato(String formato){
        this.formato = formato;
    }

    public void setDuracao(String duracao){
        this.duracao = duracao;
    }

    public void setPais(String pais){
        this.pais = pais;
    }

    public void setIdioma(String idioma){
        this.idioma = idioma;
    }

    public void setEmissora(String emissora){
        this.emissora = emissora;
    }

    public void setTransmissao(String transmissao){
        this.transmissao = transmissao;
    }

    public void setTemporadas(int temporadas){
        this.temporadas = temporadas;
    }

    public void setEpisodios(int episodios){
        this.episodios = episodios;
    }


    public String getNome(){
        return this.nome;
    }

    public String getFormato(){
        return this.formato;
    }

    public String getDuracao(){
        return this.duracao;
    }

    public String getPais(){
        return this.pais;
    }

    public String getIdioma(){
        return this.idioma;
    }

    public String getEmissora(){
        return this.emissora;
    }

    public String getTransmissao(){
        return this.transmissao;
    }

    public int getTemporadas(){
        return this.temporadas;
    }

    public int getEpisodios(){
        return this.episodios;
    }

    public Serie clone(){
        Serie resp = new Serie();
        resp.nome = this.nome;

        return resp;
    }

    String removeTags(String line){
        String newline = "";
        int i=0;
        while(i<line.length()){
            if(line.charAt(i) == '<'){
                i++; 
                while(line.charAt(i) != '>') 
                i++;             
            } else {
                if(line.charAt(i) == '&'){
                    i+=5;
                } else{
                    newline += line.charAt(i);
                }  
                
            }
            i++;
        }

        return newline;
    }



    public int myParseInt(String str){
        String newline = "";
        int i=0;
        int num = 0;

        //System.out.println(str);
        while (i<str.length() && str.charAt(i) != ' ') {

                newline += str.charAt(i);
                i++;

        }
        //System.out.print(newline);
        num = Integer.parseInt(newline);
        return num;
    }



    public void ler(String nomeArquivo) throws Exception{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));

        BufferedReader br = new BufferedReader(isr);

        while(!br.readLine().contains("infobox_v2"));
        br.readLine();
        this.nome = searchName(nomeArquivo).trim();

        while(!br.readLine().contains("Formato"));
        this.formato = removeTags(br.readLine());

        while(!br.readLine().contains("Duração"));
        this.duracao = removeTags(br.readLine());

        while(!br.readLine().contains("País de origem"));
        this.pais = removeTags(br.readLine()).trim();

        while(!br.readLine().contains("Idioma original"));
        this.idioma = removeTags(br.readLine());

        while(!br.readLine().contains("Emissora de televisão original"));
        this.emissora = removeTags(br.readLine());

        while(!br.readLine().contains("Transmissão original"));
        this.transmissao = removeTags(br.readLine());

        while(!br.readLine().contains("N.º de temporadas"));
        //System.out.print("" + removeTags(br.readLine()));
        this.temporadas = myParseInt(removeTags(br.readLine()));
        
        while(!br.readLine().contains("N.º de episódios"));
        //System.out.print("" + removeTags(br.readLine()));
        this.episodios = myParseInt(removeTags(br.readLine()));

        br.close();
    }

    //método para tratar o nome do arquivo e retornar o nome da série
    public String searchName(String fileName){
        String resp = "";
        for(int i = 12; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
                resp += ' ';
            } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
    }

    public void imprimir(){
        MyIO.println("" + nome + " " + formato + " " + duracao + " " + pais + " " + idioma +  " " + emissora + " " + transmissao + " " + temporadas + " " + episodios);
    }

}

 class Hash {
    Serie tabela[];
    int m;
    final Serie NULO = null;
 
    public Hash() {
       this(21);
    }
 
    public Hash(int m) {
       this.m = m;
       this.tabela = new Serie[this.m];
       for (int i = 0; i < m; i++) {
          tabela[i] = NULO;
       }
    }
 
    public int h(int elemento) {
       return elemento % m;
    }
 
    public int reh(int elemento) {
       return ++elemento % m;
    }
 
    public boolean inserir(Serie elemento) {
       boolean resp = false;
       if (elemento != NULO) {
          int pos = h(getValorASC(elemento.getNome()));
          if (tabela[pos] == NULO) {
             tabela[pos] = elemento;
             resp = true;
          } else {
             pos = reh(getValorASC(elemento.getNome()));
             if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                resp = true;
             }
          }
       }
       return resp;
    }
 
    public boolean pesquisar(String elemento) {
       boolean resp = false;
       int pos = h(getValorASC(elemento));
       if (tabela[pos] != null && tabela[pos].getNome().compareTo(elemento) == 0) {
          resp = true;
       } else if (tabela[pos] != NULO) {
          pos = reh(getValorASC(elemento));
          if (tabela[pos].getNome().compareTo(elemento) == 0) {
             resp = true;
          }
       }
       return resp;
    }
 
    boolean remover(int elemento) {
       boolean resp = false;
       // ...
       return resp;
    }
 

    public static int getValorASC (String s){
        int resp = 0;
        for(int i = 0; i < s.length(); i++){
            resp += (int)s.charAt(i);
            
        }
        return resp;

 }

}

public class TP04Q7 {
    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }

     public static boolean pesquisa(Serie[] array,String str, int i){
        int cont = 0;
        for(cont = 0; cont < i; cont++){
            if(array[cont].getNome().equals(str)){
                return true;
            }
        }
        return false;
     }
    
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Serie serie = new Serie();
        Serie[] array = new Serie[100];
        Hash hash = new Hash();
        String str = "";
        int i = 0;

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            //serie.ler("/tmp/series/" + str);//mudar dps "temp" para "tmp" q é onde esta o arquivo no verde
            serie = new Serie();
            serie.ler("/tmp/series/" + str);
            hash.inserir(serie);
            i++;
            //serie.imprimir();
            str = MyIO.readLine();
            }
            str = MyIO.readLine();
            while(isFim(str) != true){
            if (hash.pesquisar(str)){
                System.out.print(" SIM\n");
            } else {
                System.out.print(" NAO\n");
            }
            str = MyIO.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
