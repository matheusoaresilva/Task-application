# Task Application(Em desenvolvimento)

Este é um aplicativo de gerenciamento de tarefas desenvolvido que permite aos usuários criar, editar, excluir e listar tarefas. Cada tarefa possui um título, descrição, status e categoria. O objetivo deste projeto é fornecer uma ferramenta simples e eficiente para organizar e gerenciar as tarefas diárias.

## Funcionalidades

- **Criar tarefa**: Os usuários podem criar novas tarefas fornecendo um título, descrição, status e categoria.

- **Editar tarefa**: Os usuários podem editar as tarefas existentes, alterando qualquer um dos campos (título, descrição, status e categoria).

- **Excluir tarefa**: Os usuários podem excluir tarefas existentes.

- **Listar tarefas**: Os usuários podem ver uma lista de todas as tarefas existentes, ordenadas por data de criação ou categoria.
- **Buscar Tasks**: Buscar tasks pelo titulo.

![Interface](https://github.com/matheusoaresilva/Task-application/blob/main/front-end/img/taskapp.png)

### Adicionar Nova Task

![new](https://github.com/matheusoaresilva/Task-application/blob/main/front-end/img/taskapp-new.png)


### Deletar Task
![delete](https://github.com/matheusoaresilva/Task-application/blob/main/front-end/img/taskapp-delete.png)
## Utilizados nesse projeto:

- **Java 17**
- **Spring Boot**
- **Postman**: para testar as funcionalidades da API.
- **MySQL**: para armazenar os dados das tarefas.
- **JUnit5** e **Mockito**: para escrever e executar testes unitários.

- **HTML5**: para estruturar a página e os elementos do formulário.
- **CSS3**: para estilizar os elementos e criar o layout responsivo.
- **JavaScript**: para adicionar interatividade e manipular os dados do formulário.
- **Bootstrap 5**: para adicionar estilos responsivos e componentes pré-estilizados.


## Exceptions

Além disso, o projeto também trata as exceptions, ou seja, quando um campo obrigatório não é preenchido, uma mensagem personalizada é retornada ao usuário. Foram criadas as classes exception para tratar esses casos específicos. 

Se houvesse tentativa de inserir os dados sem passar algum valor para o Titulo
seria retornado a seguinte mensagem:

```
{
    "error": "Erro ao salvar tarefa",
    "message": "Titulo da tarefa não pode ser vazio"
}
```



## Dificuldades encontradas

As dificuldades encontradas foram principalmente relacionadas a implementação das validações das entradas dos dados, tendo que criar as classes exceptions para tratar os casos de campos não preenchidos ou dados inválidos. Também foi necessário um bom entendimento sobre o funcionamento do Postman e do MySQL para implementar as funcionalidades de criação, edição, exclusão e listagem de tarefas.
