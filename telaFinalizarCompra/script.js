// Função para finalizar o pagamento
function finalizarPagamento() {
    const bandeira = document.getElementById('bandeiraCartao').value;
    const numeroCartao = document.getElementById('numeroCartao').value;
    const dataExpiracao = document.getElementById('dataExpiracao').value;
    const codigoSeguranca = document.getElementById('codigoSeguranca').value;

    if (!numeroCartao || !dataExpiracao || !codigoSeguranca) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    // Criar objeto com os dados de pagamento para enviar para o backend
    const dadosPagamento = {
        bandeira: bandeira,
        numeroCartao: numeroCartao,
        dataExpiracao: dataExpiracao,
        codigoSeguranca: codigoSeguranca
    };

    // Enviar os dados para o backend via Fetch (ou usar qualquer outro método de sua preferência)
    fetch('http://localhost:8080/formas-pagamento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dadosPagamento)
    })
    .then(response => response.json())
    .then(data => {
        if (data.id) {
            // Sucesso! Exibir a página de pedido finalizado ou outra ação
            window.location.href = 'pedido_finalizado.html';  // Redireciona para a página de pedido finalizado
        } else {
            alert('Erro ao processar o pagamento');
        }
    })
    .catch(error => {
        alert('Erro ao enviar os dados de pagamento');
        console.error(error);
    });
}
