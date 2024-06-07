package com.sulimann.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferenciaLojistaException extends PicPayException{

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Transferência de lojista");
    pb.setDetail("Um Lojista não pode realizar transferência");

    return pb;
  }

}
