document.getElementById("editarAlunoForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const id = document.getElementById("id").value;
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("cpf").value;
    const cpf = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const idade = document.getElementById("idade").value;

    const alunoAtualizado = {
        id: id,
        nome: nome,
        idade: idade,
        cpf: cpf,
        senha: senha,
        email: email
    };

    fetch(`http://localhost:8080/api/alunos/${id}`, {
        method: 'PUT', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(alunoAtualizado)
    })
    .then(response => {
        if (response.ok) {
            alert('Aluno atualizado com sucesso!');

        } else {
            alert('Erro ao atualizar o aluno.');
        }
    })
    .catch(error => {
        console.error('Erro ao atualizar o aluno:', error);
    });
});
