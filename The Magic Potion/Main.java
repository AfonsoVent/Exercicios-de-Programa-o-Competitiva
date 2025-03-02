import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //Agora sao globais.
    static int N, P, nPocao;
    static int[][] posicaoPotions;
    static int[][] posicaoHouses;
    static boolean[] combinacoesCertas;
    static int[][] coordenadasUsadas;

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        P = Integer.parseInt(reader.readLine());

        posicaoPotions = new int[P][2];
        posicaoHouses = new int[P][2];
        combinacoesCertas = new boolean[P];

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
            coordenadasUsadas[posicaoHouses[i][0]][posicaoHouses[i][1]] = P + 1;
            coordenadasUsadas[posicaoPotions[i][0]][posicaoPotions[i][1]] = P + 1;
        }
    }

    //Se a ultima casa nao chegou a ter a sua pocao, returna false
    public static boolean casasComPocoes(){
        return combinacoesCertas[P - 1];
    }

    public static String horaDeAventura(){
        coordenadasUsadas = new int[N][N];

        ocuparEspaçosIniciais();
        inicioDeUmaAventura();

        String respFinal = "Alesia";
        if(casasComPocoes()){
            respFinal = "Toutatis";
        }

        return respFinal;
    }

    //Identifica se a posicao esta livre para andar
    public static boolean posicaoLivre(int posicaoAvaliadaX, int posicaoAvaliadaY, char direction){ 
        //Passou dos limites do mapa?
        if(posicaoAvaliadaX >= N || posicaoAvaliadaX < 0 || posicaoAvaliadaY >= N || posicaoAvaliadaY < 0){
            return false;
        }

        //Verificar se nao tem adjacencia:
        switch (direction) {
            case 'c':
                //Verificar esquerda, cima, direita
                if(
                (posicaoAvaliadaX > 0 && coordenadasUsadas[posicaoAvaliadaX - 1][posicaoAvaliadaY] == (nPocao + 1)) ||
                (posicaoAvaliadaY + 1 < N && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY + 1] == (nPocao + 1)) ||
                (posicaoAvaliadaX + 1 < N && coordenadasUsadas[posicaoAvaliadaX + 1][posicaoAvaliadaY] == (nPocao + 1))){
                    return false;
                }
                break;
            case 'e':
                //Verificar cima, baixo, esquerda
                if(
                (posicaoAvaliadaY + 1 < N && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY + 1] == (nPocao + 1)) ||
                (posicaoAvaliadaY > 0 && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY - 1] == (nPocao + 1)) ||
                (posicaoAvaliadaX > 0 && coordenadasUsadas[posicaoAvaliadaX - 1][posicaoAvaliadaY] == (nPocao + 1))){
                    return false;
                }
                break;
            case 'b':
                //Verificar esquerda, baixo, direita
                if(
                (posicaoAvaliadaX > 0 && coordenadasUsadas[posicaoAvaliadaX - 1][posicaoAvaliadaY] == (nPocao + 1)) || 
                (posicaoAvaliadaY > 0 && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY - 1] == (nPocao + 1)) || 
                (posicaoAvaliadaX + 1 < N && coordenadasUsadas[posicaoAvaliadaX + 1][posicaoAvaliadaY] == (nPocao + 1))){
                    return false;
                }
                break;
            case 'd':
                //Verificar direita, baixo, cima
                if(
                (posicaoAvaliadaX + 1 < N && coordenadasUsadas[posicaoAvaliadaX + 1][posicaoAvaliadaY] == (nPocao + 1)) || 
                (posicaoAvaliadaY > 0 && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY - 1] == (nPocao + 1)) || 
                (posicaoAvaliadaY + 1 < N && coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY + 1] == (nPocao + 1))){
                    return false;
                }
                break;
        }

        if(moradorAcharPocao(posicaoAvaliadaX, posicaoAvaliadaY)){
            acheiPosicao(posicaoAvaliadaX, posicaoAvaliadaY);
        }

        //0: ninguem esta a usar esse caminho
        if(coordenadasUsadas[posicaoAvaliadaX][posicaoAvaliadaY] != 0){
            return false;
        }

        return true;
    }

    public static void inicioDeUmaAventura(){
        //Andar para cima
        entrePassosDeUmaAventura(posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] + 1), 'c');
        //Andar para a esquerda
        entrePassosDeUmaAventura((posicaoHouses[nPocao][0] - 1), posicaoHouses[nPocao][1], 'e');
        //Andar para baixo
        entrePassosDeUmaAventura(posicaoHouses[nPocao][0], (posicaoHouses[nPocao][1] - 1), 'b');
        //Andar para a direita
        entrePassosDeUmaAventura((posicaoHouses[nPocao][0] + 1), posicaoHouses[nPocao][1], 'd');
    }

    public static void entrePassosDeUmaAventura(int posicaoAndadaX, int posicaoAndadaY, char direction){
        //validar
        if(!posicaoLivre(posicaoAndadaX, posicaoAndadaY, direction)){
            return;
        }

        //enviar o passo
        coordenadasUsadas[posicaoAndadaX][posicaoAndadaY] = (nPocao + 1);
        meioDeUmaAventura(posicaoAndadaX, posicaoAndadaY);
        coordenadasUsadas[posicaoAndadaX][posicaoAndadaY] = 0;
    }

    //Se o Morador da casa achar a pocao
    public static boolean moradorAcharPocao(int posicaoAvaliadaX, int posicaoAvaliadaY){
        return posicaoPotions[nPocao][0] == posicaoAvaliadaX && posicaoPotions[nPocao][1] == posicaoAvaliadaY;
    }

    public static void meioDeUmaAventura(int posicaoAndadaX, int posicaoAndadaY){
        //Andar para cima
        entrePassosDeUmaAventura(posicaoAndadaX, (posicaoAndadaY + 1), 'c');
        //Andar para a esquerda
        entrePassosDeUmaAventura((posicaoAndadaX - 1), posicaoAndadaY, 'e');
        //Andar para baixo
        entrePassosDeUmaAventura(posicaoAndadaX, (posicaoAndadaY - 1), 'b');
        //Andar para a direita
        entrePassosDeUmaAventura((posicaoAndadaX + 1), posicaoAndadaY, 'd');
    }

    public static void acheiPosicao(int posicaoAndadaX, int posicaoAndadaY){
        combinacoesCertas[nPocao] = true;
        nPocao++;

        if(nPocao < P){
            inicioDeUmaAventura();
        }

        if(nPocao == P && combinacoesCertas[nPocao - 1]){
            finalDaAventura();
        }

        //Se o da frente dele esta com falso, é porque algum nó nao conseguio fazer o caminho
        if(nPocao < P && !combinacoesCertas[nPocao]){
            combinacoesCertas[nPocao - 1] = false;
        }
        
        nPocao--;
        return;
    }

    public static void finalDaAventura(){
        System.out.println("Toutatis");
        System.exit(0);
    }
}