package com.sulimann.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SaldoInsuficienteException extends PicPayException{

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Saldo insuficiente");
    pb.setDetail("Não foi possível realizar a transferência por saldo insuficiente");

    return pb;
  }

}
