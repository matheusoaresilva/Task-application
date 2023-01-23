# Task Application

Este é um aplicativo de gerenciamento de tarefas desenvolvido que permite aos usuários criar, editar, excluir e listar tarefas. Cada tarefa possui um título, descrição, status e categoria.

## Funcionalidades

- **Criar tarefa**: Os usuários podem criar novas tarefas fornecendo um título, descrição, status e categoria.

- **Editar tarefa**: Os usuários podem editar as tarefas existentes, alterando qualquer um dos campos (título, descrição, status e categoria).

- **Excluir tarefa**: Os usuários podem excluir tarefas existentes.

- **Listar tarefas**: Os usuários podem ver uma lista de todas as tarefas existentes, ordenadas por data de criação ou categoria.

## Utilizados nesse projeto:

- **Java 17**
- **Spring Boot**
- **Postman**: para testar as funcionalidades da API
- **MySQL**: para armazenar os dados das tarefas

## Exceptions

Além disso, o projeto também trata as exceptions, ou seja, quando um campo obrigatório não é preenchido, uma mensagem personalizada é retornada ao usuário. Foram criadas as classes exception para tratar esses casos específicos. O objetivo deste projeto é fornecer uma ferramenta simples e eficiente para organizar e gerenciar as tarefas diárias.

```
{
    "error": "Erro ao salvar tarefa",
    "message": "Titulo da tarefa não pode ser vazio"
}
```

## Dificuldades encontradas

As dificuldades encontradas foram principalmente relacionadas a implementação das validações das entradas dos dados, tendo que criar as classes exceptions para tratar os casos de campos não preenchidos ou dados inválidos. Também foi necessário um bom entendimento sobre o funcionamento do Postman e do MySQL para implementar as funcionalidades de criação, edição, exclusão e listagem de tarefas.
