function updateTaskContainersDisplay() {
    const container = document.getElementById("container-tarefas");
    container.innerHTML = ""; 
  
   
    taskContainersQueue.forEach((taskContainer) => {
      container.appendChild(taskContainer);
    });
  }


function searchTasks(term) {
    const filteredTasks = [];
  
   
    allTasks.forEach((taskContainer) => {
      const title = taskContainer.querySelector(".card-title").textContent.toLowerCase();
  
      if (title.includes(term.toLowerCase())) {
        taskContainer.style.display = "block";
        filteredTasks.push(taskContainer);
      } else {
        taskContainer.style.display = "none";
      }
    });
  
   
    if (term !== "") {
      taskContainersQueue = filteredTasks;
    } else {
      taskContainersQueue = allTasks;
    }
  
    
    updateTaskContainersDisplay();
  }
  

  function handleSearchInputChange(event) {
    const query = event.target.value.trim(); 
    searchTasks(query);
  }
  

  const searchInput = document.querySelector('input[type="search"]');
  searchInput.addEventListener("input", handleSearchInputChange);
  

  function resetTaskContainersDisplay() {
    searchInput.value = ""; 
    searchTasks(""); 
  }
 
  searchInput.addEventListener("keydown", (event) => {
    if (event.keyCode === 8 && searchInput.value === "") {
      resetTaskContainersDisplay();
    }
  });