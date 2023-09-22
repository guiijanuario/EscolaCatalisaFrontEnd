const form = document.getElementById("formulario");

form.addEventListener("submit", event =>{
    event.preventDefault();

    const nome = document.getElementById("nomeProfessor").value;
    const idade = parseInt(document.getElementById("idade").value);
    const cursos = [document.getElementById("curso").value]; // Pode ser uma lista
    const salario = parseFloat(document.getElementById("salario").value);

    const novoProfessor = {
        nomeProfessor: nome,
        idade : idade,
        curso: [{cursos}],
        salario : salario

    };
    console.log(novoProfessor);
    fetch("http://localhost:8080/professor", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoProfessor)
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("formulario").reset();
        alert('Aluno cadastrado com sucesso!');
    })
    .catch(error => {
        console.log(error);
        console.error('Erro ao cadastrar o aluno:', error);
    });
});

function mensagem(){
    alert("Cadastro feito com sucesso!")
    window.location.reload(true);
}