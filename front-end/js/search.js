
function searchByTitle(query) {
    const tasks = document.getElementsByClassName("card");
  
    for (let i = 0; i < tasks.length; i++) {
      const task = tasks[i];
      const titleElement = task.getElementsByClassName("card-title")[0];
      const title = titleElement.textContent.toLowerCase();
  
      if (title.includes(query.toLowerCase())) {
        task.style.display = "block"; 
      } else {
        task.style.display = "none"; 
      }
    }
  }
  function handleSearchInputChange(event) {
    const query = event.target.value; 
    searchByTitle(query); 
  }
  
  // Adiciona um ouvinte de evento para o campo de pesquisa
  const searchInput = document.querySelector('input[type="search"]');
  searchInput.addEventListener("input", handleSearchInputChange);
  