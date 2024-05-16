package br.com.cotco.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:5000", "http://localhost:3000"})
public class AnaliseCotacaoController {
    @PostMapping("/enviarAnalise")
    public ResponseEntity<String> getProduto(@RequestParam("productname") String productname,
                                             @RequestParam("file") MultipartFile file) {
        RestTemplate restTemplate = new RestTemplate();

        // Define a URL da API Flask
        String url = "http://127.0.0.1:5000/analise";

        // Cria um objeto HttpHeaders e define o tipo de conteúdo para MULTIPART_FORM_DATA
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Cria um objeto MultiValueMap e adiciona o recurso do arquivo, o nome do produto e o critério a ele
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(convert(file)));
        body.add("productname", productname);

        // Cria um objeto HttpEntity usando os headers e o body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Faz a chamada para a API e armazena a resposta
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        // Retorna o corpo da resposta
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    // Método auxiliar para converter um MultipartFile em um File
    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(convFile)) {
                fos.write(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }
}