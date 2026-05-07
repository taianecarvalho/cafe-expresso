# Café Expresso

Sistema de autoatendimento em console para cafeterias, com interfaces de cliente/totem e atendente/cozinha.

## Funcionalidades

- Cadastro e consulta de produtos
- Criação de pedidos com múltiplos itens e cálculo automático do total
- Controle de status: `PENDENTE`, `PAGO`, `EM_PREPARO`, `FINALIZADO`

## Pré-requisitos

- Java 11+
- Maven 3.6+

## Instalação

```bash
git clone https://github.com/taianecarvalho/cafe-expresso.git
cd cafe-expresso
mvn compile
```

## Como executar

```bash
mvn compile exec:java "-Dexec.mainClass=Main"
```

Ou rode `Main.java` direto pelo Eclipse.

## Testes

```bash
mvn test
```

## Autora

Taiane Carvalho dos Santos
