document.addEventListener('DOMContentLoaded', () => {
    const cadastroForm = document.getElementById('cadastroForm');
  
    cadastroForm.addEventListener('submit', (e) => {
      e.preventDefault(); // Evita o envio padrão do formulário
  
      const nome = cadastroForm.name.value.trim();
      const email = cadastroForm.email.value.trim();
      const senha = cadastroForm.password.value.trim();
      const cidade = cadastroForm.city.value.trim();
      const estado = cadastroForm.state.value.trim();
      const cep = cadastroForm.cep.value.trim();
      const complemento = cadastroForm.complemento.value.trim();
  
      // Verifique se todos os campos obrigatórios foram preenchidos
      if (!nome || !email || !senha || !cidade || !estado || !cep) {
        alert('Por favor, preencha todos os campos obrigatórios.');
        return;
      }
  
      // Envia os dados para o backend
      const dados = {
        nome: nome,
        email: email,
        senha: senha,
        cidade: cidade,
        estado: estado,
        cep: cep,
        complemento: complemento
      };
  
      fetch('http://localhost:8080/cadastro', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            return response.text().then((msg) => {
              throw new Error(msg || 'Erro ao realizar o cadastro');
            });
          }
        })
        .then((message) => {
          alert(message); // Mensagem de sucesso
          window.location.href = 'index.html'; // Redireciona para a página de login
        })
        .catch((error) => {
          alert('Erro: ' + error.message);
          console.error('Erro ao realizar o cadastro:', error);
        });
    });
  });
  