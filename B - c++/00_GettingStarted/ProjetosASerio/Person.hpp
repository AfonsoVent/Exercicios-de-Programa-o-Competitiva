#ifndef PERSON_HPP
#define PERSON_HPP

#include <string>

class Person {
private:
    std::string name;
    int age;

public:
    Person(std::string name, int age);

    // Que raios é isto? É chamado quando damos desalocamos o Person.
    // Então é útil para quando temos estruturas muito complexas e queremos 
    // apagá-las de memória também, por exemplo e se Person tivesse um Carro?
    // que foi alocado com "malloc"? Iriamos querer apagar o Carro quando a Pessoa deixasse
    // de existir.
    ~Person();

    // No .hpp apenas queremos declarações de funções e
    // não o seu corpo.
    void greetings();
};

#endif