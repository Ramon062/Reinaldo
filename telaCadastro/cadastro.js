document.getElementById("cadastroForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Impede o envio do formulário e a recarga da página

    // Captura os valores dos campos do formulário
    const nome = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("password").value;
    const cidade = document.getElementById("cidade").value;
    const estado = document.getElementById("estado").value;
    const cep = document.getElementById("cep").value;
    const complemento = document.getElementById("complemento").value;

    // Valida os campos para garantir que todos foram preenchidos
    if (!nome || !email || !senha || !cidade || !estado || !cep) {
        alert("Por favor, preencha todos os campos obrigatórios!");
        return;
    }

    // Cria o objeto usuario com os dados do formulário
    const usuario = {
        nome: nome,
        email: email,
        senha: senha,
        cidade: cidade,
        estado: estado,
        cep: cep,
        complemento: complemento
    };

    console.log("Enviando dados para o backend...");

    // Envia a requisição de cadastro para o backend
    fetch("http://localhost:8080/cadastro", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(usuario)
    })
    .then(response => {
        console.log("Resposta do servidor:", response);
        if (!response.ok) {
            return Promise.reject("Erro ao cadastrar o usuário");
        }
        return response.text();  // Alterado para response.text() se a resposta for um texto
    })
    .then(data => {
        console.log("Cadastro bem-sucedido:", data);
        // Caso o cadastro seja bem-sucedido, redireciona para a tela de login
        alert("Cadastro realizado com sucesso!");
        window.location.href = "../telaLogin/index.html"; // Ajuste o caminho, se necessário
    })
    .catch(error => {
        console.error("Erro ao cadastrar o usuário:", error);
        alert("Falha ao cadastrar. Tente novamente.");
    });
});
