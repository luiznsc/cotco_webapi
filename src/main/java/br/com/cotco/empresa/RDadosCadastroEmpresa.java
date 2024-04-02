package br.com.cotco.empresa;

public record RDadosCadastroEmpresa(
        String respEmpresa,
        String rzSocialEmpresa,
        String nmFantEmpresa,
        String cnpjEmpresa,
        String telEmpresa,
        String emailEmpresa,
        String situacaoEmpresa
    ) {
}