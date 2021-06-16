package br.com.demo.clientsapi.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "description" })
@Builder
@Data
public class UfDTO implements Serializable {
    private Integer idUF;
    private String siglaUF;
}
