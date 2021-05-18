package br.com.demo.clientsapi.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "description" })
@Builder
@Data
public class Cliente implements Serializable {
    private Long id;
    private String nome;
    private String conta;
}
