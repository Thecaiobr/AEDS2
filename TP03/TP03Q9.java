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
        this.nome = searchName(nomeArquivo);

        while(!br.readLine().contains("Formato"));
        this.formato = removeTags(br.readLine());

        while(!br.readLine().contains("Dura????o"));
        this.duracao = removeTags(br.readLine());

        while(!br.readLine().contains("Pa??s de origem"));
        this.pais = removeTags(br.readLine()).trim();

        while(!br.readLine().contains("Idioma original"));
        this.idioma = removeTags(br.readLine()).trim();

        while(!br.readLine().contains("Emissora de televis??o original"));
        this.emissora = removeTags(br.readLine());

        while(!br.readLine().contains("Transmiss??o original"));
        this.transmissao = removeTags(br.readLine());

        while(!br.readLine().contains("N.?? de temporadas"));
        //System.out.print("" + removeTags(br.readLine()));
        this.temporadas = myParseInt(removeTags(br.readLine()));
        
        while(!br.readLine().contains("N.?? de epis??dios"));
        //System.out.print("" + removeTags(br.readLine()));
        this.episodios = myParseInt(removeTags(br.readLine()));

        br.close();
    }

    //m??todo para tratar o nome do arquivo e retornar o nome da s??rie
    public String searchName(String fileName){
        String resp = "";
        for(int i = 12; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posi????o i seja igual ao '_' a vari??vel resp recebe um espa??o em branco
                resp += ' ';
            } else { //caso n??o tenha espa??o em branco o caracter ?? concatenado ?? string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 ??ltimos caracteres relacionados ?? extens??o do arquivo
    }

    public void imprimir(){
        MyIO.println("" + nome + " " + formato + " " + duracao + " " + pais + " " + idioma +  " " + emissora + " " + transmissao + " " + temporadas + " " + episodios);
    }

}

class Lista {
	private Serie[] array;
	private int n;

	/**
	 * Construtor da classe.
	 */
	public Lista() {
		this(100);
	}

	/**
	 * Construtor da classe.
	 */
	public Lista(int tamanho) {
		array = new Serie[tamanho];
		n = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e desloca os demais elementos
	 * para o fim da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirInicio(Serie item) {
		if (n < array.length) {
			// Desloca elementos para o fim do array
			for (int i = n; i > 0; i--)
				array[i] = array[i - 1];

			array[0] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirFim(Serie item) {
		// validar insercao
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento em uma posicao especifica e move os demais elementos para
	 * o fim da lista.
	 * 
	 * @param item: elemento a ser inserido.
	 * @param pos:  Posicao de insercao.
	 */
	public boolean inserir(int pos, Serie item) {

		// validar insercao
		if (n < array.length && pos >= 0 && pos <= n) {
			// Desloca elementos para o fim do array
			for (int i = n; i > pos; i--)
				array[i] = array[i - 1];

			array[pos] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Serie removerInicio() {
		if (n > 0) {
			Serie item = array[0];
			n--;

			for (int i = 0; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return Elemento a ser removido.
	 */
	public Serie removerFim() {
		if (n > 0)
			return array[--n];
		return null;
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @param pos: Posicao de remocao.
	 * @return Elemento a ser removido.
	 */
	public Serie remover(int pos) {
		if (n > 0 && pos >= 0 && pos < n) {
			Serie item = array[pos];
			n--;

			for (int i = pos; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

    public static String limpaString(String s,int posicao){
        String resp = "";
        for(int i = posicao; i < s.length(); i++){
            resp += s.charAt(i);
        }

        return resp;
    }

    public static int myParseInt (String s){
        String resp = "";
        for(int i = 3; i < 5; i++){
            resp += s.charAt(i);
        }
        

        return Integer.parseInt(resp);
    }

    public void imprimir2(){
        for(int i = 0; i < n; i++){
            
            array[i].imprimir();
        }
    }

    public void swap(int i, int j) {
        Serie temp = array[i];
        array[i] = array[j];
        array[j] = temp;
     }

     public void sort() {
        mergesort(0, n-1);
     }
  
     /**
      * Algoritmo de ordenacao Mergesort.
      * @param int esq inicio do array a ser ordenado
      * @param int dir fim do array a ser ordenado
      */
     private void mergesort(int esq, int dir) {
        if (esq < dir){
           int meio = (esq + dir) / 2;
           mergesort(esq, meio);
           mergesort(meio + 1, dir);
           intercalar(esq, meio, dir);
        }
     }
  
     /**
      * Algoritmo que intercala os elementos entre as posicoes esq e dir
      * @param int esq inicio do array a ser ordenado
      * @param int meio posicao do meio do array a ser ordenado
      * @param int dir fim do array a ser ordenado
      */ 
     public void intercalar(int esq, int meio, int dir){
        int n1, n2, i, j, k;
  
        //Definir tamanho dos dois subarrays
        n1 = meio-esq+1;
        n2 = dir - meio;
  
        Serie[] a1 = new Serie[n1+1];
        Serie[] a2 = new Serie[n2+1];
  
        //Inicializar primeiro subarray
        for(i = 0; i < n1; i++){
           a1[i] = array[esq+i];
        }
  
        //Inicializar segundo subarray
        for(j = 0; j < n2; j++){
           a2[j] = array[meio+j+1];
        }
  
        //Sentinela no final dos dois arrays
        Serie sentinela = new Serie();
        sentinela.setEpisodios(0x7FFFFFFF);//coloca a quantidade de F certinho
        a1[i] = a2[j] = sentinela;  
  
        //Intercalacao propriamente dita
        for(i = j = 0, k = esq; k <= dir; k++){
           array[k] = (a1[i].getEpisodios()<(a2[j].getEpisodios()) || (a1[i].getEpisodios()==(a2[j].getEpisodios()) && a1[i].getNome().compareTo(a2[j].getNome())<0) ? a1[i++] : a2[j++]);
        }
        
     }

}


public class TP03Q9 {
    public static boolean isFim(String str){
        return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
     }


     public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        Serie serie = new Serie();
        Lista lista = new Lista();
        String str = "";

        str = MyIO.readLine();
        try{
            while(isFim(str) != true){
            serie = new Serie();
            serie.ler("/tmp/series/" + str);
            lista.inserirFim(serie);
            //serie.imprimir();
            str = MyIO.readLine();
            }
            lista.sort();
            lista.imprimir2();

     } catch (Exception e){
        e.printStackTrace();
    }
    }
}


