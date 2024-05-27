package com.gerenciador.tarefas.exceptions;

public class TarefaExistenteException extends RuntimeException{

    public TarefaExistenteException() {
        super();
    }

    public TarefaExistenteException(String message) {
        super(message);
    }
}
