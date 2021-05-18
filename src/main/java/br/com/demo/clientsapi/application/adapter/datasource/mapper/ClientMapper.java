package br.com.demo.clientsapi.application.adapter.datasource.mapper;


import br.com.demo.clientsapi.application.adapter.datasource.entity.ClienteEntity;
import br.com.demo.clientsapi.core.model.Cliente;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "conta", source = "conta")
    ClienteEntity from(final Cliente model);

    @InheritInverseConfiguration
    Cliente to(final ClienteEntity clienteEntity);

    List<ClienteEntity> fromList(final List<Cliente> clientesModel);
    List<Cliente> toList(final List<ClienteEntity> clientesEntity);
}
