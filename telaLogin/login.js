document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm');

  loginForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const email = loginForm.email.value.trim();
    const senha = loginForm.password.value.trim();

    // Verifica se os campos estão preenchidos
    if (!email || !senha) {
      alert('Por favor, preencha todos os campos.');
      return;
    }

    const dados = {
      email: email,
      senha: senha,
    };

    // Envia os dados para o backend via fetch
    fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dados),
    })
      .then((response) => {
        if (response.ok) {
          return response.text(); // O backend retorna uma mensagem simples
        } else {
          return response.text().then((msg) => {
            throw new Error(msg || "Erro ao realizar login");
          });
        }
      })
      .then((message) => {
        alert(message); // Exibe mensagem de sucesso
        window.location.href = "produtos.html"; // Redireciona para a página de produtos
      })
      .catch((error) => {
        alert("Erro: " + error.message);
        console.error("Erro ao realizar login:", error);
      });
  });
});
