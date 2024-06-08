package com.sulimann.picpay.models;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.sulimann.picpay.models.enums.TipoUsuario;
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
@Table(name = TableName.USUARIO)
@Getter
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nomeCompleto;
  private String cpfCnpj;
  private String email;
  private String senha;

  private BigDecimal saldo;

  @Enumerated(EnumType.STRING)
  private TipoUsuario tipoUsuario;

  /**
  * @deprecated
  * Não utilizar! Criado por obrigação do hibernate
  */
  @Deprecated
  public Usuario() {
  }

  public Usuario(String nomeCompleto, String cpfCnpj, String email, String senha, TipoUsuario tipoUsuario) {
    this.nomeCompleto = nomeCompleto;
    this.cpfCnpj = cpfCnpj;
    this.email = email;
    this.senha = senha;
    this.tipoUsuario = tipoUsuario;
  }

  public boolean isLojista() {
    return this.tipoUsuario.equals(TipoUsuario.LOJISTA);
  }

  public boolean possuiSaldoSuficiente(BigDecimal valor) {
    Assert.notNull(valor, "Valor não pode ser nulo");
    return this.saldo.compareTo(valor) >= 0;
  }

  public void debitar(BigDecimal valor) {
    Assert.notNull(valor, "Valor não pode ser nulo");
    Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor deve ser maior que zero");
    Assert.isTrue(this.saldo.compareTo(valor) >= 0, "Saldo insuficiente");
    this.saldo = this.saldo.subtract(valor);
  }

  public void creditar(BigDecimal valor) {
    Assert.notNull(valor, "Valor não pode ser nulo");
    Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor deve ser maior que zero");
    this.saldo = this.saldo.add(valor);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cpfCnpj == null) ? 0 : cpfCnpj.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Usuario other = (Usuario) obj;
    if (cpfCnpj == null) {
      if (other.cpfCnpj != null)
        return false;
    } else if (!cpfCnpj.equals(other.cpfCnpj))
      return false;
    return true;
  }



}
