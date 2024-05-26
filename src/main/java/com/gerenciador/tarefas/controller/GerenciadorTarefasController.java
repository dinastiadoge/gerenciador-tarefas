package com.gerenciador.tarefas.controller;


import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.request.AtualizarTarefaRequest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.response.AtualizarTarefaResponse;
import com.gerenciador.tarefas.response.CadastrarTarefaResponse;
import com.gerenciador.tarefas.response.ObterTarefasPaginadaResponse;
import com.gerenciador.tarefas.response.ObterTarefasResponse;
import com.gerenciador.tarefas.service.GerenciadorTarefasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerenciador-tarefas")
public class GerenciadorTarefasController {

    @Autowired
    private GerenciadorTarefasService gerenciadorTarefasService;


    @PostMapping
    public ResponseEntity<CadastrarTarefaResponse> salvarTarefa(@Valid @RequestBody CadastrarTarefaRequest request) {
        Tarefa tarefaSalva = gerenciadorTarefasService.salvarTarefa(request);

        CadastrarTarefaResponse response = CadastrarTarefaResponse.builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ObterTarefasPaginadaResponse> obterTarefas(
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Page<Tarefa> tarefasPaginada = null;

        if (titulo == null) {
            tarefasPaginada = this.gerenciadorTarefasService.obterTodasTarefas(PageRequest.of(page, size));
        } else {
            tarefasPaginada = this.gerenciadorTarefasService.obterTarefasPorTitulo(titulo, PageRequest.of(page, size));
        }

        List<ObterTarefasResponse> tarefas = tarefasPaginada
                .getContent()
                .stream()
                .map(Tarefa -> {
                    return ObterTarefasResponse
                            .builder()
                            .id(Tarefa.getId())
                            .titulo(Tarefa.getTitulo())
                            .descricao(Tarefa.getDescricao())
                            .responsavel(Tarefa.getResponsavel() != null ? Tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDA")
                            .criador(Tarefa.getCriador().getUsername())
                            .status(Tarefa.getStatus())
                            .quantidadeHorasEstimadas(Tarefa.getQuantidadeHorasEstimadas())
                            .quantidadeHorasRealizada(Tarefa.getQuantidadeHorasRealizada())
                            .dataCadastro(Tarefa.getDataCadastro())
                            .dataAtualizacao(Tarefa.getDataAtualizacao())
                            .build();
                })
                .toList();

        ObterTarefasPaginadaResponse response = ObterTarefasPaginadaResponse.builder()
                .paginaAtual(tarefasPaginada.getNumber())
                .totalItens(tarefasPaginada.getTotalElements())
                .totalPaginas(tarefasPaginada.getTotalPages())
                .tarefas(tarefas)
                .build();


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<AtualizarTarefaResponse> AtualizarTarefa( @PathVariable Long id, @Valid @RequestBody AtualizarTarefaRequest request) {
        Tarefa tarefaAtualizada = gerenciadorTarefasService.atualizarTarefa(id,request);

        AtualizarTarefaResponse response = AtualizarTarefaResponse.builder()
                .id(tarefaAtualizada.getId())
                .titulo(tarefaAtualizada.getTitulo())
                .descricao(tarefaAtualizada.getDescricao())
                .criador(tarefaAtualizada.getCriador().getUsername())
                .quantidadeHorasEstimadas(tarefaAtualizada.getQuantidadeHorasEstimadas())
                .status(tarefaAtualizada.getStatus().toString())
                .responsavel(tarefaAtualizada.getResponsavel().getUsername())
                .quantidadeHorasRealizadas(tarefaAtualizada.getQuantidadeHorasRealizada())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public void excluirTarefa(@PathVariable Long id) {
        gerenciadorTarefasService.deletarTarefa(id);
    }




}