import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try3 {
    private static int numeroDeGranadas;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        for(int i = 0; i < N; i++){
            int D = Integer.parseInt(reader.readLine());
            String C = reader.readLine();
            numeroDeGranadas = 0;

            System.out.println(granadeExplosion(D, C)); 
        }
    }

    public static int granadeExplosion(int D, String C){
        int conjuntoMaior  = Integer.MIN_VALUE;
        int[] radius = new int[2]; 

        //Descobrir o grupo com o maximo que da para ter com 1 granada
        for(int j = 0; (j + D) <= C.length(); j++){
            int numeroLetrasF = 0;

            for(int l = 0; l < D; l++){
                char letra = C.charAt(j + l);

                if(letra == 'F'){
                    numeroLetrasF++;
                }
            }

            if(numeroLetrasF > conjuntoMaior){
                conjuntoMaior = numeroLetrasF;
                radius[0] = j;
                radius[1] = j + D - 1;
            }
        }

        if(conjuntoMaior == 0){
            return 0;
        }
        else{
            numeroDeGranadas++;
        }

        //Se sobrou algo na sua esquerda, matar com outra
        for(int j = 0; j < radius[0]; j++){
            char letra = C.charAt(j);

            if(letra == 'F'){
                String primeraParte = C.substring(0, radius[0]);
                granadeExplosion(D, primeraParte);
                break;
            }
        }

        //Se sobrou aldo na sua direita, matar com outra
        for(int j = radius[1] + 1; j < C.length(); j++){
            char letra = C.charAt(j);

            if(letra == 'F'){
                String segundaParte = C.substring(radius[1] + 1, C.length());
                granadeExplosion(D, segundaParte);
                break;
            }
        }

        return numeroDeGranadas;
    }
}
