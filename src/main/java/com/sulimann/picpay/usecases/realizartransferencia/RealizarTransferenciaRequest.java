package com.sulimann.picpay.usecases.realizartransferencia;

import java.math.BigDecimal;

import com.sulimann.picpay.utils.constants.ErrorMessage;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RealizarTransferenciaRequest(

  @NotNull(message = ErrorMessage.CAMPO_OBRIGATORIO)
  @DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que 0")
  BigDecimal valor,

  @NotNull(message = ErrorMessage.CAMPO_OBRIGATORIO)
  Long pagadorId,

  @NotNull(message = ErrorMessage.CAMPO_OBRIGATORIO)
  Long recebedorId

) {}
