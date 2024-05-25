package com.gerenciador.tarefas.request;


import com.gerenciador.tarefas.status.TarefasStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTarefaRequest {

    private String titulo;
    private String descricao;
    private TarefasStatusEnum status;
    private Long responsavelId;
    private int quantidadeHorasEstimadas;
    private int quantidadeHorasRealizadas;

}
