#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>
#include <bits/stdc++.h>



// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall

// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

vector<vector<int>> matrizMovQ() {
    vector<vector<int>> d(64, vector<int>(64)); //(d)istancias

    for (int i = 0; i < 64; i++) {
        for (int j = 0; j < 64; j++) {
            if (i == j) d[i][j] = 0;
            else d[i][j] = 2;
        }
    }

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
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

    for (int i = 0; i < 64; i++) {
        for (int j = 0; j < 64; j++) {
            if (i == j) d[i][j] = 0;
            else d[i][j] = 2;
        }
    }

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
            int rowI = i / 8;
            int rowJ = j / 8;
            if(rowI == rowJ) {
                d[i][j] = 1;
            }

            int colI = i % 8;  
            int colJ = j % 8;

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

    for (int i = 0; i < 64; i++) {
        for (int j = 0; j < 64; j++) {
            if (i == j) d[i][j] = 0;
            else d[i][j] = 3;
        }
    }

    for(int i = 0; i < 64; i++) {
        for(int j = 0; j < 64; j++) {
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

int thinkA(vector<vector<int>> matriz, vector<string> Positions, int numberPos) {
    int moves;
    int minMoves = INT_MAX;
    string standing;

    vector<int> allPossiblesOrders(numberPos);
    for (int i = 0; i < numberPos; i++) {
        allPossiblesOrders[i] = i + 1;
    }

    do {
        standing = Positions[0];
        moves = 0;

        for (int move : allPossiblesOrders) {
            string step = Positions[move];

            // Converte as posições
            int startIndex = (standing[0] - '1') + (standing[1] - '1') * 8; 
            int endIndex = (step[0] - '1') + (step[1] - '1') * 8;

            // cout << "standingY: " << standing[1] << " standingX: " << standing[0] << " stepY: " << step[1] << " stepX: " << step[0] << endl;
            // cout << "index de start: " << startIndex << " e o do fim " << endIndex << endl;
            moves += matriz[startIndex][endIndex];

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && minMoves > numberPos);

    // cout << "the end" << endl;
    return minMoves;
}

int thinkC(vector<vector<int>> matriz, vector<string> Positions, int numberPos) {
    int moves;
    int minMoves = INT_MAX;
    string standing;

    vector<int> allPossiblesOrders(numberPos);
    for (int i = 0; i < numberPos; i++) {
        allPossiblesOrders[i] = i + 1;
    }

    do {
        standing = Positions[0];
        moves = 0;

        for (int move : allPossiblesOrders) {
            string step = Positions[move];

            // Converte as posições
            int startIndex = (standing[0] - '1') + (standing[1] - '1') * 8; 
            int endIndex = (step[0] - '1') + (step[1] - '1') * 8;

            moves += matriz[startIndex][endIndex];

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && minMoves > numberPos);

    return minMoves;
}

int thinkQ(vector<vector<int>> matriz, vector<string> Positions, int numberPos) {
    int moves;
    int minMoves = INT_MAX;
    string standing;

    vector<int> allPossiblesOrders(numberPos);
    for (int i = 0; i < numberPos; i++) {
        allPossiblesOrders[i] = i + 1;
    }

    do {
        standing = Positions[0];
        moves = 0;

        for (int move : allPossiblesOrders) {
            string step = Positions[move];

            // Converte as posições
            int startIndex = (standing[0] - '1') + (standing[1] - '1') * 8; 
            int endIndex = (step[0] - '1') + (step[1] - '1') * 8;

            moves += matriz[startIndex][endIndex];

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && minMoves > numberPos);

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
void letsDance(int numberPos, string start, vector<string> toCover) {
    // vector<vector<vector<int>>> brain(3, vector<vector<int>>(64, vector<int>(64)));
    vector<int> steps(3);
    vector<string> Positions;
    
    Positions.push_back(start);
    for(int i = 0; i < numberPos; i++){
        Positions.push_back(toCover[i]);
    }

    // vector<vector<int>> matrizA = matrizMovA();
    // vector<vector<int>> matrizC = matrizMovC();
    // vector<vector<int>> matrizQ = matrizMovQ();
    // cout << "morri" << endl;

    // Basicamente bruxaria
    // cout << Positions[0][0] << endl;
    steps[0] = thinkA(matrizMovA(), Positions, numberPos);
    steps[1] = thinkC(matrizMovC(), Positions, numberPos);
    steps[2] = thinkQ(matrizMovQ(), Positions, numberPos);

    // cout << steps[0] << endl;
    output(steps);
}

// Ninguem merece trabalhar com letras, entao isto foi criado:
char convertLettersToNumber(char letter){
    return letter - 'A' + '1';
}

int main(int argc, char const *argv[]) {
    string line, start;
    int games, numberPos;

    // Extrair dados
    if(getline(cin, line)) {
        games = stoi(line);
    }

    for(int i = 0; i < games; i++){
        if(getline(cin, line)) {
            stringstream ss(line); // NUMERO DE s => numero de separaçoes que vai fazer
            ss >> numberPos >> start; //Colocar o S1 => numberPos; colocar o s2 => start
        }

        start[0] = convertLettersToNumber(start[0]);

        vector<string> toCover(numberPos);

        for(int i = 0; i < numberPos; i++){
            if (getline(cin, line)) {
                stringstream ss(line);
                ss >> toCover[i];

                toCover[i][0] = convertLettersToNumber(toCover[i][0]);
            }
        }

        letsDance(numberPos, start, toCover);
    }

    return 0;
}