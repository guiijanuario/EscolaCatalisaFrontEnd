fetch("http://localhost:8080/api/zupSchool/cursos").then((data)=>{
    return data.json();
  }).then((todosCursos) =>{
      let data1 = "";
      todosCursos.map((values) =>{
          data1 += `
          <tbody>
              <tr>
                  <th scope="row">${values.id} </td>
                  <td>${values.nome}</td> 
                  <td>${values.cargaHoraria}</td> 
              </tr>
          </tbody>
          `
      })
      document.getElementById("cursosDados").innerHTML = data1;
      console.log(todosCursos);
  })


const form = document.getElementById("formulario");

form.addEventListener("submit", event =>{
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/api/zupSchool/cursos", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(data => console.log(data)).catch(error => console.log(error));
});

function mensagem(){
    alert("Aluno(a) cadastrado(a) com sucesso!")
    window.location.reload(true);
}


