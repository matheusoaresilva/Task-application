// Função para exibir o formulário flutuante e a sobreposição escura
function showFloatingContainer() {
  document.getElementById("floatingContainer").classList.add("show");
  document.querySelector(".dark-overlay").style.display = "block";
}

// Função para fechar o formulário flutuante e remover a sobreposição escura
function closeFloatingContainer() {
  document.getElementById("floatingContainer").classList.remove("show");
  document.querySelector(".dark-overlay").style.display = "none";
}

// Evento de clique no botão "New task"
document
  .getElementById("newTaskButton")
  .addEventListener("click", showFloatingContainer);

// Evento de clique no botão "Fechar"
document
  .getElementById("closeButton")
  .addEventListener("click", closeFloatingContainer);

document
.getElementById("saveButton")
.addEventListener("click",closeFloatingContainer);

// Enviar os dados do formulário para o back-end ao salvar
const newTaskForm = document.getElementById("newTaskForm");

newTaskForm.addEventListener("submit", (event) => {
  event.preventDefault();

  const tituloInput = document.getElementById("tituloInput").value;
  const descricaoInput = document.getElementById("descricaoInput").value;
  const categoriaInput = document.getElementById("categoriaInput").value;
  const statusInput = document.getElementById("statusInput").value;

  const taskData = {
    titulo: tituloInput,
    descricao: descricaoInput,
    categoria: categoriaInput,
    status: statusInput,
  };

  axios
    .post("http://localhost:8080/tasks/save", taskData)
    .then((response) => {
      // Verificar o status da resposta
      if (response.status === 201) {
        // Limpar os campos do formulário
        newTaskForm.reset();

        // Esconder o formulário flutuante
        floatingContainer.classList.remove("show");

        // Atualizar a lista de tarefas
        buscarTarefas();
      } else {
        throw new Error("Failed to create task. Status: " + response.status);
      }
    })
    .catch((error) => {
      console.error("Erro ao criar tarefa:", error);
    });
});
