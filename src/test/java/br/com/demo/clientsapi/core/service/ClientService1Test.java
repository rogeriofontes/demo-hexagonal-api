package br.com.demo.clientsapi.core.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.Long;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.demo.clientsapi.core.model.Cliente;
import br.com.demo.clientsapi.core.model.EnderecoDTO;
import br.com.demo.clientsapi.exception.ValidacaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test de la clase ClientService
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientService1Test {
  @InjectMocks
  private ClientService clientService = new ClientService();

  @Test(expected = ValidacaoException.class)
  public void testSalvar1() throws ValidacaoException {

    Cliente cliente = new Cliente();

    Cliente ret_ = clientService.salvar(cliente);

    assertNotNull(ret_);
  }

  /**
   * Cumple la condicion: == null  ||  .equals(&quot;&quot;)
   */
  @Test
  public void testValidaNome1() throws ValidacaoException {

    boolean ret_ = clientService.validaNome("fhm7imau151z");
    assertTrue(ret_);
  }

  /**
   * No cumple la condicion: == null  ||  .equals(&quot;&quot;)
   */
  @Test
  public void testValidaNome2() throws ValidacaoException {

    boolean ret_ = clientService.validaNome("81iqxp");
    assertTrue(ret_);
  }

  /**
   * Cumple la condicion: org.eclipse.gmt.modisco.java.emf.impl.InfixExpressionImpl@3168c390(operator:&gt;)
   */
  @Test
  public void testValidaNome3() throws ValidacaoException {

    boolean ret_ = clientService.validaNome("j7au17vrhih");
    assertTrue(ret_);
  }

  /**
   * Cumple la condicion: == null  ||  .equals(&quot;&quot;)
   */
  @Test(expected = ValidacaoException.class)
  public void testValidaCpf1() throws ValidacaoException {

    boolean ret_ = clientService.validaCpf("k7mur57jef");
    assertTrue(ret_);
  }


  /**
   * Cumple la condicion: == null
   */
  @Test
  public void testValidaDtNasc1() throws ValidacaoException {

    Date date = new Date();

    boolean ret_ = clientService.validaDtNasc(date);


    assertNotNull(ret_);
  }

  /**
   * No cumple la condicion: == null
   */
  @Test
  public void testValidaDtNasc2() throws ValidacaoException {

    Date date = new Date();

    boolean ret_ = clientService.validaDtNasc(date);


    assertTrue(ret_);
  }

  /**
   * Cumple la condicion: .getLogadouro() == null  ||  .getLogadouro().equals(&quot;&quot;)
   */
  @Test(expected = ValidacaoException.class)
  public void testValidaEndereco1() throws ValidacaoException {

    EnderecoDTO enderecoDTO = new EnderecoDTO();

    boolean ret_ = clientService.validaEndereco(enderecoDTO);

    assertNotNull(ret_);
  }

  @Test
  public void testEditar1() {

    Cliente cliente = new Cliente();

    Cliente ret_ = clientService.editar(Long.valueOf(25L), cliente);

    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testList1() {

    List<Cliente> ret_ = clientService.list();

    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testFindById1() {

    Cliente ret_ = clientService.findById(Long.valueOf(23L));
    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testDelete1() {

    clientService.delete(Long.valueOf(24L));


  }

  @Test
  public void testCalculaComissao1() {

    BigDecimal ret_ = clientService.calculaComissao(new BigDecimal("2089749212"));


    assertNotNull(ret_);
  }
}
