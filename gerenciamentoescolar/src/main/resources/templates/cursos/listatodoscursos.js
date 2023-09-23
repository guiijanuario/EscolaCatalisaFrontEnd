const listarcursos = document.getElementById("listarcursos");

if (listarcursos) {
    listarcursos.addEventListener("click", () => {
        buscaTodosCursos();
    });
}
function buscaTodosCursos() {
    const url = "http://localhost:8080/cursos"

    fetch(url)
        .then((data) => {
            return data.json();
        })
        .then((todosCursos) => {
            let data1 = "";
            todosCursos.forEach((values) => {
                data1 += `
            <tr>
            
              <th scope="row">${values.id}</th>
              <td>${values.nome}</td>
              <td>${values.cargaHoraria}</td>
              <td>${values.professorId}</td>
              <td>${values.matriculaId}</td>
            </tr>
          `;
            });
            document.getElementById("table-cursos").innerHTML = data1;
            console.log(data1);
        })
        .catch((error) => {
            console.error("Ocorreu um erro durante a solicitação:", error);
        });
}
