import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static public int xMax;
    static public int yMax;
    static public int xMin;
    static public int yMin;
    static public int dimen;
    static public int tracker;
    
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));

        String[] info = reader.readLine().split(" ");

        xMax = Integer.parseInt(info[0]) - 1;
        yMax = Integer.parseInt(info[1]) - 1;
        yMin = 0;
        xMin = 0;
        tracker = 0;
        boolean[][] map = new boolean[xMax + 1][yMax + 1];

        int T = Integer.parseInt(reader.readLine());

        encolherMap(map, info, reader, T);

        System.out.println("No");
    }

    public static void encolherMap(boolean[][] map, String[] info, BufferedReader reader, int T) throws IOException{
        int xTrueMin = Integer.MAX_VALUE;
        int xTrueMax = Integer.MIN_VALUE;
        int yTrueMin = Integer.MAX_VALUE;
        int yTrueMax = Integer.MIN_VALUE;
        int x = 0; 
        int y = 0; 

        for(int i = 0; i < T; i++){
            info = reader.readLine().split(" ");

            x = Integer.parseInt(info[0]);
            if(x < xTrueMin){
                xTrueMin = x;
            }
            else if(x > xTrueMax){
                xTrueMax = x;
            }

            y = Integer.parseInt(info[1]);
            if(y < yTrueMin){
                yTrueMin = y;
            }
            else if(y > yTrueMax){
                yTrueMax = y;
            }
            
            map[x][y] = true;
            tracker++;
        }

        while(yTrueMin - yMin > 2){
            yMin += 2;
        }
        while(xTrueMin - xMin > 2){
            xMin += 2;
        }
        while(yMax - yTrueMax > 2){
            yMax -= 2;
        }
        while(xMax - xTrueMax > 2){
            xMax -= 2;
        }

        dimen = (xMax - xMin + 1) * (yMax - yMin + 1);  

        // System.out.println("yMin: " + yMin + " " + "yMax: " + yMax + " " + "xMin: " + xMin + " " + "xMax: " + xMax);
        andar(x, y, map);
    }

    public static void andar(int x, int y, boolean[][] map) {
        // Direita
        entreAndar(x + 1, y, map);
        // Cima
        entreAndar(x, y + 1, map);
        // Esquerda
        entreAndar(x - 1, y, map);
        // Baixo
        entreAndar(x, y - 1, map);
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
        if(x < xMin || y < yMin || y > yMax || x > xMax){
            return false;
        }
        return !map[x][y];
    }

    public static boolean terminar(boolean[][] map){
        return (dimen == tracker);
    }
}