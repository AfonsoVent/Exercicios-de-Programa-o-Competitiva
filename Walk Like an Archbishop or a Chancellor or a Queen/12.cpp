#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>




// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall
// Floyd-Warshall



// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

bool mindBishop(string standing, string tryCover){
    int standingX = standing[0] - '0';
    int standingY = standing[1] - '0';
    int tryCoverX = tryCover[0] - '0';
    int tryCoverY = tryCover[1] - '0';

    if(abs(standingX - tryCoverX) == abs(standingY - tryCoverY)){
        return true;
    }
    return false;
}

bool mindHorse(string standing, string tryCover){
    int standingX = standing[0] - '0';
    int standingY = standing[1] - '0';
    int tryCoverX = tryCover[0] - '0';
    int tryCoverY = tryCover[1] - '0';
    
    if((standingX + 2 <= 8 && standingY + 1 <= 8 && standingX + 2 == tryCoverX && standingY + 1 == tryCoverY) || // 1
    (standingX + 2 <= 8 && standingY - 1 >= 1 && standingX + 2 == tryCoverX && standingY - 1 == tryCoverY) || // 2
    (standingX + 1 <= 8 && standingY - 2 >= 1 && standingX + 1 == tryCoverX && standingY - 2 == tryCoverY) || // 3
    (standingX - 1 >= 1 && standingY - 2 >= 1 && standingX - 1 == tryCoverX && standingY - 2 == tryCoverY) || // 4
    (standingX - 2 >= 1 && standingY - 1 >= 1 && standingX - 2 == tryCoverX && standingY - 1 == tryCoverY) || // 5
    (standingX - 2 >= 1 && standingY + 1 <= 8 && standingX - 2 == tryCoverX && standingY + 1 == tryCoverY) || // 6
    (standingX - 1 >= 1 && standingY + 2 <= 8 && standingX - 1 == tryCoverX && standingY + 2 == tryCoverY) || // 7
    (standingX + 1 <= 8 && standingY + 2 <= 8 && standingX + 1 == tryCoverX && standingY + 2 == tryCoverY) // 8
    ) {
        return true;
    }
    return false;
}

bool mindRook(string standing, string tryCover){
    if(standing[0] == tryCover[0] || standing[1] == tryCover[1]){
        return true;
    }
    return false;
}

bool mindHorShop(string standing, string tryCover) {
    int standingX = standing[0] - '0';
    int standingY = standing[1] - '0';
    int tryCoverX = tryCover[0] - '0';
    int tryCoverY = tryCover[1] - '0';

    if(standingX + 2 <= 8 && standingY + 1 <= 8 && (abs((standingX + 2) - tryCoverX) == abs((standingY + 1) - tryCoverY)) || // 1
    standingX + 2 <= 8 && standingY - 1 >= 1 && (abs((standingX + 2) - tryCoverX) == abs((standingY - 1) - tryCoverY)) || // 2 
    standingX + 1 <= 8 && standingY - 2 >= 1 && (abs((standingX + 1) - tryCoverX) == abs((standingY - 2) - tryCoverY)) || // 3
    ) { 
        return true;
    }
    if() { // 2
        return true;
    }
    if() { // 3
        return true;
    }
    if(standingX - 1 >= 1 && standingY - 2 >= 1 && (abs((standingX - 1) - tryCoverX) == abs((standingY - 2) - tryCoverY))) { // 4
        return true;
    }
    if(standingX - 2 >= 1 && standingY - 1 >= 1 && (abs((standingX - 2) - tryCoverX) == abs((standingY - 1) - tryCoverY))) { // 5
        return true;
    }
    if(standingX - 2 >= 1 && standingY + 1 <= 8 && (abs((standingX - 2) - tryCoverX) == abs((standingY + 1) - tryCoverY))) { // 6
        return true;
    }
    if(standingX - 1 >= 1 && standingY + 2 <= 8 && (abs((standingX - 1) - tryCoverX) == abs((standingY + 2) - tryCoverY))) { // 7
        return true;
    }
    if(standingX + 1 <= 8 && standingY + 2 <= 8 && (abs((standingX + 1) - tryCoverX) == abs((standingY + 2) - tryCoverY))) { // 8
        return true;
    }

    return false;
}

//Verificar se estao na mesma cor da casa (daria em 2 movs)
bool mindSuperBishop(string standing, string tryCover) {
    int standingX = standing[0] - '0';
    int standingY = standing[1] - '0';
    int tryCoverX = tryCover[0] - '0';
    int tryCoverY = tryCover[1] - '0';

    return ((standingX + standingY) % 2 == (tryCoverX + tryCoverY) % 2);
}

// Todos os casos a mais que lembrei que podiam dar 2 passos tbm
bool mindTwoSteps(string standing, string tryCover) {
    int standingX = standing[0] - '0';
    int standingY = standing[1] - '0';
    int tryCoverX = tryCover[0] - '0';
    int tryCoverY = tryCover[1] - '0';

    // Verifica se tem alguma nas 4 casas de distância
    int distX = abs(standingX - tryCoverX);
    int distY = abs(standingY - tryCoverY);

    if (distX <= 4 && distY <= 4) {
        return true;
    }

    // Andar com o bispo e depois se mover com o cavalo: 
    // Diagonal ( / ) Xa - Xb == Ya - Yb
    if((standingX + 2 <= 8 && standingY + 5 <= 8 && (standingX + 2) - tryCoverX == (standingY + 5) - tryCoverY) ||
    (standingX + 5 <= 8 && standingY + 2 <= 8 && (standingX + 5) - tryCoverX == (standingY + 2) - tryCoverY) ||
    (standingX - 5 >= 1 && standingY + 2 <= 8 && (standingX - 5) - tryCoverX == (standingY + 2) - tryCoverY) ||
    (standingX + 2 <= 8 && standingY - 5 >= 1 && (standingX + 2) - tryCoverX == (standingY - 5) - tryCoverY)) {
        return true;
    }

    // Diagonal ( \ ) Xa - Xb == Yb - Ya
    if((standingX - 2 >= 1 && standingY + 5 <= 8 && (standingX - 2) - tryCoverX == (standingY + 5) - tryCoverY) ||
    (standingX + 5 <= 8 && standingY - 2 >= 1 && (standingX + 5) - tryCoverX == (standingY - 2) - tryCoverY) ||
    (standingX - 5 >= 1 && standingY - 2 >= 1 && (standingX - 5) - tryCoverX == (standingY - 2) - tryCoverY) ||
    (standingX - 2 >= 1 && standingY - 5 >= 1 && (standingX - 2) - tryCoverX == (standingY - 5) - tryCoverY)) {
        return true;
    }

    return false;
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