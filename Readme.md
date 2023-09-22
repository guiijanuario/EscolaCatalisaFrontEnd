### Testes para CursoController e outros com a mesma lógica

Neste documento, apresentamos os testes unitários desenvolvidos para a classe `CursoController`, que é responsável por lidar com as operações relacionadas a cursos na nossa aplicação. Os testes foram organizados de acordo com as melhores práticas e seguem o padrão Arrange-Act-Assert (AAA).

### Organização dos Testes

Os testes foram estruturados em três partes distintas:

1. **Arrange**: Preparação do cenário, incluindo a configuração de comportamentos de mocks e estados iniciais.
2. **Act**: Execução da ação que está sendo testada, como a chamada a um endpoint específico.
3. **Assert**: Verificação dos resultados obtidos com base nas expectativas definidas.

### Teste de Listar Todos os Cursos

O objetivo deste teste é verificar se o endpoint para listar todos os cursos funciona conforme o esperado. É importante garantir que a resposta contenha os cursos esperados.

```java
@Test
public void testListarTodosCursos() throws Exception {
    // Arrange
    given(cursoService.listarTodosCursos()).willReturn(cursos);

    // Act & Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Matemática"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("História"));
}
```
### Teste de Listar Curso por ID Existente

Neste teste, estamos verificando se o endpoint para listar um curso por ID existente está funcionando corretamente. Isso inclui a validação da resposta recebida e dos valores esperados.

```java
@Test
public void testListarCursoPorIdExistente() throws Exception {
// Arrange
given(cursoService.listarCursoPorId(1L)).willReturn(Optional.of(cursos.get(0)));

    // Act & Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos/{id}", 1L))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Matemática"));
}
```

### Teste de Listar Curso por ID Não Existente

Este teste tem como objetivo verificar se o endpoint para listar um curso por ID não existente retorna o status de "não encontrado" conforme o esperado.

```java
@Test
public void testListarCursoPorIdNaoExistente() throws Exception {
    // Arrange
    given(cursoService.listarCursoPorId(3L)).willReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/cursos/{id}", 3L))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
}
```

### Conclusão

Esses são apenas alguns exemplos dos testes unitários elaborados para a classe CursoController, AlunoController e outros seguindo a mesma lógica. 

Analise esses testes de acordo com a estrutura e requisitos específicos do seu projeto.

A abordagem AAA e o uso de mocks ajudam a garantir a qualidade e a robustez dos testes em sua aplicação.



###### FIM

