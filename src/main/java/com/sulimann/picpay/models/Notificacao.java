package com.sulimann.picpay.models;

import com.sulimann.picpay.models.enums.NotificacaoStatus;
import com.sulimann.picpay.utils.constants.TableName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = TableName.NOTIFICACAO)
@Getter
public class Notificacao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private NotificacaoStatus status;
  private String mensagem;
  private String motivoErro;

  public Notificacao(NotificacaoStatus status, String mensagem, String motivoErro) {
    this.status = status;
    this.mensagem = mensagem;
    this.motivoErro = motivoErro;
  }

  public Notificacao(NotificacaoStatus status, String mensagem) {
    this.status = status;
    this.mensagem = mensagem;
  }

}
