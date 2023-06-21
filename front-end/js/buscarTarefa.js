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
        button.innerHTML = '<i class="fas fa-trash-alt"></i>';

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

      s
      updateTaskContainersDisplay();
    })
    .catch((error) => {
      console.error("Erro ao buscar tarefas:", error);
    });
}
window.onload = function () {
  buscarTarefas();
};
