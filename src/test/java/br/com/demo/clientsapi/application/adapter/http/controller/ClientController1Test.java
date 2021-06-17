package br.com.demo.clientsapi.application.adapter.http.controller;

import static org.junit.Assert.assertNotNull;

import java.lang.Long;
import java.util.List;

import br.com.demo.clientsapi.core.model.Cliente;
import br.com.demo.clientsapi.exception.ValidacaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

/**
 * Test de la clase ClientController
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientController1Test {
  @InjectMocks
  private ClientController clientController = new ClientController();

  /**
   * Cumple la condicion: !.isEmpty()
   */
  @Test(expected = NullPointerException.class)
  public void testList1() {

    ResponseEntity<List<Cliente>> ret_ = clientController.list();


    assertNotNull(ret_);
  }

  /**
   * No cumple la condicion: !.isEmpty()
   */
  @Test
  public void testList2() {

    ResponseEntity<List<Cliente>> ret_ = clientController.list();


    assertNotNull(ret_);
  }

  /**
   * Cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testFindById1() {

    ResponseEntity<Cliente> ret_ = clientController.findById(Long.valueOf(2L));


    assertNotNull(ret_);
  }

  /**
   * No cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testFindById2() {

    ResponseEntity<Cliente> ret_ = clientController.findById(Long.valueOf(27L));


    assertNotNull(ret_);
  }

  /**
   * Cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testSalvar1() throws ValidacaoException {

    Cliente cliente = new Cliente();

    ResponseEntity<Cliente> ret_ = clientController.salvar(cliente);


    assertNotNull(ret_);
  }

  /**
   * No cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testSalvar2() throws ValidacaoException {

    Cliente cliente = new Cliente();

    ResponseEntity<Cliente> ret_ = clientController.salvar(cliente);


    assertNotNull(ret_);
  }

  /**
   * Cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testEditar1() {

    Cliente cliente = new Cliente();

    ResponseEntity<Cliente> ret_ = clientController.editar(Long.valueOf(6L), cliente);

    assertNotNull(ret_);
  }

  /**
   * No cumple la condicion: != null
   */
  @Test(expected = NullPointerException.class)
  public void testEditar2() {

    Cliente cliente = new Cliente();

    ResponseEntity<Cliente> ret_ = clientController.editar(Long.valueOf(2L), cliente);
    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testDelete1() {

    ResponseEntity ret_ = clientController.delete(Long.valueOf(4L));
    assertNotNull(ret_);
  }
}
