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

// true = É sequencial (o valor faz parte da sequencia do array)
bool isSequencial(vector<int> digits, int value) {
    int copyvalue = value + 1;
    if(copyvalue < 10){
        for(int j = 0; j < digits.size(); j++){
            if(copyvalue == digits[j]) {
                return true;
            }
        }    
    }

    copyvalue = value - 1;
    if(copyvalue > 0) {
        for(int j = 0; j < digits.size(); j++){
            if(copyvalue == digits[j]) {
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
    bool breaker = false;
    vector<bool> map(tam);

    for(int i = 0; i < tam; i++) {
        map[i] = true;
    }

    for(int i = 0; i < tam && !breaker; i++) {
        for(int j = 0; j < tam; j++){
            if(map[j]){
                if(digitsToConstruc[i] == digits[j]){
                    orderedDigits[i] = digitsToConstruc[j];
                    positionsMaked++;
                    map[j] = false;
                }
                else if(digitsToConstruc[i] > digits[j]) {
                    orderedDigits[i] = digitsToConstruc[j];
                    positionsMaked++;
                    breaker = true;
                    map[j] = false;
                    break;
                }        
            }
        }
    }

    // cout << "quantas pos foram colocadas: " << positionsMaked << endl;

    //if orderedDigits esta completo, then return
    if(positionsMaked != tam){
        // Colocar por ordem
        sort(digitsToConstruc.begin() + positionsMaked, digitsToConstruc.begin() + tam);

        for(int i = positionsMaked; i < tam; i++) {
            // cout << "na posição " << i << " tenho o valor " << digitsToConstruc[i] << endl;
        }

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

    //discover(anos, N);
    
    //--------------orderDigits Correct
    // vector<int> result = orderDigits({1,2,3}, {2,1,3}, 3);
    // cout << "teste1:" << result[0] << result[1] << result[2] << endl;

    // vector<int> result2 = orderDigits({1,2,3}, {1,2,3}, 3);
    // cout << "teste2:" << result2[0] << result2[1] << result2[2] << endl;

    //-----------------containDigit Correct
    // int result3 = containDigit({1, 2, 3}, 3);
    // cout << "test3: " << result3 << endl;

    // int result4 = containDigit({1, 2, 3}, 4);
    // cout << "test4: " << result4 << endl;

    vector<int> result5 = construcDigits({1, 2, 3, 4}, 4);
    cout << "test5: " << result5[0] << result5[1] << result5[2] << result5[3] << endl;

    vector<int> result6 = construcDigits({1, 4, 4, 4}, 4);
    cout << "test6: " << result6[0] << result6[1] << result6[2] << result6[3] << endl;
    //TODO: talvez devesse dar diretamente sort aos valores e so depois ficar a verificar se entra ao nao, desta forma nao teria que dar sort mais tarde
    
    //---------------isSequencial Correct
    // bool resutl7 = isSequencial({1,2,3,4,5}, 6);
    // cout << "test7: " << resutl7 << endl;

    // bool resutl8 = isSequencial({1,2,3,4,5}, 9);
    // cout << "test8: " << resutl8 << endl;

    return 0;
}