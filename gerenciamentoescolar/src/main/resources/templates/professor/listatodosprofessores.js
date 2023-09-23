const listarprofessor = document.getElementById("listarprofessor");

if (listarprofessor) {
    listarprofessor.addEventListener("click", () => {
        buscaTodosProfessores();
    });
}
function buscaTodosProfessores() {
    const url = "http://localhost:8080/professor"

    fetch(url)
        .then((data) => {
            return data.json();
        })
        .then((todosProfessores) => {
            let data1 = "";
            todosProfessores.forEach((values) => {
                data1 += `
            <tr>
            
              <th scope="row">${values.id}</th>
              <td>${values.nomeProfessor}</td>
              <td>${values.idade}</td>
              <td>${values.curso}</td>
              <td>${values.salario}</td>
            </tr>
          `;
            });
            document.getElementById("table-professor").innerHTML = data1;
            console.log(data1);
        })
        .catch((error) => {
            console.error("Ocorreu um erro durante a solicitação:", error);
        });
}
