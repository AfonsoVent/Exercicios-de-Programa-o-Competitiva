import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class test1 {    
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));

        String[] info = reader.readLine().split(" ");

        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);

        int noFilho;
        int noPai;

        boolean[][] tabela = new boolean[N][N];

        for(int i = 0; i < M; i++){
            info = reader.readLine().split(" ");
            noFilho = Integer.parseInt(info[0]);
            noPai = Integer.parseInt(info[1]);

            tabela[noFilho][noPai] = true;
            if(tabela[noPai][noFilho]){
                System.out.println("IMPOSSIBLE");
                return;
            }
        }

        if(checkLoop(tabela, N, M)){
            System.out.println("IMPOSSIBLE");
            return;
        }

        order();
    }

    public static boolean checkLoop(boolean[][] tabela, int N, int M) {       
        Hashtable<Integer, Integer> lista = new Hashtable<>();
        int[] array = new int[M + 1];
        int valor;
        int min = Integer.MAX_VALUE;
        int first = Integer.MAX_VALUE;
        
        // fazer a lista de prioridades
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(tabela[i][j]){
                    lista.put(i, j);

                    if(j < min){
                        min = j;
                        first = i;
                    }
                    break;
                }
            }
        }

        // Terminar essa lista de prioridades e colocar num array
        array[0] = first;
        for (int i = 0; i < lista.size(); i++) {
            array[i + 1] = lista.get(i);
        }

        for(int i = 0; i < array.length; i++){
            valor = array[i];
            for(int j = i + 1; j < lista.size(); j++){
                if(valor == array[j]){
                    return true;
                }
            }   
        }

        return false;
    }

    public static void order(boolean[][] tabela, int N, int M) {

    }
}