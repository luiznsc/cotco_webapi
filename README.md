# SPRINT 3 | COT&CO | 2TDSPT
Entrega da sprint 3 | Digital Business Enablement
> [!NOTE]
>INTEGRANTES

> Nome: Eduardo Fagundes Correa | RM: 97195 <BR>
> Nome: Murilo Ariel Reis | RM: 97002 <BR>
> Nome: Luiz Henrique de Jesus do Nascimento | RM: 96335 <BR>
> Nome: Samuel Enderson Lima da Silva | RM: 96677 <BR>

---
## DOCUMENTAÇÃO DA API
#### FUNCIONALIDADES:
> - CRUD (Create, Read, Update e Delete) de empresa. A empresa é o nosso cliente direto, pois nossa solução é B2B.<BR>

> [!IMPORTANT]
> **Banco de Dados : MySql**
> O script sql para criar a tabela está nomeado como 'script_dba_cotco.txt" <BR>
> Na dúvida, clique aqui: [comando_sql](https://github.com/luiznsc/cotco_webapi/blob/main/script_dba_cotco.txt) <BR>
> **Deverá rodar todos os comandos, inclusive o schema.**
> **Deverá ser usado URL:** <BR>
> Spring: http://localhost:8080 <BR>
> DevOps: https://cotcoapibackend.azurewebsites.net
  ---

## CONTROLLER EMPRESA
### :heavy_plus_sign: CADASTRAR EMPRESA
- **URL:** /empresas/cadastrar
- **Método:** POST
- **Descrição:** Cadastrar uma nova empresa.
- **Códigos de Status:**
  - :white_check_mark: 201 (Created) - Empresa criada com sucesso.
  -  :warning: 400 (Bad Request) - Dados de entrada inválidos.
- **Corpo da Solicitação (JSON):**

  ```json
    {
        "respEmpresa": "João Silva",
        "rzSocialEmpresa": "Silva Tecnologia Ltda",
        "nmFantEmpresa": "Silva Tech",
        "cnpjEmpresa": "12.345.678/0001-99",
        "telEmpresa": "(11) 98765-4321",
        "emailEmpresa": "contato@silvatech.com.br",
        "situacaoEmpresa": "Ativa"
    }

---

### :repeat: ATUALIZAR EMPRESA
> [!IMPORTANT]
> **Poderão ser atualizados apenas os campos:** <BR/>
> respEmpresa, <BR/>
> NmFantEmpresa, <BR/>
> telEmpresa,<BR/>
> emailEmpresa, <BR/>

- **URL:** /empresas/atualizar/{idEmpresa}
- **Método:** PUT
- **Descrição:** Atualiza os dados de uma empresa existente.
- **Parâmetros da URL:**
  - {idEmpresa} - ID da empresa a ter os dados atualizados.
- **Códigos de Status:**
  - :white_check_mark: 200 (OK) - Empresa atualizada com sucesso.
  - :warning: 400 (Bad Request) - Dados de entrada inválidos.
  - :x: 404 (Not Found) - Empresa não encontrada.
- **Corpo da Solicitação (JSON):**

  ```json
    {
        "respEmpresa": "João Silva Martins Ferreira Santos",
        "nmFantEmpresa": "Silva Tech Company",
        "telEmpresa": "(11) 99999-9999",
        "emailEmpresa": "contatonovoemail@silvatech.com.br",
    }

---

### :page_with_curl: BUSCAR EMPRESA / VISUALIZAR EMPRESA

- **URL:** /empresas/buscar?situacaoEmpresa=Ativa
- **Método:** GET
- **Descrição:** Retorna os detalhes de todas empresas ATIVAS no sistema
- **Parâmetros da URL:**
  - 'situacaoEmpresa=Ativa' - Parâmetro para visualizar apenas empresas ativas em sistema.
- **Códigos de Status:**
  - :white_check_mark: 200 (OK) - Dados das empresas retornados com sucesso.
  - :x: 404 (Not Found) - Empresa(as) não encontrada.

---

### :wastebasket: APAGAR EMPRESA

> [!WARNING]
> **Ao chamar esse método a empresa NÃO SERÁ excluída, apenas ter o status alterado para INATIVA.** <BR/>
> **setando sua situacaoEmpresa para "inativa".**

- **URL:** /empresas/excluir/{idEmpresa}
- **Method:** DELETE
- **Description:** Tornar uma empresa existente como "INATIVA" no sistema
- **URL Parameters:**
  - {idEmpresa} - ID da empresa a tornar INATIVA.
- **Status Codes:**
  - :white_check_mark: 204 (No Content) - Empresa tornou-se INATIVA com sucesso.
  - :x: 404 (Not Found) - Empresa não encontrada.
