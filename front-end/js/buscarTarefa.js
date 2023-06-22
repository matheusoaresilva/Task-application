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

        cardDiv.dataset.taskId = tarefa.id;

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

        const cardStatus = document.createElement("p");
        cardStatus.classList.add("card-status");
        cardStatus.textContent = tarefa.status;

        const button = document.createElement("a");
        button.href = "#";
        button.classList.add("btn", "btn", "me-2");
        button.innerHTML = '<i class="fas fa-trash-alt buttonTrash"></i>';

        // Adicione um evento de clique ao botão de lixeira
        button.addEventListener("click", function () {
          showDeleteTaskForm(cardDiv.dataset.taskId);
        });

        // Função para exibir o formulário de exclusão da tarefa
        function showDeleteTaskForm(taskId) {
          const deleteTaskContainer = document.getElementById("deleteTaskContainer");
          const darkOverlay = document.querySelector(".dark-overlay");

          // Atribuir o ID da tarefa ao contêiner de exclusão como um atributo de dados
          deleteTaskContainer.dataset.taskId = taskId;

          deleteTaskContainer.classList.add("show");
          darkOverlay.style.display = "block";
        }

        // Função para ocultar o formulário de exclusão da tarefa
        function hideDeleteTaskForm() {
          const deleteTaskContainer = document.getElementById("deleteTaskContainer");
          const darkOverlay = document.querySelector(".dark-overlay");

          deleteTaskContainer.classList.remove("show");
          darkOverlay.style.display = "none";
        }

        const cancelDeleteButton = document.getElementById("cancelDeleteButton");
        const confirmDeleteButton = document.getElementById("confirmDeleteButton");

        // Evento de clique no botão de cancelar exclusão
        cancelDeleteButton.addEventListener("click", function () {
          hideDeleteTaskForm();
        });

        // Evento de clique no botão de confirmar exclusão
        confirmDeleteButton.addEventListener("click", function () {
          const taskId = document.getElementById("deleteTaskContainer").dataset.taskId; // Obter o ID da tarefa

          // Excluir a tarefa do banco de dados usando o Axios
          axios
            .delete(`http://localhost:8080/tasks/${taskId}`)
            .then(() => {
              // Após excluir a tarefa, recarregue as tarefas
              buscarTarefas();
              hideDeleteTaskForm();
              console.log("task with id " + taskId + " deleted");

              updateTaskContainersDisplay();
            })
            .catch((error) => {
              console.error("Erro ao excluir tarefa:", error);
            });
        });

        // botao editar
        const buttonB = document.createElement("a");
        buttonB.href = "#";
        buttonB.classList.add("btn", "btn");
        buttonB.innerHTML = '<i class="fas fa-edit buttonEdit"></i>';

        cardBody.appendChild(cardTitle);
        cardBody.appendChild(cardText);
        cardBody.appendChild(cardStatus);
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

function updateTaskContainersDisplay() {
  const noTaskMessage = document.getElementById("noTaskMessage");

  if (taskContainersQueue.length === 0) {
    // Exibir a mensagem "Não há nenhuma task"
    if (noTaskMessage) {
      noTaskMessage.style.display = "block";
    }
  } else {
    // Ocultar a mensagem "Não há nenhuma task"
    if (noTaskMessage) {
      noTaskMessage.style.display = "none";
    }
  }
}

window.onload = function () {
  buscarTarefas();
  console.log("Testando texto no task");

  // Verificar se a lista de tasks está vazia e exibir a mensagem "Não há nenhuma task"
  updateTaskContainersDisplay();

  
};
