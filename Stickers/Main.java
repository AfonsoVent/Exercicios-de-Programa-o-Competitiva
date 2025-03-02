import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        String[] informacoes = reader.readLine().split(" ");

        int[] idsDoKevin = new int[N];
        for(int i = 0; i < N; i++){
            idsDoKevin[i] = Integer.parseInt(informacoes[i]);
        }

        int M = Integer.parseInt(reader.readLine());

        informacoes = reader.readLine().split(" ");

        TreeSet<Integer> idsKevinQuer = new TreeSet<>();
        for(int i = 0; i < M; i++){
            idsKevinQuer.add(Integer.parseInt(informacoes[i]));
        }

        System.out.println(idsQueKevinTem(N, idsDoKevin, M, idsKevinQuer));
    }

    public static int idsQueKevinTem(int N, int[] idsDoKevin, int M, TreeSet<Integer> idsKevinQuer){
        int eleTem = 0;

        for(int i = 0; i < N; i++){
            if(idsKevinQuer.contains(idsDoKevin[i])){
                eleTem++;
            }
        }
        return eleTem;
    }
}
