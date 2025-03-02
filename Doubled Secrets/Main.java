import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String codigo = reader.readLine();

        descodificador(codigo);
    }

    public static void descodificador(String codigo){
        int[] indicesLetras = new int[codigo.length()];
        int[] distanciasLetras = new int[codigo.length()];
        boolean[] indicesUsado = new boolean[codigo.length()];

        //Guardar com indices as distancias maiores
        for(int i = 0; i + 1 < codigo.length(); i++){
            for(int j = (codigo.length() - 1); j > (i + 1); j--){
                if(codigo.charAt(j) == codigo.charAt(i) && !indicesUsado[j]){
                    indicesLetras[i] = j;
                    distanciasLetras[i] = j - i;
                    indicesUsado[j] = true;
                    break;
                }
            }
        }

        // System.out.println(codigo.length() - 1);
        System.out.println(Arrays.toString(distanciasLetras));

        System.out.println(construirString(indicesLetras, distanciasLetras, codigo));
    }

    public static String construirString(int[] indicesLetras, int[] distanciasLetras, String codigo){
        Stack<Integer> indicesCorretos = new Stack<>();
        Integer[] distancias = Arrays.stream(distanciasLetras).boxed().toArray(Integer[]::new);
        Arrays.sort(distancias, Collections.reverseOrder());

        for(int i = 0; i < codigo.length(); i++){
            if(distancias[i] != 0){
                if(indicesCorretos.isEmpty()){
                    System.out.println("a primeira que coloquei foi: " + distancias[i]);
                    indicesCorretos.push(distancias[i]);
                }
                else{
                    if(indicesLetras[i] < indicesLetras[indicesCorretos.peek()]){
                        indicesCorretos.push(indicesLetras[i]);
                    }
                }
            }
        }

        //Obter as letras em um array de Char
        char[] caracteres = new char[indicesCorretos.size()];
        for(int i = 0; i < caracteres.length; i++){
            caracteres[i] = codigo.charAt(indicesCorretos.pop());
        }

        //Colocar as letras em um tipo String e reverter a palavra:
        String reverse = new String(caracteres);
        StringBuilder sb = new StringBuilder(reverse);
        String palavra = sb.reverse().toString();

        System.out.println(palavra);

        return palavra;
    }
}