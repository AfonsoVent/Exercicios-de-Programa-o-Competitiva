import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class try1 {
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
            //System.out.println(valoresAreasCinzas[i]);
        }

        String[] infoDoPadrao = reader.readLine().split(" ");
        int[] valoresDoPadrao = new int[numDoPadraoDeGotas];

        for(int i = 0; i < numDoPadraoDeGotas; i++){
            valoresDoPadrao[i] = Integer.parseInt(infoDoPadrao[i]);
            //System.out.println(valoresDoPadrao[i]);
        }

        System.out.println(contaGotasCinzentas(velAngular, anguloTorneira, tempVisto, numDeAngulos, numDoPadraoDeGotas, valoresAreasCinzas, valoresDoPadrao));
    }

    public static int contaGotasCinzentas(int velAngular, int anguloTorneira , int tempVisto , int numDeAngulos , int numDoPadraoDeGotas, int[] valoresAreasCinzas, int[] valoresDoPadrao){
        int gotasAcertadas = 0;

        for(int i = 0; i < tempVisto; i++){
            //if angulo de torneira for dentro ou igual a cinza é +1
            if(valoresDoPadrao[i % numDoPadraoDeGotas] == 1){
                for(int j = 0; j < numDeAngulos; j+=2){
                    if(valoresAreasCinzas[j] > valoresAreasCinzas[j + 1]){
                        if((valoresAreasCinzas[j] <= anguloTorneira && anguloTorneira <= 360) || (0 <= anguloTorneira && anguloTorneira <= valoresAreasCinzas[j + 1])){
                            gotasAcertadas++;
                            //System.out.println("cai no tempo: " + i + " no " + (j + 1) + "º setor circular");
                            break;
                        }
                    }
                    else{
                        if(valoresAreasCinzas[j] <= anguloTorneira && anguloTorneira <= valoresAreasCinzas[j + 1]){
                            gotasAcertadas++;
                            //System.out.println("cai no tempo: " + i + " no " + (j + 1) + "º setor circular");
                            break;
                        }    
                    }
                }
            }
            
            //passar 1 tempo: i++; atualizar angulos do prato. (com o cuidado de 360 graus)
            for(int j = 0; j < numDeAngulos; j++){
                valoresAreasCinzas[j] = (velAngular + valoresAreasCinzas[j]) % 360;
                
            }
        }
        //gotas estao a sair no tempo certo => loop principal correto; condição if certa
        //System.out.println(teste + " Gotas foram aceites");

        return gotasAcertadas;
    }
}
