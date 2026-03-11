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

    for (int i = 1; i < tam; i++) {
        int nextDigit = digits[i];

        while (containDigit(newDigits, nextDigit) || nextDigit != (newDigits[i - 1] + 1) % 10) {
            nextDigit = (nextDigit + 1) % 10;
        }

        newDigits[i] = nextDigit;
    }

    return newDigits;
}

vector<int> orderDigits(vector<int> digitsToConstruc, vector<int> digits, int tam) {
    vector<int> orderedDigits(tam);
    int positionsMaked = 0;

    sort(digitsToConstruc.begin(), digitsToConstruc.end());

    bool stop = false;

    for (int i = 0; i < tam && !stop; i++) {
        for (int j = 0; j < tam; j++) {
            if (digitsToConstruc[j] == digits[i]) {
                orderedDigits[positionsMaked] = digitsToConstruc[j];
                positionsMaked++;
                break;
            }
            else if (digitsToConstruc[j] > digits[i]) {
                orderedDigits[positionsMaked] = digitsToConstruc[j];
                positionsMaked++;
                stop = true; 
                break;
            }
        }
    }

    for(int i = 0; i < tam; i++) {
        cout << "posicao " << i << " tem valor " << orderedDigits[i] << endl;
    }

    cout << endl;

    if (positionsMaked != tam) {
        sort(digitsToConstruc.begin(), digitsToConstruc.end());

        int idx = 0;
        for (int i = positionsMaked; i < tam; i++) {
            while (idx < tam && (count(orderedDigits.begin(), orderedDigits.begin() + positionsMaked, digitsToConstruc[idx]) > 0)) {
                idx++;
            }
            orderedDigits[i] = digitsToConstruc[idx++];
        }
    }

    for(int i = 0; i < tam; i++) {
        cout << "posicao " << i << " tem valor " << orderedDigits[i] << endl;
    }
    cout << "deveria ter " << tam << " de tamanho" << endl;
    exit(1);

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

    // Nao achar o proprio numero:
    digits[0]++;

    vector<int> digitsToConstruc = construcDigits(digits, tam); // parece certo

    vector<int> orderedDigits = orderDigits(digitsToConstruc, digits, tam); // nao parece mais certo

    // Juntar valores
    for(int i = 0; i < tam; i++) {
        result = result * 10 + orderedDigits[tam - i - 1];
    }

    cout << endl << "Resultado: " << endl;
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