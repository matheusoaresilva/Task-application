// Evento de escuta para o botão de editar
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("buttonEdit")) {
      const taskId = event.target.closest(".card").parentNode.dataset.taskId;
      const task = allTasks.find((t) => t.dataset.taskId === taskId);
  
      if (task) {
        const title = task.querySelector(".card-title").textContent;
        const description = task.querySelector(".card-text").textContent;
        const status = task.querySelector(".card-status").textContent;
        const category = task.querySelector(".card-header").textContent;
  
        // Preencher o formulário de edição com os dados da tarefa
        document.getElementById("editTituloInput").value = title;
        document.getElementById("editStatusInput").value = status;
        document.getElementById("editDescricaoInput").value = description;
        document.getElementById("editCategoriaInput").value = category;
        document.querySelector(".edit-task-container").dataset.taskId = taskId;
  
        // Mostrar o formulário de edição
        mostrarFormularioEditarTarefa();
      }
    }
  });
  
  // Mostrar o formulário de edição de tarefa
  function mostrarFormularioEditarTarefa() {
    const containerEditarTarefa = document.querySelector(".edit-task-container");
    const darkOverlay = document.querySelector(".dark-overlay");
  
    containerEditarTarefa.classList.add("show");
    darkOverlay.style.display = "block";
  }
  // Ocultar o formulário de edição de tarefa
function ocultarFormularioEditarTarefa() {
    const containerEditarTarefa = document.querySelector(".edit-task-container");
    const darkOverlay = document.querySelector(".dark-overlay");

    containerEditarTarefa.classList.remove("show");
    darkOverlay.style.display = "none";
}
  // Evento de escuta para o formulário de edição de tarefa
  document.getElementById("editTaskForm").addEventListener("submit", function (event) {
    event.preventDefault();
  
    const taskId = document.querySelector(".edit-task-container").dataset.taskId;
    const updatedTitle = document.getElementById("editTituloInput").value;
    const updatedStatus = document.getElementById("editStatusInput").value;
    const updatedDescription = document.getElementById("editDescricaoInput").value;
    const updatedCategory = document.getElementById("editCategoriaInput").value;
  
    if (updatedCategory) {
      const updatedTask = {
        titulo: updatedTitle,
        status: updatedStatus,
        descricao: updatedDescription,
        categoria: updatedCategory
      };
  
      axios
        .put(`http://localhost:8080/tasks/${taskId}`, updatedTask)
        .then(() => {
          // Após atualizar a tarefa, recarregar as tarefas
          buscarTarefas();
          ocultarFormularioEditarTarefa();
          console.log("Tarefa com ID " + taskId + " atualizada");
  
          updateTaskContainersDisplay();
        })
        .catch((error) => {
          console.error("Erro ao atualizar tarefa:", error);
        });
    } else {
      console.error("A categoria não pode ser nula ou vazia");
    }
  });
  
  
  