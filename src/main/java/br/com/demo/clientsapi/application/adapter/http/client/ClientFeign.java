package br.com.demo.clientsapi.application.adapter.http.client;

import br.com.demo.clientsapi.core.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "client", url = "http://localhost:8080/clients")
public interface ClientFeign {

    @GetMapping
    Cliente retornaCliente();

    @PostMapping
    public Page<Cliente> searchHellos(@RequestHeader("id") String clientId);
}
