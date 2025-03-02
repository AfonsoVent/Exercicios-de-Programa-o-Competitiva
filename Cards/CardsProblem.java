package Cards;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardsProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        System.out.println(torre(N, 0, 0));
    }

    public static int torre (int N, int nivel, int base){
        int cartasNivelAcima = base + 2 * nivel + (nivel - 1);

        if(cartasNivelAcima <= N){
            torre(N, nivel++, cartasNivelAcima);
        }
        
        return nivel;
    }
}
