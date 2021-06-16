package br.com.demo.clientsapi.application.adapter.http.client;

import br.com.demo.clientsapi.core.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ClientHttpClient {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/http-client/clients")
    public String getClienteList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(" http://localhost:8080/clients", HttpMethod.GET, entity, String.class).getBody();
    }

    @PostMapping(value = "/http-client/clients")
    public String createProducts(@RequestBody Cliente cliente) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cliente> entity = new HttpEntity<>(cliente,headers);

        return restTemplate.exchange(
                "http://localhost:8080/clients", HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping(value = "/http-client/clients/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestBody Cliente product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cliente> entity = new HttpEntity<>(product,headers);

        return restTemplate.exchange(
                "http://localhost:8080/clients/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping(value = "/http-client/clients/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Cliente> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "http://localhost:8080/clients/"+id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
