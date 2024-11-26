// Função para enviar uma sugestão
function enviarSugestao() {
    const nomeSugestao = document.getElementById('nomeSugestao').value;
    const descricaoSugestao = document.getElementById('descricaoSugestao').value;
    const marcaSugestao = document.getElementById('marcaSugestao').value;

    if (!nomeSugestao || !descricaoSugestao || !marcaSugestao) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const sugestaoData = {
        nome: nomeSugestao,
        descricao: descricaoSugestao,
        marca: marcaSugestao
    };

    fetch('/sugestoes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sugestaoData)
    })
    .then(response => response.json())
    .then(data => {
        console.log("Resposta do backend: ", data);  // Log para verificar o que o backend está retornando
        document.getElementById('formSugestao').reset();
        document.getElementById('mensagemSucesso').style.display = 'block';
        carregarSugestoes(); // Recarrega a lista de sugestões
    })
    .catch(error => {
        console.log("Erro ao enviar sugestão:", error);  // Log de erro
        alert("Erro ao enviar sugestão");
    });
}

// Função para carregar as sugestões
function carregarSugestoes() {
    fetch('/sugestoes')
        .then(response => response.json())
        .then(data => {
            const listaSugestoes = document.getElementById('listaSugestoes');
            listaSugestoes.innerHTML = '';  // Limpa a lista antes de preencher
    
            data.forEach(sugestao => {
                const li = document.createElement('li');
                li.classList.add('list-group-item');
                li.innerHTML = `
                    <strong>${sugestao.nome}</strong><br>
                    ${sugestao.descricao}<br>
                    Marca: ${sugestao.marca}
                    <button class="btn btn-warning btn-sm float-right ml-2" onclick="editarSugestao(${sugestao.id})">Editar</button>
                    <button class="btn btn-danger btn-sm float-right" onclick="apagarSugestao(${sugestao.id})">Apagar</button>
                `;
                listaSugestoes.appendChild(li);
            });
        })
        .catch(error => {
            console.log("Erro ao carregar sugestões:", error);  // Log de erro
            alert("Erro ao carregar sugestões");
        });
}

// Função para editar uma sugestão
function editarSugestao(id) {
    fetch(`/sugestoes/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('nomeSugestao').value = data.nome;
            document.getElementById('descricaoSugestao').value = data.descricao;
            document.getElementById('marcaSugestao').value = data.marca;
            // Trocar a função do botão para atualizar
            const button = document.querySelector('button[onclick="enviarSugestao()"]');
            button.setAttribute('onclick', `atualizarSugestao(${id})`);
            button.innerText = 'Atualizar Sugestão';
        })
        .catch(error => alert("Erro ao carregar sugestão para editar"));
}

// Função para atualizar uma sugestão
function atualizarSugestao(id) {
    const nomeSugestao = document.getElementById('nomeSugestao').value;
    const descricaoSugestao = document.getElementById('descricaoSugestao').value;
    const marcaSugestao = document.getElementById('marcaSugestao').value;

    const sugestaoData = {
        nome: nomeSugestao,
        descricao: descricaoSugestao,
        marca: marcaSugestao
    };

    fetch(`/sugestoes/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sugestaoData)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('formSugestao').reset();
        carregarSugestoes(); // Recarrega a lista de sugestões
        alert("Sugestão atualizada com sucesso");
    })
    .catch(error => alert("Erro ao atualizar sugestão"));
}

// Função para apagar uma sugestão
function apagarSugestao(id) {
    fetch(`/sugestoes/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            carregarSugestoes(); // Recarrega a lista de sugestões
            alert("Sugestão apagada com sucesso");
        } else {
            alert("Erro ao apagar sugestão");
        }
    })
    .catch(error => alert("Erro ao apagar sugestão"));
}

// Carregar as sugestões ao inicializar a página
carregarSugestoes();
