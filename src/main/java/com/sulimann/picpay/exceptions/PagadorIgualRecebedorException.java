package com.sulimann.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PagadorIgualRecebedorException extends PicPayException{

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Pagador igual ao recebedor");
    pb.setDetail("Um usuario não pode transferir para ele mesmo");

    return pb;
  }

}
