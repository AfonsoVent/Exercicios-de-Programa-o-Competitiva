#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>


// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

int mindA(string start, vector<string> toCover) {
    return 4;
}

int mindC(string start, vector<string> toCover) {
    return 6;
}

int mindQ(string start, vector<string> toCover) {
    return 4;
}

// Ter atenção ao nivel de prioridade
void output(vector<int> steps){
    int min = INT_MAX;
    string buffer;

    for(int i = 0; i < 3; i++){
        if(steps[i] < min){
            min = steps[i];
            if(i == 0){
                buffer = 'A';
            }
            if(i == 1){
                buffer = 'C';
            }
            if(i == 2){
                buffer = 'Q';
            }
        }
        else if(steps[i] == min) {
            if(i == 0){
                buffer += 'A';
            }
            if(i == 1){
                buffer += 'C';
            }
            if(i == 2){
                buffer += 'Q';
            }
        }
    }

    cout << min << ' ' << buffer << endl;
}

// Função onde o codigo de verdade começa
void letsDance(string start, vector<string> toCover) {
    vector<int> steps(3);

    steps[0] = mindA(start, toCover);
    steps[1] = mindC(start, toCover);
    steps[2] = mindQ(start, toCover);

    output(steps);
}

int main(int argc, char const *argv[]) {
    ifstream inputFil("input.txt");    
    string line, start;
    int games, numberPos;

    // Extrair dados
    if(getline(inputFil, line)) {
        games = stoi(line);
    }

    for(int i = 0; i < games; i++){
        if(getline(inputFil, line)) {
            stringstream ss(line); // NUMERO DE s => numero de separaçoes que vai fazer
            ss >> numberPos >> start; //Colocar o S1 => numberPos; colocar o s2 => start
        }

        vector<string> toCover(numberPos);

        for(int i = 0; i < numberPos; i++){
            if (getline(inputFil, line)) {
                stringstream ss(line);
                ss >> toCover[i];
            }
        }

        //Só verificar output
        // cout << games << endl;
        // cout << numberPos << ' ' << start[0] << start[1] << endl;
        // cout << toCover[0][0] << toCover[0][1] << endl;
        // cout << toCover[1][0] << toCover[1][1] << endl;
        // cout << toCover[2][0] << toCover[2][1] << endl;
    
        letsDance(start, toCover);
    }

    inputFil.close();
    return 0;
}

