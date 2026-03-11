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

// true => contem o digito no array de int
bool containDigit(vector<int> digits, int value) {
    for(int j = 0; j < digits.size(); j++){
        if(value == digits[j]) {
            return true;
        }
    }
    return false;
}

// true = É sequencial
bool isSequencial(vector<int> digits, int value) {
    int copyvalue = value++;
    if(copyvalue < 10){
        for(int j = 0; j < digits.size(); j++){
            if(value == digits[j]) {
                return true;
            }
        }    
    }

    copyvalue = value--;
    if(copyvalue > 0) {
        for(int j = 0; j < digits.size(); j++){
            if(value == digits[j]) {
                return true;
            }
        }    
    }

    return false;
}

vector<int> construcDigits(vector<int> digits, int tam) {
    vector<int> newDigits(tam);

    newDigits[0] = digits[0];

    for(int i = 1; i < tam; i++){
        if(containDigit(newDigits, digits[i]) && !isSequencial(newDigits, digits[i])) {
            digits[i] = (digits[i] + 1) % 10;
        }
        newDigits[i] = digits[i];
    }

    return newDigits;
}

vector<int> orderDigits(vector<int> digitsToConstruc, vector<int> digits, int tam) {
    vector<int> orderedDigits(tam);
    int positionsMaked = 0;

    for(int i = 0; i < tam; i++) {
        if(digitsToConstruc[i] == digits[i]){
            orderedDigits[i] = digitsToConstruc[i];
            positionsMaked++;
        }
        else if(digitsToConstruc[i] > digits[i]) {
            orderedDigits[i] = digitsToConstruc[i];
            positionsMaked++;
            break;
        }
    }

    //if orderedDigits esta completo, then return
    if(positionsMaked != tam){
        // Colocar por ordem
        sort(digitsToConstruc.begin() + positionsMaked, digitsToConstruc.begin() + tam);

        // Adicionar ao array
        for(int i = positionsMaked; i < tam; i++){
            orderedDigits[i] = digitsToConstruc[i];
        }
    }

    return orderedDigits;
}

unsigned long unique(unsigned long valor) {
    int tam = 0;
    int result = 0;
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

    vector<int> digitsToConstruc = construcDigits(digits, tam);

    vector<int> orderedDigits = orderDigits(digitsToConstruc, digits, tam);

    // Juntar valores
    for(int i = 0; i < tam; i++) {
        result = result * 10 + orderedDigits[tam - i - 1];
    }
   
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