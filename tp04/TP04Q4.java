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

class NoAN {
    public boolean cor;
    public Serie elemento;
    public NoAN esq, dir;
  
    public NoAN() {
      this(null);
    }
  
    public NoAN(Serie elemento) {
      this(elemento, false, null, null);
    }
  
    public NoAN(Serie elemento, boolean cor) {
      this(elemento, cor, null, null);
    }
  
    public NoAN(Serie elemento, boolean cor, NoAN esq, NoAN dir) {
      this.cor = cor;
      this.elemento = elemento;
      this.esq = esq;
      this.dir = dir;
    }
  }

class Alvinegra {
    private NoAN raiz; // Raiz da arvore.
 
    /**
     * Construtor da classe.
     */
    public Alvinegra() {
       raiz = null;
    }
 
    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    public boolean pesquisar(String elemento) {
        System.out.print(" raiz ");
       return pesquisar(elemento, raiz);
    }
 
    /**
     * Metodo privado recursivo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @param i        NoAN em analise.
     * @return <code>true</code> se o elemento existir,
     *         <code>false</code> em caso contrario.
     */
    private boolean pesquisar(String elemento, NoAN i) {
       boolean resp;
       if (i == null) {
          resp = false;
       } else if (elemento.compareTo(i.elemento.getNome())==0) {
          resp = true;
       } else if (elemento.compareTo(i.elemento.getNome())<0) {
          System.out.print("esq ");
          resp = pesquisar(elemento, i.esq);
       } else {
          System.out.print("dir ");
          resp = pesquisar(elemento, i.dir);
       }
       return resp;
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharCentral() {
       System.out.print("[ ");
       caminharCentral(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharCentral(NoAN i) {
       if (i != null) {
          caminharCentral(i.esq); // Elementos da esquerda.
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          caminharCentral(i.dir); // Elementos da direita.
       }
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPre() {
       System.out.print("[ ");
       caminharPre(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharPre(NoAN i) {
       if (i != null) {
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
          caminharPre(i.esq); // Elementos da esquerda.
          caminharPre(i.dir); // Elementos da direita.
       }
    }
 
    /**
     * Metodo publico iterativo para exibir elementos.
     */
    public void caminharPos() {
       System.out.print("[ ");
       caminharPos(raiz);
       System.out.println("]");
    }
 
    /**
     * Metodo privado recursivo para exibir elementos.
     * 
     * @param i NoAN em analise.
     */
    private void caminharPos(NoAN i) {
       if (i != null) {
          caminharPos(i.esq); // Elementos da esquerda.
          caminharPos(i.dir); // Elementos da direita.
          System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
       }
    }
 
    /**
     * Metodo publico iterativo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @throws Exception Se o elemento existir.
     */
    public void inserir(Serie elemento) throws Exception {
       // Se a arvore estiver vazia
       if (raiz == null) {
          raiz = new NoAN(elemento);
          //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");
 
       // Senao, se a arvore tiver um elemento
       } else if (raiz.esq == null && raiz.dir == null) {
          if (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
             raiz.esq = new NoAN(elemento);
             //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento + ").");
          } else {
             raiz.dir = new NoAN(elemento);
             //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento + ").");
          }
 
       // Senao, se a arvore tiver dois elementos (raiz e dir)
       } else if (raiz.esq == null) {
          if (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
             raiz.esq = new NoAN(elemento);
             //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
 
          } else if (elemento.getNome().compareTo(raiz.dir.elemento.getNome())<0) {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
             //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
 
          } else {
             raiz.esq = new NoAN(raiz.elemento);
             raiz.elemento = raiz.dir.elemento;
             raiz.dir.elemento = elemento;
             //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, se a arvore tiver dois elementos (raiz e esq)
       } else if (raiz.dir == null) {
          if (elemento.getNome().compareTo(raiz.elemento.getNome())>0) {
             raiz.dir = new NoAN(elemento);
             //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
 
          } else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome())>0) {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = elemento;
             //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
 
          } else {
             raiz.dir = new NoAN(raiz.elemento);
             raiz.elemento = raiz.esq.elemento;
             raiz.esq.elemento = elemento;
             //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento + ") e dir(" + raiz.dir.elemento + ").");
          }
          raiz.esq.cor = raiz.dir.cor = false;
 
       // Senao, a arvore tem tres ou mais elementos
       } else {
          //System.out.println("Arvore com tres ou mais elementos...");
          inserir(elemento, null, null, null, raiz);
       }
       raiz.cor = false;
    }
 
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
             if (i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome())< 0) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
          //System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir("
          //      + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
       } // if(pai.cor == true)
    }
 
    /**
     * Metodo privado recursivo para inserir elemento.
     * 
     * @param elemento Elemento a ser inserido.
     * @param avo      NoAN em analise.
     * @param pai      NoAN em analise.
     * @param i        NoAN em analise.
     * @throws Exception Se o elemento existir.
     */
    private void inserir(Serie elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
       if (i == null) {
          if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
             i = pai.esq = new NoAN(elemento, true);
          } else {
             i = pai.dir = new NoAN(elemento, true);
          }
          if (pai.cor == true) {
             balancear(bisavo, avo, pai, i);
          }
       } else {
          // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
             if (i == raiz) {
                i.cor = false;
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
             }
          }
          if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
             inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
             inserir(elemento, avo, pai, i, i.dir);
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private NoAN rotacaoDir(NoAN no) {
       //System.out.println("Rotacao DIR(" + no.elemento + ")");
       NoAN noEsq = no.esq;
       NoAN noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private NoAN rotacaoEsq(NoAN no) {
       //System.out.println("Rotacao ESQ(" + no.elemento + ")");
       NoAN noDir = no.dir;
       NoAN noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private NoAN rotacaoDirEsq(NoAN no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private NoAN rotacaoEsqDir(NoAN no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }


public class TP04Q4 {
   public static boolean isFim(String str){
      return(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M');
   }

   public static void main(String[] args){
      MyIO.setCharset("UTF-8");
      Serie serie = new Serie();
      Alvinegra alvinegra = new Alvinegra();
      String str = "";
      int i = 0;
      int declaracoes;

      str = MyIO.readLine();
      try{
          while(isFim(str) != true){
          serie = new Serie();
          serie.ler("/tmp/series/" + str);
          alvinegra.inserir(serie);
          i++;
          //serie.imprimir();
          str = MyIO.readLine();
          }
         
          
          str = MyIO.readLine();
          while(isFim(str)!= true){
            serie = new Serie();
            if(alvinegra.pesquisar(str) == true){
               System.out.println("SIM");
            }else {
               System.out.println("NAO");
            }
            str = MyIO.readLine();
          }

      } catch (Exception e){
          e.printStackTrace();
      }
  }
}
