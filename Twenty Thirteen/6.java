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

        // Teste
        // System.out.println(takeBiggerDigit(73618)); => 7
        // System.out.println(takeLength(2000)); => 4
        // System.out.println(Arrays.toString(constructDigits(3, 2, 0))); => [2, 3]
        int[] arr = {4, 3, 2};
        System.out.println(assemblyNumber(arr, 432, 3)); // => 432
        // int[] arr = {2, 3, 4};
        // System.out.println(assemblyNumber(arr, 482, 3)); // => 0
        // int[] arr = {3, 4, 5};
        // System.out.println(assemblyNumber(arr, 521, 3));
        
        // Printar
        for(int i = 0; i < N; i++){
            // System.out.println(resp[i]);
        }
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
        for (int i = 0; i < length; i++) {
            result[length - i - 1] = biggerDigit - i;
        }
    
        if (shifft != 0) {
            result[length - 1] += shifft;
        }
    
        return result;
    }

    // Uma bela tentativa de montar os digitos
    public static int assemblyNumber(int[] digits, int year, int length){
        String yearStr = String.valueOf(year);
        StringBuilder result = new StringBuilder();
        boolean greaterFound = false;
        int select = 1;

        for (int i = 0; i < length && !greaterFound; i++) {
            // Obter o valor do digito do ano
            int currentYearDigit = Character.getNumericValue(yearStr.charAt(i));
            
            if(digits[i] == currentYearDigit){
                select = currentYearDigit;
            }
            else if(digits[i] > currentYearDigit){
                select = currentYearDigit;
                greaterFound = true;
            }
            else{
                break;
            }

            result.append(select);
        }

        return Integer.parseInt(result.toString());
    }
}
