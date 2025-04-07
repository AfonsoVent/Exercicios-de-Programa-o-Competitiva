#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>


// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

void start(ifstream &inputFil) {
    string line;
    while (getline(inputFil, line)) {
        cout << line << endl;
    }
}

int main(int argc, char const *argv[]) {
    ifstream inputFil("input.txt");    
    string line, start;
    int games, numberPos;

    // Extrair dados
    if(getline(inputFil, line)) {
        games = stoi(line);
    }

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

    cout << games << endl;
    cout << numberPos << ' ' << start[0] << endl;
    cout << toCover[0] << endl;
    cout << toCover[1] << endl;
    cout << toCover[2] << endl;

    inputFil.close();
    return 0;
}

