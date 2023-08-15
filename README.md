GET
R getporid - alunos
http://localhost:8080/alunos/4
﻿

GET
R listatodosalunos
http://localhost:8080/alunos
﻿

POST
C cadastra Alunos
http://localhost:8080/alunos
﻿

Body
raw (json)
json
{
    "nome": "fasdf",
    "idade": 22,
    "email": "asdfas@df.com"
}

PUT
U update alunos
http://localhost:8080/alunos/5
﻿
Body
raw (json)
json
{
    "id": 5,
    "nome" : "asdubral",
    "idade": 44,
    "email": "asdfas@df.com"
}

GET
listatodoscursos
http://localhost:8080/cursos
﻿

DELETE
delete aluno ex 5
http://localhost:8080/alunos/5
﻿

POST
update cursos
http://localhost:8080/cursos
﻿

Body
raw (json)
json
{


    "nome": "banco de dados",
    "cargaHoraria":360,
    "professorId": 1
}

DELETE
delete curso
http://localhost:8080/cursos/2
﻿

PUT
save
http://localhost:8080/cursos/9
﻿

Body
raw (json)
json
{
    "id": 9,
    "nome": "compiladores de computadores",
    "cargaHoraria":400
}

GET
listaprofessor
http://localhost:8080/professor
﻿

PUT
http://localhost:8080/cursos/1 - atualiza curso
http://localhost:8080/cursos/1
﻿

Body
raw (json)
json
{
        "id": 1,
        "nome": "lfa",
        "cargaHoraria": 240,
        "professorId": 1
}

POST
http://localhost:8080/professor
http://localhost:8080/professor
﻿

Body
raw (json)
json
{
    "id":1,
    "nomeProfessor" : "asdrubal",
    "idade": 50,
    "curso": 1,
    "salario": 8000

}
GET
get matricula
http://localhost:8080/matricula
