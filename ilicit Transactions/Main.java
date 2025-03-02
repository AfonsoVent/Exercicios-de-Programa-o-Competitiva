import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        System.out.println(avaliadorTrans(N, reader));
    }

    public static char avaliadorTrans(int N, BufferedReader reader) throws IOException{
        Stack<Integer> trasacoes = new Stack<>();
        char respFinal = 'y';
        int M;

        for(int i = 0; i < N; i++){
            M = Integer.parseInt(reader.readLine());

            if(M < 0){
                //Tentou retirar um valor da pilha, sem ao menos ter algo nela? Nao pode!
                if(trasacoes.empty()){
                    respFinal = 'n';
                    break;
                }
                //Verificamos se o topo da pilha é igual ao valor que quer ser retirado. Caso seja, ele é retirado
                else if(trasacoes.peek() == Math.abs(M)){
                    trasacoes.pop();
                }
                //Caso não seja, a trans é invalida
                else{
                    respFinal = 'n';
                    break;
                }
            }
            //colocar o valor na pilha se ele for positivo
            else{
                trasacoes.push(M);
            }
        }

        //Se nao ficou vazio é porque falto retirar valores, ou seja é invalida
        if(!trasacoes.empty()){
            respFinal = 'n';
        }

        return respFinal;
    }
}
