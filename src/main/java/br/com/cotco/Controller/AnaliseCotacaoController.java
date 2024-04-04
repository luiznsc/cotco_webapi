package br.com.cotco.Controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
public class AnaliseCotacaoController {

    @PostMapping("/sendFile")
    public ResponseEntity<String> sendFile(@RequestParam String product_name) {
        RestTemplate restTemplate = new RestTemplate();

        // Define o caminho para o arquivo CSV
        String filePath = "C:\\Users\\Luiz_\\Documents\\ANALISE_DEV_SISTEMAS\\cotco\\cotco_webapi\\electronics_product.csv"; // Certifique-se de incluir o nome do arquivo

        // Cria um objeto FileSystemResource a partir do arquivo CSV
        FileSystemResource resource = new FileSystemResource(new File(filePath));

        // Cria um objeto HttpHeaders e define o tipo de conteúdo para MULTIPART_FORM_DATA
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Cria um objeto MultiValueMap e adiciona o recurso do arquivo e o nome do produto a ele
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);
        body.add("product_name", product_name);

        // Cria um objeto HttpEntity usando os headers e o body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Faz a chamada para a API e armazena a resposta
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/analyze", requestEntity, String.class);

        // Imprime o resultado do retorno da API no console
        System.out.println("Resposta da API: " + response.getBody());

        return response;
    }
}
