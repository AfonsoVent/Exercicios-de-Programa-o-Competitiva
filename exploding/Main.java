import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
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
        char letra;

        //Sempre que acharmos 1 F, lançaremos uma granada
        for(int j = 0; j < C.length(); j++){
            letra = C.charAt(j);

            if(letra == 'F'){
                numeroDeGranadas++;

                if((j + D) > C.length()){
                    break;
                }
                else{
                    j = j + D - 1;
                }
            }   
        }

        return numeroDeGranadas;
    }
}
