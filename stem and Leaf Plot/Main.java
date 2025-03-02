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

    public static int dotsDeFolhasNecessarias(int N2, Integer[] arrayOrdenado2){
        int maxDotsFolhas = 0;
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
            if (dot > maxDotsFolhas) {
                maxDotsFolhas = dot;
            }
        }

        return maxDotsFolhas;
    }

    public static TreeSet<Integer> verificandoACasaDasDezenas(int N1, int N2, Integer[] arrayOrdenado1, Integer[] arrayOrdenado2){
        TreeSet<Integer> arvoreDezenas = new TreeSet<>();

        for(int i = 0; i < N1; i++){
            int dezena = arrayOrdenado1[i] / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
            }
        }

        for(int i = 0; i < N2; i++){
            int dezena = arrayOrdenado2[i] / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
            }
        }

        return arvoreDezenas;
    }

    public static int printRequesitado(int N1, int N2, Integer[] arrayOrdenado1, Integer[] arrayOrdenado2){
        //Calular Dots de folhas e caule:
        int maxDotsFolhas = dotsDeFolhasNecessarias(N2, arrayOrdenado2);
        int dezenaMax;

        if(N1 != 0 && N2 != 0){
            dezenaMax = Math.max(arrayOrdenado1[N1 - 1], arrayOrdenado2[N2 - 1]);
        }
        else if(N1 != 0){
            dezenaMax = arrayOrdenado1[N1 - 1];            
        }
        else if(N2 != 0){
            dezenaMax = arrayOrdenado2[N2 - 1];            
        }
        else{
            dezenaMax = 0;
        }

        //Dots do caule:
        int maxDotsCaule = String.valueOf(dezenaMax).length();
        //System.out.println(maxDotsCaule);

        //TODO:Criar diretamente a lista, em vez de passar uma arvore para lista
        List<Integer> listDezenas = new ArrayList<>(verificandoACasaDasDezenas(N1, N2, arrayOrdenado1, arrayOrdenado2));

        //Printar por caule e folhas
        int indiceDeOrdem1 = 0;
        int indiceDeOrdem2 = 0;
        boolean ultimo1 = false;
        boolean ultimo2 = false;
        int numDots;

        for(int i = 0; i < listDezenas.size(); i++){
            int numeros = 0;
            numDots = maxDotsFolhas;

            if(indiceDeOrdem2 + 1 <= N2 && listDezenas.get(i) == (arrayOrdenado2[indiceDeOrdem2] / 10)){
                while (indiceDeOrdem2 + 1 < N2) {
                    int dezena1 = arrayOrdenado2[indiceDeOrdem2] / 10;
                    int dezena2 = arrayOrdenado2[indiceDeOrdem2 + 1] / 10;
                    indiceDeOrdem2++;
                    //System.out.println("o valor de indiceDeOrdem2 vai em: " + indiceDeOrdem2);
    
                    if(dezena1 == dezena2){
                        numeros++;
                    }
                    else{
                        numeros++;
                        break;
                    }
    
                }
                if(indiceDeOrdem2 + 1 == N2){
                    int dezena1 = arrayOrdenado2[indiceDeOrdem2 - 1] / 10;
                    int dezena2 = arrayOrdenado2[indiceDeOrdem2] / 10;

                    if(dezena1 == dezena2 || ultimo2){
                        indiceDeOrdem2++;
                        numeros++;
                    }
                    else{
                        ultimo2 = true;
                    }
                }
                
                numDots = maxDotsFolhas - numeros;
            }


            while (numDots > 0) {
                System.out.print('.');
                numDots--;
            }

            for(int j = indiceDeOrdem2 - 1; numeros > 0; numeros--, j--){
                System.out.print(arrayOrdenado2[j] % 10);
            } 

            System.out.print(".|");

            int valor = listDezenas.get(i);            
            int numeroDeCaracteres = String.valueOf(valor).length();

            for(int j = maxDotsCaule; j > numeroDeCaracteres + 1; j--){
                System.out.print(".");
            }
            System.out.print(listDezenas.get(i));

            

            System.out.print("|.");


            if(indiceDeOrdem1 + 1 <= N1 && listDezenas.get(i) == (arrayOrdenado1[indiceDeOrdem1] / 10)){
                while (indiceDeOrdem1 + 1 < N1) {
                    int dezena1 = arrayOrdenado1[indiceDeOrdem1] / 10;
                    int dezena2 = arrayOrdenado1[indiceDeOrdem1 + 1] / 10;
                    indiceDeOrdem1++;
                    //System.out.println("o valor de indiceDeOrdem1 vai em: " + indiceDeOrdem1);
    
                    if(dezena1 == dezena2){
                        numeros++;
                    }
                    else{
                        numeros++;
                        break;
                    }
    
                }
                if(indiceDeOrdem1 + 1 == N1){
                    int dezena1 = arrayOrdenado1[indiceDeOrdem1 - 1] / 10;
                    int dezena2 = arrayOrdenado1[indiceDeOrdem1] / 10;

                    if(dezena1 == dezena2 || ultimo1){
                        indiceDeOrdem1++;
                        numeros++;
                    }
                    else{
                        ultimo1 = true;
                    }
                }
            }

            for(int j = indiceDeOrdem1 - numeros; numeros > 0; numeros--, j++){
                System.out.print(arrayOrdenado1[j] % 10);
            }

            System.out.println("");

        }
        
        return 0;
    }
}
