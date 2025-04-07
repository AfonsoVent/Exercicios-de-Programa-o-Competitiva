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

void floydWarshall(vector<vector<int>> &graph) {
    int V = graph.size();
    for (int k = 0; k < V; k++) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if ((graph[i][j] == -1 || 
                    graph[i][j] > (graph[i][k] + graph[k][j]))
                    && (graph[k][j] != -1 && graph[i][k] != -1))
                    graph[i][j] = graph[i][k] + graph[k][j];
            }
        }
    }
}

// bispo + cavalo
int mindA(int numberPos, vector<string> Positions, int maxSteps) {
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
            if(mindBishop(standing, step)){
                moves++;
            }
            else if(mindHorse(standing, step)){
                moves++;
            }
            else if(mindHorShop(standing, step)) {
                moves += 2;

            }
            else if(mindSuperBishop(standing, step)) {
                moves += 2;
            }
            else if(mindTwoSteps(standing, step)) {
                moves += 2;
            }
            else {
                moves += 3;

            }
            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()));

    //cout << endl << " dessa forma incrivel eu concluo que o min é " << minMoves << endl;
    
    return minMoves;
}

// torre + cavalo
int mindC(int numberPos, vector<string> Positions, int maxSteps) {
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

        // cout << " Vamos tentar:" << endl;

        for (int move : allPossiblesOrders) {
            string step = Positions[move];
            if(mindRook(standing, step)){
                moves++;
            }
            else if(mindHorse(standing, step)){
                moves++;
            }
            else {
                moves += 2;
                // cout << " deu nao ";
            }

            // cout << " fui de " << standing << " para " << step << " ";

            standing = step;
        }

        // cout << endl;

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()));

    return minMoves;
}

int mindQ(int numberPos, vector<string> Positions, int maxSteps) {
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

            if(mindRook(standing, step)){
                moves++;
            }
            else if(mindBishop(standing, step)){
                moves++;
            }
            else {
                moves += 2;
            }

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()));

    // cout << endl << " dessa forma incrivel eu concluo que o min é " << minMoves << endl;

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
    vector<int> steps(3);
    vector<string> Positions;
    int maxSteps;
    
    Positions.push_back(start);
    for(int i = 0; i < numberPos; i++){
        Positions.push_back(toCover[i]);
    }

    // Basicamente bruxaria
    // cout << Positions[0][0] << endl;

    // Numero maximo de passos a ser verificado #ainda nao usei para nada, so uso se me aparecer Time Limited:
    maxSteps = max(10, numberPos*2);

    steps[0] = mindA(numberPos, Positions, maxSteps);
    steps[1] = mindC(numberPos, Positions, maxSteps);
    steps[2] = mindQ(numberPos, Positions, maxSteps);

    output(steps);
}

// Ninguem merece trabalhar com letras, entao isto foi criado:
char convertLettersToNumber(char letter){
    switch (letter)
    {
    case 'A':
        return '1';
        break;
    case 'B':
        return '2';
        break;
    case 'C':
        return '3';
        break;
    case 'D':
        return '4';
        break;
    case 'E':
        return '5';
        break;
    case 'F':
        return '6';
        break;
    case 'G':
        return '8';
        break;
    default:
        return '9';
        break;
    }    
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