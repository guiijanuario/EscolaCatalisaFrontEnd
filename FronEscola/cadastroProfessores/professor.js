



fetch("http://localhost:8080/professores").then((data)=>{
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
                  <td>${values.curso} </td>
                  
              </tr>
          </tbody>
          `
      })
      document.getElementById("produtoDados").innerHTML = data1;
      console.log(todosProfessores);
  })