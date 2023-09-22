function formatarNumeroComVirgula(numero) {
    return numero.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

document.getElementById("cadastroForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const idade = parseInt(document.getElementById("idade").value);
    const cpf = document.getElementById("cpf").value;
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    const novoAluno = {
        nome: nome,
        idade: idade,
        cpf: cpf,
        email: email,
        senha: senha
    };

    fetch('http://localhost:8080/api/alunos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoAluno)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("cadastroForm").reset();
        alert('Aluno cadastrado com sucesso!');
    })
    .catch(error => {
        console.error('Erro ao cadastrar o aluno:', error);
    });
});
