document.getElementById('loginForm').addEventListener('submit', function (event) {
  event.preventDefault(); // Evita o comportamento padrão de envio do formulário

  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;

  let data = {
    username: username,
    password: password
  };

  const usernm = localStorage.getItem('username');
  const token = localStorage.getItem('token');

  const authHeader = 'Bearer ' + token;

  axios.post('http://localhost:8080/user/login', data, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization':  authHeader
      }
  })
    .then(function (response) {
      console.log(response.data); // Exibe a resposta da solicitação no console

      // Oculta a animação de carregamento
      document.getElementById('loading-animation').style.display = 'none';

      if (response.status === 200) {
        // Login bem-sucedido, salva os dados de login (incluindo o token) e redireciona para uma nova página
        console.log("login success");
        saveLoginData(username, response.data.token); // Salva os dados de login (incluindo o token) no storage
        window.location.href = 'home.html';
      } else {
        // Trate o caso de status de resposta não 200
      }
    })
    .catch(function (error) {
      console.error(error);
      // Trate os erros da solicitação

      // Oculta a animação de carregamento em caso de erro
      document.getElementById('loading-animation').style.display = 'none';
    });

  // Exibe a animação de carregamento antes da solicitação ser enviada
  document.getElementById('loading-animation').style.display = 'block';
});

// Salvar dados de login (incluindo o token)
function saveLoginData(username, token) {
  localStorage.setItem('username', username);
  localStorage.setItem('token', token);
}

// Verificar autenticação
function checkAuthentication() {
  const username = localStorage.getItem('username');
  const token = localStorage.getItem('token');
  return !!username && !!token;
}

// Recuperar dados de login
function getLoginData() {
  const username = localStorage.getItem('username');
  const token = localStorage.getItem('token');
  return { username, token };
}

// Exemplo de uso ao recuperar os dados de login
const { username } = getLoginData();
console.log(username);
