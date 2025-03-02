import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        int P = Integer.parseInt(reader.readLine());

        boolean[] combinacoesCertas = new boolean[P];

        for(int i = 0; i < P; i++){
            combinacoesCertas[i] = false;
        }

        int[][] posicaoPotions = new int[P][2];
        int[][] posicaoHouses = new int[P][2];

        for(int i = 0; i < P; i++){
            String[] informacoes = reader.readLine().split(" ");

            posicaoPotions[i][0] = Integer.parseInt(informacoes[0]); 
            posicaoPotions[i][1] = Integer.parseInt(informacoes[1]); 
            posicaoHouses[i][0] = Integer.parseInt(informacoes[2]); 
            posicaoHouses[i][1] = Integer.parseInt(informacoes[3]); 
        }

        System.out.println(horaDeAventura(N, P, posicaoPotions, posicaoHouses, combinacoesCertas));
    }

    //Ocupar os espaços que tem casas e pocoes logo no inicio
    public static void ocuparEspaçosIniciais(int P, int[][] posicaoPotions, int[][] posicaoHouses, Set<String> coordenadasUsadas){
        for(int i = 0; i < P; i++){
            String casa = "(" + posicaoHouses[i][0] + ", " + posicaoHouses[i][1] + ")";
            coordenadasUsadas.add(casa);
        }
        for(int i = 0; i < P; i++){
            String pocao = "(" + posicaoPotions[i][0] + ", " + posicaoPotions[i][1] + ")";
            coordenadasUsadas.add(pocao);
        }
    }

    //Se alguma casa nao chegou a ter a sua pocao, returna false
    public static boolean casasComPocoes(int P, boolean[] combinacoesCertas){
        for(int i = 0; i < P; i++){
            if(!combinacoesCertas[i]){
                return false;
            }
        }
        return true;
    }

    public static String horaDeAventura(int N, int P, int[][] posicaoPotions, int[][] posicaoHouses, boolean[] combinacoesCertas){
        String respFinal = "Alesia";
        Set<String> coordenadasUsadas = new HashSet<>();

        ocuparEspaçosIniciais(P, posicaoPotions, posicaoHouses, coordenadasUsadas);

        inicioDeUmaAventura(N, P, 0, posicaoPotions, posicaoHouses, combinacoesCertas, coordenadasUsadas);

        if(casasComPocoes(P, combinacoesCertas)){
            respFinal = "Toutatis";
        }

        return respFinal;
    }

    //Identifica se a posicao esta livre para andar
    public static boolean posicaoLivre(int posicaoAvaliadaX, int posicaoAvaliadaY, Set<String> coordenadasUsadas){
        String posicaoAvaliada = "(" + posicaoAvaliadaX + ", " + posicaoAvaliadaY + ")";

        if(coordenadasUsadas.contains(posicaoAvaliada)){
            return false;
        }
        return true;
    }

    public static void inicioDeUmaAventura(int N, int P, int nPocao, int[][] posicaoPotions, int[][] posicaoHouses, boolean[] combinacoesCertas, Set<String> coordenadasUsadas){
        //Se o valor passar do limite, quer dizer que nao tem mais nada para buscar
        if(nPocao >= P){
            return;
        }
        
        //Remover a coordenada que será buscada
        String pocaoDesejada = "(" + posicaoPotions[nPocao][0] + ", " + posicaoPotions[nPocao][1] + ")";
        coordenadasUsadas.remove(pocaoDesejada);
        String primeiroAvanco;

        //Pocao esta a esquerda
        if(posicaoPotions[nPocao][0] < posicaoHouses[nPocao][0]){
            //Andar para a esquerda
            if(posicaoHouses[nPocao][0] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0] - 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] - 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);
                
                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para baixo
            if(posicaoHouses[nPocao][1] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] - 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] - 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a direita
            if(posicaoHouses[nPocao][0] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0] + 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] + 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para cima
            if(posicaoHouses[nPocao][1] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] + 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] + 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }
        }
        //pocao esta a direita
        else if(posicaoPotions[nPocao][0] >= posicaoHouses[nPocao][0]){
            //Andar para a direita
            if(posicaoHouses[nPocao][0] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0] + 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] + 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para cima
            if(posicaoHouses[nPocao][1] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] + 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] + 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a esquerda
            if(posicaoHouses[nPocao][0] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0] - 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] - 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);
                
                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para baixo
            if(posicaoHouses[nPocao][1] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] - 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] - 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }
        }
        //pocao esta abaixo
        else if(posicaoPotions[nPocao][1] < posicaoHouses[nPocao][1]){
            //Andar para baixo
            if(posicaoHouses[nPocao][1] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] - 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] - 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a direita
            if(posicaoHouses[nPocao][0] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0] + 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] + 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para cima
            if(posicaoHouses[nPocao][1] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] + 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] + 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a esquerda
            if(posicaoHouses[nPocao][0] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0] - 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] - 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);
                
                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }
        }
        //pocao esta acima
        else{
            //Andar para cima
            if(posicaoHouses[nPocao][1] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] + 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] + 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a esquerda
            if(posicaoHouses[nPocao][0] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0] - 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] - 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);
                
                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para baixo
            if(posicaoHouses[nPocao][1] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] - 1, coordenadasUsadas)){
                primeiroAvanco = "(" + posicaoHouses[nPocao][0] + ", " + (posicaoHouses[nPocao][1] - 1) + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }

            //Andar para a direita
            if(posicaoHouses[nPocao][0] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0] + 1, posicaoHouses[nPocao][1], coordenadasUsadas)){
                primeiroAvanco = "(" + (posicaoHouses[nPocao][0] + 1) + ", " + posicaoHouses[nPocao][1] + ")";
                coordenadasUsadas.add(primeiroAvanco);

                meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], combinacoesCertas, coordenadasUsadas);

                coordenadasUsadas.remove(primeiroAvanco);
            }
        }
    }

    //Se o Morador da casa achar a pocao
    public static boolean moradorAcharPocao(int nPocao, int[][] posicaoPotions, int posicaoAvaliadaX, int posicaoAvaliadaY){
        if(posicaoPotions[nPocao][0] == posicaoAvaliadaX && posicaoPotions[nPocao][1] == posicaoAvaliadaY){
            return true;
        }
        else {
            return false;
        }
    }

    public static void meioDeUmaAventura(int N, int P, int nPocao, int[][] posicaoPotions, int[][] posicaoHouses, int posicaoAndadaX, int posicaoAndadaY, boolean[] combinacoesCertas, Set<String> coordenadasUsadas){
        if(moradorAcharPocao(nPocao, posicaoPotions, posicaoAndadaX, posicaoAndadaY)){
            combinacoesCertas[nPocao] = true;
            nPocao++;
            inicioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, combinacoesCertas, coordenadasUsadas);
            nPocao--;
            return;
        }
        else{
            //Pocao esta a esquerda
            if(posicaoPotions[nPocao][0] < posicaoAndadaX){
                //Andar para a esquerda
                if(posicaoAndadaX - 1 >= 0 && posicaoLivre(posicaoAndadaX - 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX - 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX - 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);


                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para baixo
                if(posicaoAndadaY - 1 >= 0 && posicaoLivre(posicaoAndadaX, posicaoAndadaY - 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY - 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY - 1), combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a direita
                if(posicaoAndadaX + 1 < N && posicaoLivre(posicaoAndadaX + 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX + 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX + 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para cima
                if(posicaoAndadaY + 1 < N && posicaoLivre(posicaoAndadaX, posicaoAndadaY + 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY + 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);
        
                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY + 1), combinacoesCertas, coordenadasUsadas);
        
                    coordenadasUsadas.remove(meiosAvancos);
                }
            }
            //pocao esta na direita
            else if(posicaoPotions[nPocao][0] > posicaoAndadaX){
                //Andar para a direita
                if(posicaoAndadaX + 1 < N && posicaoLivre(posicaoAndadaX + 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX + 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX + 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }
                //Andar para cima
                if(posicaoAndadaY + 1 < N && posicaoLivre(posicaoAndadaX, posicaoAndadaY + 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY + 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);
        
                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY + 1), combinacoesCertas, coordenadasUsadas);
        
                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a esquerda
                if(posicaoAndadaX - 1 >= 0 && posicaoLivre(posicaoAndadaX - 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX - 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX - 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);


                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para baixo
                if(posicaoAndadaY - 1 >= 0 && posicaoLivre(posicaoAndadaX, posicaoAndadaY - 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY - 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY - 1), combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }
            }
            //pocao esta baixo
            else if(posicaoPotions[nPocao][1] > posicaoAndadaY){
                //Andar para baixo
                if(posicaoAndadaY - 1 >= 0 && posicaoLivre(posicaoAndadaX, posicaoAndadaY - 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY - 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY - 1), combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a direita
                if(posicaoAndadaX + 1 < N && posicaoLivre(posicaoAndadaX + 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX + 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX + 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para cima
                if(posicaoAndadaY + 1 < N && posicaoLivre(posicaoAndadaX, posicaoAndadaY + 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY + 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);
        
                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY + 1), combinacoesCertas, coordenadasUsadas);
        
                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a esquerda
                if(posicaoAndadaX - 1 >= 0 && posicaoLivre(posicaoAndadaX - 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX - 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX - 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);


                    coordenadasUsadas.remove(meiosAvancos);
                }
            }
            //pocao esta cima
            else{
                //Andar para cima
                if(posicaoAndadaY + 1 < N && posicaoLivre(posicaoAndadaX, posicaoAndadaY + 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY + 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);
        
                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY + 1), combinacoesCertas, coordenadasUsadas);
        
                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a esquerda
                if(posicaoAndadaX - 1 >= 0 && posicaoLivre(posicaoAndadaX - 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX - 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX - 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);


                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para baixo
                if(posicaoAndadaY - 1 >= 0 && posicaoLivre(posicaoAndadaX, posicaoAndadaY - 1, coordenadasUsadas)){
                    String meiosAvancos = "(" + posicaoAndadaX + ", " + (posicaoAndadaY - 1) + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, posicaoAndadaX, (posicaoAndadaY - 1), combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }

                //Andar para a direita
                if(posicaoAndadaX + 1 < N && posicaoLivre(posicaoAndadaX + 1, posicaoAndadaY, coordenadasUsadas)){
                    String meiosAvancos = "(" + (posicaoAndadaX + 1) + ", " + posicaoAndadaY + ")";
                    coordenadasUsadas.add(meiosAvancos);

                    meioDeUmaAventura(N, P, nPocao, posicaoPotions, posicaoHouses, (posicaoAndadaX + 1), posicaoAndadaY, combinacoesCertas, coordenadasUsadas);

                    coordenadasUsadas.remove(meiosAvancos);
                }
            }
        }
    }
}