import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class try2 {
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

        int[] valDefSeguidos = valoresSeguidos(N, M, informacoes);
        int result = 1;

        for(int i = 0; i < valDefSeguidos.length; i++){
            formas = 0;
            result *= contaCombinacoes(valDefSeguidos[i]);
        }

        System.out.println(result);
    }

    //m(k) = m(k - 1) + m(k - 2) + (...); m(0) = 1; 
    public static int contaCombinacoes(int k){
        if(k == 0){
            return formas++;
        }
        else{
            for(int i = 0; i < tamanhosDePecas.length; i++){
                if(k >= tamanhosDePecas[i]){
                    contaCombinacoes(k - tamanhosDePecas[i]);
                }
            }
        }

        return formas;
    }

    //Achar e listar em valores numericos, as cores seguidas (nao sera preciso anotar na lista pecas isoladas)
    public static int[] valoresSeguidos(int N, int M, char[][] informacoes){
        List<Integer> espacosSeguidos = new ArrayList<Integer>();
        char caracterSalvo = '.';
        int vezes = 1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(informacoes[i][j] != '.'){
                    if(caracterSalvo == '.'){
                        caracterSalvo = informacoes[i][j];
                    }
                    else if(informacoes[i][j] == caracterSalvo){
                        vezes++;
                    }
                    else{
                        caracterSalvo = informacoes[i][j];

                        //Nao precisamos saber se existe uma peça com 1 unico de tamanho
                        if(vezes > 1){
                            espacosSeguidos.add(vezes);
                            vezes = 1; 
                        }   
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

        int[] valDefSeguidos = new int[espacosSeguidos.size()];

        for(int i = 0; i < espacosSeguidos.size(); i++){
            valDefSeguidos[i] = espacosSeguidos.get(i);
        }

        return valDefSeguidos;
    }
}