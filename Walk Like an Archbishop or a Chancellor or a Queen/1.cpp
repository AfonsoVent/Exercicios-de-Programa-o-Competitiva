#include "iostream"
#include "fstream"
#include "string"


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
    string line;

    // Code
    start(inputFil);

    inputFil.close();
    return 0;
}

