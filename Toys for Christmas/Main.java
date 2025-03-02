import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] info = reader.readLine().split(" ");

        int T = Integer.parseInt(info[0]);
        int C = Integer.parseInt(info[1]);

        int[] toySpace = new int[T];
        int[] numChildren = new int[C];

        for(int i = 0; i < T; i++){
            toySpace[i] = Integer.parseInt(reader.readLine());
        }

        int max = 0;
        for(int i = 0; i < T; i++){
            if(toySpace[i] > max){
                max = toySpace[i];
            }
        }

        int[][] childrenSpace = new int[C][max * 10];

        for(int i = 0; i < C; i++){
            info = reader.readLine().split(" ");

            numChildren[i] = Integer.parseInt(info[0]);
            for(int j = 0; j < numChildren[i]; j++){
                childrenSpace[i][j] = Integer.parseInt(info[j + 1]);
            }
        }

        funVille(T, C, toySpace, numChildren, childrenSpace);
    }

    public static void funVille(int T, int C, int[] toySpace, int[] numChildren, int[][] childrenSpace){
        int vezesMax = 0;

        for(int i = 0; i < T; i++){
            for(int j = 0; j < C; j++){
                int vezes = 0;
                if(toySpace[i] == numChildren[j]){
                    for(int l = 0; l < numChildren[j]; l++){
                        vezes += childrenSpace[j][l];
                    }
                    if(vezes > vezesMax){
                        vezesMax = vezes;
                    }
                }
            }
        }

        System.out.println(vezesMax);
    } 
}
