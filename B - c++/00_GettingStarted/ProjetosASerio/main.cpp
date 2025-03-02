#include <iostream>
#include <memory>

#include "Person.hpp"

int main(int argc, char const *argv[])
{
    std::cout << "Hello World!\n";
    std::cout << "Digite um número: ";

    int n { 0 };
    std::cin >> n;
    std::cout << "O nº digitado foi: " << n << std::endl;

    Person *p0 { new Person("Afonso", 20) };
    p0->greetings();
    delete p0;

    // Também já temos smart pointers como no Rust!
    // Um pequeno cheirinho.
    std::unique_ptr<Person> p1 { std::make_unique<Person>("Afonso", 20) };
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
    Person pStack { Person("Afonso", 20) };
    pStack.greetings();

    return 0;
}
