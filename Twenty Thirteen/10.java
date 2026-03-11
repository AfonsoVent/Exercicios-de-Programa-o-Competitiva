import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Obter valores
        int N = Integer.parseInt(reader.readLine());
        int[] anos = new int[N];
        for(int i = 0; i < N; i++){
            anos[i] = Integer.parseInt(reader.readLine());
            anos[i]++;
        }

        // Obter os valores de resposta
        // int[] resp = new int[N];
        // for(int i = 0; i < N; i++){
        //     resp[i] = craftYear(anos[i]);
        //     System.out.println(resp[i]);
        // }

        // ---------------Teste
        // System.out.println(takeBiggerDigit(73618)); => 7
        // System.out.println(takeLength(2000)); => 4
        // System.out.println(Arrays.toString(constructDigits(3, 2, 0))); // => [2, 3]
        // int[] arr1 = {4, 3, 2};
        // System.out.println(assemblyNumber(arr1, 432, 3) + " e deveria dar " + 432); // => 432
        // int[] arr2 = {2, 3, 4};
        // System.out.println(assemblyNumber(arr2, 482, 3) + " e deveria dar " + 0); // => 0
        // int[] arr3 = {3, 4, 5};
        // System.out.println(assemblyNumber(arr3, 521, 3) + " e deveria dar " + 543); // => 543
        // System.out.println(Arrays.toString(constructDigits(4, 3, 2))); // => [6, 5, 4]
        // System.out.println(Arrays.toString(constructDigits(4, 3, 1))); // => [5, 4, 3]
        // System.out.println(Arrays.toString(constructDigits(2, 4, 0))); // => [3, 2, 1, 0]
        // int[] arr4 = {3, 2, 1, 0};
        // System.out.println(assemblyNumber(arr4, 1431, 3) + " e deveria dar " + 0); // => 0
        int[] arr4 = {4, 3, 2, 1};
        System.out.println(assemblyNumber(arr4, 1431, 4) + " e deveria dar " + 1432); // => 1432
        // System.out.println(Arrays.toString(constructDigits(1, 4, 0))); // => [3, 2, 1, 0]
        // int[] arr5 = {3, 2, 1, 0};
        // System.out.println(assemblyNumber(arr5, 1988, 4) + " e deveria dar " + 2013); // => 2013
        
        // Printar
        // for(int i = 0; i < N; i++){
        //     System.out.println(resp[i]);
        // }
    }

    // Função Mae com tds as subfunções
    public static int craftYear(int year){
        int biggerDigit = takeBiggerDigit(year);

        int length = takeLength(year);

        int[] digits = new int[length];
        int result = 0;

        int shifft = 0;

        while(result == 0){
            digits = constructDigits(biggerDigit, length, shifft);

            result = assemblyNumber(digits, year, length);

            if(result == 0){
                shifft++;
            }
        }

        return result;
    }

    // Obtem o valor + significativo
    public static int takeBiggerDigit(int year){
        while (year >= 10) {
            year /= 10;
        }
        return year;
    }

    // Micro função só para obter o tamanho do ano
    public static int takeLength(int year){
        String yearStr = Integer.toString(year);
        return yearStr.length();
    }

    // Uma bela tentativa de construir os digitos
    public static int[] constructDigits(int biggerDigit, int length, int shifft) {
        int[] result = new int[length];
        biggerDigit += shifft;
        int overNumber = 0;
        if(biggerDigit < length){
            overNumber = length - biggerDigit - 1;
        }
        biggerDigit += overNumber;
        
        for (int i = 0; i < length; i++) {
            result[i] = biggerDigit - i;
        }
    
        return result;
    }
    

    // Uma bela tentativa de montar os digitos
    public static int assemblyNumber(int[] digits, int year, int length){
        String yearStr = String.valueOf(year);
        StringBuilder result = new StringBuilder();
        boolean greaterFound = false;
        boolean digitFound = false;
        boolean normalFlow = true;
        boolean[] digitUsed = new boolean[length]; 
        int select = 1;

        for (int i = 0; i < length && !greaterFound; i++) {
            // Obter o valor do digito do ano
            int currentYearDigit = Character.getNumericValue(yearStr.charAt(i));
            digitFound = false;

            // Até achar um digito igual
            for(int j = 0; normalFlow && j < length; j++){
                // System.out.println("No digito: " + (i + 1) + " Na volta: " + (j + 1) + "º Sei pelo menos que este valor foi lido: " + digits[j]);
                if(!digitUsed[j] && digits[j] == currentYearDigit){
                    select = digits[j];
                    digitFound = true;
                    digitUsed[j] = true;
                    break;
                }    
            }

            // Caso não ache, achamos um maior
            for(int j = length - 1; !digitFound && j >= 0; j--){
                if(!digitUsed[j] && digits[j] > currentYearDigit){
                    select = digits[j];
                    digitFound = true;
                    digitUsed[j] = true;
                    greaterFound = true;
                    normalFlow = true;
                    break;
                }   
            }

            // Caso nenhum tenha sido achado, return erro, precisamos de uma nova lista de digitos:
            if(!digitFound){
                // System.out.println("tinha isto antes de apagar:" + result);
                // TODO: Eu deveria pensar de outra forma se eu quiser apagar os digitos, uma vez que 1432 -> 2013 e 1987 -> 2013
                    // Verificar se da para voltar 1 digito
                    if (i > 0) {
                        // Obter o indice do valor anterior
                        i -= 1;
                        
                        // Obter o valor antes colocado
                        char lastChar = result.charAt(i);
                        int lastDigit = Character.getNumericValue(lastChar);
                    
                        // Encontrar o valor colocado no result
                        int idx = -1;
                        for (int k = 0; k < digits.length; k++) {
                            if (digits[k] == lastDigit) {
                                idx = k;
                                break;
                            }
                        }
                        
                        // Agora ele não esta usado
                        digitUsed[idx] = false;
                        
                        // Apagar o digito e avisar que temos de pular o primeiro for
                        result.deleteCharAt(i);
                        normalFlow = false;
                        // Voltar uma posição a mais ainda
                        i -= 1;
                    }
                    else return 0; // Caso nao tenha, retorna 0 e pede uma nova construção de digitos
            }

            if(normalFlow){
                result.append(select);

                // caso tenha sido encontrado um maior, entao é so colocar os restantes por ordem
                if(greaterFound) break;
            }
        }

        // Colocar por ordem
        if(greaterFound){
            for(int i = length - 1; i >= 0; i--){
                if(!digitUsed[i]) result.append(digits[i]);
            }
        }

        return Integer.parseInt(result.toString());
    }
}
