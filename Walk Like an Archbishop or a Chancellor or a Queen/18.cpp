#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>
#include <bits/stdc++.h>

// TITULO:
// VERSAO COM OS STRING CONVERTIDOS A INT
// 2 SUBMISSOES FEITAS APOS A SESSAO PROGRAMAÇAO COMPETITIVA

// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

vector<vector<int>> matrizMovQ() {
    vector<vector<int>> d(64, vector<int>(64)); //(d)istancias

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
            if (i == j) {
                d[i][j] = 0;
            } else {
                d[i][j] = 2;
            }

            int rowI = i / 8;
            int rowJ = j / 8;
            if(rowI == rowJ) {
                d[i][j] = 1;
            }

            int colI = i % 8;  
            int colJ = j % 8;  
            if (colI == colJ) {
                d[i][j] = 1;  
            }

            if (abs(rowI - rowJ) == abs(colI - colJ)) {
                d[i][j] = 1;
            }
        }
    }

    return d;
}

vector<vector<int>> matrizMovC() {
    vector<vector<int>> d(64, vector<int>(64)); //(d)istancias

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
            if (i == j) {
                d[i][j] = 0;
            } else {
                d[i][j] = 2;
            }

            int rowI = i / 8;
            int rowJ = j / 8;
            if(rowI == rowJ) {
                d[i][j] = 1;
            }

            int colI = i % 8;  
            int colJ = j % 8;

            if (colI == colJ) {
                d[i][j] = 1;  
            }

            int rowDiff = abs(rowI - rowJ);
            int colDiff = abs(colI - colJ);

            if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
                d[i][j] = 1;
            }
        }
    }

    return d;
}

vector<vector<int>> matrizMovA() {
    vector<vector<int>> d(64, vector<int>(64)); //(d)istancias

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
            if (i == j) {
                d[i][j] = 0;
            } else {
                d[i][j] = 2;
            }

            int colI = i % 8;  
            int colJ = j % 8;  
            int rowI = i / 8;
            int rowJ = j / 8;
            if (abs(rowI - rowJ) == abs(colI - colJ)) {
                d[i][j] = 1;
            }

            int rowDiff = abs(rowI - rowJ);
            int colDiff = abs(colI - colJ);
            
            if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
                d[i][j] = 1;
            }

            // 2 movs( O VERDADEIRO DESAFIO COMEÇA )
            if(d[i][j] > 1){
                // bispo + bispo (cor da telha)
                if(((colI + rowI) % 2 == (colJ + rowJ) % 2)) {
                    d[i][j] = 2;
                }
                // o quadrado
                else if (rowDiff <= 4 && colDiff <= 4) {
                    d[i][j] = 2;
                }
                // bispo + cavalo (8 direções)
                else if(colDiff + 2 == rowDiff + 1 ||
                colDiff + 2 == rowDiff - 1 ||
                colDiff + 1 == rowDiff - 2 ||
                colDiff - 1 == rowDiff - 2 ||
                colDiff - 2 == rowDiff - 1 ||
                colDiff - 2 == rowDiff + 1 ||
                colDiff - 1 == rowDiff + 2 ||
                colDiff + 1 == rowDiff + 2) {
                    d[i][j] = 2;
                }
                // cavalo + bispo (a formula)
                    // Diagonal ( / ) Xa - Xb == Ya - Yb
                    else if((rowI + 2 <= 8 && rowI + 5 <= 8 && (rowI + 2) - colJ == (rowI + 5) - rowJ) ||
                    (rowI + 5 <= 8 && rowI + 2 <= 8 && (rowI + 5) - colJ == (rowI + 2) - rowJ) ||
                    (rowI - 5 >= 1 && rowI + 2 <= 8 && (rowI - 5) - colJ == (rowI + 2) - rowJ) ||
                    (rowI + 2 <= 8 && rowI - 5 >= 1 && (rowI + 2) - colJ == (rowI - 5) - rowJ)) {
                        d[i][j] = 2;
                    }

                    // Diagonal ( \ ) Xa - Xb == Yb - Ya
                    else if((rowI - 2 >= 1 && rowI + 5 <= 8 && (rowI - 2) - colJ == (rowI + 5) - rowJ) ||
                    (rowI + 5 <= 8 && rowI - 2 >= 1 && (rowI + 5) - colJ == (rowI - 2) - rowJ) ||
                    (rowI - 5 >= 1 && rowI - 2 >= 1 && (rowI - 5) - colJ == (rowI - 2) - rowJ) ||
                    (rowI - 2 >= 1 && rowI - 5 >= 1 && (rowI - 2) - colJ == (rowI - 5) - rowJ)) {
                        d[i][j] = 2;
                    }
            }
        }
    }

    return d;
}

int think(vector<vector<int>> matriz, vector<int> Positions, int numberPos) {
    int moves;
    int minMoves = INT_MAX;
    int standing;

    vector<int> allPossiblesOrders(numberPos);
    for (int i = 0; i < numberPos; i++) {
        allPossiblesOrders[i] = i + 1;
    }

    do {
        standing = Positions[0];
        moves = 0;

        for (int move : allPossiblesOrders) {
            int step = Positions[move];

            moves += matriz[standing][step];

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && minMoves > numberPos);

    // cout << "the end" << endl;
    return minMoves;
}

// Ter atenção ao nivel de prioridade
void output(vector<int> steps){
    int minimo;
    string buffer;
    
    minimo = min(steps[0], min(steps[1], steps[2]));

    if(minimo == steps[0]) {
        buffer = 'A';
    }
    if(minimo == steps[1]) {
        buffer += 'C';
    }
    if(minimo == steps [2]) {
        buffer += 'Q';
    }

    cout << minimo << ' ' << buffer << endl;
}

// Função onde o codigo de verdade começa
void letsDance(int numberPos, int start, vector<int> toCover, vector<vector<vector<int>>> brain) {
    vector<int> Positions;
    vector<int> steps(3);
    
    Positions.push_back(start);
    for(int i = 0; i < numberPos; i++){
        Positions.push_back(toCover[i]);
    }

    for(int i = 0; i < 3; i++) {
        steps[i] = think(brain[i], Positions, numberPos);
    }

    output(steps);
}

// Ninguem merece trabalhar com letras, entao isto foi criado:
int convertToIndex(string Position){
   return (Position[0] - 'A') + (Position[1] - '1') * 8;
}

int main(int argc, char const *argv[]) {
    string line, inputStart;
    int games, numberPos, start;

    // Extrair dados
    if(getline(cin, line)) {
        games = stoi(line);
    }

    vector<vector<vector<int>>> brain(3, vector<vector<int>>(64, vector<int>(64)));

    brain[0] = matrizMovA();
    brain[1] = matrizMovC();
    brain[2] = matrizMovQ();

    for(int i = 0; i < games; i++){
        if(getline(cin, line)) {
            stringstream ss(line); // NUMERO DE s => numero de separaçoes que vai fazer
            ss >> numberPos >> inputStart; //Colocar o S1 => numberPos; colocar o s2 => start

            start = convertToIndex(inputStart);
        }

        vector<int> toCover(numberPos);

        for(int j = 0; j < numberPos; j++){
            if (getline(cin, line)) {
                toCover[j] = convertToIndex(line);
            }
        }

        letsDance(numberPos, start, toCover, brain);
    }

    return 0;
}