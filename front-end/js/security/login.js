document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evita o comportamento padrão de envio do formulário

    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let data = {
        username: username,
        password: password
    };

    axios.post('http://localhost:8080/user/login', data)
        .then(function (response) {
            console.log(response.data); // Exibe a resposta da solicitação no console

            // Oculta a animação de carregamento
            document.getElementById('loading-animation').style.display = 'none';

            if (response.status === 200) {
                // Login bem-sucedido, faça o que for necessário, como redirecionar para uma nova página
                console.log("login success");
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
