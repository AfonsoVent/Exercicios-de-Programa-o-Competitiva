import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try1 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(reader.readLine());

        System.out.println(criandoTriangulos(M));
    }

    /* 
    public static int criandoTriangulos(int M){
        boolean retangulo = true;

        int triangulos = 0;

        for(int i = 1; i * 3 <= M; i++){
            System.out.println("Aqui no equilatero deu com: " + i + " " + i + " " + i + "; no final com: " + i * 3);
            triangulos++;
        }

        //=) pq nao deixar sem condiçao e deixar q a unica forma de sair é com break ?

        for(int i = 2; i * 2 <= M; i++){
            for(int l = 1; l < i && (i * 2 + l) <= M; l++){
                triangulos++;
                System.out.println("Aqui no isoceles deu com: " + i + " " + i + " " + l + "; no final com: " + ((i * 2) + l));
            }
        }

        for(int i = 2; (i * 2) + (i + 1) <= M; i++){
                triangulos++;
                System.out.println("Aqui no retangulo deu com: " + i + " " + i + " " + (i + 1) + "; no final com: " + ((i * 2) + (i + 1)));
        }

        for(int i = 3; i < M; i++){
            for(int l = 2; l < i; l++){
                for(int j = 1; j < l && (i + l + j) <= M; j++){
                    if(l + j > i){
                        triangulos++;
                        System.out.println("Aqui no triangulo la coisado com lados diferentes e tal deu com valores: " + i + " " + l + " " + j + "; no final com: " + (i + l + j));    
                    }
                }
            }
        }
        
        return triangulos;
    }
    */

    public static int criandoTriangulos(int M){
        int triangulo = 0;

        //regra dos 3 lados
        //for(int i = 1; i + 2 < M; i++){
        //    for(int j = 1; j <= i && (i + j) <= M; j++){
        //        for(int l = 1; l <= j && (i + l + j) <= M; l++){
        //            if(l + j > i && l + i > j && j + i > l){
        //                triangulo++;
        //                //System.out.println("Aqui no triangulo la coisado com lados diferentes e tal deu com valores: " + i + " " + l + " " + j + "; no final com: " + (i + l + j));    
        //            }
        //        }
        //    }
        //}

        //for(int i = (M / 2); i > 0; i--){
        //    for(int j = i; j > 0; j--){
        //        for(int l = j; l > 0; l--){
        //            if(l + j > i && l + i > j && j + i > l && (i + l + j) <= M){
        //                triangulo++;
        //                //System.out.println("Aqui no triangulo la coisado com lados diferentes e tal deu com valores: " + i + " " + l + " " + j + "; no final com: " + (i + l + j));    
        //            }
        //        }
        //    }
        //}

        for(int i = (M / 2); i > 0; i--){
            for(int j = i; j > 0; j--){
                for(int l = j; l > 0; l--){
                    if(l + j > i && (i + l + j) <= M){
                        triangulo++;
                        //System.out.println("Aqui no triangulo la coisado com lados diferentes e tal deu com valores: " + i + " " + l + " " + j + "; no final com: " + (i + l + j));    
                    }
                }
            }
        }

        return triangulo;
    }
}
