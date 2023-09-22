const listaralunos = document.getElementById("listaralunos");

if (listaralunos) {
    listaralunos.addEventListener("click", () => {
        buscaTodosAlunos();
    });
}
function buscaTodosAlunos() {
    const url = "http://localhost:8080/alunos"

    fetch(url)
        .then((data) => {
            return data.json();
        })
        .then((todosAlunos) => {
            let data1 = "";
            todosAlunos.forEach((values) => {
                data1 += `
            <tr>
              <th scope="row">${values.id}</th>
              <td>${values.nome}</td>
              <td>${values.idade}</td>
              <td>${values.email}</td>
              <td>${values.matriculaId}</td>
              <td>${values.cursoId}</td>
            </tr>
          `;
            });
            document.getElementById("table-alunos").innerHTML = data1;
        })
        .catch((error) => {
            console.error("Ocorreu um erro durante a solicitação:", error);
        });
}
