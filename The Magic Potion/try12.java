import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try12 {
    //Agora sao globais.
    static int N, P;
    static int[][] posicaoPotions;
    static int[][] posicaoHouses;
    static boolean[] combinacoesCertas;
    static boolean[][] coordenadasUsadas;

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
    public static void ocuparEspaçosIniciais(){
        for(int i = 0; i < P; i++){
            coordenadasUsadas[posicaoHouses[i][0]][posicaoHouses[i][1]] = true;
        }
        for(int i = 0; i < P; i++){
            coordenadasUsadas[posicaoPotions[i][0]][posicaoPotions[i][1]] = true;
        }
    }

    //Se a ultima casa nao chegou a ter a sua pocao, returna false
    public static boolean casasComPocoes(){
        if(combinacoesCertas[P - 1]){
            return true;
        }
        return false;
    }

    public static String horaDeAventura(){
        String respFinal = "Alesia";
        coordenadasUsadas = new boolean[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                coordenadasUsadas[i][j] = false;
            }
        }

        ocuparEspaçosIniciais();

        inicioDeUmaAventura(0);

        if(casasComPocoes()){
            respFinal = "Toutatis";
        }

        return respFinal;
    }

    //Identifica se a posicao esta livre para andar
    public static boolean posicaoLivre(int posicaoAvaliadaX, int posicaoAvaliadaY){
        if(posicaoAvaliadaX >= N || posicaoAvaliadaX < 0 || posicaoAvaliadaY >= N || posicaoAvaliadaY < 0){
            return false;
        }

        if(coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY]){
            return false;
        }

        return true;
    }

    public static void inicioDeUmaAventura(int nPocao){
        //Se o valor passar do limite, quer dizer que nao tem mais nada para buscar
        if(nPocao >= P){
            return;
        }
        
        //Remover a coordenada que será buscada
        coordenadasUsadas[posicaoPotions[nPocao][0]][posicaoPotions[nPocao][1]] = false;
        
        //Andar para a direita
        entrePassosDeUmaAventura(nPocao, (posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1]);
        //Andar para cima
        entrePassosDeUmaAventura(nPocao, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1));
        //Andar para a esquerda
        entrePassosDeUmaAventura(nPocao, (posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1]);
        //Andar para baixo
        entrePassosDeUmaAventura(nPocao, posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1));
    }

    public static void entrePassosDeUmaAventura(int nPocao, int posicaoAndadaX, int posicaoAndadaY){
        //validar
        if(!posicaoLivre(posicaoAndadaX, posicaoAndadaY)){
            return;
        }

        //enviar o passo
        coordenadasUsadas[posicaoAndadaX][posicaoAndadaY] = true;
        // System.out.println("Explorar posição: (" + posicaoAndadaX + "," + posicaoAndadaY + ") para a poção " + (nPocao + 1));

        meioDeUmaAventura(nPocao, posicaoAndadaX, posicaoAndadaY);

        coordenadasUsadas[posicaoAndadaX][posicaoAndadaY] = false;
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

    public static void meioDeUmaAventura(int nPocao, int posicaoAndadaX, int posicaoAndadaY){
        if(moradorAcharPocao(nPocao, posicaoAndadaX, posicaoAndadaY)){
            combinacoesCertas[nPocao] = true;
            nPocao++;

            inicioDeUmaAventura(nPocao);

            //Se o da frente dele esta com falso, é porque algum nó nao conseguio fazer o caminho
            if(nPocao < P && !combinacoesCertas[nPocao]){
                combinacoesCertas[nPocao - 1] = false;
            }

            // if(nPocao - 1 == P && combinacoesCertas[nPocao]){
            //     System.out.println("Toutatis");
            //     System.exit(0);
            // }

            return;
        }
        
        //Andar para a direita
        entrePassosDeUmaAventura(nPocao, (posicaoAndadaX + 1), posicaoAndadaY);
        //Andar para cima
        entrePassosDeUmaAventura(nPocao, posicaoAndadaX, (posicaoAndadaY + 1));
        //Andar para a esquerda
        entrePassosDeUmaAventura(nPocao, (posicaoAndadaX - 1), posicaoAndadaY);
        //Andar para baixo
        entrePassosDeUmaAventura(nPocao, posicaoAndadaX, (posicaoAndadaY - 1));
    }
}