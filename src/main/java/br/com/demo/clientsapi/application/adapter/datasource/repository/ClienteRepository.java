package br.com.demo.clientsapi.application.adapter.datasource.repository;

import br.com.demo.clientsapi.application.adapter.datasource.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
