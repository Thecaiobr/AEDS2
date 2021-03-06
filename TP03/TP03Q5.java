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
		this(70);
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

     /**
    * Algoritmo de ordenacao Heapsort.
    */
   public void sort() {
      //Alterar o vetor ignorando a posicao zero
      Serie[] tmp = new Serie[n+1];
      for(int i = 0; i < n; i++){
         tmp[i+1] = array[i];
      }
      array = tmp;

      //Contrucao do heap
      for(int tamHeap = 2; tamHeap <= n; tamHeap++){
         construir(tamHeap);
      }

      //Ordenacao propriamente dita
      int tamHeap = n;
      while(tamHeap > 1){
         swap(1, tamHeap--);
         reconstruir(tamHeap);
      }

      //Alterar o vetor para voltar a posicao zero
      tmp = array;
      array = new Serie[n];
      for(int i = 0; i < n; i++){
         array[i] = tmp[i+1];
      }
   }

   //heapfy up
   public void construir(int tamHeap){
       //divide por i/2 para comparar com o pai dele
    for(int i=tamHeap; ((i > 1) && ((array[i].getFormato().compareTo(array[i/2].getFormato())>0) || ((array[i].getFormato().compareTo(array[i/2].getFormato()) == 0) && (array[i].getNome().compareTo(array[i/2].getNome())>0)))); i /=2){
         swap(i, i/2);
      }
   }

   public void reconstruir(int tamHeap){
      int i = 1;
      while(i <= (tamHeap/2)){
         int filho = getMaiorFilho(i, tamHeap);
         if(array[i].getFormato().compareTo(array[filho].getFormato())<0 || (array[i].getFormato().compareTo(array[filho].getFormato()) == 0 && array[i].getNome().compareTo(array[filho].getNome())<0)){
            swap(i, filho);
            i = filho;
         }else{
            i = tamHeap;
         }
      }
   }

   public int getMaiorFilho(int i, int tamHeap){
      int filho;
      if (2*i == tamHeap || array[2*i].getFormato().compareTo(array[2*i+1].getFormato())>0 || (array[2*i].getFormato().compareTo(array[2*i+1].getFormato()) == 0 && array[2*i].getNome().compareTo(array[2*i+1].getNome())>0)){
         filho = 2*i;
      } else {
         filho = 2*i + 1;
      }
      return filho;
   }
}


public class TP03Q5 {
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


