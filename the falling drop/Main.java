import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//TODO: EM VEZ DE COLOCAR TODS AS VARIAVEIS NUMDEANGULOS * 2, FAZER ELA MESMA ANTES DE ENTRAR NA FUNÇAO SER MULTIPLICADA POR 2

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int velAngular = Integer.parseInt(reader.readLine());
        int anguloTorneira = Integer.parseInt(reader.readLine());
        int tempVisto = Integer.parseInt(reader.readLine());
        int numDeAngulos = Integer.parseInt(reader.readLine());
        int numDoPadraoDeGotas = Integer.parseInt(reader.readLine());

        String[] infoAreasCinzas = reader.readLine().split(" ");
        int[] valoresAreasCinzas = new int[numDeAngulos * 2];

        for(int i = 0; i < numDeAngulos * 2; i++){
            valoresAreasCinzas[i] = Integer.parseInt(infoAreasCinzas[i]);
        }

        String[] infoDoPadrao = reader.readLine().split(" ");
        int[] valoresDoPadrao = new int[numDoPadraoDeGotas];

        for(int i = 0; i < numDoPadraoDeGotas; i++){
            valoresDoPadrao[i] = Integer.parseInt(infoDoPadrao[i]);
        }

        System.out.println(contaGotasCinzentas(velAngular, anguloTorneira, tempVisto, numDeAngulos, numDoPadraoDeGotas, valoresAreasCinzas, valoresDoPadrao));
    }

    public static int contaGotasCinzentas(int velAngular, int anguloTorneira , int tempVisto , int numDeAngulos , int numDoPadraoDeGotas, int[] valoresAreasCinzas, int[] valoresDoPadrao){
        int gotasAcertadas = 0;
        boolean gotasForaDoBranco = true;

        for(int i = 0; i < tempVisto && gotasForaDoBranco; i++){
            //if angulo de torneira for dentro ou igual a cinza é +1
            if(valoresDoPadrao[i % numDoPadraoDeGotas] == 1){
                for(int j = 0; j < numDeAngulos * 2; j+=2){
                    //angulos de x -> 360 U 0 -> x
                    //System.out.println("Suposto primeiro valro: " + valoresAreasCinzas[j] + "; Suposto segundo valor: " + valoresAreasCinzas[j + 1]);

                    if(valoresAreasCinzas[j] > valoresAreasCinzas[j + 1]){
                        if((valoresAreasCinzas[j] <= anguloTorneira && anguloTorneira <= 360) || (0 <= anguloTorneira && anguloTorneira <= valoresAreasCinzas[j + 1])){
                            gotasAcertadas++;
                            gotasForaDoBranco = true;
                            break;
                        }
                        else{
                            gotasForaDoBranco = false;
                        }
                    }
                    else{
                        if(valoresAreasCinzas[j] <= anguloTorneira && anguloTorneira <= valoresAreasCinzas[j + 1]){
                            gotasAcertadas++;
                            gotasForaDoBranco = true;
                            break;
                        }    
                        else{
                            gotasForaDoBranco = false;
                        }
                    }
                }
            }
            
            //passar 1 tempo: i++; atualizar angulos do prato. (com o cuidado de 360 graus)
            for(int j = 0; j < numDeAngulos * 2; j++){
                valoresAreasCinzas[j] = (velAngular + valoresAreasCinzas[j]) % 360;
                //System.out.println(valoresAreasCinzas[j]);
            }
            //System.out.println(" ");
        }
        //gotas estao a sair no tempo certo => loop principal correto; condição if certa
        //System.out.println(teste + " Gotas foram aceites");

        //if(gotasForaDoBranco == false){
        //    System.out.println("fugi");
        //}

        return gotasAcertadas;
    }
}
