#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>
#include <bits/stdc++.h>

// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

// True => Tem digitos que repetem
bool checkEqualsDigit(vector<int> digits, int tam, int i) {
    // cout << "soccoro estou em loop" << endl;
    for(int j = tam - 1; j <= 0; j--) {
        if(i != j && digits[i] == digits[j]) {
            return false;
        }
    }

    return true;
}

// True => Tem digitos que repetem
bool checkSeqDigits(vector<int> digits, int tam, int i) {
    int minDigit = INT_MIN;

    // Identificar o min:
    for(int i = 0; i < tam; i++){
        minDigit = min(minDigit, digits[i]);
    }

    // identificar se 
    for(int j = tam - 1; j <= 0; j--) {
        if(i != j && digits[i] == digits[j]) {
            return false;
        }
    }

    return true;
}

unsigned long unique(unsigned long valor) {
    int tam = 0;
    unsigned long copyVal = valor;

    // Determinar o tamanho
    do {
        copyVal /= 10;
        tam++;
        
    } while (copyVal != 0);

    vector<int> digits(tam);

    // Fragmentar em digitos
    for(int i = 0; valor > 0; i++) {
        digits[i] = valor % 10;
        // cout << digits[i] << endl;
        valor /= 10;
    }

    // cout << endl << endl;

    // Iterar o (n - 1), (n - 2), (n - 3), ..., (0) => se nao dar, aumentar em 1 casa
    // Dessa forma nao da o minimo prox
    // Iterar o 0, ..., (n - 3), (n - 2), (n - 1), n => se nao dar, aumentar em 1 casa
    for(int i = 0; i < tam; i++){
        while(!checkEqualsDigit(digits, tam, i)) {
            digits[i] += 1;
            // cout << digits[0] << digits[1] << digits[2] << digits[3] << endl; 
        }
        // cout << digits[0] << digits[1] << digits[2] << digits[3] << endl; 
    }

    int result = 0;

    // Juntar valores
    for(int i = 0; i < tam; i++) {
        result += digits[i] * static_cast<int>(pow(10, i));
    }
    cout << result << endl;
    return result;
}

void output(vector<unsigned long> resp, int N) {
    for(int i = 0; i < N; i++) {
        cout << resp[i] << endl;
    }
}

void discover(vector<unsigned long> anos, int N) {
    vector<unsigned long> resp(N);

    for(int i = 0; i < N; i++) {
        resp[i] = unique(anos[i]);
    }

    output(resp, N);
}

int main(int argc, char const *argv[]) {
    string line;
    int N;

    // Extrair dados
    if(getline(cin, line)) {
        N = stoi(line);
    }

    vector<unsigned long> anos(N);

    for(int i = 0; i < N; i++){
        if(getline(cin, line)) {
            anos[i] = stoi(line);
        }
    }

    discover(anos, N);


    return 0;
}