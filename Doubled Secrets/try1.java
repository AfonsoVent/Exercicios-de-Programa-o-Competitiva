import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        for(int i = 0; i < codigo.length(); i++){
            if(indicesLetras[i] != 0){
                //Colocar a primeira letra a ser aceite
                if(indicesCorretos.isEmpty()){
                    System.out.println("a primeira que coloquei foi: " + indicesLetras[i]);
                    indicesCorretos.push(indicesLetras[i]);
                }
                else{
                    //Fazer as perguntas para adicionar ou remover da stack
                    while(true){
                        //Colocar a letra com maior distancia
                        if(distanciasLetras[i] > distanciasLetras[indicesCorretos.peek()]){
                            System.out.println("Eu coloquei: " + indicesLetras[i]);
                            indicesCorretos.pop();
                            indicesCorretos.push(indicesLetras[i]);
                        }
                        
                        if(!indicesCorretos.isEmpty() && indicesLetras[i] < indicesCorretos.peek()){
                            indicesCorretos.pop();
                        }
                        else{
                            indicesCorretos.push(indicesLetras[i]);
                            break;
                        }
                        
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