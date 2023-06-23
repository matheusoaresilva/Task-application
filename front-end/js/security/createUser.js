document.getElementById("signupModal").addEventListener("submit", function (e) {
    e.preventDefault(); // Impede o envio do formulario

    // Obtendo os valores dos campos input que serão enviados via post
    let name = document.getElementById("signupName").value;
    let username = document.getElementById("signupUsername").value;
    let password = document.getElementById("signupPassword").value;
    let confirmPassword = document.getElementById("signupConfirmPassword").value;

    // Criando objeto com dados que serao enviados
    let data = {
        name: name,
        username: username,
        password: password,
        confirmPassword: confirmPassword
    }

    axios
        .post('http://localhost:8080/user/create', data)
        .then(function (response) {

            if (response.status === 200) {
                console.log("Usuario criado com sucesso");

                // Cria o elemento do alerta de sucesso
                let successAlert = document.createElement('div');
                successAlert.classList.add('alert', 'alert-success', 'alert-dismissible', 'fade', 'show', 'fixed-bottom', 'text-center');
                successAlert.setAttribute('role', 'alert');
                successAlert.style.fontSize = '20px'; // Define o tamanho da fonte

                successAlert.textContent = 'Usuário criado com sucesso!';

                // Adiciona o alerta à página
                let bodyElement = document.getElementsByTagName('body')[0];
                bodyElement.appendChild(successAlert);

                // Define um tempo para remover automaticamente o alerta
                setTimeout(function () {
                    bodyElement.removeChild(successAlert);
                }, 3000); // Tempo em milissegundos (neste exemplo, 3 segundos)
                // TODO: hide modal
            } else {
                console.log("Ocorreu um erro na solicitação");
                console.log(response.data);
            }

        })
        .catch(function (error) {
            console.error(error);
            console.log("Erro na requisicao");
            // Cria o elemento do alerta de falha
            var failureAlert = document.createElement('div');
            failureAlert.classList.add('alert', 'alert-warning', 'alert-dismissible', 'fade', 'show', 'fixed-bottom', 'text-center');
            failureAlert.setAttribute('role', 'alert');
            failureAlert.style.fontSize = '20px'; // Define o tamanho da fonte
            // failureAlert.style.width = '300px'; // Define a largura desejada em pixels

            failureAlert.textContent = 'Falha ao criar usuário.';

            // Adiciona o alerta à página
            var bodyElement = document.getElementsByTagName('body')[0];
            bodyElement.appendChild(failureAlert);

            // Define um tempo para remover automaticamente o alerta
            setTimeout(function () {
                bodyElement.removeChild(failureAlert);
            }, 3000); // Tempo em milissegundos (neste exemplo, 3 segundos)
        });
})