### Bem-vindo(a) ao meu projeto!👋🏽

#### Teste pratico - Cadastro basico usuario

O CRUD é um acrônimo de Create(Criação), Retrieve(Consulta), Update(Atualização), Delete(Deleta) realiza as 4 operações básicas usadas em Banco de dados relacionais.

Tecnologias usadas no projet Java 11, maven para gerenciar as dependências, postgres para banco de dados

### ✅Tecnologias usadas para realizar o projeto
As seguintes ferramentas foram usadas na construção do projeto:

- [Java](https://www.java.com/pt-BR/)
- [PostgreSql](https://www.postgresql.org/download/)
- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/)   

## Criei cinco pacotes(package)
### 👉Package entity
- Contém a class User
    - Na classe User temos os atributos que serão trabalhados na classe UserService.
### 👉Package service
- Contém a class UserService
    - Onde criei o crud.
### 👉Package resource
- Contem a classe UserResource
    - Onde controla nossa requisições e as manda para a UserService
### 👉Package repository
- Interface que faz a comunicação com o banco.
    - Sendo essa interface que nos fornece os metodos CRUD.
### 👉Package dto
- Classe onde contém uma copia exata da nossa entidade User
    - Usamos o dto para as requisiçoes e a manipulação e convertemos esse dto na entity User somente para salvar no banco.
### 👉Class BasicRegistrationApplication
- Class BasicRegistrationApplication para rodar o projeto
