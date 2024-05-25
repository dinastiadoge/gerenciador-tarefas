package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.IGerenciadorTarefasRepository;
import com.gerenciador.tarefas.request.AtualizarTarefaRequest;
import com.gerenciador.tarefas.request.CadastrarTarefaRequest;
import com.gerenciador.tarefas.status.TarefasStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GerenciadorTarefasService {


    @Autowired
    private IGerenciadorTarefasRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Tarefa salvarTarefa(CadastrarTarefaRequest request) {

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas())
                .status(TarefasStatusEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return this.gerenciadorTarefasRepository.save(tarefa);

    }


    public Page<Tarefa> obterTarefasPorTitulo(String titulo, Pageable pageable) {
        return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, pageable);
    }

    public Page<Tarefa> obterTodasTarefas(Pageable pageable) {
        return this.gerenciadorTarefasRepository.findAll(pageable);
    }

    public Tarefa atualizarTarefa(Long id, AtualizarTarefaRequest request) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

        tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasEstimadas());
        tarefa.setStatus(request.getStatus());
        tarefa.setTitulo(request.getTitulo());
        tarefa.setDescricao(request.getDescricao());
        tarefa.setResponsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get());
        tarefa.setQuantidadeHorasRealizada(request.getQuantidadeHorasRealizadas());

       this.gerenciadorTarefasRepository.save(tarefa);

        return tarefa;
    }

    public void deletarTarefa(Long tarefaId) {
        this.gerenciadorTarefasRepository.deleteById(tarefaId);
    }

}
