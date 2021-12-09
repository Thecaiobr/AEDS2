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
        this.nome = removeTags(br.readLine());

        while(!br.readLine().contains("Formato"));
        this.formato = removeTags(br.readLine());

        while(!br.readLine().contains("Duração"));
        this.duracao = removeTags(br.readLine());

        while(!br.readLine().contains("País de origem"));
        this.pais = removeTags(br.readLine());

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

    public void imprimir(){
        MyIO.println("" + nome + " " + formato + " " + duracao + " " + pais + " " + idioma +  " " + emissora + " " + transmissao + " " + temporadas + " " + episodios);
    }

}

class Pilha {
	private Serie[] array;
	private int n;

	/**
	 * Construtor da classe.
	 */
	public Pilha() {
		this(50);
	}

	/**
	 * Construtor da classe.
	 */
	public Pilha(int tamanho) {
		array = new Serie[tamanho];
		n = 0;
	}

    /**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean empilha(Serie item) {
		// Validar a inserção
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Desempilha um elemento da pilha (o último elemento inserido).
	 * 
	 * @return Elemento removido.
	 */
	public Serie desempilha() {
		if (n > 0)
			return array[--n];
		return null;
	}
	

    public static String limpaString(String s,int posicao){
        String resp = "";
        for(int i = posicao; i < s.length(); i++){
            resp += s.charAt(i);
        }

        return resp;
    }

    /*public static int myParseInt (String s){
        String resp = "";
        for(int i = 3; i < 5; i++){
            resp += s.charAt(i);
        }
        
        return Integer.parseInt(resp);
    }*/

    public void imprimir2(){
        
        for(int i = n-1; i >= 0; i--){
            array[i].imprimir();
        }
    }

}

public class TP02Q6 {
    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }

    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Serie serie = new Serie();
        Pilha pilha = new Pilha(1000);
        String str = "";
        int i = 0;
        int declaracoes;

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            //serie.ler("/tmp/series/" + str);//mudar dps "temp" para "tmp" q é onde esta o arquivo no verde
            serie = new Serie();
            serie.ler("/tmp/series/" + str);
            pilha.empilha(serie);
            i++;
            //serie.imprimir();
            str = MyIO.readLine();
            }
           
            declaracoes = MyIO.readInt();
            
            for(int j = 0; j < declaracoes; j++){
                str = MyIO.readLine();
                if(str.charAt(0) == 'I'){
                    serie = new Serie();
                    serie.ler("/tmp/series/" + pilha.limpaString(str,2));
                    pilha.empilha(serie);
                } else if(str.charAt(0) == 'R'){
                    serie = new Serie();
                    serie = pilha.desempilha();
                    System.out.println("(R) " + serie.getNome());
                } 
                
            }

            pilha.imprimir2();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}



