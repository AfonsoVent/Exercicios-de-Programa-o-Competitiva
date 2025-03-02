import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(reader.readLine());

        System.out.println(torre(N));
    }

    public static int torre(long N) {
        int nivel = 1;
        long base = 0;
        long cartasNivelAcima;

        while (true) {
            cartasNivelAcima = 2 * nivel + (nivel - 1);

            if (cartasNivelAcima + base > N) {
                break;
            }

            base += cartasNivelAcima;  
            nivel++;
        }

        return nivel - 1;  
    }
}


/* 
    public static int torre (long N, int nivel, long base){
        long cartasNivelAcima = 2 * nivel + (nivel - 1);

        if(cartasNivelAcima + base <= N){
            return torre(N, nivel + 1, cartasNivelAcima + base);
        }
        
        return nivel - 1;
    }
    */