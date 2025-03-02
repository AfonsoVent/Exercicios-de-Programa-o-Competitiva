import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int formas;
    static int[] tamanhosDePecas = {1, 2, 3, 4, 6, 8, 10, 12, 16};
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] mapaDimensoes = reader.readLine().split(" ");
        
        int N = Integer.parseInt(mapaDimensoes[0]);
        int M = Integer.parseInt(mapaDimensoes[1]);

        char[][] informacoes = new char[N][M];

        for (int i = 0; i < N; i++) {
            String linha = reader.readLine();
            for (int j = 0; j < M; j++) {
                informacoes[i][j] = linha.charAt(j);
            }
        }

        List<Integer> espacosSeguidos = valoresSeguidos(N, M, informacoes);

        long[] mem = fazerCache(espacosSeguidos);
        
        resultado(espacosSeguidos, mem);
    }

    //m(k) = m(k - 1) + m(k - 2) + (...); m(0) = 1; 
    public static long contaCombinacoes(int k, long[] mem) {
        //Terminou
        if (k == 0) {
            return 1;
        }
        //A cache deteta algum valor ja feito?
        if (mem[k] != 0) {
            return mem[k];
        }
        
        //m(k) = m(k - 1) + m(k - 2) + (...);
        long formas = 0;
        for (int i = 0; i < tamanhosDePecas.length; i++) {
            if (k >= tamanhosDePecas[i]) {
                formas += contaCombinacoes(k - tamanhosDePecas[i], mem);
            }
        }
        
        //A cache fica com o valor das formas calculado
        mem[k] = formas;
        return formas;
    }

    //Achar e listar em valores numericos, as cores seguidas (nao sera preciso anotar na lista pecas isoladas)
    public static List<Integer> valoresSeguidos(int N, int M, char[][] informacoes){
        List<Integer> espacosSeguidos = new ArrayList<Integer>();
        char caracterSalvo = '.';
        int vezes = 1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                    if(caracterSalvo == '.'){
                        caracterSalvo = informacoes[i][j];
                    }
                    else if(informacoes[i][j] == caracterSalvo && informacoes[i][j] != '.'){
                        vezes++;
                    }
                    else{
                        caracterSalvo = informacoes[i][j];

                        //Nao precisamos saber se existe uma peÃ§a com 1 unico de tamanho
                        if(vezes > 1){
                            espacosSeguidos.add(vezes);
                            vezes = 1; 
                        }   
                    }
                
            }

            //Limpar o caracterSalvo, para a proxima linha ser utilizavel
            caracterSalvo = '.';

            //Caso tenha alguma coisa nos vezes adicionar na lista
            if(vezes > 1){
                espacosSeguidos.add(vezes);
                vezes = 1; 
            }

        }
        return espacosSeguidos;
    }

    public static long[] fazerCache(List<Integer> espacosSeguidos){
        int maxEspaco = 0;

        for (int i = 0; i < espacosSeguidos.size(); i++){
            if (espacosSeguidos.get(i) > maxEspaco) {
                maxEspaco = espacosSeguidos.get(i);
            }
        }

        //+1 para a memoria nao estourar com o valor maximo de espaçosSeguidos
        long[] mem = new long[maxEspaco + 1];

        return mem;
    }

    public static void resultado(List<Integer> espacosSeguidos, long[] mem){
        long result = 1;
        
        for(int i = 0; i < espacosSeguidos.size(); i++){
            formas = 0;
            result *= contaCombinacoes(espacosSeguidos.get(i), mem);
        }

        System.out.println(result);
    }
}