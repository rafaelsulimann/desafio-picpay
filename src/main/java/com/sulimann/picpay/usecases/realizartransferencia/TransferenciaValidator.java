package com.sulimann.picpay.usecases.realizartransferencia;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.sulimann.picpay.exceptions.SaldoInsuficienteException;
import com.sulimann.picpay.exceptions.TransferenciaLojistaException;
import com.sulimann.picpay.models.Usuario;

@Component
public class TransferenciaValidator {

  public void validarTransferencia(BigDecimal valor, Usuario pagador) {
    if(pagador.isLojista()){
      throw new TransferenciaLojistaException();
    }
    if (pagador.possuiSaldoSuficiente(valor)) {
      throw new SaldoInsuficienteException();
    }
  }

}
