const form = document.getElementById("formulario");

form.addEventListener("submit", event =>{
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/cursos", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(data => console.log(data)).catch(error => console.log(error));
});

function mensagem(){
    alert("Cadastro feito com sucesso!")
    window.location.reload(true);
}



fetch("http://localhost:8080/cursos").then((data)=>{
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
                  <td>${values.professores} </td>
                  
              </tr>
          </tbody>
          `
      })
      document.getElementById("produtoDados").innerHTML = data1;
      console.log(todosCursos);
  })