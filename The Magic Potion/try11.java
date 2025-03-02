import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try11 {
    //Agora sao globais.
    static int N, P;
    static int[][] posicaoPotions;
    static int[][] posicaoHouses;
    static boolean[] combinacoesCertas;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        P = Integer.parseInt(reader.readLine());

        posicaoPotions = new int[P][2];
        posicaoHouses = new int[P][2];
        combinacoesCertas = new boolean[P];

        for(int i = 0; i < P; i++){
            combinacoesCertas[i] = false;
        }

        for(int i = 0; i < P; i++){
            String[] informacoes = reader.readLine().split(" ");

            posicaoPotions[i][0] = Integer.parseInt(informacoes[0]); 
            posicaoPotions[i][1] = Integer.parseInt(informacoes[1]); 
            posicaoHouses[i][0] = Integer.parseInt(informacoes[2]); 
            posicaoHouses[i][1] = Integer.parseInt(informacoes[3]); 
        }

        System.out.println(horaDeAventura());
    }

    //Ocupar os espaços que tem casas e pocoes logo no inicio
    public static void ocuparEspaçosIniciais(boolean[][] coordenadasUsadas){
        for(int i = 0; i < P; i++){
            coordenadasUsadas[posicaoHouses[i][0]][posicaoHouses[i][1]] = true;
        }
        for(int i = 0; i < P; i++){
            coordenadasUsadas[posicaoPotions[i][0]][posicaoPotions[i][1]] = true;
        }
    }

    //Se alguma casa nao chegou a ter a sua pocao, returna false
    public static boolean casasComPocoes(){
        if(combinacoesCertas[P - 1]){
            return true;
        }
        return false;
    }

    public static String horaDeAventura(){
        String respFinal = "Alesia";
        boolean[][] coordenadasUsadas = new boolean[N][N];

        ocuparEspaçosIniciais(coordenadasUsadas);

        inicioDeUmaAventura(0, coordenadasUsadas);

        if(casasComPocoes()){
            respFinal = "Toutatis";
        }

        return respFinal;
    }

    //Identifica se a posicao esta livre para andar
    public static boolean posicaoLivre(int posicaoAvaliadaX, int posicaoAvaliadaY, boolean[][] coordenadasUsadas){
        if(coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY]){
            return false;
        }
        return true;
    }

    public static void inicioDeUmaAventura(int nPocao, boolean[][] coordenadasUsadas){
        //Se o valor passar do limite, quer dizer que nao tem mais nada para buscar
        if(nPocao >= P){
            return;
        }
        
        //Remover a coordenada que será buscada
        coordenadasUsadas[posicaoPotions[nPocao][0]][posicaoPotions[nPocao][1]] = false;
        
        //Andar para cima
        if(posicaoHouses[nPocao][1] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] + 1, coordenadasUsadas)){
            coordenadasUsadas[posicaoHouses[nPocao][0]][posicaoHouses[nPocao][1] + 1] = true;            

            meioDeUmaAventura(nPocao, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), coordenadasUsadas);

            coordenadasUsadas[posicaoHouses[nPocao][0]][posicaoHouses[nPocao][1] + 1] = false;            
        }

        //Andar para a esquerda
        if(posicaoHouses[nPocao][0] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0] - 1, posicaoHouses[nPocao][1], coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoHouses[nPocao][0] - 1][posicaoHouses[nPocao][1]] = true; 
            
            meioDeUmaAventura(nPocao, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], coordenadasUsadas);

            coordenadasUsadas[posicaoHouses[nPocao][0] - 1][posicaoHouses[nPocao][1]] = false; 
        }

        //Andar para baixo
        if(posicaoHouses[nPocao][1] - 1 >= 0 && posicaoLivre(posicaoHouses[nPocao][0], posicaoHouses[nPocao][1] - 1, coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoHouses[nPocao][0]][posicaoHouses[nPocao][1] - 1] = true; 

            meioDeUmaAventura(nPocao, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), coordenadasUsadas);

            coordenadasUsadas[posicaoHouses[nPocao][0]][posicaoHouses[nPocao][1] - 1] = false; 
        }

        //Andar para a direita
        if(posicaoHouses[nPocao][0] + 1 < N && posicaoLivre(posicaoHouses[nPocao][0] + 1, posicaoHouses[nPocao][1], coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoHouses[nPocao][0] + 1][posicaoHouses[nPocao][1]] = true; 

            meioDeUmaAventura(nPocao, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], coordenadasUsadas);

            coordenadasUsadas[posicaoHouses[nPocao][0] + 1][posicaoHouses[nPocao][1]] = false; 
        }
    }

    //Se o Morador da casa achar a pocao
    public static boolean moradorAcharPocao(int nPocao, int posicaoAvaliadaX, int posicaoAvaliadaY){
        if(posicaoPotions[nPocao][0] == posicaoAvaliadaX && posicaoPotions[nPocao][1] == posicaoAvaliadaY){
            return true;
        }
        else {
            return false;
        }
    }

    public static void meioDeUmaAventura(int nPocao, int posicaoAndadaX, int posicaoAndadaY, boolean[][] coordenadasUsadas){
        if(moradorAcharPocao(nPocao, posicaoAndadaX, posicaoAndadaY)){
            combinacoesCertas[nPocao] = true;
            nPocao++;
            inicioDeUmaAventura(nPocao, coordenadasUsadas);

            //Se o da frente dele esta com falso, é porque algum nó nao conseguio fazer o caminho
            if(nPocao < P && !combinacoesCertas[nPocao]){
                combinacoesCertas[nPocao - 1] = false;
            }
            return;
        }
        
        //Andar para cima
        if(posicaoAndadaY + 1 < N && posicaoLivre(posicaoAndadaX, posicaoAndadaY + 1, coordenadasUsadas)){
            coordenadasUsadas[posicaoAndadaX][posicaoAndadaY + 1] = true; 

            meioDeUmaAventura(nPocao, posicaoAndadaX, (posicaoAndadaY + 1), coordenadasUsadas);
            
            coordenadasUsadas[posicaoAndadaX][posicaoAndadaY + 1] = false; 
        }

        //Andar para a esquerda
        if(posicaoAndadaX - 1 >= 0 && posicaoLivre(posicaoAndadaX - 1, posicaoAndadaY, coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoAndadaX - 1][posicaoAndadaY] = true; 

            meioDeUmaAventura(nPocao, (posicaoAndadaX - 1), posicaoAndadaY, coordenadasUsadas);

            coordenadasUsadas[posicaoAndadaX - 1][posicaoAndadaY] = false; 
        }

        //Andar para baixo
        if(posicaoAndadaY - 1 >= 0 && posicaoLivre(posicaoAndadaX, posicaoAndadaY - 1, coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoAndadaX][posicaoAndadaY - 1] = true; 

            meioDeUmaAventura(nPocao, posicaoAndadaX, (posicaoAndadaY - 1), coordenadasUsadas);

            coordenadasUsadas[posicaoAndadaX][posicaoAndadaY - 1] = false; 
        }

        //Andar para a direita
        if(posicaoAndadaX + 1 < N && posicaoLivre(posicaoAndadaX + 1, posicaoAndadaY, coordenadasUsadas) && !combinacoesCertas[nPocao]){
            coordenadasUsadas[posicaoAndadaX + 1][posicaoAndadaY] = true; 

            meioDeUmaAventura(nPocao, (posicaoAndadaX + 1), posicaoAndadaY, coordenadasUsadas);

            coordenadasUsadas[posicaoAndadaX + 1][posicaoAndadaY] = false; 
        }
    }
}