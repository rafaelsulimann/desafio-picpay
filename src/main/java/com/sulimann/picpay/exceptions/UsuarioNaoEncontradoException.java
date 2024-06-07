package com.sulimann.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UsuarioNaoEncontradoException extends PicPayException{

  private final Long id;

  public UsuarioNaoEncontradoException(Long id) {
    this.id = id;
  }

  @Override
  public ProblemDetail toProblemDetail() {
    var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

    pb.setTitle("Usuário não encontrado");
    pb.setDetail("Não foi possível encontrar o usuário com o id: " + id);

    return pb;
  }

}
