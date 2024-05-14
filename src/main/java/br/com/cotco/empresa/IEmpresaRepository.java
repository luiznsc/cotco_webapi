package br.com.cotco.empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {
    Page<Empresa> findAllBySituacaoEmpresa(String situacaoEmpresa, Pageable paginacao);
    Empresa findByEmailEmpresaAndSenhaEmpresa(String emailEmpresa, String senhaEmpresa);
}