#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
#include <algorithm>

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

    if(standingX + 2 <= 8 && standingY + 1 <= 8 && (abs((standingX + 2) - tryCoverX) == abs((standingY + 1) - tryCoverY))) { // 1
        return true;
    }
    if(standingX + 2 <= 8 && standingY - 1 >= 1 && (abs((standingX + 2) - tryCoverX) == abs((standingY - 1) - tryCoverY))) { // 2
        return true;
    }
    if(standingX + 1 <= 8 && standingY - 2 >= 1 && (abs((standingX + 1) - tryCoverX) == abs((standingY - 2) - tryCoverY))) { // 3
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
        //cout << endl << "boo" << endl;
        return true;
    }
    if(standingX + 1 <= 8 && standingY + 2 <= 8 && (abs((standingX + 1) - tryCoverX) == abs((standingY + 2) - tryCoverY))) { // 8
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

        // cout << " Vamos tentar:" << endl;

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
                // cout << " deu pensando mt ";

            }
            else {
                moves += 3;
                // cout << " deu nao ";

            }
            // cout << " fui de " << standing << " para " << step << " ";

            standing = step;
        }

        // cout << endl;

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && moves <= maxSteps);

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
            }

            standing = step;
        }

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && moves <= maxSteps);

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
            cout << move << " ";
        }

        cout << endl;

        // cout << endl;

        minMoves = min(minMoves, moves);
    } while (next_permutation(allPossiblesOrders.begin(), allPossiblesOrders.end()) && moves <= maxSteps);

    // cout << endl << " dessa forma incrivel eu concluo que o min é " << minMoves << endl;

    return minMoves;
}

// Ter atenção ao nivel de prioridade
void output(vector<int> steps){
    int min = INT_MAX;
    string buffer;

    for(int i = 0; i < 3; i++){
        if(steps[i] < min){
            min = steps[i];
            if(i == 0){
                buffer = 'A';
            }
            if(i == 1){
                buffer = 'C';
            }
            if(i == 2){
                buffer = 'Q';
            }
        }
        else if(steps[i] == min) {
            if(i == 0){
                buffer += 'A';
            }
            if(i == 1){
                buffer += 'C';
            }
            if(i == 2){
                buffer += 'Q';
            }
        }
    }

    cout << min << ' ' << buffer << endl;
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

    // Numero maximo de passos a ser verificado:
    maxSteps = max(10, numberPos*2);

    steps[0] = mindA(numberPos, Positions, maxSteps);
    steps[1] = mindC(numberPos, Positions, maxSteps);
    steps[2] = mindQ(numberPos, Positions, maxSteps);

    // cout << steps[0] << ' ' << steps[1] << ' ' << steps[2] << ' ';

    output(steps);
}

// Ninguem merece trabalhar com letras, entao isto foi criado:
char convertLettersToNumber(char letter){
    if(letter == 'A'){
        return '1';
    }
    else if(letter == 'B'){
        return '2';
    }
    else if(letter == 'C'){
        return '3';
    }
    else if(letter == 'D'){
        return '4';
    }
    else if(letter == 'E'){
        return '5';
    }
    else if(letter == 'F'){
        return '6';
    }
    else if(letter == 'G'){
        return '7';
    }
    else {
        return '8';
    }
    
}

int main(int argc, char const *argv[]) {
    ifstream inputFil("input.txt");    
    string line, start;
    int games, numberPos;

    // Extrair dados
    if(getline(inputFil, line)) {
        games = stoi(line);
    }

    for(int i = 0; i < games; i++){
        if(getline(inputFil, line)) {
            stringstream ss(line); // NUMERO DE s => numero de separaçoes que vai fazer
            ss >> numberPos >> start; //Colocar o S1 => numberPos; colocar o s2 => start
        }

        start[0] = convertLettersToNumber(start[0]);

        vector<string> toCover(numberPos);

        for(int i = 0; i < numberPos; i++){
            if (getline(inputFil, line)) {
                stringstream ss(line);
                ss >> toCover[i];

                toCover[i][0] = convertLettersToNumber(toCover[i][0]);
            }
        }

        //TODO: o c++ tem GERADOR DE PREMOTAÇÕES

        //Só verificar output
        // cout << games << endl;
        // cout << numberPos << ' ' << start[0] << start[1] << endl;
        // cout << toCover[0][0] << toCover[0][1] << endl;
        // cout << toCover[1][0] << toCover[1][1] << endl;
        // cout << toCover[2][0] << toCover[2][1] << endl;
    
        letsDance(numberPos, start, toCover);
    }

    inputFil.close();
    return 0;
}