function preencherTabelaAlunos(alunos) {
    const tabela = document.getElementById("alunoTableBody");
    tabela.innerHTML = "";

    alunos.forEach(aluno => {
        const newRow = tabela.insertRow();
        newRow.innerHTML = `
            <td>${aluno.id}</td>
            <td>${aluno.nome}</td>
            <td>${aluno.idade}</td>
            <td>${aluno.cpf}</td>
            <td>${aluno.email}</td>
            <td>
                <button class="btn btn-primary" onclick="editarAluno(${aluno.id})">Editar</button>
                <button class="btn btn-danger" onclick="excluirAluno(${aluno.id})">Excluir</button>
            </td>
        `;
    });
}

function editarAluno(id) {
    window.location.href = `editar_aluno.html?id=${id}`;
}

function excluirAluno(id) {
    if (confirm(`Tem certeza de que deseja excluir o aluno com ID ${id}?`)) {
        fetch(`http://localhost:8080/api/alunos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.status === 200) {
                alert(`Aluno com ID ${id} excluído com sucesso.`);
                atualizarListaDeAlunos();
            } else {
                alert('Erro ao excluir o aluno.');
            }
        })
        .catch(error => {
            console.error('Erro ao excluir o aluno:', error);
        });
    }
}

function atualizarListaDeAlunos() {
    fetch('http://localhost:8080/api/alunos')
        .then(response => response.json())
        .then(data => {
            preencherTabelaAlunos(data);
        })
        .catch(error => {
            console.error('Erro ao obter a lista de alunos:', error);
        });
}

window.addEventListener('load', () => {
    fetch('http://localhost:8080/api/alunos')
        .then(response => response.json())
        .then(data => {
            preencherTabelaAlunos(data);
        })
        .catch(error => {
            console.error('Erro ao obter a lista de alunos:', error);
        });
});
