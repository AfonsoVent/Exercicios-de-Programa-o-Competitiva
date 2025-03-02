import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class try1 {
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
        
        // Chama a função para calcular combinações
        List<List<Integer>> combinacoes = calculandoCombinacoes(4, 0);

        System.out.println(combinacoes);
        
        System.out.println(trocandoPosicoes(combinacoes));
    }

    private static List<List<Integer>> calculandoCombinacoes(int valor, int index) {
        int[] tamanhosDePecas = {1, 2, 3, 4, 6, 8, 10, 12, 16};
        List<List<Integer>> resultados = new ArrayList<>();

        // Caso base: combinação válida
        if (valor == 0) {
            resultados.add(new ArrayList<>());
            return resultados;
        }
        // Combinação que estourou
        if (valor < 0) {
            return resultados;
        }

        for (int i = index; i < tamanhosDePecas.length; i++) {
            List<List<Integer>> subResultados = calculandoCombinacoes(valor - tamanhosDePecas[i], i);
            for (int j = 0; j < subResultados.size(); j++) {
                List<Integer> subResultado = subResultados.get(j);
                subResultado.add(0, tamanhosDePecas[i]);
                resultados.add(subResultado);
            }
        }

        return resultados;
    }

    private static int trocandoPosicoes(List<List<Integer>> combinacoes) {
        List<Integer> contagemDiferentes = new ArrayList<>();

        for (int i = 0; i < combinacoes.size(); i++) {
            List<Integer> combinacao = combinacoes.get(i);
            contagemDiferentes.add(new HashSet<>(combinacao).size());
        }

        int count = 0;
        for(int i = 0; i < contagemDiferentes.size(); i++){
            if(contagemDiferentes.get(i) == 1){
                count += contagemDiferentes.get(i);
            }
            else{
                count += arranjos(combinacoes.get(i).size(), contagemDiferentes.get(i) - 1);
            }
        }

        return count;
    }

    private static int arranjos(int n, int k) {
        int resultado = 1;
        for (int i = 0; i < k; i++) {
            resultado *= (n - i);
        }
        return resultado;
    }
}


/*
 * public static int calculaandoCombinacoes(int espacosSeguidos){
        int[] tamanhosDePecas = {1, 2, 3, 4, 6, 8, 10, 12, 16};
        List<Integer> tamanhosAceitaveis = new ArrayList<>();

        for(int i = 0; i < tamanhosDePecas.length; i++){
            if(tamanhosDePecas[i] < espacosSeguidos){
                tamanhosAceitaveis.add(tamanhosDePecas[i]);
            }
        }
        
        int formasDiferentes = 0;

        for(int index = formasDiferentes - 1; index >= 0; index--){
            boolean valorObtido = false;
            int valor = 0;

            while (!valorObtido || valor > espacosSeguidos) {
                valor += tamanhosAceitaveis.get(index);

                if (valor == espacosSeguidos) {
                    valorObtido = true;
                }
            }

            if (valorObtido) {
                formasDiferentes++;
            }
        }

        return formasDiferentes;
    }
 */
