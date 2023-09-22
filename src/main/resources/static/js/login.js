document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const email = document.getElementById("username").value;
    const senha = document.getElementById("password").value;

    fetch('http://localhost:8080/api/alunos/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, senha })
    })
    .then(response => {
        if (response.status === 200) {
            window.location.href = 'login-sucesso.html';
        } else {
            alert('Credenciais inválidas. Tente novamente.');
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
    });
});
