import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(reader.readLine());

        System.out.println(criandoTriangulos(M));
    }

    public static long criandoTriangulos(int M){
        long triangulo = 0;

        for (int i = M / 2; i > 0; i--) {
            for (int j = i; j >= i / 2; j--) {
                //o maximo para nao estourar o numero M
                //ate a condiçao dos 3 triangulos estar certo
                //O maior valor possivel será igual a j. Já o menor sempre será 1.
                int terceiroLadoMax = Math.min(j, M - i - j);  
                int terceiroLadoMin = i - j + 1;

                //nao existe combinaçao possivel
                if(terceiroLadoMax < terceiroLadoMin){
                    break;
                }

                triangulo += terceiroLadoMax - terceiroLadoMin + 1;
            }
        }

        return triangulo;
    }
}
