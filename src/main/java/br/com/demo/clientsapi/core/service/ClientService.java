package br.com.demo.clientsapi.core.service;

import br.com.demo.clientsapi.application.adapter.datasource.entity.ClienteEntity;
import br.com.demo.clientsapi.application.adapter.datasource.entity.Desconto;
import br.com.demo.clientsapi.application.adapter.datasource.mapper.ClientMapper;
import br.com.demo.clientsapi.application.adapter.datasource.repository.ClienteRepository;
import br.com.demo.clientsapi.core.model.Cliente;
import br.com.demo.clientsapi.core.model.EnderecoDTO;
import br.com.demo.clientsapi.exception.ValidacaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientService {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Cliente salvar(Cliente cliente) throws ValidacaoException {
        BigDecimal comissao = calculaComissao(new BigDecimal(100));
        log.info("Comissao calculada: " + comissao);

        validaNome(cliente.getNome());
        validaCpf(cliente.getCpf());
        validaDtNasc(cliente.getDtNascimento());
        validaEndereco(cliente.getEnderecoDTO());

        ClienteEntity clienteEntity = clientMapper.from(cliente);
        ClienteEntity entity = clienteRepository.save(clienteEntity);
        return clientMapper.to(entity);
    }

    public boolean validaNome(String nome) throws ValidacaoException {
        if (nome == null || nome.equals("")) {
            throw new ValidacaoException("Campo nome é obrigatório!");
        } else if (nome.length() > 30) {
            throw new ValidacaoException("Campo nome comporta no máximo 30 chars!");
        }
        return Boolean.TRUE;
    }

    public boolean validaCpf(String cpf) throws ValidacaoException {
        if (cpf == null || cpf.equals("")) {
            throw new ValidacaoException("Campo CPF é obrigatório!");
        } else if (cpf.length() != 11) {
            throw new ValidacaoException("Campo CPF deve ter 11 dígitos!");
        } else {
            char[] digitos = cpf.toCharArray();
            for (char digito : digitos) {
                if (!Character.isDigit(digito)) {
                    throw new ValidacaoException("Campo CPF é somente numérico!");
                }
            }
        }
        return Boolean.TRUE;
    }

    public boolean validaDtNasc(Date dtNasc) throws ValidacaoException {
        if (dtNasc == null) {
            throw new ValidacaoException("Campo Dt. Nasc. é obrigatório!");
        } else {
            dateFormat.format(dtNasc);
        }
        return Boolean.TRUE;
    }

    public boolean validaEndereco(EnderecoDTO enderecoDTO) throws ValidacaoException {
        if (enderecoDTO.getLogadouro() == null || enderecoDTO.getLogadouro().equals("")) {
            throw new ValidacaoException("Campo Logradouro é obrigatório!");
        } else if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals("")) {
            throw new ValidacaoException("Bairro Obrigatorio");
        } else if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero() == 0) {
            throw new ValidacaoException("Numero Obrigatorio");
        }else if (enderecoDTO.getCep() == null || enderecoDTO.getCep().equals(0)) {
            throw new ValidacaoException("CEP Obrigatorio");
        }


        return Boolean.TRUE;
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
        return byId.isPresent() ? clientMapper.to(byId.get()) : new Cliente();
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    public BigDecimal calculaComissao(BigDecimal valor) {
        return Desconto.getType(Desconto.FIES.name()).calculaDesconto(valor);
    }
}
