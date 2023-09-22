package br.com.zup.gerenciamentoEscolar.dto;

public record AlunoDTO(
        String nome,
        int idade,
        String cpf,
        String senha,
        String email) {
}
