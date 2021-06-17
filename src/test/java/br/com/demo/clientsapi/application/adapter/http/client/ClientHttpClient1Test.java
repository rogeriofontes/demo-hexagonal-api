package br.com.demo.clientsapi.application.adapter.http.client;

import static org.junit.Assert.assertNotNull;

import java.lang.String;

import br.com.demo.clientsapi.core.model.Cliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test de la clase ClientHttpClient
 */
@RunWith(MockitoJUnitRunner.class)
public class ClientHttpClient1Test {
  @InjectMocks
  private ClientHttpClient clientHttpClient = new ClientHttpClient();

  @Test(expected = NullPointerException.class)
  public void testGetClienteList1() {

    String ret_ = clientHttpClient.getClienteList();


    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testCreateProducts1() {

    Cliente cliente = new Cliente();

    String ret_ = clientHttpClient.createProducts(cliente);


    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testUpdateProduct1() {

    Cliente cliente = new Cliente();

    String ret_ = clientHttpClient.updateProduct("de48sz", cliente);


    assertNotNull(ret_);
  }

  @Test(expected = NullPointerException.class)
  public void testDeleteProduct1() {

    String ret_ = clientHttpClient.deleteProduct("seo9cu1c7q1qyk4");


    assertNotNull(ret_);
  }
}
