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
import java.text.ParseException;
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
        boolean ehValido = true;
        if (nome == null || nome.equals("")) {
            ehValido = false;
            throw new ValidacaoException("Campo nome é obrigatório!");
        } else if (nome.length() > 30) {
            ehValido = false;
            throw new ValidacaoException("Campo nome comporta no máximo 30 chars!");
        }
        return ehValido;
    }

    public boolean validaCpf(String cpf) throws ValidacaoException {
        boolean ehValido = true;
        if (cpf == null || cpf.equals("")) {
            ehValido = false;
            throw new ValidacaoException("Campo CPF é obrigatório!");
        } else if (cpf.length() != 11) {
            ehValido = false;
            throw new ValidacaoException("Campo CPF deve ter 11 dígitos!");
        } else {
            char[] digitos = cpf.toCharArray();
            for (char digito : digitos) {
                if (!Character.isDigit(digito)) {
                    ehValido = false;
                    throw new ValidacaoException("Campo CPF é somente numérico!");
                }
            }
        }
        return ehValido;
    }

    public boolean validaDtNasc(Date dtNasc) throws ValidacaoException {
        boolean ehValido = true;
        if (dtNasc == null || dtNasc.equals("")) {
            ehValido = false;
            throw new ValidacaoException("Campo Dt. Nasc. é obrigatório!");
        } else {
            ehValido = false;
            dateFormat.format(dtNasc);
        }
        return ehValido;
    }

    public boolean validaEndereco(EnderecoDTO enderecoDTO) throws ValidacaoException {
        boolean ehValido = true;
        if (enderecoDTO.getLogadouro() == null || enderecoDTO.getLogadouro().equals("")) {
            ehValido = false;
            throw new ValidacaoException("Campo Logradouro é obrigatório!");
        } else if (enderecoDTO.getBairro() == null || enderecoDTO.getBairro().equals("")) {
            ehValido = false;
            throw new ValidacaoException("Bairro Obrigatorio");
        } else if (enderecoDTO.getNumero() == null || enderecoDTO.getNumero().equals(0)) {
            ehValido = false;
            throw new ValidacaoException("Numero Obrigatorio");
        }else if (enderecoDTO.getCep() == null || enderecoDTO.getCep().equals(0)) {
            ehValido = false;
            throw new ValidacaoException("CEP Obrigatorio");
        }


        return ehValido;
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
