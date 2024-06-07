package com.sulimann.picpay.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.sulimann.picpay.utils.constants.TableName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = TableName.TRANSFERENCIA)
@Getter
public class Transferencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal valor;

  @ManyToOne
  @JoinColumn(name = "pagador_id")
  private Usuario pagador;

  @ManyToOne
  @JoinColumn(name = "recebedor_id")
  private Usuario recebedor;

  @CreatedDate
  private LocalDateTime dataTransferencia;

  public Transferencia(BigDecimal valor, Usuario pagador, Usuario recebedor) {
    this.valor = valor;
    this.pagador = pagador;
    this.recebedor = recebedor;
  }

}
