import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class mae {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);
        
        long N = Long.parseLong(reader.readLine());

        System.out.println(torre(N));

        String formated = String.format("%16.10s", torre(N)).replace(' ', '.');
        System.out.println(formated);

        printWriter.close(); // Don't forget to close or it won't print.
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