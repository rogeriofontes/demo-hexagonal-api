package br.com.demo.clientsapi.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Test de la clase RestTemplateConfig
 */
@RunWith(MockitoJUnitRunner.class)
public class RestTemplateConfig1Test {
  @InjectMocks
  private RestTemplateConfig restTemplateConfig = new RestTemplateConfig();

  @Test
  public void testGetRestTemplate1() {

    RestTemplate ret_ = restTemplateConfig.getRestTemplate();


    assertNotNull(ret_);
  }
}
