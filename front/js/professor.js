fetch("http://localhost:8080/api/zupSchool/professores").then((data)=>{
    return data.json();
  }).then((todosProfessores) =>{
      let data1 = "";
      todosProfessores.map((values) =>{
          data1 += `
          <tbody>
              <tr>
                  <th scope="row">${values.id} </td>
                  <td>${values.nome}</td> 
                  <td>${values.idade}</td>
                  <td>${values.salario}</td>
                  <td>${values.curso.id}</td> 
              </tr>
          </tbody>
          `
      })
      document.getElementById("professoresDados").innerHTML = data1;
      console.log(todosProfessores);
  })

const form = document.getElementById("formulario");

form.addEventListener("submit", event =>{
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    fetch("http://localhost:8080/api/zupSchool/professores", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(res => res.json()).then(data => console.log(data)).catch(error => console.log(error));
});

function mensagem(){
    alert("Professor(a) cadastrado(a) com sucesso!")
    window.location.reload(true);
}