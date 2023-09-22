document.getElementById("buscaForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const produtoId = parseInt(document.getElementById("produtoId").value);

    fetch(`http://localhost:8080/api/produtos/${produtoId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Produto não encontrado');
            }
            return response.json();
        })
        .then(data => {
            const resultadoBusca = document.getElementById("resultadoBusca");
            resultadoBusca.innerHTML = `
                <h4>ID: ${data.id}</h4>
                <p>Nome: ${data.nome}</p>
                <p>Descrição: ${data.descricao}</p>
                <p>Preço: ${data.preco}</p>
                <p>Quantidade em Estoque: ${data.quantidadeEstoque}</p>
            `;
        })
        .catch(error => {
            const resultadoBusca = document.getElementById("resultadoBusca");
            resultadoBusca.innerHTML = `<p>${error.message}</p>`;
        });
});
