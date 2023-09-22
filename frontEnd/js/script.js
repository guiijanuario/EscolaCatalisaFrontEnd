$(document).ready(function () {

    // Função para listar alunos

    function listarAlunos() {
        // Fazer uma solicitação AJAX para obter a lista de alunos do servidor
        // e adicionar os alunos à lista "alunosList" no HTML
        $.ajax({
            url: "http://localhost:8080/api/alunos",
            method: "GET",
            success: function (data) {
                // Limpar a lista de alunos existente
                $("#alunosList").empty();

                // Adicionar cada aluno à lista
                data.forEach(function (aluno) {
                    $("#alunosList").append(`<li class="list-group-item">${aluno.nome_aluno} - ${aluno.idade} anos - ${aluno.email}</li>`);
                });
            },
            error: function (error) {
                console.error("Erro ao obter a lista de alunos:", error);
            }
        });
    }

    // Manipulador de envio do formulário
    $("#cadastroForm").submit(function (event) {
        event.preventDefault();

        // Obter os dados do formulário
        const nome = $("#nome_aluno").val();
        const idade = $("#idade").val();
        const email = $("#email").val();

        // Criar um objeto com os dados do aluno
        const alunoData = {
            nome_aluno: nome,
            idade: idade,
            email: email
        };

        // Enviar os dados para o servidor usando AJAX
        $.ajax({
            url: "http://localhost:8080/api/alunos",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(alunoData),
            success: function (data) {
                // Limpar o formulário após o envio
                $("#nome_aluno").val("");
                $("#idade").val("");
                $("#email").val("");

                // Chamar a função para listar alunos novamente após o cadastro
                listarAlunos();
            },
            error: function (error) {
                console.error("Erro ao cadastrar o aluno:", error);
            }
        });
    });

});
