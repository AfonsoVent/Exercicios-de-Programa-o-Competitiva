import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static public int xMax;
    static public int yMax;
    static public int dimen;
    static int tracker = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));

        String[] info = reader.readLine().split(" ");

        yMax = Integer.parseInt(info[0]);
        xMax = Integer.parseInt(info[1]);
        dimen = xMax * yMax;

        int T = Integer.parseInt(reader.readLine());

        boolean[][] map = new boolean[xMax][yMax];
        int x = 0;
        int y = 0;

        for(int i = 0; i < T; i++){
            info = reader.readLine().split(" ");

            y = Integer.parseInt(info[0]);
            x = Integer.parseInt(info[1]);
            
            map[x][y] = true;
            tracker++;
        }

        andar(x, y, map);

        System.out.println("No");
    }

    public static void andar(int x, int y, boolean[][] map) {
        // Cima
        entreAndar(x, y + 1, map);
        // Esquerda
        entreAndar(x - 1, y, map);
        // Baixo
        entreAndar(x, y - 1, map);
        // Direita
        entreAndar(x + 1, y, map);
    }

    public static void entreAndar(int x, int y, boolean[][] map) {
        if(!valido(x, y, map)) {
            return;
        }

        map[x][y] = true;
        tracker++;

        if(terminar(map)) {
            System.out.println("Yes");
            System.exit(0);
        }

        andar(x, y, map);

        map[x][y] = false;
        tracker--;
    }

    public static boolean valido(int x, int y, boolean[][] map) {
        if(x < 0 || y < 0 || y >= yMax || x >= xMax){
            return false;
        }
        return !map[x][y];
    }

    public static boolean terminar(boolean[][] map){
        return (tracker == dimen);
    }
}