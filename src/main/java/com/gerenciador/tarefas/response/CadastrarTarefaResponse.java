package com.gerenciador.tarefas.response;


import com.gerenciador.tarefas.entity.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CadastrarTarefaResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private String criador;

}
