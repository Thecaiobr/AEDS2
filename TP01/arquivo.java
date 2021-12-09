import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

public class arquivo {
// Chamamento das funçoes e captaçao de dados
    public static void main(String[] args) {
        int n = MyIO.readInt();
        openFile(n);
        readFile(n);

    }
//Leitura do arquivo
    public static void readFile(int n) {
        File arquivo = new File("arquivo.txt");

        try {// tenta abrir o arquivo
            if (!arquivo.exists()) {
                throw new Exception();
            }
            RandomAccessFile file = new RandomAccessFile(arquivo, "r");

            for(int i = 0;i<n;i++){
                file.seek((n-1-i)*8);
                double num = file.readDouble();
                if(num % 1 !=0){
                    MyIO.println(num);
                }else{
                    MyIO.println((int)num);
                }
            }
            file.close();
        }catch(Exception e){

        }
    }

    public static void openFile(int n){
        double abrir = 0;
        File arquivo = new File("arquivo.txt");
        try{
            if(!arquivo.exists()){
                arquivo.createNewFile();
            }
            if(!arquivo.canWrite()){
                throw new Exception();
            }
            RandomAccessFile file = new RandomAccessFile(arquivo, "rw");
            for(int i=0;i<n;i++){
                abrir = MyIO.readDouble();
                file.writeDouble(abrir);
            }
            file.close();
        }catch(Exception e){

        }
    }

}

