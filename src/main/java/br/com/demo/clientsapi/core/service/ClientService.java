package br.com.demo.clientsapi.core.service;

import br.com.demo.clientsapi.application.adapter.datasource.entity.ClienteEntity;
import br.com.demo.clientsapi.application.adapter.datasource.entity.Desconto;
import br.com.demo.clientsapi.application.adapter.datasource.mapper.ClientMapper;
import br.com.demo.clientsapi.application.adapter.datasource.repository.ClienteRepository;
import br.com.demo.clientsapi.core.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Cliente salvar(Cliente cliente) {
        BigDecimal comissao = calculaComissao(new BigDecimal(100));
        log.info("Comissao calculada: " + comissao);
        ClienteEntity clienteEntity = clientMapper.from(cliente);
        ClienteEntity entity = clienteRepository.save(clienteEntity);
        return clientMapper.to(entity);
    }

    public Cliente editar(Long id, Cliente cliente) {
        cliente.setId(id);
        ClienteEntity clienteEntity = clientMapper.from(cliente);
        ClienteEntity entity = clienteRepository.save(clienteEntity);
        return clientMapper.to(entity);
    }

    public List<Cliente> list() {
        List<ClienteEntity> all = clienteRepository.findAll();
        return clientMapper.toList(all);
    }

    public Cliente findById(Long id) {
        Optional<ClienteEntity> byId = clienteRepository.findById(id);
        return clientMapper.to(byId.get());
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public BigDecimal calculaComissao(BigDecimal valor) {
        return Desconto.getType(Desconto.FIES.name()).calculaDesconto(valor);
    }
}
