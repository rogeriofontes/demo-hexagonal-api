package br.com.demo.clientsapi.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "description" })
@Builder
@Data
public class EnderecoDTO implements Serializable {

    private Integer idEndereco;
    private String logadouro;
    private String bairro;
    private String cidade;
    private Long numero;
    private Integer cep;
    private UfDTO ufDTO;
}
