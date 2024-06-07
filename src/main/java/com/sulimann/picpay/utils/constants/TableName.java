package com.sulimann.picpay.utils.constants;

public final class TableName {

    public static final String TRANSFERENCIA = "tb_transferencias";
    public static final String USUARIO = "tb_usuarios";
    public static final String NOTIFICACAO = "tb_notificacoes";

    private TableName() {
        throw new AssertionError("Não é permitido instanciar esta classe.");
    }

}
