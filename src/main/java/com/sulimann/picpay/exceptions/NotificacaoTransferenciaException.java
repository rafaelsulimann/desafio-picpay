package com.sulimann.picpay.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.sulimann.picpay.usecases.realizartransferencia.AutorizacaoTransferenciaResponse;
import com.sulimann.picpay.usecases.realizartransferencia.NotificacaoTransferenciaErrorResponse;

public class NotificacaoTransferenciaException extends PicPayException{

  private final HttpServerErrorException erro;

  public NotificacaoTransferenciaException(HttpServerErrorException erro) {
    this.erro = erro;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(this.erro.getStatusCode());

    pb.setTitle("Notificação com erro");
    pb.setDetail("Não foi possível enviar a notificação");
    pb.setProperty("responseError", this.erro.getResponseBodyAs(NotificacaoTransferenciaErrorResponse.class));

    return pb;
  }

}
