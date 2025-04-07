#include "iostream"
#include "fstream"
#include "string"
#include <sstream>
#include <vector>
#include <climits>
// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

bool mindBishop(string standing, string tryCover){
    int diagSpace;

    diagSpace = standing[0] - tryCover[0];
    if(standing[1] - tryCover[1] == diagSpace){
        return true;
    }
    return false;
}

bool mindHorse(string standing, string tryCover){
    if((standing[0] + 2 <= 8 && standing[1] + 1 <= 8 && standing[0] + 2 == tryCover[0] && standing[1] + 1 == tryCover[1]) || // 1
    (standing[0] + 2 <= 8 && standing[1] - 1 >= 1 && standing[0] + 2 == tryCover[0] && standing[1] - 1 == tryCover[1]) || // 2
    (standing[0] + 1 <= 8 && standing[1] - 2 >= 1 && standing[0] + 1 == tryCover[0] && standing[1] - 2 == tryCover[1]) || // 3
    (standing[0] - 1 >= 1 && standing[1] - 2 >= 1 && standing[0] - 1 == tryCover[0] && standing[1] - 2 == tryCover[1]) || // 4
    (standing[0] - 2 >= 1 && standing[1] - 1 >= 1 && standing[0] - 2 == tryCover[0] && standing[1] - 1 == tryCover[1]) || // 5
    (standing[0] - 2 >= 1 && standing[1] + 1 <= 8 && standing[0] - 2 == tryCover[0] && standing[1] + 1 == tryCover[1]) || // 6
    (standing[0] - 1 >= 1 && standing[1] + 2 <= 8 && standing[0] - 1 == tryCover[0] && standing[1] + 2 == tryCover[1]) || // 7
    (standing[0] + 1 <= 8 && standing[1] + 2 <= 8 && standing[0] + 1 == tryCover[0] && standing[1] + 2 == tryCover[1]) // 8
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

int mindA(int numberPos, vector<string> Positions) {
    int moves = 0;
    string standing = Positions[0];

    for(int i = 0; i < numberPos; i++){
        // fazer pensar em bispo
        if(mindBishop(Positions[i], Positions[i + 1])){
            moves++;
        }
        // fazer pensar em cavalo
        else if(mindHorse(Positions[i], Positions[i + 1])){
            moves++;
        }
        else{
            moves += 2;
        }
    }
    return moves;
}

int mindC(int numberPos, vector<string> Positions) {
    int moves = 0;

    for(int i = 0; i < numberPos; i++){
        // fazer pensar em torre
        if(mindRook(Positions[i], Positions[i + 1])){
            moves++;
        }
        // fazer pensar em cavalo
        else if(mindHorse(Positions[i], Positions[i + 1])){
            moves++;
        }
        else{
            moves += 2;
        }
    }
    return moves;
}

int mindQ(int numberPos, vector<string> Positions) {
    int moves = 0;

    for(int i = 0; i < numberPos; i++){
        // fazer pensar em torre
        if(mindRook(Positions[i], Positions[i + 1])){
            moves++;
        }
        // fazer pensar em bispo
        else if(mindBishop(Positions[i], Positions[i + 1])){
            moves++;
        }
        else{
            moves += 2;
        }
    }
    return moves;
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
    
    Positions.push_back(start);
    for(int i = 0; i < numberPos; i++){
        Positions.push_back(toCover[i]);
    }

    // Basicamente bruxaria
    // cout << Positions[0][0] << endl;

    steps[0] = mindA(numberPos, Positions);
    steps[1] = mindC(numberPos, Positions);
    steps[2] = mindQ(numberPos, Positions);

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
    
        // letsDance(numberPos, start, toCover);
    }

    inputFil.close();
    return 0;
}