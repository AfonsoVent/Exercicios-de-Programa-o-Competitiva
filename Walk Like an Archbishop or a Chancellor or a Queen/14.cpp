#include <iostream>
#include <vector>
#include <climits>

using namespace std;

const int INF = INT_MAX;
const int SIZE = 64;

// Converte a posição do tabuleiro para um índice na matriz
int getIndex(string pos) {
    int x = pos[0] - '1'; // Converte '1'-'8' para 0-7
    int y = pos[1] - '1';
    return x * 8 + y;
}

// Floyd-Warshall para encontrar caminhos mínimos entre todas as casas do tabuleiro
void floydWarshall(vector<vector<int>> &dist) {
    for (int k = 0; k < SIZE; k++) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (dist[i][k] < INF && dist[k][j] < INF) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

int main() {
    // Inicializar matriz de distâncias
    vector<vector<int>> dist(SIZE, vector<int>(SIZE, INF));

    // Inicializar a matriz com os movimentos válidos do cavalo e outras peças
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            int index = i * 8 + j;

            // Exemplo: Adicionando movimentos do cavalo (você pode adicionar outras peças aqui)
            int dx[] = {2, 2, -2, -2, 1, 1, -1, -1};
            int dy[] = {1, -1, 1, -1, 2, -2, 2, -2};

            for (int d = 0; d < 8; d++) {
                int ni = i + dx[d];
                int nj = j + dy[d];
                if (ni >= 0 && ni < 8 && nj >= 0 && nj < 8) {
                    int newIndex = ni * 8 + nj;
                    dist[index][newIndex] = 1; // Movimento válido do cavalo
                }
            }

            dist[index][index] = 0; // Distância para si mesmo é 0
        }
    }

    // Aplicar Floyd-Warshall
    floydWarshall(dist);

    // Teste: Ver a menor distância entre duas casas (exemplo: "11" para "88")
    string start = "11", end = "88";
    int startIndex = getIndex(start);
    int endIndex = getIndex(end);

    cout << "Menor número de movimentos: " << dist[startIndex][endIndex] << endl;

    return 0;
}
