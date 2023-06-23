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

                // TODO: hide modal
            } else {
                console.log("Ocorreu um erro na solicitação");
                console.log(response.data);
            }

        })
        .catch(function (error) {
            console.error(error);
            console.log("Erro na requisicao");
        });
})