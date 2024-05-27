package com.gerenciador.tarefas.exceptions;

public class NaoPermitirAlterarStatusException extends RuntimeException{

    public NaoPermitirAlterarStatusException() {
        super();
    }

    public NaoPermitirAlterarStatusException(String message) {
        super(message);
    }
}
