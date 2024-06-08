package com.sulimann.picpay.usecases.realizartransferencia;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.sulimann.picpay.exceptions.AutorizacaoTransferenciaException;

@Service
public class AutorizacaoTransferenciaService {

  private final RestClient restClient;

  public AutorizacaoTransferenciaService(RestClient restClient) {
    this.restClient = restClient;
  }

  public void autorizar(){
    try {
      restClient.get()
        .uri("https://util.devi.tools/api/v2/authorize")
        .retrieve()
        .body(Void.class);
    } catch (HttpClientErrorException e) {
      throw new AutorizacaoTransferenciaException(e);
    }
  }

}
