import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] informacoes = reader.readLine().split(" ");
        int N1 = Integer.parseInt(informacoes[0]);
        Integer[] arrayOrdenado1 = (agruparValores(N1, informacoes));
        
        informacoes = reader.readLine().split(" ");
        int N2 = Integer.parseInt(informacoes[0]);
        Integer[] arrayOrdenado2 = (agruparValores(N2, informacoes));

        printRequesitado(N1, N2, arrayOrdenado1, arrayOrdenado2);
    }

    public static Integer[] agruparValores(int N, String[] informacoes){
        Integer[] arrayOrdenado = new Integer[N];

        for(int i = 1; i <= N; i++){
            arrayOrdenado[i - 1] = Integer.parseInt(informacoes[i]);
        }

        Arrays.sort(arrayOrdenado);

        //System.out.println(Arrays.toString(arrayOrdenado));

        return arrayOrdenado;
    }

    public static int printRequesitado(int N1, int N2, Integer[] arrayOrdenado1, Integer[] arrayOrdenado2){
        //Descobrir o maximo de folhas de 1 (e só de 1)        
        int maxDots = 0;
        int dezenaMax = 0;
        for (int i = 0; i < N2; i++) {
            int dot = 0;
            for (int j = 0; j < N2; j++) {
                int dezenaI = arrayOrdenado2[i] / 10;
                int dezenaJ = arrayOrdenado2[j] / 10;

                //System.out.println("com o valores: " + arrayOrdenado2[i] + " e " + arrayOrdenado2[j] + " as dezenas são: " + dezenaI + " e " + dezenaJ);
                if (dezenaI == dezenaJ) {
                dot++;
                }
            }

            if (dot > maxDots) {
                maxDots = dot;
                dezenaMax = arrayOrdenado2[i];
            }
        }

        int multiplicadoPor10 = 0;

        while (dezenaMax >= 10) {
            dezenaMax /= 10;  
            multiplicadoPor10++;    
        }

        TreeSet<Integer> arvoreDezenas = new TreeSet<>();
        int dezenasDiferentes = 0;

        for(int i = 0; i < N1; i++){
            int dezena = arrayOrdenado1[i] / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
                dezenasDiferentes++;
            }
        }

        for(int i = 0; i < N2; i++){
            int dezena = arrayOrdenado2[i] / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
                dezenasDiferentes++;
            }
        }

        //System.out.println("Dot: " + maxDots + "; dezenas != : " + dezenasDiferentes);

        List<Integer> listDezenas = new ArrayList<>(arvoreDezenas);

        //Printar por caule e folhas
        int ordem1 = 0;
        int ordem2 = 0;
        boolean ultimo1 = false;
        boolean ultimo2 = false;
        int numDots;

        for(int i = 0; i < dezenasDiferentes; i++){
            int numeros = 0;
            numDots = maxDots;

            if(ordem2 + 1 <= N2 && listDezenas.get(i) == (arrayOrdenado2[ordem2] / 10)){
                while (ordem2 + 1 < N2) {
                    int dezena1 = arrayOrdenado2[ordem2] / 10;
                    int dezena2 = arrayOrdenado2[ordem2 + 1] / 10;
                    ordem2++;
                    //System.out.println("o valor de ordem2 vai em: " + ordem2);
    
                    if(dezena1 == dezena2){
                        numeros++;
                    }
                    else{
                        numeros++;
                        break;
                    }
    
                }
                if(ordem2 + 1 == N2){
                    int dezena1 = arrayOrdenado2[ordem2 - 1] / 10;
                    int dezena2 = arrayOrdenado2[ordem2] / 10;

                    if(dezena1 == dezena2 || ultimo2){
                        ordem2++;
                        numeros++;
                    }
                    else{
                        ultimo2 = true;
                    }
                }
                
                numDots = maxDots - numeros;
            }


            while (numDots > 0) {
                System.out.print('.');
                numDots--;
            }

            for(int j = ordem2 - 1; numeros > 0; numeros--, j--){
                System.out.print(arrayOrdenado2[j] % 10);
            } 

            System.out.print(".|");

            int valor = listDezenas.get(i);            
            int numeroDeCaracteres = String.valueOf(valor).length();

            for(int j = multiplicadoPor10; j >= numeroDeCaracteres; j--){
                if(j > 0){
                    System.out.print(".");
                }
            }
            System.out.print(listDezenas.get(i));

            

            System.out.print("|.");


            if(ordem1 + 1 <= N1 && listDezenas.get(i) == (arrayOrdenado1[ordem1] / 10)){
                while (ordem1 + 1 < N1) {
                    int dezena1 = arrayOrdenado1[ordem1] / 10;
                    int dezena2 = arrayOrdenado1[ordem1 + 1] / 10;
                    ordem1++;
                    //System.out.println("o valor de ordem1 vai em: " + ordem1);
    
                    if(dezena1 == dezena2){
                        numeros++;
                    }
                    else{
                        numeros++;
                        break;
                    }
    
                }
                if(ordem1 + 1 == N1){
                    int dezena1 = arrayOrdenado1[ordem1 - 1] / 10;
                    int dezena2 = arrayOrdenado1[ordem1] / 10;

                    if(dezena1 == dezena2 || ultimo1){
                        ordem1++;
                        numeros++;
                    }
                    else{
                        ultimo1 = true;
                    }
                }
            }

            for(int j = ordem1 - numeros; numeros > 0; numeros--, j++){
                System.out.print(arrayOrdenado1[j] % 10);
            }

            System.out.println("");

        }
        
        return 0;
    }
}
