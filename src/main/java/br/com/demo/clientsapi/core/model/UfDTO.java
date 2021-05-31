package br.com.demo.clientsapi.core.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "description" })
@Builder
@Data
public class UfDTO {
    private Integer idUF;
    private String siglaUF;
}
