package br.com.cotco.empresa;

public record RDadosListagemEmpresa(
        Long   idEmpresa,
        String respEmpresa,
        String nmFantEmpresa,
        String cnpjEmpresa,
        String situacaoEmpresa
){

    public RDadosListagemEmpresa(Empresa empresa){
        this(empresa.getIdEmpresa(), empresa.getRespEmpresa(),empresa.getNmFantEmpresa(), empresa.getCnpjEmpresa(), empresa.getSituacaoEmpresa());
    }
}