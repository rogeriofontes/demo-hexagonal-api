package br.com.demo.clientsapi.application.adapter.http.controller;

import br.com.demo.clientsapi.application.adapter.datasource.entity.ClienteEntity;
import br.com.demo.clientsapi.core.model.Cliente;
import br.com.demo.clientsapi.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseBody
    public  ResponseEntity<List<Cliente>> list() {
        List<Cliente> list = clientService.list();

        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public  ResponseEntity<Cliente> findById(@PathVariable("id") Long id){
        Cliente cliente = clientService.findById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteCadastrado = clientService.salvar(cliente);

        if (clienteCadastrado != null) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteCadastrado.getId()).toUri();
            return ResponseEntity.created(uri).body(clienteCadastrado);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public  ResponseEntity<Cliente> editar(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        Cliente clienteEditado = clientService.editar(id, cliente);

        if(clienteEditado != null) {
            return ResponseEntity.ok(clienteEditado);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
