package com.sulimann.picpay.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;

import com.sulimann.picpay.usecases.realizartransferencia.AutorizacaoTransferenciaResponse;

public class AutorizacaoTransferenciaException extends PicPayException{

  private final HttpClientErrorException erro;

  public AutorizacaoTransferenciaException(HttpClientErrorException erro) {
    this.erro = erro;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(this.erro.getStatusCode());

    pb.setTitle("Autorização de transferência com erro");
    pb.setDetail("Não foi possível autorizar a transferência, tente novamente mais tarde");
    pb.setProperty("responseError", this.erro.getResponseBodyAs(AutorizacaoTransferenciaResponse.class));

    return pb;
  }

}
