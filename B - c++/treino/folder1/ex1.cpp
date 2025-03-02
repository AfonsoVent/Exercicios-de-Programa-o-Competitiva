#include "iostream"

// Lembrando que em projetos maiores, nao deve ser feito.
using namespace std;

int main(int argc, char const *argv[])
{
    // << : output
    // >> : input
    cout << "coloque um valor para ser ao quadrado: ";
    int n = 0;
    cin >> n;
    cout << "O valor ao quadrado é: " << (n * n) << endl;

    return 0;
}
