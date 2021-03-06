#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>

#define MAX_FIELD_SIZE 100

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void print_serie(Serie serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie.nome,
        serie.formato,
        serie.duracao,
        serie.pais,
        serie.idioma,
        serie.emissora,
        serie.transmissao,
        serie.num_temporadas,
        serie.num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';
    return html;
}

/**
 * @brief Extrai os textos de uma tag html.
 * 
 * @param html Ponteiro para o caractere que abre a tag '<'.
 * @param texto Ponteiro para onde o texto deve ser colocado.
 * 
 * @return Ponteiro para o texto extraído.
 */
char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

/**
 * @brief Lê o HTML da série e popula os campos da struct.
 * 
 * @param serie Struct Serie que vai receber os dados.
 * @param html String contendo todo o HTML do arquivo.
 */
void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));
    if (strstr(serie->pais,"Estados Unidos ")!= NULL)
    {
       strcpy(serie->pais,"Estados Unidos");
    }

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}

#define MAXTAM    70
#define bool      short
#define true      1
#define false     0

typedef struct Celula {
	Serie elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Serie elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

//PILHA PROPRIAMENTE DITA =======================================================
Celula* topo; // Sem celula cabeca.


/**
 * Cria uma fila sem elementos.
 */
void start () {
   topo = NULL;
}


/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(Serie x) {
   Celula* tmp = novaCelula(x);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;
}

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Serie remover() {
   if (topo == NULL) {
      errx(1, "Erro ao remover!");
   }

   Serie resp = topo->elemento;
   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}


/**
 * Mostra os elementos separados por espacos, comecando do topo.
 */
void mostrar() {
   Celula* i;

   for(i = topo; i != NULL; i = i->prox) {
      print_serie(i->elemento);
   }

}

//int x [20]
//int tamanho = sizeof(x)/sizeof(x[0])

char* limpaString(char *s,int posicao){
        int i;
        char* resp = (char*) malloc(sizeof(char)*strlen(s));
        for( i = posicao; i < strlen(s); i++){
            resp[i-posicao] = s[i];
        }
        
        resp[i-posicao] = '\0';
        
        return resp;
    }

int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"
//METODO PRINCIPAL ==============================================================
int main() {

    
    Serie serie;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    start();

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserir(serie);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    } 

    int declaracoes;
    scanf("%d",&declaracoes);

    char linha[MAX_LINE_SIZE];
    
    for (int j = 0; j < declaracoes; j++)
    {
        scanf(" %[^\n]s",linha);
        //printf("%s\n",linha);
        if(linha[0] == 'I'){
            char caminho [MAX_LINE_SIZE];
            strcpy(caminho,PREFIXO);
            strcat(caminho,limpaString(linha,2));
            //caminho[strlen(caminho)-1] = '\0';
            //printf("%c\n",caminho[37]);
            char *html = ler_html(caminho);
            ler_serie(&serie, html);
            free(html);
            inserir(serie);
        } else if (linha[0] == 'R')
        {
            serie = remover();
            printf("(R) %s\n",serie.nome);
        }
    }
    mostrar();
    
    

    return EXIT_SUCCESS;
}