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
    for(int j = tam - 1; j <= 0; j--) {
        if(i != j && digits[i] == digits[j]) {
            return false;
        }
    }

    return true;
}

// True => Sao uma sequencia
bool checkSeqDigits(vector<int> digits, int tam) {
    int minDigit = INT_MAX;
    int position;
    cout << "o menor valor do digito foi: " << endl;

    // Identificar o min:
    for(int j = 0; j < tam; j++){
        if(minDigit > digits[j]){
            minDigit = digits[j];
            position = j;
            cout << "o menor valor do digito foi: " << digits[j] << " e a sua posiçao é: " << j << endl;
        }
    }

    // identificar se sao uma seq:
    for(int j = 0; j < tam; j++) {
        minDigit++;
        // Se nao encontrar nenhum repetido entao retorna false
        for(int l = 0; l < tam; l++){
            if(!(minDigit == digits[l] && l != position)){
                return false;
            }
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
        valor /= 10;
    }

    // Iterar das unidades, ate a casa mais alta do numero
    while (checkEqualsDigit && checkSeqDigits)
    {
        /* code */
    }
    
    for(int i = 0; i < tam; i++){
        // Enquanto, tds os valores NAO forem unicos && NAO for uma sequencia, INCREMENTAMOS
        while() {
            cout << "alo????" << endl;
            digits[i] += 1;
        }
    }

    int result = 0;

    // Juntar valores
    for(int i = 0; i < tam; i++) {
        result += digits[i] * static_cast<int>(pow(10, i));
    }
    // cout << result << endl;
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