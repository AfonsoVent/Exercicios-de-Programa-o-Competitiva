#include "iostream"
#include "fstream"
#include "string"


// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

// Objetivo, ler um ficheiro, replicar conteudo

int main(int argc, char const *argv[])
{
    // Nome da variavel: inputFil
    ifstream inputFil("input.txt");

    // Verifica se foi aberto
    if(!inputFil.is_open()){
        cerr << "Nao foi aberto\n";
        return 1;
    }
    
    // Criação de variavel
    string line;

    // coloca (cada linha?) na variavel
    while(getline(inputFil, line)) {
        cout << line;
    }

    // fecha o file
    inputFil.close();
    return 0;
}
