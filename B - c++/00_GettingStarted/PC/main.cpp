#include <iostream>
#include <memory>

// Má prática para projetos a sério.
using namespace std;

class Person {
private:
    string name;
    // ignorar o bit do sinal, me permite o intervalo ser maior
    // unsigned int age;
    int age;

public:
    Person(string name, int age) {
        this->name = name; // Funciona, mas será que deviamos de fazer assim?
                            // Desta forma estamos a fazer uma cópia da string.
                            // Será que queremos fazer cópia ou passar um apontador?
                            // Depende do programador apenas :) e da melhor solução claro.
                            // Neste caso, faz-me bastante sentido.
        this->age = age;
    }

    ~Person() {
        // Este não era preciso sequer ser declarado, pois não estamos a usá-lo.
        // Mas fica aqui para te lembrares para projetos futuros.
    }

    void greetings() {
        cout << "Hello! My name is " << this->name << " and I am " << this->age << " years old!\n";
        // cout << "It is a pleasure to meet you." << endl;
        // Ou
        cout << "It is a pleasure to meet you.\n";
    }
};

int main(int argc, char const *argv[])
{
    cout << "Hello World!\n";
    cout << "Digite um número: ";

    int n = 0;
    cin >> n;
    // Em vez do String.split(), temos a vantagem de 
    // fazer isto diretamente.
    // std::cin >> n0 >> n1 >> n2 >> n3;
    // scanf("%d %d %d %d", &n1, &n2, &n3, &n4);
    cout << "O nº digitado foi: " << n << endl;

    Person *p0 = new Person("Afonso", 20);
    p0->greetings();
    delete p0;

    // Também já temos smart pointers como no Rust!
    // Um pequeno cheirinho.
    unique_ptr<Person> p1 = make_unique<Person>("Afonso", 20);
    p1->greetings();

    // Ou ainda, podemos usar a memória Stack diretamente.
    /* Lembraste quando fazias em C arrays? Tinhas duas formas
     * int arr[3] = {0, 1, 2};
     * Ou
     * int *arr = (int *) malloc(sizeof(int * 3));
     * arr[0] = 0;
     * arr[1] = 1;
     * arr[2] = 2;
     * free(arr);
     * 
     * E ainda podias fazer o mesmo com structs certo?
     * Em C++, também podemos alocar os objetos diretamente na memória
     * Stack.
     */
    Person pStack = Person("Afonso", 20);
    pStack.greetings();

    return 0;
}

