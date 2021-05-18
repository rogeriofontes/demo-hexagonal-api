package br.com.demo.clientsapi.application.adapter.datasource.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "description" })
@Builder
@Data
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String conta;
}
