# Banco POO em Java

Este repositório contém um mini-projeto desenvolvido para fins de estudo da Programação Orientada a Objetos em Java.  
O objetivo não é a criação de um sistema bancário real, mas sim exercitar conceitos fundamentais de modelagem, encapsulamento, validação e testes.

---

## Conceitos aplicados

- Encapsulamento: atributos privados e acesso controlado por getters e setters.  
- Imutabilidade parcial: CPF do cliente é definido como `final`, preservando a identidade.  
- Validação de invariantes: regras aplicadas em construtores e métodos (nome não pode ser nulo ou em branco, CPF deve conter 11 dígitos, valores de depósito e saque precisam ser positivos, etc.).  
- Membros estáticos:  
  - contador incremental para o número da conta (`AtomicLong`),  
  - agência padrão definida de forma estática para novas contas.  
- Uso de `BigDecimal`: manipulação de valores monetários com escala fixa e `RoundingMode`.  
- Sobrescrita de `equals` e `hashCode`: baseada em identificadores únicos (CPF no Cliente, CNPJ no Banco).  
- Tratamento de exceções: diferenciação entre erros de validação (`IllegalArgumentException`) e falhas de operação (`IllegalStateException`).  
- Testes unitários com JUnit 5: uso de parametrização (`@CsvSource`) e verificações como `assertDoesNotThrow`, `assertThrows`, `assertEquals` e `assertAll`.

---

## Estrutura

- **Cliente** – representa o titular de uma conta.  
- **Conta** – implementa operações de depósito, saque e transferência.  
- **Banco** – permite abertura de contas, busca por número e mantém limite máximo de contas.  
- **Testes** – classes `ClienteTest` e `ContaTest` utilizando JUnit 5.  
- **Maven** – configuração de build e dependências no `pom.xml`.
