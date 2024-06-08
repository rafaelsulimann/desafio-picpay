package com.sulimann.picpay.usecases.realizartransferencia;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sulimann.picpay.exceptions.UsuarioNaoEncontradoException;
import com.sulimann.picpay.models.Transferencia;
import com.sulimann.picpay.repositories.UsuarioRepository;
import com.sulimann.picpay.utils.constants.Path;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = Path.TRANSFERENCIA)
public class RealizarTransferenciaController {

  private final UsuarioRepository usuarioRepository;
  private final TransferenciaValidator transferenciaValidator;
  private final AutorizacaoTransferenciaService autorizacaoTransferenciaService;
  private final NotificacaoTransferenciaService notificacaoTransferenciaService;
  private final EntityManager manager;

  public RealizarTransferenciaController(UsuarioRepository usuarioRepository, TransferenciaValidator transferenciaValidator, AutorizacaoTransferenciaService autorizacaoTransferenciaService, NotificacaoTransferenciaService notificacaoTransferenciaService, EntityManager manager) {
    this.usuarioRepository = usuarioRepository;
    this.transferenciaValidator = transferenciaValidator;
    this.autorizacaoTransferenciaService = autorizacaoTransferenciaService;
    this.notificacaoTransferenciaService = notificacaoTransferenciaService;
    this.manager = manager;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> realizarTransferencia(@RequestBody @Valid RealizarTransferenciaRequest request) {
    var pagador = this.usuarioRepository
      .findById(request.pagadorId())
      .orElseThrow(() -> new UsuarioNaoEncontradoException(request.pagadorId()));

    var recebedor = this.usuarioRepository
      .findById(request.recebedorId())
      .orElseThrow(() -> new UsuarioNaoEncontradoException(request.recebedorId()));

    this.transferenciaValidator.validarTransferencia(request.valor(), pagador, recebedor);
    this.autorizacaoTransferenciaService.autorizar();

    pagador.debitar(request.valor());
    recebedor.creditar(request.valor());

    Transferencia transferencia = new Transferencia(request.valor(), pagador, recebedor);

    this.manager.merge(pagador);
    this.manager.merge(recebedor);
    this.manager.persist(transferencia);

    CompletableFuture.runAsync(() -> {
      this.notificacaoTransferenciaService.enviarNotificacaoTransferencia(transferencia);
    });

    return ResponseEntity.noContent().build();
  }

}
