package br.com.cotco.Controller;


import br.com.cotco.empresa.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.com.cotco.empresa.Empresa;
import br.com.cotco.empresa.IEmpresaRepository;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpresaController {

    @Autowired
    private IEmpresaRepository EmpRepository;

    //cadastrar
    @PostMapping("/cadastrar")
    @Transactional
    public void cadastroEmpresa(@RequestBody @Valid RDadosCadastroEmpresa dadosEmpresa){
        EmpRepository.save(new Empresa(dadosEmpresa));
    }

    @GetMapping("/buscar")
    public ResponseEntity<RDadosListagemEmpresa> listar(
            @RequestParam(name = "emailEmpresa") String emailEmpresa,
            @RequestParam(name = "senhaEmpresa") String senhaEmpresa,
            @RequestParam(name = "situacaoEmpresa") String situacaoEmpresa,
            @PageableDefault(size = 1, sort = {"nmFantEmpresa"}) Pageable paginacao){

        Empresa empresa = EmpRepository.findByEmailEmpresaAndSenhaEmpresa(emailEmpresa, senhaEmpresa);

        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Verifica se a empresa está ativa
        if (!situacaoEmpresa.equalsIgnoreCase("ATIVA")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        RDadosListagemEmpresa empresaDados = new RDadosListagemEmpresa(empresa);
        return ResponseEntity.ok(empresaDados);
    }


    //atualizar
    @PutMapping("/atualizar/{idEmpresa}")
    @Transactional
    public void atualizarEmpresa(@PathVariable Long idEmpresa, @RequestBody @Valid RDadosAtualizacaoEmpresa dadosEmpresa){
        var empresa = EmpRepository.getReferenceById(idEmpresa);
        empresa.atualizarInformacoes(dadosEmpresa);
    }

    //excluir
    @DeleteMapping("excluir/{idEmpresa}")
    @Transactional
    public void excluirEmpresa(@PathVariable Long idEmpresa){
        var empresa = EmpRepository.getReferenceById(idEmpresa);
        empresa.excluirEmpresa();
    }
}