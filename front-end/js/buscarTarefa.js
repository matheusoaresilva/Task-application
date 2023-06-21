let allTasks = [];
let taskContainersQueue = [];

function buscarTarefas() {
  axios
    .get("http://localhost:8080/tasks/all")
    .then((response) => {
      const data = response.data;
      const container = document.getElementById("container-tarefas");
      container.innerHTML = "";

      allTasks = [];
      taskContainersQueue = [];

      data.forEach((tarefa) => {
        const cardDiv = document.createElement("div");
        cardDiv.classList.add("col-md-3", "custom-margin");

        const card = document.createElement("div");
        card.classList.add("card");

        const cardHeader = document.createElement("div");
        cardHeader.classList.add("card-header");
        cardHeader.textContent = tarefa.categoria;

        const cardBody = document.createElement("div");
        cardBody.classList.add("card-body");

        const cardTitle = document.createElement("h5");
        cardTitle.classList.add("card-title");
        cardTitle.textContent = tarefa.titulo;

        const cardText = document.createElement("p");
        cardText.classList.add("card-text");
        cardText.textContent = tarefa.descricao;

        const button = document.createElement("a");
        button.href = "#";
        button.classList.add("btn", "btn", "me-2");
        button.innerHTML = '<i class="fas fa-trash-alt buttonTrash"></i>';

        // Adicione um evento de clique ao botão de lixeira
        button.addEventListener("click", function () {
          showDeleteTaskForm();
        });

        // Função para exibir o formulário flutuante e a sobreposição escura
        function showDeleteTaskForm() {
          document.getElementById("deleteTaskContainer").classList.add("show");
          document.querySelector(".dark-overlay").style.display = "block";
        }

        // Função para fechar o formulário flutuante e remover a sobreposição escura
        function hideDeleteTaskForm() {
          document.getElementById("deleteTaskContainer").classList.remove("show");
          document.querySelector(".dark-overlay").style.display = "none";
        }

        const cancelDeleteButton = document.getElementById("cancelDeleteButton");
        const confirmDeleteButton = document.getElementById("confirmDeleteButton");

        // Evento de clique no botão de cancelar exclusão
        cancelDeleteButton.addEventListener("click", function () {
          hideDeleteTaskForm();
        });

        // Evento de clique no botão de confirmar exclusão
        confirmDeleteButton.addEventListener("click", function () {
          // TODO logica excluir task

          // Após excluir a tarefa, você pode recarregar as tarefas
          buscarTarefas();

          hideDeleteTaskForm();
        });


        // botao editar
        const buttonB = document.createElement("a");
        buttonB.href = "#";
        buttonB.classList.add("btn", "btn");
        buttonB.innerHTML = '<i class="fas fa-edit"></i>';

        cardBody.appendChild(cardTitle);
        cardBody.appendChild(cardText);
        cardBody.appendChild(button);
        cardBody.appendChild(buttonB);

        card.appendChild(cardHeader);
        card.appendChild(cardBody);

        cardDiv.appendChild(card);

        container.appendChild(cardDiv);

        allTasks.push(cardDiv);
        taskContainersQueue.push(cardDiv);
      });

      updateTaskContainersDisplay();
    })
    .catch((error) => {
      console.error("Erro ao buscar tarefas:", error);
    });
}

window.onload = function () {
  buscarTarefas();
};
