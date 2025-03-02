#include "Person.hpp"

#include <iostream>

Person::Person(std::string name, int age) {
    this->name = name; // Funciona, mas será que deviamos de fazer assim?
                        // Desta forma estamos a fazer uma cópia da string.
                        // Será que queremos fazer cópia ou passar um apontador?
                        // Depende do programador apenas :) e da melhor solução claro.
                        // Neste caso, faz-me bastante sentido.
    this->age = age;
}

Person::~Person() {
    // Este não era preciso sequer ser declarado, pois não estamos a usá-lo.
    // Mas fica aqui para te lembrares para projetos futuros.
}

void Person::greetings() {
    std::cout << "Hello! My name is " << this->name << " and I am " << this->age << " years old!\n";
    // std::cout << "It is a pleasure to meet you." << std::endl;
    // Ou
    std::cout << "It is a pleasure to meet you.\n";
}