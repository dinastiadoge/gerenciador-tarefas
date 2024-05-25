package com.gerenciador.tarefas.response;


import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.status.TarefasStatusEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ObterTarefasResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private TarefasStatusEnum status;
    private String responsavel;
    private String criador;
    private int quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizada;
    private LocalTime dataCadastro;
    private LocalTime dataAtualizacao;
}
