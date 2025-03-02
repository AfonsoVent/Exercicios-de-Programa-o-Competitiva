import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try2 {
    private static int numeroDeGranadas;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        for(int i = 0; i < N; i++){
            int D = Integer.parseInt(reader.readLine());
            String C = reader.readLine();
            System.out.println("A String inicial é: " + C + " e ela tem "+C.length());
            numeroDeGranadas = 0;

            System.out.println(granadeExplosion(D, C)); 
        }
    }

    public static int granadeExplosion(int D, String C){
        String campoAnts = C;

        while (true) {
            int conjuntoMaior  = Integer.MIN_VALUE;
            int[] radius = new int[2]; 

            //Descobrir o grupo com o maximo que da para ter com 1 granada
            for(int j = 0; (j + D) < campoAnts.length(); j++){
                int numeroLetrasF = 0;

                for(int l = 0; l < D; l++){
                    char letra = campoAnts.charAt(j + l);

                    if(letra == 'F'){
                        numeroLetrasF++;
                    }
                }

                if(numeroLetrasF > conjuntoMaior){
                    conjuntoMaior = numeroLetrasF;
                    radius[0] = j;
                    radius[1] = j + D;
                }
            }

            if(conjuntoMaior != 0){
                numeroDeGranadas++;
            }
            else{
                break;
            }

            //Se sobrou algo na sua esquerda, matar com outra
            for(int j = 0; j < radius[0]; j++){
                char letra = campoAnts.charAt(j);

                if(letra == 'F'){
                    campoAnts = campoAnts.substring(0, radius[0]);
                }
            }

            //Se sobrou aldo na sua direita, matar com outra
            for(int j = radius[1]; j < campoAnts.length(); j++){
                char letra = campoAnts.charAt(j);

                if(letra == 'F'){
                    campoAnts = campoAnts.substring(radius[1], campoAnts.length());
                }
            }
        }

        return numeroDeGranadas;
    }
}
