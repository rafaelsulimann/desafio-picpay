package com.sulimann.picpay.usecases.realizartransferencia;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import com.sulimann.picpay.exceptions.NotificacaoTransferenciaException;
import com.sulimann.picpay.models.Transferencia;

@Component
public class NotificacaoTransferenciaService {

  private final RestClient restClient;

  public NotificacaoTransferenciaService(RestClient restClient) {
    this.restClient = restClient;
  }

  public void enviarNotificacaoTransferencia(Transferencia transferencia) {
    try {
      restClient.post()
        .uri("https://util.devi.tools/api/v1/notify")
        .body(transferencia)
        .retrieve();
    } catch (HttpServerErrorException e) {
      throw new NotificacaoTransferenciaException(e);
    }
  }

}
