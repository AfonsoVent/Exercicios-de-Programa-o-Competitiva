import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String[] companhias = reader.readLine().split(" ");
        int M = Integer.parseInt(reader.readLine());

        String[] portForces = listaDePortForces();

        maxVoosCancelados(N, companhias, M, portForces, reader);
    }

    //caso a lista no futuro seja alterada Ã© so alterar os valores inseridos nesta funÃ§ao
    public static String[] listaDePortForces(){
        String[] listaNovember2023 = new String[5];

        listaNovember2023[0] = "Lisbon";
        listaNovember2023[1] = "Porto";
        listaNovember2023[2] = "Faro";
        listaNovember2023[3] = "Funchal";
        listaNovember2023[4] = "Beja";

        return listaNovember2023;
    }

    public static void maxVoosCancelados(int N, String[] companhias, int M, String[] portForces, BufferedReader reader) throws IOException{
        String[] informacoes = new String[7];
        boolean eDaCompanhia;
        List<Integer> listDeDias = new ArrayList<Integer>();
        boolean boolDay1 = false;
        boolean boolDay2 = false;

        for(int i = 0; i < M; i++){
            eDaCompanhia = false;
            informacoes = reader.readLine().split(" ");

            //Pertence as PortForce?
            for(int j = 0; j < N; j++){
                if(informacoes[0].equals(companhias[j])){
                    eDaCompanhia = true;
                    break;
                }
            }

            for(int j = 0; j < portForces.length && eDaCompanhia == true; j++){
                if(informacoes[1].equals(portForces[j])){
                    boolDay1 = true;
                }
                if(informacoes[4].equals(portForces[j])){
                    boolDay2 = true;
                }
                if(boolDay1 && boolDay2){
                    break;
                }
            }

            if((boolDay1 || boolDay2) && eDaCompanhia == true){
                if(boolDay1 && boolDay2){
                    listDeDias.add(Integer.parseInt(informacoes[2]));
                    if(!informacoes[2].equals(informacoes[5])){
                        listDeDias.add(Integer.parseInt(informacoes[5]));
                    }
                }
                else if(boolDay1){
                    listDeDias.add(Integer.parseInt(informacoes[2]));
                }
                else{
                    listDeDias.add(Integer.parseInt(informacoes[5]));
                }       

                boolDay1 = false;
                boolDay2 = false;
            }
            
        }

        int freqMaxDia = 0;
        int dia = 1;

        //TODO: quando encontrar um dia max, ver se esse dia é menor que 'dia'.
        //Ate onde entendi: achar o primeiro dia (nota: eu juro q pensava que ja estava assim)
        
        for (int i = 0; i < listDeDias.size(); i++) {
            int freq = 0;
            for (int j = 0; j < listDeDias.size(); j++) {
                if (listDeDias.get(i) == listDeDias.get(j)) {
                freq++;
                }
            }

            //se a freqMax atualizou, o dia tem que mudar
            if (freq > freqMaxDia) {
                freqMaxDia = freq;
                dia = listDeDias.get(i);
            }
            //se a freqMax apanhou outro dia igual, o dia pode ou nao mudar
            if(freq == freqMaxDia && listDeDias.get(i) < dia) {
                dia = listDeDias.get(i);
            }
        }

        System.out.println(dia);
        System.out.println(freqMaxDia);
    }
}
