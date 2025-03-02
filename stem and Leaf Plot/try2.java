import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] informacoes = reader.readLine().split(" ");
        int N1 = Integer.parseInt(informacoes[0]);
        List<Integer> listaOrdenada1 = new ArrayList<>(agruparValores(N1, informacoes));
        
        informacoes = reader.readLine().split(" ");
        int N2 = Integer.parseInt(informacoes[0]);
        List<Integer> listaOrdenada2 = new ArrayList<>(agruparValores(N2, informacoes));

        printRequesitado(N1, N2, listaOrdenada1, listaOrdenada2);
    }

    public static TreeSet<Integer> agruparValores(int N, String[] informacoes){
        TreeSet<Integer> arvoreDePesquisa = new TreeSet<>();

        for(int i = 1; i <= N; i++){
            arvoreDePesquisa.add(Integer.parseInt(informacoes[i]));
        }

        System.out.println(arvoreDePesquisa);

        return arvoreDePesquisa;
    }

    public static int printRequesitado(int N1, int N2, List<Integer> listaOrdenada1, List<Integer> listaOrdenada2){
        //Descobrir o maximo de folhas de 1 (e só de 1)        
        int maxDots = 0;
        for (int i = 0; i < listaOrdenada2.size(); i++) {
            int dot = 0;
            for (int j = 0; j < listaOrdenada2.size(); j++) {
                int dezenaI = listaOrdenada2.get(i) / 10;
                int dezenaJ = listaOrdenada2.get(j) / 10;

                //System.out.println("com o valores: " + listaOrdenada2.get(i) + " e " + listaOrdenada2.get(j) + " as dezenas são: " + dezenaI + " e " + dezenaJ);
                if (dezenaI == dezenaJ) {
                dot++;
                }
            }

            if (dot > maxDots) {
                maxDots = dot;
            }
        }

        TreeSet<Integer> arvoreDezenas = new TreeSet<>();
        int dezenasDiferentes = 0;

        for(int i = 0; i < 9; i++){
            int dezena = listaOrdenada1.get(i) / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
                dezenasDiferentes++;
            }
        }

        for(int i = 0; i < 13; i++){
            int dezena = listaOrdenada2.get(i) / 10;

            if(!arvoreDezenas.contains(dezena)){
                arvoreDezenas.add(dezena);
                dezenasDiferentes++;
            }
        }

        System.out.println("Dot: " + maxDots + "; dezenas != : " + dezenasDiferentes);

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

            if(ordem2 + 1 <= listaOrdenada2.size() && listDezenas.get(i) == (listaOrdenada2.get(ordem2) / 10)){
                while (ordem2 + 1 < listaOrdenada2.size()) {
                    int dezena1 = listaOrdenada2.get(ordem2) / 10;
                    int dezena2 = listaOrdenada2.get(ordem2 + 1) / 10;
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
                if(ordem2 + 1 == listaOrdenada2.size()){
                    int dezena1 = listaOrdenada2.get(ordem2 - 1) / 10;
                    int dezena2 = listaOrdenada2.get(ordem2) / 10;

                    if(dezena1 == dezena2 || ultimo2){
                        ordem2++;
                        numeros++;
                    }
                    else{
                        ultimo2 = true;
                    }
                }
                
                numDots = maxDots - numeros - 1;
            }


            while (numDots > 0) {
                System.out.print('.');
                numDots--;
            }

            for(int j = ordem2 - 1; numeros > 0; numeros--, j--){
                System.out.print(listaOrdenada2.get(j) % 10);
            } 

            System.out.print(".|");

            if(listDezenas.get(i) >= 10){
                System.out.print(listDezenas.get(i) + "|.");
            }
            else{
                System.out.print("." + listDezenas.get(i) + "|.");
            }

            if(ordem1 + 1 <= listaOrdenada1.size() && listDezenas.get(i) == (listaOrdenada1.get(ordem1) / 10)){
                while (ordem1 + 1 < listaOrdenada1.size()) {
                    int dezena1 = listaOrdenada1.get(ordem1) / 10;
                    int dezena2 = listaOrdenada1.get(ordem1 + 1) / 10;
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
                if(ordem1 + 1 == listaOrdenada1.size()){
                    int dezena1 = listaOrdenada1.get(ordem1 - 1) / 10;
                    int dezena2 = listaOrdenada1.get(ordem1) / 10;

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
                System.out.print(listaOrdenada1.get(j) % 10);
            }

            System.out.println("");

        }
        
        return 0;
    }
}
